package com.opar.mobile.web;

import android.os.Build;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * 初始化界面配置
 */
public class InitWebViewUtil {

    /**
     * 初始化webview的配置
     *
     * @param webView
     */
    public static void initWebView(WebView webView) {
        webView.getSettings().setJavaScriptEnabled(true);//可用JS
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setBuiltInZoomControls(false);//不启用内部的缩放工具
//        webView.getSettings().setSupportZoom(true);//出现缩放工具
        webView.getSettings().setUseWideViewPort(true);//扩大比例缩放
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setDomStorageEnabled(true);
        CookieManager.getInstance().setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= 19) {
            webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        }
        webView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setSavePassword(false);
        if (Build.VERSION.SDK_INT < 17) {
            //移除高危漏洞
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("ccessibilityTraversal");
            webView.removeJavascriptInterface("accessibility");
        }
        
    }

}
