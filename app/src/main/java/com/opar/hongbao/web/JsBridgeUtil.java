package com.opar.hongbao.web;

import android.app.Activity;

import com.github.lzyzsd.jsbridge.CallBackFunction;

/**
 * Created by wangbo on 17/11/22.
 */

public class JsBridgeUtil {

    private MyWebView mWebView;
    private Activity activity;

    public JsBridgeUtil(Activity activity, MyWebView webView) {
        this.mWebView = webView;
        this.activity = activity;
//        mWebView.registerHandler(KeyConfig.USE_NATIVE_FUNCTION, bridgeHandler);
    }

    MyBridgeHandler bridgeHandler = new MyBridgeHandler() {

        @Override
        public void handler(String response, CallBackFunction callBackFunction) {

        }
    };

}
