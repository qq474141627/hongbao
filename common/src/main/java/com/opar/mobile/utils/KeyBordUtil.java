package com.opar.mobile.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

public class KeyBordUtil {

    /**
     * 强制隐藏软键盘
     *
     * @param context
     * @param editText
     */
    public static void hindSoftInput(Activity context, EditText editText) {
        try {
            if (context != null && !context.isFinishing()) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0); //强制隐藏键盘
            }
        } catch (Exception e) {

        }
    }

    /**
     * 强制隐藏软键盘
     *
     * @param context
     */
    public static void hindSoftInput(Activity context) {
        try {
            if (context != null && !context.isFinishing()) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception e) {

        }
    }

    /**
     * dialog 关闭 输入键盘
     *
     * @param view
     */
    public static void hideInputMethod(EditText view) {
        try {
            InputMethodManager inputManager = (InputMethodManager) view
                    .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
        } catch (Exception e) {

        }
    }

    /**
     * dialog 进入显示 输入键盘
     *
     * @param view
     */
    public static void showInputMethod(final EditText view, Object... params) {
        if (params == null || params.length == 0) {
            params = new Object[1];
            params[0] = 200L;
        }
        Timer timer = new Timer();
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                //设置可获得焦点
                view.setFocusable(true);
                view.setFocusableInTouchMode(true);
                //请求获得焦点
                view.requestFocus();
                //调用系统输入法
                InputMethodManager inputManager = (InputMethodManager) view
                        .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.showSoftInput(view, 0);
            }
        };
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        }, Long.valueOf(params[0].toString()));
    }

}
