package com.aglook.comapp.url;

import com.aglook.comapp.util.DefineUtil;
import com.lidroid.xutils.http.RequestParams;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;


/**
 * Created by aglook on 2015/11/28.
 */
public class PickInfoUrl {
    private static RequestParams params;

    public static RequestParams postPickInfoUrl(String code,String token,String userId, String originalListId, String orderdataId, String deliveryNum){
        params=new RequestParams();
        JSONObject jsonObject = new JSONObject();
        try {
           JSONObject jsonObject1 = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonObject1.put("id","1");
            jsonObject1.put("getWeight",1);
            jsonArray.add(jsonObject1);


            jsonObject.put("token", token);
            jsonObject.put("userId", userId);
            jsonObject.put("originalListId", originalListId);
            jsonObject.put("orderdataId", orderdataId);
            jsonObject.put("deliveryNum", deliveryNum);
            jsonObject.put("dirverList", jsonArray);
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
