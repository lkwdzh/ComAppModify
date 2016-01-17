package com.aglook.comapp.url;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/12/25.
 */
public class RegisterUrl {
    private static RequestParams params;

    /**
     * 获取验证码
     * @param tel 手机号
     * @return
     */
    public static RequestParams postGetCodeUrl(String tel){
        params=new RequestParams();
        params.addBodyParameter("tel",tel);
        return params;
    }


    /**
     * 注册
     * @param userName 用户名（必须）
     * @param userPw 密码（必须）
     * @param userPhone 手机号（必须）
     * @param authCode 手机验证码（必须）
     * @param userType 类型（必须） （1：公司 2 个人）（非必须）
     * @param userCompany 公司（为公司时必须）
     * @param userAddres 公司地址（为公司时必须）
     * @return
     */
    public static RequestParams postRegisterUrl(String userName,String userPw,String userPhone,String authCode,
                                                String userType,String userCompany,String userAddres,String userCode){
        params=new RequestParams();
        params.addBodyParameter("userName",userName);
        params.addBodyParameter("userPw",userPw);
        params.addBodyParameter("userPhone",userPhone);
        params.addBodyParameter("authCode",authCode);
        params.addBodyParameter("userType",userType);
        params.addBodyParameter("userCompany",userCompany);
        params.addBodyParameter("userAddres",userAddres);
        params.addBodyParameter("userCode",userCode);
//        Log.d("result_params", new Gson().toJson(params));
        return params;
    }
}
