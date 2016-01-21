package com.aglook.comapp.bean;

import java.io.Serializable;

/**
 * Created by aglook on 2015/11/3.
 */
public class Login  implements Serializable{
    private String token;
    private String isNeedFee;
    private LoginPshUser pshUser;
    private boolean bankBind;//是否绑定银行卡
    private CardList bankCard;//银行卡信息
    private boolean isXingYe;//是否是兴业

    public CardList getBankCard() {
        return bankCard;
    }

    public void setBankCard(CardList bankCard) {
        this.bankCard = bankCard;
    }

    public boolean isXingYe() {
        return isXingYe;
    }

    public void setXingYe(boolean isXingYe) {
        this.isXingYe = isXingYe;
    }

    public boolean isBankBind() {
        return bankBind;
    }

    public void setBankBind(boolean bankBind) {
        this.bankBind = bankBind;
    }

    @Override
    public String toString() {
        return "Login{" +
                "token='" + token + '\'' +
                ", isNeedFee='" + isNeedFee + '\'' +
                ", pshUser=" + pshUser +
                ", bankBind=" + bankBind +
                ", bankCard=" + bankCard +
                ", isXingYe=" + isXingYe +
                '}';
    }

    public String getIsNeedFee() {
        return isNeedFee;
    }

    public void setIsNeedFee(String isNeedFee) {
        this.isNeedFee = isNeedFee;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginPshUser getPshUser() {
        return pshUser;
    }

    public void setPshUser(LoginPshUser pshUser) {
        this.pshUser = pshUser;
    }
}
