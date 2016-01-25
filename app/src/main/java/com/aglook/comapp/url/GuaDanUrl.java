package com.aglook.comapp.url;

import com.aglook.comapp.util.DefineUtil;
import com.lidroid.xutils.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;

/**
 * Created by aglook on 2015/11/25.
 */
public class GuaDanUrl {
    private static RequestParams params;

    /**
     * 我要挂单
     * @param code
     * @param token
     * @param userId
     * @param originalListid
     * @param tradeNum
     * @param limitDate
     * @param tradePrice
     * @param productName
     * @param designatedUserid
     * @param productText
     * @param actionFlag 是否匿名 0，不匿名，1，匿名
     * @return
     */
    public static RequestParams postGuaDanAddUrl(String code,String token, String userId, String originalListid,
                                                 String tradeNum, String limitDate, String tradePrice,
                                                 String productName, String designatedUserid,String productText,String actionFlag ) {

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
        params.addBodyParameter("actionFlag", actionFlag);
//        Log.d("result_json",new Gson().toJson(params));
        return params;
    }

    public static RequestParams postGuaDanPlateAddUrl(String code,String token, String userId, String originalListId,
                                                 String tradeNum, String limitDate, String tradePrice,
                                                 String productName, String designatedUserid,String productText,String orderdataId) {

        params=new RequestParams();
        String sign=null;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("userId", userId);
            jsonObject.put("originalListId", originalListId);
            jsonObject.put("tradeNum", tradeNum);
            jsonObject.put("limitDate", limitDate);
            jsonObject.put("tradePrice", tradePrice);
            jsonObject.put("productName", productName);
            jsonObject.put("designatedUserid", designatedUserid);
            jsonObject.put("productText",productText);
            jsonObject.put("orderdataId",orderdataId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String content=jsonObject.toString();
        params.addBodyParameter("code", code);
        params.addBodyParameter("version", DefineUtil.VERSON);
        params.addBodyParameter("content", content);
        params.addBodyParameter("sign", sign);
//        Log.d("result_json",new Gson().toJson(params));
        return params;
    }
    public static String  postGua(String code,String token, String userId, String originalListId,
                                                 String tradeNum, String limitDate, String tradePrice,
                                                 String productName, String designatedUserid) {

        String sign=null;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("userId", userId);
            jsonObject.put("originalListId", originalListId);
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
//        Log.d("result_json",str);
        return str;
    }


    /**
     * 挂单详情
     * @param code 4007
     * @param token 用户token
     * @param userId 用户id
     * @param orderdataId 平台仓单id
     * @param productId 商品id
     * @return
     */
    public static RequestParams postGuaDanDetailUrl(String code,String token, String userId, String orderdataId,
                                                    String productId){
        params=new RequestParams();
        String sign=null;
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("userId", userId);
            jsonObject.put("orderdataId", orderdataId);
            jsonObject.put("productId", productId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String content=jsonObject.toString();
        params.addBodyParameter("code", code);
        params.addBodyParameter("version", DefineUtil.VERSON);
        params.addBodyParameter("content", content);
        params.addBodyParameter("sign", sign);
        return params;
    }
}
