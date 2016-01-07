package com.aglook.comapp.url;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/11/16.
 */
public class ShoppingCartUrl {
    private static RequestParams params;


    /**
     * 加入购物车
     *
     * @param userId
     * @param token
     * @param productId
     * @param productNum
     * @return
     */
    public static RequestParams postAddCartUrl(String userId, String token, String productId, String productNum,String pointUser) {
        params = new RequestParams();
        params.addBodyParameter("userId", userId);
        params.addBodyParameter("token", token);
        params.addBodyParameter("productId", productId);
        params.addBodyParameter("productNum", productNum);
        params.addBodyParameter("pointUser", pointUser);
        return params;
    }

    /**
     * 编辑购物车
     *
     * @param userId
     * @param token
     * @param cartId
     * @param productNum
     * @param deleteFlag
     * @return
     */
    public static RequestParams postDeleteUrl(String userId, String token, String cartId, String productNum, String deleteFlag) {
        params = new RequestParams();
        params.addBodyParameter("userId", userId);
        params.addBodyParameter("token", token);
        params.addBodyParameter("cartId", cartId);
        params.addBodyParameter("productNum", productNum);
        params.addBodyParameter("deleteFlag", deleteFlag);
//        Log.d("result_shop",new Gson().toJson(params));
        return params;
    }


    /**
     * 购物车列表
     *
     * @param userId
     * @param token
     * @return
     */
    public static RequestParams postCartListUrl(String userId, String token) {
        params = new RequestParams();
        params.addBodyParameter("userId", userId);
        params.addBodyParameter("token", token);
        return params;
    }

}
