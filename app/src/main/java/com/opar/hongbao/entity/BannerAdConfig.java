package com.opar.hongbao.entity;

public class BannerAdConfig {

    /**
     * isOn : true
     * title :
     * address : http://ak.aikeying.cn/mobile/ss/doReg.do?agentId=2000000219&mobile=15669962095
     * desc : 最低0.35%秒到，爱客赢app－最全的支付模式，移动支付新时代，分享还可拿份润！
     */

    private String title;
    private String link;
    private String desc;
    private String img;
    private boolean isNew;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
