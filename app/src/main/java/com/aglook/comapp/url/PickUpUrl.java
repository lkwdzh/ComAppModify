package com.aglook.comapp.url;

import com.aglook.comapp.util.DefineUtil;
import com.lidroid.xutils.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by aglook on 2015/11/28.
 */
public class PickUpUrl {
    private static RequestParams params;

    /**
     * 提货单
     * @param code
     * @param token
     * @param userId
     * @param pageSize
     * @param pageNum
     * @param _sort
     * @return
     */
    public static RequestParams postPickUpListUrl(String code,String token, String userId, String pageSize, String pageNum, String _sort){
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
        String  content=jsonObject.toString();
        String sign=null;
        params.addBodyParameter("code", code);
        params.addBodyParameter("version", DefineUtil.VERSON);
        params.addBodyParameter("content", content);
        params.addBodyParameter("sign", sign);
        return params;
    }

    /**
     * 取消提货单
     * @param code
     * @param token
     * @param userId
     * @param getId
     * @return
     */
    public static RequestParams postCancelUrl(String code,String token, String userId,String getId ){
        params=new RequestParams();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("userId", userId);
            jsonObject.put("getId", getId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String  content=jsonObject.toString();
        String sign=null;
        params.addBodyParameter("code", code);
        params.addBodyParameter("version", DefineUtil.VERSON);
        params.addBodyParameter("content", content);
        params.addBodyParameter("sign", sign);
        return params;
    }

    /**
     * 提货详情
     * @param code
     * @param token
     * @param userId
     * @param getId
     * @return
     */
    public static RequestParams postDetailUrl(String code,String token, String userId,String getId ){
        params=new RequestParams();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("userId", userId);
            jsonObject.put("getId", getId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String  content=jsonObject.toString();
        String sign=null;
        params.addBodyParameter("code", code);
        params.addBodyParameter("version", DefineUtil.VERSON);
        params.addBodyParameter("content", content);
        params.addBodyParameter("sign", sign);
        return params;
    }


}
