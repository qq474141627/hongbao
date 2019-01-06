package com.opar.hongbao.entity;

import java.util.List;

public class AdConfig {
    /**
     * bannerAd : {"isOn":true,"title":"","link":"http://ak.aikeying.cn/mobile/ss/doReg.do?agentId=2000000219&mobile=15669962095","desc":"最低0.35%秒到，爱客赢app－最全的支付模式，移动支付新时代，分享还可拿份润！"}
     * wonderAd : [{"isOn":true,"title":"免费砸蛋赢好礼","link":"https://engine.lvehaisen.com/index/activity?appKey=4CCKM35QenRH1wzh5oXv6UwTnXAe&adslotId=180886","desc":"免费砸蛋赢好礼！"},{"isOn":true,"title":"","link":"http://ak.aikeying.cn/mobile/ss/doReg.do?agentId=2000000219&mobile=15669962095","desc":"最低0.35%秒到，爱客赢app－最全的支付模式，移动支付新时代，分享还可拿份润！"}]
     * bannerAdOn : true
     * wonderAdOn : true
     */

    private BannerAdConfig bannerAd;
    private boolean bannerAdOn;
    private boolean wonderAdOn;
    private List<BannerAdConfig> wonderAd;

    public BannerAdConfig getBannerAd() {
        return bannerAd;
    }

    public void setBannerAd(BannerAdConfig bannerAd) {
        this.bannerAd = bannerAd;
    }

    public boolean isBannerAdOn() {
        return bannerAdOn;
    }

    public void setBannerAdOn(boolean bannerAdOn) {
        this.bannerAdOn = bannerAdOn;
    }

    public boolean isWonderAdOn() {
        return wonderAdOn;
    }

    public void setWonderAdOn(boolean wonderAdOn) {
        this.wonderAdOn = wonderAdOn;
    }

    public List<BannerAdConfig> getWonderAd() {
        return wonderAd;
    }

    public void setWonderAd(List<BannerAdConfig> wonderAd) {
        this.wonderAd = wonderAd;
    }


}
