package com.opar.mobile.web;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Message;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;

import com.github.lzyzsd.jsbridge.BridgeWebView;
import com.github.lzyzsd.jsbridge.BridgeWebViewClient;


/**
 * Created by wangbo on 17/11/27.
 */

public class MyWebViewClient extends BridgeWebViewClient{

    private IWebView iWebView;
    private Context context;

    public MyWebViewClient(Context context ,BridgeWebView webView, IWebView iWebView) {
        super(webView);
        this.iWebView = iWebView;
        this.context = context;
    }
    @Override
    public void onFormResubmission(WebView view, Message dontResend, Message resend) {
        resend.sendToTarget();
    }

    public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
        return super.shouldOverrideUrlLoading(view,url);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        iWebView.onPageFinished();
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        iWebView.onPageStarted();
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        iWebView.onReceivedError(failingUrl);
    }

    @Override
    public void onReceivedSslError(WebView view, final SslErrorHandler handler, SslError error) {
        handler.proceed(); // 接受证书
    }
}
