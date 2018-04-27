package com.opar.mobile.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.opar.mobile.data.R;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
//======================================================================
//        All rights reserved
//
//        created by tangge at  04/19/2018
//        https://juejin.im/user/572a11851ea4930064b43203
//
//======================================================================

public class BaseActivity extends Activity {

    public Context context;

    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        context = this;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    public void setTitle(String title){
        TextView tvTitle = (TextView) findViewById(R.id.toolbar_title);
        tvTitle.setText(title);
    }

    public void setTitle(int resId){
        setTitle(getResources().getString(resId));
    }

    public void setLeftBtn(boolean show, View.OnClickListener clickListener){
        ImageView ivBack = (ImageView) findViewById(R.id.toolbar_back_icon);
        ivBack.setVisibility(show ? View.VISIBLE : View.GONE);
        if(clickListener == null){
            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }else{
            ivBack.setOnClickListener(clickListener);
        }
    }

    public void setRightBtn(String title, View.OnClickListener clickListener){
        TextView tvRight = (TextView) findViewById(R.id.toolbar_right);
        tvRight.setText(title);
        tvRight.setOnClickListener(clickListener);
    }

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
