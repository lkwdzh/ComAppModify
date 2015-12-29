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

    //微信分享
//    private static final  String APP_ID="wxb7be9be3615de8a9";
//    //IWXAPI是第三方app和微信通信的openapi接口
//    public IWXAPI api;

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
        JPushInterface.setDebugMode(false); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
//        regToWx();
    }

//    private void regToWx(){
//        //通过WXAPIFactory工厂，获取IWXAPI的实例
//        api= WXAPIFactory.createWXAPI(this, APP_ID, true);
//
//        //将应用的appid注册到微信
//        api.registerApp(APP_ID);
//    }
    public static ComAppApplication getInstance(){
        if (null==instance){
            instance=new ComAppApplication();
        }
        return instance;
    }
}
