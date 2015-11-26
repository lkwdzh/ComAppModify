package com.aglook.comapp.url;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/11/13.
 */
public class GoodsDetailUrl {
    private static RequestParams params;

    /**
     * 获取商品详情
     * @param userId
     * @param productId
     * @return
     */
    public static RequestParams postGoodsDetailUrl(String userId,String productId){
        params=new RequestParams();
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("productId",productId);
        return params;
    }

    /**
     * 收藏
     * @param token
     * @param userId
     * @param productId
     * @return
     */
    public static RequestParams postShouUrl(String token, String userId, String productId){
        params=new RequestParams();
        params.addBodyParameter("token",token);
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("productId",productId);
        return params;
    }


    /**
     * 取消收藏
     * @param token
     * @param userId
     * @param productId
     * @return
     */
    public static RequestParams postDeleteUrl(String token, String userId, String productId){
        params=new RequestParams();
        params.addBodyParameter("token",token);
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("productId",productId);
        return params;
    }

}
