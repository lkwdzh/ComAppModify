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

    private int userType;//用户类别：1.公司，2.个人
    private String userCompany;//公司名称
    private String userAddres;//公司地址


    /**
     * 普通发票税号
     */
    private String userInvoice;
    /**
     * 增值发票税号
     */
    private String userInvoices;
    /**
     * 经理姓名
     */
    private String userJname;
    /**
     * 增值税发票公司名称
     */
    private String userCaty;
    /**
     * 增值税发票纳税人识别号
     */
    private String userNnumb;
    /**
     * 增值税发票注册地址
     */
    private String userZcdz;
    /**
     * 增值税发票公司电话
     */
    private String userTels;
    /**
     * 增值税发票开户银行
     */
    private String userBanks;
    /**
     * 增值税发票银行账户
     */
    private String userBnumb;

    public String getUserInvoice() {
        return userInvoice;
    }

    public void setUserInvoice(String userInvoice) {
        this.userInvoice = userInvoice;
    }

    public String getUserInvoices() {
        return userInvoices;
    }

    public void setUserInvoices(String userInvoices) {
        this.userInvoices = userInvoices;
    }

    public String getUserJname() {
        return userJname;
    }

    public void setUserJname(String userJname) {
        this.userJname = userJname;
    }

    public String getUserCaty() {
        return userCaty;
    }

    public void setUserCaty(String userCaty) {
        this.userCaty = userCaty;
    }

    public String getUserNnumb() {
        return userNnumb;
    }

    public void setUserNnumb(String userNnumb) {
        this.userNnumb = userNnumb;
    }

    public String getUserZcdz() {
        return userZcdz;
    }

    public void setUserZcdz(String userZcdz) {
        this.userZcdz = userZcdz;
    }

    public String getUserTels() {
        return userTels;
    }

    public void setUserTels(String userTels) {
        this.userTels = userTels;
    }

    public String getUserBanks() {
        return userBanks;
    }

    public void setUserBanks(String userBanks) {
        this.userBanks = userBanks;
    }

    public String getUserBnumb() {
        return userBnumb;
    }

    public void setUserBnumb(String userBnumb) {
        this.userBnumb = userBnumb;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUserCompany() {
        return userCompany;
    }

    public void setUserCompany(String userCompany) {
        this.userCompany = userCompany;
    }

    public String getUserAddres() {
        return userAddres;
    }

    public void setUserAddres(String userAddres) {
        this.userAddres = userAddres;
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
                ", userType=" + userType +
                ", userCompany='" + userCompany + '\'' +
                ", userAddres='" + userAddres + '\'' +
                ", userInvoice='" + userInvoice + '\'' +
                ", userInvoices='" + userInvoices + '\'' +
                ", userJname='" + userJname + '\'' +
                ", userCaty='" + userCaty + '\'' +
                ", userNnumb='" + userNnumb + '\'' +
                ", userZcdz='" + userZcdz + '\'' +
                ", userTels='" + userTels + '\'' +
                ", userBanks='" + userBanks + '\'' +
                ", userBnumb='" + userBnumb + '\'' +
                '}';
    }
}
