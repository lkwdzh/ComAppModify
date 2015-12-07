package com.aglook.comapp.url;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/12/7.
 */
public class SettingUrl {
    private static RequestParams params;

    /**
     * 退出登录
     * @param userId
     * @return
     */
    public static RequestParams postLogin_out_url(String userId){
        params=new RequestParams();
        params.addBodyParameter("userId",userId);
        return params;
    }

    public static RequestParams postHelpUrl(String pageSize,String pageNumber){
        params=new RequestParams();
        params.addBodyParameter("pageSize",pageSize);
        params.addBodyParameter("pageNumber",pageNumber);
        return params;
    }
}
