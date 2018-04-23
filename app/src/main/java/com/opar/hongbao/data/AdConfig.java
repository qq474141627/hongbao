package com.opar.hongbao.data;

public class AdConfig {
    /**
     * ad : true
     * address : http://www.jobi5.com/down+101.html?c=211709
     * pkgName : ccccc
     */

    private boolean ad;
    private String address;
    private String pkgName;

    public boolean isAd() {
        return ad;
    }

    public void setAd(boolean ad) {
        this.ad = ad;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }
}
