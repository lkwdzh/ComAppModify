package com.aglook.comapp.url;

import android.util.Log;

import com.aglook.comapp.util.DefineUtil;
import com.google.gson.Gson;
import com.lidroid.xutils.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * Created by aglook on 2015/11/20.
 */
public class CangDanUrl {
    private static RequestParams params;

    /**
     * 用户仓单列表
     *
     * @param token
     * @param userId
     * @param pageSize 页码
     * @param pageNum  每页大小
     * @param _sort    排序规则
     * @return
     */
    public static RequestParams postMyCangDanUrl(String token, String userId, String pageSize, String pageNum, String _sort) {
        params = new RequestParams();
        params.addBodyParameter("token", token);
        params.addBodyParameter("userId", userId);
        params.addBodyParameter("pageSize", pageSize);
        params.addBodyParameter("pageNum", pageNum);
        params.addBodyParameter("_sort", _sort);
        return params;
    }

    public static RequestParams postCangDanUrl(String token, String userId, String pageSize, String pageNum, String _sort) {
        params = postMyCangDanUrl(token, userId, pageSize, pageNum, _sort);
        String sign = null;
        RequestParams params1 = new RequestParams();
        params1.addBodyParameter("code", "1001");
        params1.addBodyParameter("version", "1.0");
        params1.addBodyParameter("content", getMyCangDanUrl(token, userId, pageSize, pageNum, _sort).toString());
        params1.addBodyParameter("sign", sign);
        return params1;
    }


    public static JSONObject getMyCangDanUrl(String token, String userId, String pageSize, String pageNum, String _sort) {
        params = new RequestParams();
        params.addBodyParameter("token", token);
        params.addBodyParameter("userId", userId);
        params.addBodyParameter("pageSize", pageSize);
        params.addBodyParameter("pageNum", pageNum);
        params.addBodyParameter("_sort", _sort);
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

    public static String getCangDanUrl(String token, String userId, String pageSize, String pageNum, String _sort) {
//        params = gettMyCangDanUrl(token, userId, pageSize, pageNum, _sort);
        Gson gson = new Gson();
        Log.d("json", gson.toJson(params));
        String sign = null;
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("version","1.0");
//            jsonObject.put("code","1001");
//            jsonObject.put("content",getMyCangDanUrl(token, userId, pageSize, pageNum, _sort));
//            jsonObject.put("sign",sign);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        Log.d("json11", gson.toJson(params1));
        String str = null;
            str = DefineUtil.CANG_DAN + "?&" + "version=1.0&code=1001&sign="+null+"&content=" + URLEncoder.encode(getMyCangDanUrl(token, userId, pageSize, pageNum, _sort).toString());
        Log.d("1111111", str);
        String encode = null;
        try {
            encode = URLEncoder.encode("UTF-8", str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }


    public static RequestParams postCangDanDetailUrl(String token, String userId, String originalid) {
        params = new RequestParams();
        params.addBodyParameter("token", token);
        params.addBodyParameter("userId", userId);
        params.addBodyParameter("originalid", originalid);
        return params;
    }
}
