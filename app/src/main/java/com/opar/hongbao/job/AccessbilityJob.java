package com.opar.hongbao.job;

import android.app.Notification;
import android.view.accessibility.AccessibilityEvent;

import com.opar.hongbao.service.LuckyMoneyService;

//======================================================================
//        All rights reserved
//
//        created by tangge at  04/19/2018
//        https://juejin.im/user/572a11851ea4930064b43203
//
//======================================================================

public interface AccessbilityJob {
    String getTargetPackageName();
    void onCreateJob(LuckyMoneyService service);
    void onReceiveJob(AccessibilityEvent event);
    void onStopJob();
    void onNotificationPosted(Notification notification);
    boolean isEnable();
}
