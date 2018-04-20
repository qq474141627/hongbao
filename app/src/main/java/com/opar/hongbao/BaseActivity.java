package com.opar.hongbao;

import android.support.v7.app.AppCompatActivity;

import com.umeng.analytics.MobclickAgent;
//======================================================================
//        All rights reserved
//
//        created by tangge at  04/19/2018
//        https://juejin.im/user/572a11851ea4930064b43203
//
//======================================================================

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onResume(){
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause(){
        super.onPause();
        MobclickAgent.onPause(this);
    }

}
