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
}
