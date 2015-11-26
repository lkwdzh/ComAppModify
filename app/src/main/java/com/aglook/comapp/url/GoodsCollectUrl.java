package com.aglook.comapp.url;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/11/26.
 */
public class GoodsCollectUrl {
    private static RequestParams params;

    /**
     * 收藏商品列表
     * @param userId
     * @param token
     * @return
     */
    public static RequestParams postGoodsCollectUrl(String userId, String token) {
        params = new RequestParams();
        params.addBodyParameter("userId", userId);
        params.addBodyParameter("token", token);
        return params;
    }
}
