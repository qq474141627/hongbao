package com.opar.mobile.web;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.webkit.DownloadListener;

import com.github.lzyzsd.jsbridge.BridgeWebView;

/**
 * Created by wangbo on 17/11/21.
 */
public class MyWebView extends BridgeWebView {
    private Context mContext;

    public MyWebView(Context context) {
        super(context);
        this.mContext = context;
    }

    public MyWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    public MyWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    public void init(IWebView iWebView) {
        setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                try{
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    mContext.startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        InitWebViewUtil.initWebView(this);
        setWebChromeClient(new MyWebChromeClient(iWebView));
        setWebViewClient(new MyWebViewClient(mContext,this,iWebView));
    }



}
