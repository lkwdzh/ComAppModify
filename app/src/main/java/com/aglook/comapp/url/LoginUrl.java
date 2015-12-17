package com.aglook.comapp.url;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/11/3.
 */
public class LoginUrl {
    private static RequestParams params;

    public static RequestParams postLonginUrl(String account,String password,String accountType,String deviceNumber){
        params=new RequestParams();
        params.addBodyParameter("account",account);
        params.addBodyParameter("password",password);
        params.addBodyParameter("accountType",accountType);
        params.addBodyParameter("deviceNumber",deviceNumber);
        return params;
    }
}
