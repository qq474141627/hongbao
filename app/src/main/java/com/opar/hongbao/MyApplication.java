package com.opar.hongbao;

import android.app.Application;

import com.opar.hongbao.utils.MyCrashHandler;

//======================================================================
//        All rights reserved
//
//        created by tangge at  04/19/2018
//        https://juejin.im/user/572a11851ea4930064b43203
//
//======================================================================

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        MyCrashHandler.getInstance().init(this);
    }

}
