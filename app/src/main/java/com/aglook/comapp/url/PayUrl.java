package com.aglook.comapp.url;

import android.util.Base64;

import com.aglook.comapp.encrypt.DESUtil;
import com.aglook.comapp.encrypt.MD5;
import com.aglook.comapp.util.DefineUtil;
import com.lidroid.xutils.http.RequestParams;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * Created by aglook on 2015/11/25.
 */
public class PayUrl {
    private static RequestParams params;

    /**
     * 企业
     *
     * @param orderId
     * @param userId
     * @param amount
     * @param money
     * @return
     */
    public static String postPay(String orderId, String userId, String amount, String money) {
        params = new RequestParams();
        JSONObject jsonObject = new JSONObject();
        JSONObject object = new JSONObject();
        try {
            object.put("payType", "online");
            object.put("money", money);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(object);
            jsonObject.put("orderId", orderId);
            jsonObject.put("userId", userId);
            jsonObject.put("amount", amount);
            jsonObject.put("payList", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String content = null;
        try {
            content = DESUtil.encode("12345678", jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = "code=" + "2001" + "&content=" + Base64.encodeToString(content.getBytes(), Base64.DEFAULT) + "&merchantNo=" + DefineUtil.MERCHANTNO + "&serviceScope=" +
                DefineUtil.SERVICESCOPE + "&signType=" + DefineUtil.SIGNTYPE + "&version=" + DefineUtil.VERSON;
        url = url.replaceAll("\\n", "");
        String ss = MD5.ss(url, "xxxxxxxx");

        String uu = DefineUtil.PAY + "?sign=" + ss + "&code=2001&merchantNo=" + DefineUtil.MERCHANTNO + "&serviceScope="
                + DefineUtil.SERVICESCOPE + "&signType=" + DefineUtil.SIGNTYPE + "&version=" + DefineUtil.VERSON + "&content="
                + Base64.encodeToString(content.getBytes(), Base64.DEFAULT);
        uu = uu.replaceAll("\\n", "");
//        Log.d("result_uu——1",uu);


        return uu;
    }

    /**
     * 个人支付
     *
     * @param orderId
     * @param userId
     * @param amount
     * @param money
     * @return
     */
    public static String postPayPer(String orderId, String userId, String amount, String money) {
        params = new RequestParams();
        JSONObject jsonObject = new JSONObject();
        JSONObject object = new JSONObject();
        try {
            object.put("payType", "online");
            object.put("money", money);
            JSONArray jsonArray = new JSONArray();
            jsonArray.add(object);
            jsonObject.put("orderId", orderId);
            jsonObject.put("userId", userId);
            jsonObject.put("amount", amount);
            jsonObject.put("payList", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String content = null;
        try {
            content = DESUtil.encode("12345678", jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = "code=" + "1003" + "&content=" + Base64.encodeToString(content.getBytes(), Base64.DEFAULT) + "&merchantNo=" + DefineUtil.MERCHANTNO + "&serviceScope=" +
                DefineUtil.SERVICESCOPEPER + "&signType=" + DefineUtil.SIGNTYPE + "&version=" + DefineUtil.VERSON;
        url = url.replaceAll("\\n", "");
        String ss = MD5.ss(url, "xxxxxxxx");

        String uu = DefineUtil.PAY + "?sign=" + ss + "&code=1003&merchantNo=" + DefineUtil.MERCHANTNO + "&serviceScope="
                + DefineUtil.SERVICESCOPEPER + "&signType=" + DefineUtil.SIGNTYPE + "&version=" + DefineUtil.VERSON + "&content="
                + Base64.encodeToString(content.getBytes(), Base64.DEFAULT);
        uu = uu.replaceAll("\\n", "");
//        Log.d("result_uu——2",uu);


        return uu;
    }
}
