package com.opar.mobile.base;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.opar.mobile.data.R;
import com.opar.mobile.inteface.IBaseDialog;
import com.opar.mobile.utils.KeyBordUtil;
import com.opar.mobile.web.MyWebView;

import butterknife.ButterKnife;

/**
 * Created by tangge on 18/3/13.
 */
public class BaseDialog extends Dialog implements IBaseDialog {

    public final String TAG = this.getClass().getName();

    protected Activity context;
    protected MyWebView mWebView;
    protected boolean bottom = true;
    protected String function;
    protected String callback;
    protected View view;

    public BaseDialog(Activity context) {
        super(context, R.style.dialog_share);
        this.context = context;
    }

    public BaseDialog(Activity context , int style) {
        super(context, style);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(bottom){
            Window window = getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            if(params != null){
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                params.gravity = Gravity.BOTTOM;
                window.setAttributes(params);
            }
        }
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override
    public void initView(int layout) {
        view = LayoutInflater.from(context).inflate(layout, null);
        ButterKnife.bind(this, view);
        setContentView(view);
    }

    @Override
    public void showDialog() {
        if(!this.isShowing()){
            if(!context.isFinishing()){
                this.show();
            }
        }
    }

    @Override
    public void cancelDialog() {
        //隐藏键盘
        KeyBordUtil.hindSoftInput(context);
        if(this.isShowing()){
            //this.dismiss();
            this.cancel();
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
         
    }

    public BaseDialog setWebview(MyWebView mWebview) {
        this.mWebView = mWebview;
        return this;
    }

    public void setBottom(boolean bottom) {
        this.bottom = bottom;
    }

    public BaseDialog setFunction(String function) {
        this.function = function;
        return this;
    }

    public BaseDialog setCallBack(String callback) {
        this.callback = callback;
        return this;
    }
}
