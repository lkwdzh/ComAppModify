package com.aglook.comapp.Application;

import android.app.Application;

import com.aglook.comapp.bean.Login;
import com.aglook.comapp.util.DefineUtil;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by aglook on 2015/11/11.
 */
public class ComAppApplication extends Application {
    private static ComAppApplication instance;
    private Login login;
//    private  DbUtils db;
//
//    public  DbUtils getDb() {
//        return db;
//    }
//
//    public void setDb(DbUtils db) {
//        this.db = db;
//    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        DefineUtil.IS_LAUNCH=true;
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
//        db=DbUtils.create(this,"MESSAGE");
    }
    public static ComAppApplication getInstance(){
        if (null==instance){
            instance=new ComAppApplication();
        }
        return instance;
    }
}
