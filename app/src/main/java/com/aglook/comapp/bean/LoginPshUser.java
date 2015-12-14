package com.aglook.comapp.bean;

import java.io.Serializable;

/**
 * Created by aglook on 2015/11/12.
 */
public class LoginPshUser implements Serializable {
    private String userId;//用户id
    private String username;//用户姓名
    private String userMoney;//用户余额
    private String userPoint;//剩余积分
    private String userAllPoint;//总积分
    private String userNumber;//身份证号
    private String userTName;//真实姓名
    private String userPhone;//手机号
    private String userEmail;//邮箱
    private String userTel;//座机
    private String userQq;//QQ
    private String userAddress;//地址
    private String userSeat;//席位号
    private double rate;
    private boolean bankBind;//是否绑定银行卡

    public boolean isBankBind() {
        return bankBind;
    }

    public void setBankBind(boolean bankBind) {
        this.bankBind = bankBind;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(String userMoney) {
        this.userMoney = userMoney;
    }

    public String getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(String userPoint) {
        this.userPoint = userPoint;
    }

    public String getUserAllPoint() {
        return userAllPoint;
    }

    public void setUserAllPoint(String userAllPoint) {
        this.userAllPoint = userAllPoint;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserTName() {
        return userTName;
    }

    public void setUserTName(String userTName) {
        this.userTName = userTName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserQq() {
        return userQq;
    }

    public void setUserQq(String userQq) {
        this.userQq = userQq;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserSeat() {
        return userSeat;
    }

    public void setUserSeat(String userSeat) {
        this.userSeat = userSeat;
    }

    @Override
    public String toString() {
        return "LoginPshUser{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", userMoney='" + userMoney + '\'' +
                ", userPoint='" + userPoint + '\'' +
                ", userAllPoint='" + userAllPoint + '\'' +
                ", userNumber='" + userNumber + '\'' +
                ", userTName='" + userTName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userQq='" + userQq + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userSeat='" + userSeat + '\'' +
                ", rate=" + rate +
                ", bankBind=" + bankBind +
                '}';
    }
}
