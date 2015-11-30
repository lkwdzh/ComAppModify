package com.aglook.comapp.bean;

import java.io.Serializable;

/**
 * Created by aglook on 2015/11/20.
 */
public class LinkMan implements Serializable{
    private int userId;//用户ID
    private boolean isChecked;
    private String userName;//用户姓名
    private String userPw;
    private String userMoney;
    private String userPoint;
    private String userPointAll;
    private String userTname;
    private String userPhone;
    private String userTel;
    private String userQq;
    private String userEmail;
    private String userAtime;
    private String userLtime;
    private String userAddress;
    private String userIp;
    private String tokenNo;
    private String userSeat;//席位号
    private String userNumber;
    private String rate;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
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

    public String getUserPointAll() {
        return userPointAll;
    }

    public void setUserPointAll(String userPointAll) {
        this.userPointAll = userPointAll;
    }

    public String getUserTname() {
        return userTname;
    }

    public void setUserTname(String userTname) {
        this.userTname = userTname;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAtime() {
        return userAtime;
    }

    public void setUserAtime(String userAtime) {
        this.userAtime = userAtime;
    }

    public String getUserLtime() {
        return userLtime;
    }

    public void setUserLtime(String userLtime) {
        this.userLtime = userLtime;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getTokenNo() {
        return tokenNo;
    }

    public void setTokenNo(String tokenNo) {
        this.tokenNo = tokenNo;
    }

    public String getUserSeat() {
        return userSeat;
    }

    public void setUserSeat(String userSeat) {
        this.userSeat = userSeat;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "LinkMan{" +
                "userId=" + userId +
                ", isChecked=" + isChecked +
                ", userName='" + userName + '\'' +
                ", userPw='" + userPw + '\'' +
                ", userMoney='" + userMoney + '\'' +
                ", userPoint='" + userPoint + '\'' +
                ", userPointAll='" + userPointAll + '\'' +
                ", userTname='" + userTname + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userQq='" + userQq + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userAtime='" + userAtime + '\'' +
                ", userLtime='" + userLtime + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userIp='" + userIp + '\'' +
                ", tokenNo='" + tokenNo + '\'' +
                ", userSeat='" + userSeat + '\'' +
                ", userNumber='" + userNumber + '\'' +
                ", rate='" + rate + '\'' +
                '}';
    }
}
