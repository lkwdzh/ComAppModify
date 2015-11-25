package com.aglook.comapp.bean;

import java.io.Serializable;

/**
 * Created by aglook on 2015/11/23.
 */
public class HomePageScroll implements Serializable{
    private String adId;
    private String adLogo;
    private String adUrl;
    private String  adPosition;
    private String adState;
    private String adOrder;

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getAdLogo() {
        return adLogo;
    }

    public void setAdLogo(String adLogo) {
        this.adLogo = adLogo;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public String getAdPosition() {
        return adPosition;
    }

    public void setAdPosition(String adPosition) {
        this.adPosition = adPosition;
    }

    public String getAdState() {
        return adState;
    }

    public void setAdState(String adState) {
        this.adState = adState;
    }

    public String getAdOrder() {
        return adOrder;
    }

    public void setAdOrder(String adOrder) {
        this.adOrder = adOrder;
    }

    @Override
    public String toString() {
        return "HomePageScroll{" +
                "adId='" + adId + '\'' +
                ", adLogo='" + adLogo + '\'' +
                ", adUrl='" + adUrl + '\'' +
                ", adPosition='" + adPosition + '\'' +
                ", adState='" + adState + '\'' +
                ", adOrder='" + adOrder + '\'' +
                '}';
    }
}
