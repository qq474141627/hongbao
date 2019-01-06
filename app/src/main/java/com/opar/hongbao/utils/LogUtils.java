package com.opar.hongbao.utils;


import android.util.Log;

/**
 * 日志打印
 * Created by tangge on 18/3/18.
 */
public class LogUtils {

    private static final boolean DEBUG_ENABLE = false;
    public static final String DEBUG_TAG = "logger";// LogCat的标记

    public static void d(String tag, String msg) {
        if (DEBUG_ENABLE) {
            Log.d(tag,msg);
        }
    }

    public static void d(String msg) {
        if (DEBUG_ENABLE) {
            Log.d(DEBUG_TAG,msg);
        }
    }

    public static void e(String tag, String msg) {
        if (DEBUG_ENABLE) {
            Log.e(tag,msg);
        }
    }

    public static void e(String msg) {
        if (DEBUG_ENABLE) {
            Log.e(DEBUG_TAG,msg);
        }
    }


}

