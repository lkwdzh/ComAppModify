package com.aglook.comapp.url;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/11/18.
 */
public class AllOrderUrl {

    private static RequestParams params;

    /**
     *订单列表
     * @param userId
     * @param token
     * @param orderStatus  订单状态 @"":所有订单  @"1":待付款订单  @"0":已完成订单  @"2":取消订单
     * @return
     */
    public static RequestParams postAllOrderUrl(String userId, String token,String orderStatus,String pageSize,String pageNumber,String orderId){
        params=new RequestParams();
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("token",token);
        params.addBodyParameter("orderStatus",orderStatus);
        params.addBodyParameter("pageSize",pageSize);
        params.addBodyParameter("pageNumber", pageNumber);
        params.addBodyParameter("orderId",orderId);
        return params;
    }
    public static RequestParams postAllOrderUrl1(String userId, String token,String orderId){
        params=new RequestParams();
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("token",token);
        params.addBodyParameter("orderId",orderId);
        return params;
    }

    /**
     * 订单列表
     * @param code
     * @param token
     * @param userId
     * @param pageSize
     * @param pageNum
     * @param _sort
     * @param orderState close(关闭)
                            notpay(待支付)
                             success(支付成功)
     * @return
     */
//    public static RequestParams postAllOrderUrl(String code,String token, String userId, String pageSize, String pageNum, String _sort,String orderState) {
//        params=new RequestParams();
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("token", token);
//            jsonObject.put("userId", userId);
//            jsonObject.put("pageSize", pageSize);
//            jsonObject.put("pageNum", pageNum);
//            jsonObject.put("_sort", _sort);
//            jsonObject.put("orderState", orderState);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        String sign=null;
//        String content=jsonObject.toString();
//        params.addBodyParameter("code", code);
//        params.addBodyParameter("version", DefineUtil.VERSON);
//        params.addBodyParameter("content", content);
//        params.addBodyParameter("sign", sign);
//        return params;
//    }

    /**
     * 取消订单
     * @param userId
     * @param token
     * @param orderId
     * @return
     */
    public static RequestParams postCancelOrderUrl(String userId, String token,String orderId){
        params=new RequestParams();
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("token",token);
        params.addBodyParameter("orderId",orderId);
        return params;
    }

    /**
     * 订单详情
     * @param userId
     * @param token
     * @param orderId
     * @return
     */
    public static RequestParams postOrderDetailUrl(String userId, String token,String orderId){
        params=new RequestParams();
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("token",token);
        params.addBodyParameter("orderId",orderId);
        return params;
    }
}
