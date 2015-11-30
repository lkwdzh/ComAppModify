package com.aglook.comapp.url;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/11/30.
 */
public class DriverUrl {
    private static RequestParams params;

    /**
     * 司机列表
     *
     * @param token
     * @param userId
     * @return
     */
    public static RequestParams postDriverListUrl(String token, String userId) {
        params = new RequestParams();
        params.addBodyParameter("token", token);
        params.addBodyParameter("userId", userId);
        return params;
    }

    /**
     * 添加司机
     *
     * @param token
     * @param userId
     * @param driverName
     * @param driverTel
     * @param driverPhone
     * @param carCode
     * @param cardNo
     * @return
     */
    public static RequestParams postDriverAddUrl(String token, String userId, String driverName, String driverTel, String driverPhone, String carCode, String cardNo) {
        params = new RequestParams();
        params.addBodyParameter("token", token);
        params.addBodyParameter("userId", userId);
        params.addBodyParameter("driverName", driverName);
        params.addBodyParameter("driverTel",driverTel);
        params.addBodyParameter("driverPhone",driverPhone);
        params.addBodyParameter("carCode",carCode);
        params.addBodyParameter("cardNo",cardNo);
        return params;
    }
}
