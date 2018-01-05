package com.dong.leakcanarydemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * 内存泄漏的几种情况：
 * 1、集合类
 *    当集合类是全局性的变量，只有添加元素的方法，此时集合所占用的内存只增不减，却没有相应的删除机制，对象得不到释放。从而导致内存泄漏。
 *    Vector v = new Vector(10);
 *    for (int i = 1; i < 100; i++) {
 *      Object o = new Object();
 *      v.add(o);  
 *    }
 * 2、单例（造成的内存泄漏）
 *    单例的静态特性使得其生命周期跟应用的生命周期一样长。
 *    如果此时传入了Context，当这个Context的Activity被销毁时，由于该Context被单例对象持有得不到释放回收，这就造成了内存泄漏。
 *    解决方法：使用context.getApplicationContext()；使用Application的Context，如 MyApplication.getContext()。
 * 3、非静态内部类（创建了静态实例，造成的内存泄漏）
 *    因为静态实例的生命周期和应用是一样长，非静态的内部类默认是持有Activity（外部类）的引用，导致Activity内存资源不能被回收。
 *    解决方法：将内部类封装成一个单例，并且尽量使用Application 的Context。
 * 4、匿名内部类（造成的内存泄漏）
 *    非静态的内部类默认持有外部类。如果将Context引用该类中，容易因生命周期不一致而产生的内存泄漏。
 * 5、Handler（造成的内存泄漏）
 *    由于 Handler 属于 TLS(Thread Local Storage) 变量, 生命周期和 Activity 是不一致的。
 *    因此这种实现方式一般很难保证跟 View 或者 Activity 的生命周期保持一致，故很容易导致无法正确释放。
 *    如在Activity的onCreate中执行下列方法。
 *    mLeakyHandler.postDelayed(new Runnable() {
 *        @Override
 *        public void run() { }
 *    }, 1000 * 60 * 10);
 *    解决方法：使用静态内部类 + WeakReference + 在Activity的Destroy时应该移除消息队列MessageQueue中的消息。（3种方式）
 *    a、private static class MyHandler extends Handler{}
 *    b、private final WeakReference<SampleActivity> mActivity = new WeakReference<SampleActivity>(activity);
 *    c、handler.removeCallbacks(mRunnable);
 * 6、除了以上的5种情况会造成内存泄漏外。还应尽量避免以下4种情况：
 *      a、尽量避免使用static成员变量
 *      （如果成员变量被声明为 static，那我们都知道其生命周期将与整个app进程生命周期一样。那即使app切到后台，这部分内存也不会被释放。）
 *      b、资源未关闭造成的内存泄漏
 *      （对于使用了BraodcastReceiver，ContentObserver，File，游标 Cursor，Stream，Bitmap等资源的使用，
 *      应该在Activity销毁时及时关闭或者注销，否则这些资源将不会被回收，造成内存泄漏。）
 *      c、避免override finalize()
 *      （GC在回收对象之前调用该方法，finalize()被执行的时间不确定。）
 *      d、一些不良的代码
 *      （构造 Adapter 时，没有使用缓存的 convertView ,每次都在创建新的 convertView。这里推荐使用 ViewHolder。）
 */
public class MainActivity extends AppCompatActivity {

    private TextView mTvStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvStart = (TextView) findViewById(R.id.startOOM);
        mTvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAsyncTask();
                Log.e("TEST", "startAsyncTask");
            }
        });

        // 内存泄漏1
        MemoryHelper.getInstance(this).setRetainedTextView(mTvStart);
    }

    // 内存泄漏2
    private void startAsyncTask() {
        // This async task is an anonymous class and therefore has a hidden reference to the outer
        // class MainActivity. If the activity gets destroyed before the task finishes (e.g. rotation),
        // the activity instance will leak.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                // Do some slow work in background
                SystemClock.sleep(60000);
                return null;
            }
        }.execute();
    }
}
