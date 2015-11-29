package com.aglook.comapp.url;

import com.aglook.comapp.util.DefineUtil;
import com.lidroid.xutils.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by aglook on 2015/11/20.
 */
public class CangDanUrl {
    private static RequestParams params;


    /**
     * 用户仓单列表
     * @param token
     * @param userId
     * @param pageSize 页码
     * @param pageNum 每页大小
     * @param _sort 排序规则
     * @return
     */
    public static RequestParams postCangDanUrl(String code,String token, String userId, String pageSize, String pageNum, String _sort) {
        String sign = null;
        RequestParams params1 = new RequestParams();
        params1.addBodyParameter("code", code);
        params1.addBodyParameter("version", DefineUtil.VERSON);
        params1.addBodyParameter("content", getMyCangDanUrl(token, userId, pageSize, pageNum, _sort).toString());
        params1.addBodyParameter("sign", sign);
        return params1;
    }




    /**
     * content内容拼接
     * @param token
     * @param userId
     * @param pageSize
     * @param pageNum
     * @param _sort
     * @return
     */
    public static JSONObject getMyCangDanUrl(String token, String userId, String pageSize, String pageNum, String _sort) {
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
        return jsonObject;
    }


    /**
     * 平台仓单详情
     * @param token
     * @param userId
     * @param orderdataId
     * @return
     */

    public static RequestParams postPlatCangDanDetailUrl(String code,String token, String userId, String orderdataId) {
        params=new RequestParams();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("userId", userId);
            jsonObject.put("orderdataId", orderdataId);
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
     * 仓单详情
     * @param code
     * @param token
     * @param userId
     * @return
     */
    public static RequestParams postCangDanDetailUrl(String code,String token, String userId, String originalId) {
        params=new RequestParams();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("userId", userId);
            jsonObject.put("originalId", originalId);
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
