package com.aglook.comapp.url;

import android.util.Log;

import com.aglook.comapp.util.DefineUtil;
import com.google.gson.Gson;
import com.lidroid.xutils.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;

/**
 * Created by aglook on 2015/11/25.
 */
public class GuaDanUrl {
    private static RequestParams params;

    public static RequestParams postGuaDanAddUrl(String code,String token, String userId, String originalListid,
                                                 String tradeNum, String limitDate, String tradePrice,
                                                 String productName, String designatedUserid,String productText) {

        params=new RequestParams();
        String sign=null;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("userId", userId);
            jsonObject.put("originalListid", originalListid);
            jsonObject.put("tradeNum", tradeNum);
            jsonObject.put("limitDate", limitDate);
            jsonObject.put("tradePrice", tradePrice);
            jsonObject.put("productName", productName);
            jsonObject.put("designatedUserid", designatedUserid);
            jsonObject.put("productText",productText);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String content=jsonObject.toString();
        params.addBodyParameter("code", code);
        params.addBodyParameter("version", DefineUtil.VERSON);
        params.addBodyParameter("content", content);
        params.addBodyParameter("sign", sign);
        Log.d("result_json",new Gson().toJson(params));
        return params;
    }

    public static String  postGua(String code,String token, String userId, String originalListid,
                                                 String tradeNum, String limitDate, String tradePrice,
                                                 String productName, String designatedUserid) {

        String sign=null;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("userId", userId);
            jsonObject.put("originalListid", originalListid);
            jsonObject.put("tradeNum", tradeNum);
            jsonObject.put("limitDate", limitDate);
            jsonObject.put("tradePrice", tradePrice);
            jsonObject.put("productName", productName);
            jsonObject.put("designatedUserid", designatedUserid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String content=jsonObject.toString();
//        params.addBodyParameter("code", code);
//        params.addBodyParameter("version", DefineUtil.VERSON);
//        params.addBodyParameter("content", content);
//        params.addBodyParameter("sign", sign);
        String str=DefineUtil.CANG_DAN+"?&code=1003&version=1.0&sign=null&content="+ URLEncoder.encode(content);
//        Log.d("result_json",new Gson().toJson(params));
        Log.d("result_json",str);
        return str;
    }
}
