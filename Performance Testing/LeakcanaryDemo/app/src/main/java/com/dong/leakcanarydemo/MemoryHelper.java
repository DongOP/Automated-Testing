package com.dong.leakcanarydemo;

import android.content.Context;
import android.widget.TextView;

/**
 * Created by Dong on 2017/12/13 0013.
 */

public class MemoryHelper {
    private Context mContext;
    private TextView mTextView;
    private static MemoryHelper ourInstance = null;

    private MemoryHelper() {
    }

    private MemoryHelper(Context context) {
        this.mContext = context;
    }

    public static MemoryHelper getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new MemoryHelper(context);
        }
        return ourInstance;
    }

    /**
     * 静态实例 ourInstance 由于有一个对 mTextView 的引用，而 mTextView 由于要 setText() 所以持有了一个对 Context 的引用，
     * 而我们在 MainActivity 里获取 XXXHelper 实例时因为传入了 MainActivity 的 Context，这使得一旦这个 Activity 不在了之后，
     * MemoryHelper 依然会 hold 住它的 Context 不放，
     * 而这个时候因为 Activity 已经不在了，所以内存泄露自然就产生了。
     *
     * @param tv
     */
    public void setRetainedTextView(TextView tv) {
        this.mTextView = tv;
        mTextView.setText(mContext.getString(android.R.string.ok));
    }

}
