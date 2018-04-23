package com.opar.hongbao.web;

/**
 * Created by wangbo on 17/11/27.
 */

public interface IWebView {
    void onPageStarted();
    void onPageFinished();
    void onReceivedError(String failingUrl);
    void onProgressChanged(int newProgress);
    void onReceivedTitle(String title);
}
