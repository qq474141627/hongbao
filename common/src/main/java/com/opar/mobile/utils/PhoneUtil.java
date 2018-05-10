package com.opar.mobile.utils;

import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.PowerManager;

public class PhoneUtil {
    /**
     * 获取本地软件版本号
     */
    public static int getLocalVersion(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     *     唤醒屏幕并解锁权限
     *     <uses-permission android:name="android.permission.WAKE_LOCK" />
     */
    public static void wakeUpAndUnlock(Context context) {
        try {
            // 获取电源管理器对象
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            // 获取PowerManager.WakeLock对象，后面的参数|表示同时传入两个值，最后的是调试用的Tag
            PowerManager.WakeLock wl = pm.newWakeLock(
                    PowerManager.ACQUIRE_CAUSES_WAKEUP
                            | PowerManager.FULL_WAKE_LOCK, "bright");
            // 点亮屏幕
            if(!wl.isHeld()){
                wl.acquire();
            }
            // 释放
            wl.release();
            // 得到键盘锁管理器对象
            KeyguardManager km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
            KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
            // 解锁
            if (km.inKeyguardRestrictedInputMode()) {
                // 解锁键盘
                kl.disableKeyguard();
            }
        }catch (Exception e){

        }
    }

    public static void lockScreen(Context context) {
        try {
// 获取电源管理器对象
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            // 获取PowerManager.WakeLock对象，后面的参数|表示同时传入两个值，最后的是调试用的Tag
            PowerManager.WakeLock wl = pm.newWakeLock(
                    PowerManager.ACQUIRE_CAUSES_WAKEUP
                            | PowerManager.FULL_WAKE_LOCK, "bright");
            // 点亮屏幕
            if(!wl.isHeld()){
                wl.release();
            }
            // 得到键盘锁管理器对象
            KeyguardManager km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
            KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
            // 解锁
            if (km.inKeyguardRestrictedInputMode()) {
                // 解锁键盘
                kl.reenableKeyguard();
            }
        }catch (Exception e){

        }
    }
}
