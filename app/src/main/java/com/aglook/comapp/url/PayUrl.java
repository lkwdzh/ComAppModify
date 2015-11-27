package com.aglook.comapp.url;

import android.util.Base64;
import android.util.Log;

import com.aglook.comapp.encrypt.DESUtil;
import com.aglook.comapp.encrypt.MD5;
import com.aglook.comapp.util.DefineUtil;
import com.lidroid.xutils.http.RequestParams;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aglook on 2015/11/25.
 */
public class PayUrl {
    private static RequestParams params;

    public static RequestParams postPayWGUrl(String orderId, String userId, String amount, String money) {
        params = new RequestParams();
        JSONObject jsonObject = new JSONObject();
        JSONObject object = new JSONObject();
        try {
            object.put("payType","online");
            object.put("money",money);
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
            content=content.replaceAll(" ","");
        } catch (Exception e) {
            e.printStackTrace();
        }


//        List<String> sortList = new ArrayList<>();
//        sortList.add("code");
//        sortList.add("content");
//        sortList.add("merchantNo");
//        sortList.add("serviceScope");
//        sortList.add("version");
//        sortList.add("signType");
//        Collections.sort(sortList);
//
//        Map<String, String> param = new HashMap<>();
//        param.put("code", "2001");
//        param.put("content", content);
//        param.put("merchantNo", DefineUtil.MERCHANTNO);
//        param.put("serviceScope", DefineUtil.SERVICESCOPE);
//        param.put("signType", DefineUtil.SIGNTYPE);
//        param.put("version", "DefineUtil.VERSON");


//        String sign = MD5.buildSignByString(param, "xxxxxxxx", null);

     String url = "code=" + "2001" + "&content=" +Base64.encodeToString(content.getBytes(), Base64.DEFAULT) + "&merchantNo=" + DefineUtil.MERCHANTNO + "&serviceScope=" +
                DefineUtil.SERVICESCOPE + "&signType=" + DefineUtil.SIGNTYPE + "&version=" + DefineUtil.VERSON;
        url=url.replaceAll(" ","");
        String ss = MD5.ss(url, "xxxxxxxx");
//        String sign = MD5.sign(url, "xxxxxxxx", null);
        params.addBodyParameter("sign",ss);
        params.addBodyParameter("code","2001");
        params.addBodyParameter("merchantNo",DefineUtil.MERCHANTNO);
        params.addBodyParameter("serviceScope",DefineUtil.SERVICESCOPE);
        params.addBodyParameter("signType",DefineUtil.SIGNTYPE);
        params.addBodyParameter("version",DefineUtil.VERSON);
        params.addBodyParameter("content",Base64.encodeToString(content.getBytes(), Base64.DEFAULT));

        Log.d("result_DEs", url);
        Log.d("result_sign", Base64.encodeToString(content.getBytes(), Base64.DEFAULT));
        String uu=DefineUtil.PAY+"?sign="+ss+"&code=2001&merchantNo="+DefineUtil.MERCHANTNO+"&serviceScope="
                +DefineUtil.SERVICESCOPE+"&signType="+DefineUtil.SIGNTYPE+"&version="+DefineUtil.VERSON+"&content="
                +Base64.encodeToString(content.getBytes(), Base64.DEFAULT);
        Log.d("result_uu",uu);
//        Map<String ,String >map=new HashMap<>();
//        map.put("sign",ss);
//        map.put("code", "2001");
//        map.put("merchantNo", DefineUtil.MERCHANTNO);
//        map.put("serviceScope", DefineUtil.SERVICESCOPE);
//        map.put("signType", DefineUtil.SIGNTYPE);
//        map.put("version", DefineUtil.VERSON);
//        map.put("content", Base64.encodeToString(content.getBytes(), Base64.DEFAULT));
//        List<NameValuePair>nvps=new ArrayList<>();
//        Set<String >keySet=map.keySet();
//        for (String key:keySet){
//            nvps.add(new BasicNameValuePair(key,map.get(key)));
//        }
//
//        Log.d("nvps",nvps.toString());
        Log.d("result_Base64",Base64.encodeToString(content.getBytes(), Base64.DEFAULT));
        return params;
    }

    public static String  postPay(String orderId, String userId, String amount, String money) {
        params = new RequestParams();
        JSONObject jsonObject = new JSONObject();
        JSONObject object = new JSONObject();
        try {
            object.put("payType","online");
            object.put("money",money);
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


        List<String> sortList = new ArrayList<>();
        sortList.add("code");
        sortList.add("content");
        sortList.add("merchantNo");
        sortList.add("serviceScope");
        sortList.add("version");
        sortList.add("signType");
        Collections.sort(sortList);

        Map<String, String> param = new HashMap<>();
        param.put("code", "2001");
        param.put("content", content);
        param.put("merchantNo", DefineUtil.MERCHANTNO);
        param.put("serviceScope", DefineUtil.SERVICESCOPE);
        param.put("signType", DefineUtil.SIGNTYPE);
        param.put("version", "DefineUtil.VERSON");


//        String sign = MD5.buildSignByString(param, "xxxxxxxx", null);


        String url = "code=" + "2001" + "&content=" +Base64.encodeToString(content.getBytes(), Base64.DEFAULT) + "&merchantNo=" + DefineUtil.MERCHANTNO + "&serviceScope=" +
                DefineUtil.SERVICESCOPE + "&signType=" + DefineUtil.SIGNTYPE + "&version=" + DefineUtil.VERSON;
        url=url.replaceAll("\\n","");
        String ss = MD5.ss(url, "xxxxxxxx");

        String uu=DefineUtil.PAY+"?sign="+ss+"&code=2001&merchantNo="+DefineUtil.MERCHANTNO+"&serviceScope="
                +DefineUtil.SERVICESCOPE+"&signType="+DefineUtil.SIGNTYPE+"&version="+DefineUtil.VERSON+"&content="
                +Base64.encodeToString(content.getBytes(), Base64.DEFAULT);
        uu=uu.replaceAll("\\n","");
        Log.d("result_uu",uu);


        return uu;
    }
}
