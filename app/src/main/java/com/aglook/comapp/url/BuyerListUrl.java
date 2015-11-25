package com.aglook.comapp.url;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/11/23.
 */
public class BuyerListUrl {
    private static RequestParams params;

    public static RequestParams postBuyerListUrl(String userId,String token){
        params=new RequestParams();
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("token",token);
        return params;
    }
}
