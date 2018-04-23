package com.opar.hongbao.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.opar.hongbao.R;
import com.opar.hongbao.web.IWebView;
import com.opar.hongbao.web.MyWebView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.BindView;

//======================================================================
//        All rights reserved
//
//        created by tangge at  04/19/2018
//        https://juejin.im/user/572a11851ea4930064b43203
//
//======================================================================

public class WebActivity extends BaseActivity implements IWebView{
    @BindView(R.id.webview)
    MyWebView webView;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.tvTitle)
    TextView tvTitle;

    private String adAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView.init(this);
        adAddress = getIntent().getStringExtra("url");
        webView.loadUrl(adAddress);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (webView.canGoBack() && keyCode == KeyEvent.KEYCODE_BACK){//点击返回按钮的时候判断有没有上一页
            webView.goBack(); // goBack()表示返回webView的上一页面
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        webView.destroy();
        webView=null;
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
        if(TextUtils.isEmpty(title)){
            tvTitle.setText(title);
        }
    }

}
