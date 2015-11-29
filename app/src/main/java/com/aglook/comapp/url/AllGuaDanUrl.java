package com.aglook.comapp.url;

import com.aglook.comapp.util.DefineUtil;
import com.lidroid.xutils.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by aglook on 2015/11/28.
 */
public class AllGuaDanUrl {
    private static RequestParams params;

    /**
     * 挂单列表
     * @param code
     * @param token
     * @param userId
     * @param pageSize
     * @param pageNum
     * @param _sort
     * @return
     */
    public static RequestParams postGuaDanListUrl(String code,String token, String userId, String pageSize, String pageNum, String _sort) {
       params=new RequestParams();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("userId", userId);
            jsonObject.put("pageSize", pageSize);
            jsonObject.put("pageNum", pageNum);
            jsonObject.put("_sort", _sort);
        } catch (JSONException e) {
            e.printStackTrace();
        }
       String content=jsonObject.toString();
        String sign = null;
        params.addBodyParameter("code", code);
        params.addBodyParameter("version", DefineUtil.VERSON);
        params.addBodyParameter("content", content);
        params.addBodyParameter("sign", sign);
        return params;
    }


    /**
     * 交易记录
     * @param code
     * @param token
     * @param userId
     * @param pageSize
     * @param pageNum
     * @param _sort
     * @param productId
     * @param orderState
     * @return
     */
    public static RequestParams postLogUrl(String code,String token, String userId, String pageSize, String pageNum, String _sort,String productId,String orderState) {
        params=new RequestParams();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("userId", userId);
            jsonObject.put("pageSize", pageSize);
            jsonObject.put("pageNum", pageNum);
            jsonObject.put("_sort", _sort);
            jsonObject.put("productId", productId);
            jsonObject.put("orderState", orderState);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String content=jsonObject.toString();
        String sign = null;
        params.addBodyParameter("code", code);
        params.addBodyParameter("version", DefineUtil.VERSON);
        params.addBodyParameter("content", content);
        params.addBodyParameter("sign", sign);
        return params;
    }


    /**
     * 取消挂单
     * @param code
     * @param token
     * @param userId
     * @param productId
     * @return
     */
    public static RequestParams postDeleteUrl(String code,String token, String userId,String productId ) {
        params=new RequestParams();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("userId", userId);
            jsonObject.put("productId", productId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String content=jsonObject.toString();
        String sign = null;
        params.addBodyParameter("code", code);
        params.addBodyParameter("version", DefineUtil.VERSON);
        params.addBodyParameter("content", content);
        params.addBodyParameter("sign", sign);
        return params;
    }

    /**
     * 交易中与交易成功
     * @param code
     * @param token
     * @param userId
     * @param pageSize
     * @param pageNum
     * @param _sort
     * @param orderState
     * @return
     */
    public static RequestParams postTranUrl(String code,String token, String userId, String pageSize, String pageNum, String _sort,String orderState) {
        params=new RequestParams();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("userId", userId);
            jsonObject.put("pageSize", pageSize);
            jsonObject.put("pageNum", pageNum);
            jsonObject.put("_sort", _sort);
            jsonObject.put("orderState", orderState);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String content=jsonObject.toString();
        String sign = null;
        params.addBodyParameter("code", code);
        params.addBodyParameter("version", DefineUtil.VERSON);
        params.addBodyParameter("content", content);
        params.addBodyParameter("sign", sign);
        return params;
    }



    public static RequestParams postModifyUrl(String code,String token, String userId, String productName, String productMoney, String validTime,String productId,String productDesc) {
        params=new RequestParams();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("userId", userId);
            jsonObject.put("productName", productName);
            jsonObject.put("productMoney", productMoney);
            jsonObject.put("validTime", validTime);
            jsonObject.put("productId", productId);
            jsonObject.put("productDesc", productDesc);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String content=jsonObject.toString();
        String sign = null;
        params.addBodyParameter("code", code);
        params.addBodyParameter("version", DefineUtil.VERSON);
        params.addBodyParameter("content", content);
        params.addBodyParameter("sign", sign);
        return params;
    }

}
