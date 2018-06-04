# 性能测试

### 说明


名称 | 作用
------- | -------
LeakcanaryDemo| LeakCanary工具的使用，检测并定位存在的内存泄漏(IDE：Android Studio)


# LeakCanary使用说明
![](https://github.com/square/leakcanary/raw/master/assets/screenshot.png)

## 监控Activity对象
首先，在build.gradle中添加依赖：

	 dependencies {
	   debugCompile 'com.squareup.leakcanary:leakcanary-android:1.4-beta2'
	   releaseCompile 'com.squareup.leakcanary:leakcanary-android-no-op:1.4-beta2'
	   testCompile 'com.squareup.leakcanary:leakcanary-android-no-op:1.4-beta2'
	 }

然后，在Application类中安装监听钩子:

	public class ExampleApplication extends Application {
	
	  @Override public void onCreate() {
	    super.onCreate();
	    LeakCanary.install(this);
	  }
	}

> 我们衣橱Application类为`JzCircle\app\src\main\java\com\jd\wxsq\app\application\SysApplication.java`

然后编译安装即可。

## 监控其他对象的泄露
由于`ActivityLifecycleCallbacks`无法自动监控其他非Activity对象，但通过手动注册的方式，LeakCanary也可以完成对其他对象的监控。

首先，简单修改Application，使得可以在任意代码中获取`RefWatcher`实例

	public class ExampleApplication extends Application {
	
	    public static RefWatcher getRefWatcher(Context context) {
	        ExampleApplication application = (ExampleApplication) context.getApplicationContext();
	        return application.refWatcher;
	    }
	
	    private RefWatcher refWatcher;
	
	    @Override public void onCreate() {
	        super.onCreate();
	        refWatcher = LeakCanary.install(this);
	    }
	}

然后，监控Fragment泄漏：

	public abstract class BaseFragment extends Fragment {
	
	    @Override 
	    public void onDestroy() {
	        super.onDestroy();
	        RefWatcher refWatcher = ExampleApplication.getRefWatcher(getActivity());
	        refWatcher.watch(this);
	    }
	}

或者，监控Bitmap Image泄露：

	Bitmap bm = new ....
	//这里使用bm，balabala
	......
	//用完之后，打算将其释放
	RefWatcher refWatcher = ExampleApplication.getRefWatcher(getActivity());
	refWatcher.watch(bm);
	bm = null; //这里期望内存会被回收

通过这种方式，可以扩展到任意对象的监控，但在实际使用过程中，由于要修改大量的业务代码来实现，因此实际应用有限。

# LeakCanary原理分析

## 回顾Java中的四种引用类型
* 强引用（Strong Reference）

	强引用是使用最普遍的引用。如果一个对象具有强引用，那垃圾回收器绝不会回收它。
	
* 软引用（SoftReference）

	如果一个对象只具有软引用，则内存空间足够，垃圾回收器就不会回收它；如果内存空间不足了，就会回收这些对象的内存。所以弱引用天生适合做内存缓存模块。

* 弱引用（WeakReference）

	弱引用与软引用的区别在于：只具有弱引用的对象拥有更短暂的生命周期。可以简单理解为，弱引用不会影响GC正常回收内存。

* 虚引用（PhantomReference）

	Java还有一种“形同虚设”的虚引用，就当它形同虚设即可，本文不过多关注。

## 监控生命周期并检测内存泄露

### Android的生命周期
![](http://pic001.cnblogs.com/img/tea9/201008/2010080516521645.png)

可见，在onDestroy被调用之后，Activity会进入Shutdown状态，但要注意，此时Activity不一定会立即被GC回收，因为GC线程是一个优先级很低的线程。

### LeakCanary检测原理
Application中可在onCreate前注册`ActivityLifecycleCallbacks`来实现对Activity状态的监控，如下：

	public void onCreate() {  
	  super.onCreate();  
	  this.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {  
	   
	    @Override  
	    public void onActivityStopped(Activity activity) {  
	        Logger.v(activity, "onActivityStopped");  
	    }  
	   
	    @Override  
	    public void onActivityStarted(Activity activity) {  
	        Logger.v(activity, "onActivityStarted");  
	    }  
	   
	    @Override  
	    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {  
	        Logger.v(activity, "onActivitySaveInstanceState");  
	    }  
	   
	    @Override  
	    public void onActivityResumed(Activity activity) {  
	        Logger.v(activity, "onActivityResumed");  
	    }  
	   
	    @Override  
	    public void onActivityPaused(Activity activity) {  
	        Logger.v(activity, "onActivityPaused");  
	    }  
	   
	    @Override  
	    public void onActivityDestroyed(Activity activity) {  
	        Logger.v(activity, "onActivityDestroyed");  
	    }  
	   
	    @Override  
	    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {  
	        Logger.v(activity, "onActivityCreated");  
	    }  
	  });  
	};  

所以，LeakCanary的监控原理可以简单概括为：

1. 通过`Application.registerActivityLifecycleCallbacks()`给APP所有Activity添加监控

2. 在Activity的onCreate的时候，将创建对象的弱引用

3. 当检测到Activity的onDestory时，等待一段时间（默认500毫秒），再尝试通过弱引用获取Activity对象

4. 如果能够获取到非空的Activity对象，说明可能存在泄漏，此时将内存dump下来，方便后续进一步分析。

## 泄露路径分析
简单来说，就是计算泄漏对象的最短强引用路径，猜测对象的泄露链。

# 参考资料
* [Leakcanary官网](https://github.com/square/leakcanary)
* [利用 LeakCanary 来检查 Android 内存泄漏](http://www.jianshu.com/p/0049e9b344b0)
