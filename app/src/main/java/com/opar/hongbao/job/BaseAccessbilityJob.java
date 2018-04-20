package com.opar.hongbao.job;

import android.content.Context;

import com.opar.hongbao.Config;
import com.opar.hongbao.service.LuckyMoneyService;
//======================================================================
//        All rights reserved
//
//        created by tangge at  04/19/2018
//        https://juejin.im/user/572a11851ea4930064b43203
//
//======================================================================


public abstract class BaseAccessbilityJob implements AccessbilityJob {

    private LuckyMoneyService service;

    @Override
    public void onCreateJob(LuckyMoneyService service) {
        this.service = service;
    }

    public Context getContext() {
        return service.getApplicationContext();
    }

    public Config getConfig() {
        return service.getConfig();
    }

    public LuckyMoneyService getService() {
        return service;
    }
}
