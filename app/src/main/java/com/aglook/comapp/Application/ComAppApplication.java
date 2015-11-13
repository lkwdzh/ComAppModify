package com.aglook.comapp.Application;

import android.app.Application;

import com.aglook.comapp.bean.Login;

/**
 * Created by aglook on 2015/11/11.
 */
public class ComAppApplication extends Application {
    private static ComAppApplication instance;
    private Login login;

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public static ComAppApplication getInstance(){
        if (null==instance){
            instance=new ComAppApplication();
        }
        return instance;
    }
}
