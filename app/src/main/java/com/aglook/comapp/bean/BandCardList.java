package com.aglook.comapp.bean;

/**
 * Created by aglook on 2015/11/13.
 */
public class BandCardList {
    private String bankCodeId;
    private String bankCodeName;

    public String getBankCodeId() {
        return bankCodeId;
    }

    public void setBankCodeId(String bankCodeId) {
        this.bankCodeId = bankCodeId;
    }

    public String getBankCodeName() {
        return bankCodeName;
    }

    public void setBankCodeName(String bankCodeName) {
        this.bankCodeName = bankCodeName;
    }

    @Override
    public String toString() {
        return "BandCardList{" +
                "bankCodeId='" + bankCodeId + '\'' +
                ", bankCodeName='" + bankCodeName + '\'' +
                '}';
    }
}
