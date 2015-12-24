package com.aglook.comapp.url;

import android.util.Log;

import com.google.gson.Gson;
import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/12/24.
 */
public class AddressUrl {
    private static RequestParams params;

    /**
     * 地址列表
     * @param userId 用户id
     * @param token 用户token
     * @param defaultFlag 1默认 0 非默认（非必须）,null所有
     * @return
     */
    public static RequestParams postAddressListUrl(String userId,String token,String defaultFlag){
        params=new RequestParams();
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("token",token);
        params.addBodyParameter("defaultFlag",defaultFlag);
        return params;
    }


    /**
     * 新增地址
     * @param userId 用户Id（必须）
     * @param token token（必须）
     * @param defaultFlag 是否默认 （1：默认 0 非默认）（必须）
     * @param userArea 地区（必须）
     * @param userAddress 地址（必须）
     * @param userPhone 手机号（必须）
     * @param userName 姓名（必须）
     * @return
     */
    public static RequestParams postAddAddressUrl(String userId,String token,String defaultFlag,String userArea,String  userAddress,String userPhone,String userName){
        params=new RequestParams();
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("token",token);
        params.addBodyParameter("defaultFlag",defaultFlag);
        params.addBodyParameter("userArea",userArea);
        params.addBodyParameter("userAddress",userAddress);
        params.addBodyParameter("userPhone",userPhone);
        params.addBodyParameter("userName",userName);

        return params;
    }

    /**
     * 删除
     * @param userId
     * @param token
     * @param id 地址id
     * @return
     */
    public static RequestParams postDeletaAddressUrl(String userId,String token,String id){
        params=new RequestParams();
        String userArea = null;
        String userAddress = null;
        String userPhone = null;
        String userName = null;
        String defaultFlag = null;
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("token",token);
        params.addBodyParameter("id",id);
        params.addBodyParameter("userArea",userArea);
        params.addBodyParameter("userAddress",userAddress);
        params.addBodyParameter("userPhone",userPhone);
        params.addBodyParameter("userName",userName);
        params.addBodyParameter("defaultFlag",defaultFlag);
        params.addBodyParameter("deleteFlag", "1");
        return params;
    }


    /**
     * 修改
     * @param userId 用户Id（必须）
     * @param token token（必须）
     * @param id id（必须）
     * @param userArea 地区（非必须）
     * @param userAddress 地址（非必须）
     * @param userPhone 手机号（非必须）
     * @param userName 姓名（非必须）
     * @param defaultFlag 是否默认 （1：默认 0 非默认）（非必须）
     * @return
     */
    public static RequestParams postUpdateAddressUlr(String userId,String token,String id,String userArea,
                                                     String  userAddress,String userPhone,String userName,String defaultFlag){
        params=new RequestParams();
        String deleteFlag = null;
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("token",token);
        params.addBodyParameter("id",id);
        params.addBodyParameter("userArea",userArea);
        params.addBodyParameter("userAddress",userAddress);
        params.addBodyParameter("userPhone",userPhone);
        params.addBodyParameter("userName",userName);
        params.addBodyParameter("defaultFlag",defaultFlag);
        params.addBodyParameter("deleteFlag",deleteFlag);
        Log.d("result_add_params",new Gson().toJson(params));
        return params;
    }

}
