package com.aglook.comapp.bean;

import java.io.Serializable;

/**
 * Created by aglook on 2015/11/3.
 */
public class Login  implements Serializable{
    private String token;
    private String isNeedFee;
    private LoginPshUser pshUser;

    @Override
    public String toString() {
        return "Login{" +
                "token='" + token + '\'' +
                ", isNeedFee='" + isNeedFee + '\'' +
                ", pshUser=" + pshUser +
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
