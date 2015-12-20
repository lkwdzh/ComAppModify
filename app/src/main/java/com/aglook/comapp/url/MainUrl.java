package com.aglook.comapp.url;

import android.util.Log;

import com.google.gson.Gson;
import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/12/19.
 */
public class MainUrl {
    private static RequestParams params;

    /**
     * 检查更新
     * @param appName 应用名称（必须）
     * @param marketType 市场类型
     * @return
     */
    public static RequestParams postCheckUpUrl(String appName,String marketType){
        params=new RequestParams();
        params.addBodyParameter("appType","android");
        params.addBodyParameter("appName",appName);
        params.addBodyParameter("marketType",marketType);
        Log.d("result",new Gson().toJson(params));
        return params;
    }
}
