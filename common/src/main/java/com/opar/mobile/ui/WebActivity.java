package com.opar.mobile.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.opar.mobile.base.BaseActivity;
import com.opar.mobile.data.R;
import com.opar.mobile.data.UrlContent;
import com.opar.mobile.web.IWebView;
import com.opar.mobile.web.MyWebView;

import java.lang.reflect.Field;

//======================================================================
//        All rights reserved
//
//        created by tangge at  04/19/2018
//        https://juejin.im/user/572a11851ea4930064b43203
//
//======================================================================

public class WebActivity extends BaseActivity implements IWebView {
    MyWebView mWebView;
    ProgressBar progressBar;

    private UrlContent urlContent = null;
    protected boolean canGoBack;//true->goback,   false->finish

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_web);
        initView();
        intData(bundle);
    }

    private void initView(){
        mWebView = (MyWebView) findViewById(R.id.webview);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        mWebView.init(this);
        setLeftBtn(true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        setRightBtn("分享",null);
    }

    private void intData(Bundle bundle){
        if (bundle != null) {
            urlContent = bundle.getParcelable("content");
        } else {
            urlContent = getIntent().getParcelableExtra("content");
        }
        if(urlContent != null){
            if(!TextUtils.isEmpty(urlContent.getTitle())){
                setTitle(urlContent.getTitle());
            }
            if(!TextUtils.isEmpty(urlContent.getUrl())){
                mWebView.loadUrl(urlContent.getUrl());
            }
        }
    }

    public void goBack() {
        if (canGoBack) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
            } else {
                finish();
            }
        } else {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){//点击返回按钮的时候判断有没有上一页
            goBack();
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }


    /**
     * 当Activity执行onPause()时让WebView执行pause
     */
    @Override
    protected void onPause() {
        super.onPause();

        try {
            if (mWebView != null) {
                mWebView.getClass().getMethod("onPause").invoke(mWebView, (Object[]) null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 当Activity执行onResume()时让WebView执行resume
     */
    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (mWebView != null) {
                mWebView.getClass().getMethod("onResume").invoke(mWebView, (Object[]) null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        releaseAllWebViewCallback();
    }

    @Override
    public void onPageStarted() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageFinished() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onReceivedError(String failingUrl) {

    }

    @Override
    public void onProgressChanged(int newProgress) {
        progressBar.setProgress(newProgress);
    }

    @Override
    public void onReceivedTitle(String title) {
        if(!TextUtils.isEmpty(title)){
            setTitle(title);
        }
    }

    //解决webview内存溢出问题
    private void releaseAllWebViewCallback() {
        if (android.os.Build.VERSION.SDK_INT < 19) {
            try {
                Field field = WebView.class.getDeclaredField("mWebViewCore");
                field = field.getType().getDeclaredField("mBrowserFrame");
                field = field.getType().getDeclaredField("sConfigCallback");
                field.setAccessible(true);
                field.set(null, null);
            } catch (Exception e) {
//                e.printStackTrace();
            }
        } else {
            try {
                Field sConfigCallback = Class.forName("android.webkit.BrowserFrame").getDeclaredField("sConfigCallback");
                if (sConfigCallback != null) {
                    sConfigCallback.setAccessible(true);
                    sConfigCallback.set(null, null);
                }
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }
    }

}
