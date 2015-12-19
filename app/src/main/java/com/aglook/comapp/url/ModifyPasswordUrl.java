package com.aglook.comapp.url;

import android.util.Log;

import com.google.gson.Gson;
import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/11/13.
 */
public class ModifyPasswordUrl {
    private static RequestParams params;

    //修改信息
    public static RequestParams postModifypwdUrl(String userId, String token,String pwd,String newPwd) {
        params = new RequestParams();
        params.addBodyParameter("userId", userId);
        params.addBodyParameter("token", token);
        params.addBodyParameter("pwd", pwd);
        params.addBodyParameter("newPwd", newPwd);
        Log.d("result_pass",new Gson().toJson(params));
        return params;
    }
}
