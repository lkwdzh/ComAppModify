package com.aglook.comapp.url;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/11/13.
 */
public class GoodsDetailUrl {
    private static RequestParams params;
    public static RequestParams postGoodsDetailUrl(String productId){
        params=new RequestParams();
        params.addBodyParameter("productId",productId);
        return params;
    }
}
