package com.opar.hongbao.utils;

import android.content.Context;
import android.os.Message;

/**
 * UncaughtException处理类,当程序发生Uncaught异常的时候,纪录并输出到手机
 * Created by tagnge on 2018/3/8.
 */
public class MyCrashHandler implements Thread.UncaughtExceptionHandler {
    public final String TAG = this.getClass().getName();
    private static final int MESSAGECODE_SHOW_TOAST = 0;
    /**
     * CrashHandler实例
     **/
    private static MyCrashHandler instance;
    private MyHandler mUIHandler;

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static MyCrashHandler getInstance() {
        if(instance == null){
            instance = new MyCrashHandler();
        }
        return instance;
    }

    /**
     * 初始化,注册Context对象,
     * 获取系统默认的UncaughtException处理器,
     * 设置该CrashHandler为程序的默认处理器
     *
     * @param ctx
     */
    public void init(final Context ctx) {
        Thread.setDefaultUncaughtExceptionHandler(this);
        mUIHandler = new MyHandler<MyCrashHandler>(this){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case MESSAGECODE_SHOW_TOAST:
                        ToastUtil.showToast(ctx, "很抱歉,程序出现异常,即将退出.");
                        break;
                }
            }
        };
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (ex == null) {
            //抛给系统处理
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(thread,ex);
            return;
        }try {
            mUIHandler.sendEmptyMessage(MESSAGECODE_SHOW_TOAST);
        } catch (Exception e) {
            LogUtils.e(TAG, e.getMessage());
        }
        mUIHandler.sendEmptyMessage(MESSAGECODE_SHOW_TOAST);
        System.exit(0);
    }


}
