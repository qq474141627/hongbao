package com.opar.mobile.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tangge on 18/3/27.
 */
public class UrlContent implements Parcelable {

    public static final int ACTION_TYPE_GET = 0;
    public static final int ACTION_TYPE_POST = 1;
    private String url;
    private int actionType = ACTION_TYPE_GET;
    private String data;
    private String title;

    private boolean canShare;
    private String shareTitle, shareUrl;
    private String from;
    private int theme;
    private boolean showh5cache;
    private int background = -1;
    private boolean hideToolbar;
    private boolean addParam = true;
    private boolean canGoBack;
    private String userAgent;
    private String rightTitle;//右上角按钮
    private String rightUrl;//右上角按钮点击跳转

    private boolean returnToPrevious;

    public UrlContent() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCanShare() {
        return canShare;
    }

    public void setCanShare(boolean canShare) {
        this.canShare = canShare;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme) {
        this.theme = theme;
    }

    public boolean isShowh5cache() {
        return showh5cache;
    }

    public void setShowh5cache(boolean showh5cache) {
        this.showh5cache = showh5cache;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public boolean isHideToolbar() {
        return hideToolbar;
    }

    public void setHideToolbar(boolean hideToolbar) {
        this.hideToolbar = hideToolbar;
    }

    public boolean isAddParam() {
        return addParam;
    }

    public void setAddParam(boolean addParam) {
        this.addParam = addParam;
    }

    public boolean isCanGoBack() {
        return canGoBack;
    }

    public void setCanGoBack(boolean canGoBack) {
        this.canGoBack = canGoBack;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getRightTitle() {
        return rightTitle;
    }

    public void setRightTitle(String rightTitle) {
        this.rightTitle = rightTitle;
    }

    public String getRightUrl() {
        return rightUrl;
    }

    public void setRightUrl(String rightUrl) {
        this.rightUrl = rightUrl;
    }

    public boolean isReturnToPrevious() {
        return returnToPrevious;
    }

    public void setReturnToPrevious(boolean returnToPrevious) {
        this.returnToPrevious = returnToPrevious;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeInt(this.actionType);
        dest.writeString(this.data);
        dest.writeString(this.title);
        dest.writeByte(this.canShare ? (byte) 1 : (byte) 0);
        dest.writeString(this.shareTitle);
        dest.writeString(this.shareUrl);
        dest.writeString(this.from);
        dest.writeInt(this.theme);
        dest.writeByte(this.showh5cache ? (byte) 1 : (byte) 0);
        dest.writeInt(this.background);
        dest.writeByte(this.hideToolbar ? (byte) 1 : (byte) 0);
        dest.writeByte(this.addParam ? (byte) 1 : (byte) 0);
        dest.writeByte(this.canGoBack ? (byte) 1 : (byte) 0);
        dest.writeString(this.userAgent);
        dest.writeString(this.rightTitle);
        dest.writeString(this.rightUrl);
        dest.writeByte(this.returnToPrevious ? (byte) 1 : (byte) 0);
    }

    protected UrlContent(Parcel in) {
        this.url = in.readString();
        this.actionType = in.readInt();
        this.data = in.readString();
        this.title = in.readString();
        this.canShare = in.readByte() != 0;
        this.shareTitle = in.readString();
        this.shareUrl = in.readString();
        this.from = in.readString();
        this.theme = in.readInt();
        this.showh5cache = in.readByte() != 0;
        this.background = in.readInt();
        this.hideToolbar = in.readByte() != 0;
        this.addParam = in.readByte() != 0;
        this.canGoBack = in.readByte() != 0;
        this.userAgent = in.readString();
        this.rightTitle = in.readString();
        this.rightUrl = in.readString();
        this.returnToPrevious = in.readByte() != 0;
    }

    public static final Creator<UrlContent> CREATOR = new Creator<UrlContent>() {
        @Override
        public UrlContent createFromParcel(Parcel source) {
            return new UrlContent(source);
        }

        @Override
        public UrlContent[] newArray(int size) {
            return new UrlContent[size];
        }
    };
}