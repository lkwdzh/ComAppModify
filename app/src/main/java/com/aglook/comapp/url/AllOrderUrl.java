package com.aglook.comapp.url;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/11/18.
 */
public class AllOrderUrl {

    private static RequestParams params;

    /**
     *
     * @param userId
     * @param token
     * @param orderStatus  订单状态 @"":所有订单  @"1":待付款订单  @"0":已完成订单  @"2":取消订单
     * @return
     */
    public static RequestParams postAllOrderUrl(String userId, String token,String orderStatus){
        params=new RequestParams();
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("token",token);
        params.addBodyParameter("orderStatus",orderStatus);
        return params;
    }
}
