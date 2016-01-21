package com.aglook.comapp.url;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2016/1/21.
 */
public class OrderDetailUrl {
    private static RequestParams params;

    /**
     * 订单详情接口
     * @param userId id
     * @param token token
     * @param orderId 订单id
     * @return
     */
    public static RequestParams postOrdetDetailUrl(String userId, String token,String orderId ){
        params=new RequestParams();
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("token",token);
        params.addBodyParameter("orderId",orderId);
        return params;
    }
}
