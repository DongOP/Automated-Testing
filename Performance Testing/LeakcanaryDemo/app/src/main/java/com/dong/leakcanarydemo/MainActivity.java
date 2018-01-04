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
 * 1、集合类泄漏
 *    当集合类是全局性的变量，只有添加元素的方法，此时集合所占用的内存只增不减，却没有相应的删除机制，对象得不到释放。从而导致内存泄漏。
 *    Vector v = new Vector(10);
 *    for (int i = 1; i < 100; i++) {
 *      Object o = new Object();
 *      v.add(o);  
 *    }
 * 2、单例造成的内存泄漏
 *    
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
