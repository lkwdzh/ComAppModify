package com.aglook.comapp.url;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/11/13.
 */
public class BasicInformationUrl {
    private static RequestParams params;

    //银行卡列表
    public static RequestParams postBasicInfoUpdateUrl(String userId, String token, String userTName,
                                                       String userNumber, String userPhone, String userEmail, String userTel, String userQq, String userMoney,
                                                       String userPoint, String userAllPoint, String username, String userAddress, String userSeat) {
        params = new RequestParams();
        params.addBodyParameter("userId", userId);
        params.addBodyParameter("token", token);
        params.addBodyParameter("userTName", userTName);
        params.addBodyParameter("userNumber", userNumber);
        params.addBodyParameter("userPhone", userPhone);
        params.addBodyParameter("userEmail", userEmail);
        params.addBodyParameter("userTel", userTel);
        params.addBodyParameter("userQq", userQq);
        params.addBodyParameter("userMoney", userMoney);
        params.addBodyParameter("userPoint", userPoint);
        params.addBodyParameter("userAllPoint", userAllPoint);
        params.addBodyParameter("username", username);
        params.addBodyParameter("userAddress", userAddress);
        params.addBodyParameter("userSeat", userSeat);
        return params;
    }

    //银行卡列表
//    public static RequestParams postBasicInfoUpdateUrl(String userId, String token, String userTName,
//                                                       String userNumber, String userPhone, String userEmail, String userTel, String userQq,
//                                                       String username) {
//        params = new RequestParams();
//        params.addBodyParameter("userId", userId);
//        params.addBodyParameter("token", token);
//        params.addBodyParameter("userTName", userTName);
//        params.addBodyParameter("userNumber", userNumber);
//        params.addBodyParameter("userPhone", userPhone);
//        params.addBodyParameter("userEmail", userEmail);
//        params.addBodyParameter("userTel", userTel);
//        params.addBodyParameter("userQq", userQq);
//        params.addBodyParameter("username", username);
//        return params;
//    }
}
