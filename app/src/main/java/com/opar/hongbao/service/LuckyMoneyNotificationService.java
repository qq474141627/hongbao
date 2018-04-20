package com.opar.hongbao.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.os.Build;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;


import com.opar.hongbao.Config;
import com.opar.hongbao.util.EventBusMsg;

import de.greenrobot.event.EventBus;
//======================================================================
//        All rights reserved
//
//        created by tangge at  04/19/2018
//        https://juejin.im/user/572a11851ea4930064b43203
//
//======================================================================

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class LuckyMoneyNotificationService extends NotificationListenerService {

    private static final String TAG = "LuckyMoneyNotificationService";

    private static LuckyMoneyNotificationService service;

    @Override
    public void onCreate() {
        super.onCreate();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            onListenerConnected();
        }
    }

    private Config getConfig() {
        return Config.getConfig(this);
    }

    @Override
    public void onNotificationPosted(final StatusBarNotification sbn) {
        if(!getConfig().isEnableNotificationService()) {
            return;
        }
        LuckyMoneyService.handeNotificationPosted(sbn.getPackageName(), sbn.getNotification());
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            super.onNotificationRemoved(sbn);
        }
    }

    @Override
    public void onListenerConnected() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            super.onListenerConnected();
        }
        service = this;
        EventBusMsg msg = new EventBusMsg();
        msg.setType(EventBusMsg.NOTIFICATION_CONNECTED);
        EventBus.getDefault().post(msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        service = null;
        EventBusMsg msg = new EventBusMsg();
        msg.setType(EventBusMsg.NOTIFICATION_DISCONNECTED);
        EventBus.getDefault().post(msg);
    }

    /** 是否启动通知栏监听*/
    public static boolean isRunning() {
        if(service == null) {
            return false;
        }
        return true;
    }
}
