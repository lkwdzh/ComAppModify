package com.aglook.comapp.bean;

import java.io.Serializable;

/**
 * Created by aglook on 2015/11/12.
 */
public class CardList implements Serializable{
    private String bankCardId;//银行卡id
    private String cardNo;//银行卡号: "5432123456788881"
    private String cardPhone;//手机号: "18613346633"
    private String bankCode;//银行卡图片 "6"
    private String cardType;//卡类型: "0"
    private String userName;//用户名 "lk"
    private String  bankAlis;//银行名字 :"北京银行"
    private String defaultType;//默认状态:"0"

    public String getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(String bankCardId) {
        this.bankCardId = bankCardId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardPhone() {
        return cardPhone;
    }

    public void setCardPhone(String cardPhone) {
        this.cardPhone = cardPhone;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBankAlis() {
        return bankAlis;
    }

    public void setBankAlis(String bankAlis) {
        this.bankAlis = bankAlis;
    }

    public String getDefaultType() {
        return defaultType;
    }

    public void setDefaultType(String defaultType) {
        this.defaultType = defaultType;
    }

    @Override
    public String toString() {
        return "CardList{" +
                "bankCardId='" + bankCardId + '\'' +
                ", cardNo='" + cardNo + '\'' +
                ", cardPhone='" + cardPhone + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", cardType='" + cardType + '\'' +
                ", userName='" + userName + '\'' +
                ", bankAlis='" + bankAlis + '\'' +
                ", defaultType='" + defaultType + '\'' +
                '}';
    }
}
