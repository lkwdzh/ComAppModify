package com.aglook.comapp.url;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/11/13.
 */
public class ScreenUrl {
    private static RequestParams params;
    public static RequestParams postClassificationUrl(String categoryId){
        params=new RequestParams();
        params.addBodyParameter("categoryId",categoryId);
        return params;
    }
}
