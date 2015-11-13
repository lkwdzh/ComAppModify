package com.aglook.comapp.bean;

/**
 * Created by aglook on 2015/11/3.
 */
public class Login {
    private String token;
    private LoginPshUser pshUser;

    @Override
    public String toString() {
        return "Login{" +
                "token='" + token + '\'' +
                ", pshUser=" + pshUser +
                '}';
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
