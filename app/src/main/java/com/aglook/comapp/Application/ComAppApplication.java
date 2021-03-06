package com.aglook.comapp.Application;

import android.app.Application;

import com.aglook.comapp.bean.Login;
import com.aglook.comapp.util.DefineUtil;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by aglook on 2015/11/11.
 */
public class ComAppApplication extends Application {
    private static ComAppApplication instance;
    private Login login;
    private RefWatcher mRefWatcher;

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mRefWatcher= LeakCanary.install(this);
        DefineUtil.IS_LAUNCH=true;
        JPushInterface.setDebugMode(false); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
    }

    public static ComAppApplication getInstance(){
        if (null==instance){
            instance=new ComAppApplication();
        }
        return instance;
    }
}
