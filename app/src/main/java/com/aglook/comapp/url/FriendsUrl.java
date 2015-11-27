package com.aglook.comapp.url;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/11/27.
 */
public class FriendsUrl {
    private static RequestParams params;

    /**
     * 联系人列表
     * @param userId
     * @param token
     * @return
     */
    public static RequestParams postFriendsListUrl(String userId,String token){
        params=new RequestParams();
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("token",token);
        return params;
    }

    /**
     * 添加联系人
     * @param userId
     * @param token
     * @param seatNo
     * @return
     */
    public static RequestParams postAddUrl(String userId,String token,String seatNo){
        params=new RequestParams();
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("token",token);
        params.addBodyParameter("seatNo",seatNo);
        return params;
    }

    public static RequestParams postDeleteUrl(String userId,String token,String contactId){
        params=new RequestParams();
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("token",token);
        params.addBodyParameter("contactId",contactId);
        return params;
    }


}
