package com.aglook.comapp.url;

import com.aglook.comapp.bean.DriverList;
import com.aglook.comapp.util.DefineUtil;
import com.lidroid.xutils.http.RequestParams;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.util.List;


/**
 * Created by aglook on 2015/11/28.
 */
public class PickInfoUrl {
    private static RequestParams params;

    public static RequestParams postPickInfoUrl(String code, String token, String userId, String originalListId, String orderdataId, String deliveryNum, List<DriverList> dirverList) {
        params = new RequestParams();
        JSONObject jsonObject = new JSONObject();
        try {
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject1 = null;
            if (dirverList != null && dirverList.size() != 0) {
                for (int i = 0; i < dirverList.size(); i++) {
                    jsonObject1 = new JSONObject();
//                    jsonObject1.put("id", "1");
                    jsonObject1.put("id", dirverList.get(i).getId());
                    if (dirverList.get(i).getWeight() != null) {
                        jsonObject1.put("getWeight", 0);
                    }
//                    jsonObject1.put("getWeight", Integer.parseInt(dirverList.get(i).getWeight()));
//                    jsonObject1.put("getWeight", 1);
                    jsonArray.add(jsonObject1);
                }
            }

            jsonObject.put("token", token);
            jsonObject.put("userId", userId);
            jsonObject.put("originalListId", originalListId);
            jsonObject.put("orderdataId", orderdataId);
            jsonObject.put("deliveryNum", deliveryNum);
            jsonObject.put("dirverList", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        Log.d("result_json", jsonObject.toString());
        String content = jsonObject.toString();
        String sign = null;
        params.addBodyParameter("code", code);
        params.addBodyParameter("version", DefineUtil.VERSON);
        params.addBodyParameter("content", content);
        params.addBodyParameter("sign", sign);
        return params;
    }
}
