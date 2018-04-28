package com.opar.mobile.utils;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by wangbo on 18/3/23.
 */

public class MyHandler<T> extends Handler{
    private final WeakReference<T> reference;

    public MyHandler(T t) {
        reference = new WeakReference<T>(t);
    }

    @Override
    public void handleMessage(Message msg) {
        T t = reference.get();
        if (t == null) {
            return;
        }
    }
}
