package com.aglook.comapp.util;

import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aglook on 2015/10/26.
 */
public class JsonUtils {

    //解析class
    public static <T> T parse(String data,Class<T> tClass){
        if (TextUtils.isEmpty(data)){
            return null;
        }
        return new Gson().fromJson(data,tClass);
    }

    //解析List
    public static <T> List<T> parseList(String data,Class<T> tClass){
        if (TextUtils.isEmpty(data)){
            return null;
        }
        List mList=new ArrayList();
        try {
            JSONArray mArray = new JSONArray(data);
            int size=mArray.length();
            for (int i = 0; i <size ; i++) {
                Object t=parse(mArray.getJSONObject(i).toString(),tClass);
                mList.add(t);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mList;
    }

    //解析字符串
    public static String getJsonParam(String data,String dataName){
        try {
            JSONObject object = new JSONObject(data);
            String name = object.getString(dataName);
            return name;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
