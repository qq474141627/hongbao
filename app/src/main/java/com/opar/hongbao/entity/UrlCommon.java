package com.opar.hongbao.entity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.opar.hongbao.ui.WebActivity;
import com.opar.hongbao.utils.LogUtils;

/**
 * Created by tangge on 18/3/27.
 */
public class UrlCommon {

    private Context context;
    private String title;
    private String url;
    private String customTitle;
    private String customUrl;
    private boolean needLogin;
    private boolean isPost;
    private boolean canshare;
    private String from;
    private int theme;
    private int flags;
    private boolean showh5cache;
    private int background;
    private boolean hideToolbar;
    private boolean isTransparent;
    private boolean addParam = true;
    private boolean canGoBack;
    private String userAgent;

    private String rightTitle;
    private String rightUrl;

    private boolean returnToPrevious;

    public UrlCommon(Context context) {
        this.context = context;
    }

    public String getTitle() {
        return title;
    }

    public UrlCommon setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public UrlCommon setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getCustomTitle() {
        return customTitle;
    }

    public UrlCommon setCustomTitle(String customTitle) {
        this.customTitle = customTitle;
        return this;
    }

    public String getCustomUrl() {
        return customUrl;
    }

    public UrlCommon setCustomUrl(String customUrl) {
        this.customUrl = customUrl;
        return this;
    }

    public boolean isNeedLogin() {
        return needLogin;
    }

    public UrlCommon setNeedLogin(boolean needLogin) {
        this.needLogin = needLogin;
        return this;
    }

    public boolean isPost() {
        return isPost;
    }

    public UrlCommon setPost(boolean post) {
        isPost = post;
        return this;
    }

    public boolean isCanshare() {
        return canshare;
    }

    public UrlCommon setCanshare(boolean canshare) {
        this.canshare = canshare;
        return this;
    }

    public String getFrom() {
        return from;
    }

    public UrlCommon setFrom(String from) {
        this.from = from;
        return this;
    }

    public int getTheme() {
        return theme;
    }

    public UrlCommon setTheme(int theme) {
        this.theme = theme;
        return this;
    }

    public int getFlags() {
        return flags;
    }

    public UrlCommon setFlags(int flags) {
        this.flags = flags;
        return this;
    }

    public boolean isShowh5cache() {
        return showh5cache;
    }

    public UrlCommon setShowh5cache(boolean showh5cache) {
        this.showh5cache = showh5cache;
        return this;
    }

    public int getBackground() {
        return background;
    }

    public UrlCommon setBackground(int background) {
        this.background = background;
        return this;
    }

    public boolean isHideToolbar() {
        return hideToolbar;
    }

    public UrlCommon setHideToolbar(boolean hideToolbar) {
        this.hideToolbar = hideToolbar;
        return this;
    }

    public boolean isTransparent() {
        return isTransparent;
    }

    public UrlCommon setTransparent(boolean transparent) {
        isTransparent = transparent;
        return this;
    }

    public boolean isAddParam() {
        return addParam;
    }

    public UrlCommon setAddParam(boolean addParam) {
        this.addParam = addParam;
        return this;
    }

    public boolean isCanGoBack() {
        return canGoBack;
    }

    public UrlCommon setCanGoBack(boolean canGoBack) {
        this.canGoBack = canGoBack;
        return this;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public UrlCommon setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public String getRightTitle() {
        return rightTitle;
    }

    public UrlCommon setRightTitle(String rightTitle) {
        this.rightTitle = rightTitle;
        return this;
    }

    public String getRightUrl() {
        return rightUrl;
    }

    public UrlCommon setRightUrl(String rightUrl) {
        this.rightUrl = rightUrl;
        return this;
    }

    public boolean getReturnToPrevious() {
        return returnToPrevious;
    }

    public UrlCommon setReturnToPrevious(boolean returnToPrevious) {
        this.returnToPrevious = returnToPrevious;
        return this;
    }

    @Override
    public String toString() {
        return "UrlCommon{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", customTitle='" + customTitle + '\'' +
                ", customUrl='" + customUrl + '\'' +
                ", needLogin=" + needLogin +
                ", isPost=" + isPost +
                ", canshare=" + canshare +
                ", from='" + from + '\'' +
                ", theme='" + theme + '\'' +
                '}';
    }

    public void start(){
        LogUtils.e("UrlCommon",toString());
        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(url)) {
            return;
        }
        UrlContent content = new UrlContent();
        content.setTitle(title);
        content.setUrl(url);
        content.setShareTitle(customTitle);
        content.setShareUrl(customUrl);
        if(!TextUtils.isEmpty(from)){
            content.setFrom(from);
        }
        content.setAddParam(addParam);
        content.setShowh5cache(showh5cache);
        content.setCanShare(canshare);
        content.setTheme(theme);
        content.setBackground(background);
        content.setHideToolbar(hideToolbar);
        content.setCanGoBack(canGoBack);
        content.setUserAgent(userAgent);
        content.setRightTitle(rightTitle);
        content.setRightUrl(rightUrl);
        content.setReturnToPrevious(returnToPrevious);
        Intent intent = new Intent(context, WebActivity.class);
        if(flags != 0){
            intent.setFlags(flags);
        }
        intent.putExtra("content", content);
        context.startActivity(intent);
    }
}
