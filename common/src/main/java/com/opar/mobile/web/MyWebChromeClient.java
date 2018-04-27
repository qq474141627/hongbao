package com.opar.mobile.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by wangbo on 17/11/27.
 */

public class MyWebChromeClient extends WebChromeClient {
    private IWebView iWebView;

    public MyWebChromeClient(IWebView iWebView){
        this.iWebView = iWebView;
    }
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        iWebView.onProgressChanged(newProgress);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        iWebView.onReceivedTitle(title);
    }
}
