package com.aglook.comapp.url;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2016/1/18.
 */
public class FindPasswordUrl {
    private static RequestParams params;

    /**
     * 找回密码
     *
     * @param userPhone 手机号（必须）
     * @param userName  用户名（必须）
     * @param newPwd    新密码（必须）
     * @param authCode  手机验证码（必须）
     * @return
     */
    public static RequestParams postFindPasswordUrl(String userPhone, String userName,
                                                    String newPwd, String authCode) {
        params = new RequestParams();
        params.addBodyParameter("userPhone", userPhone);
        params.addBodyParameter("userName", userName);
        params.addBodyParameter("newPwd", newPwd);
        params.addBodyParameter("authCode", authCode);
        return params;
    }

    /**
     * 验证码
     *
     * @param userPhone 手机号
     * @return
     */
    public static RequestParams postFindPwdCodeUrl(String userPhone) {
        params = new RequestParams();
        params.addBodyParameter("userPhone", userPhone);
        return params;
    }
}
