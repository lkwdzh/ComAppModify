package com.aglook.comapp.url;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/11/13.
 */
public class BasicInformationUrl {
    private static RequestParams params;


    public static RequestParams postBasicInfoUpdateUrl(String userId, String token, String userTName,
                                                       String userNumber, String userPhone, String userEmail) {
        params = new RequestParams();
        params.addBodyParameter("userId", userId);
        params.addBodyParameter("token", token);
        params.addBodyParameter("userTName", userTName);
        params.addBodyParameter("userNumber", userNumber);
        params.addBodyParameter("userPhone", userPhone);
        params.addBodyParameter("userEmail", userEmail);
        return params;
    }


    /**
     * @param userId       id
     * @param token        token
     * @param userTName    法人姓名
     * @param userNumber   法人身份证号
     * @param userPhone    经理手机号
     * @param userEmail    经理邮箱
     * @param userTel      经理固定电话
     * @param userJname    经理姓名
     * @param userInvoice  普通税票号
     * @param userInvoices 增值税票号
     * @return
     */
    public static RequestParams postCompanyInfoUpdateUrl(String userId, String token, String userTName,
                                                         String userNumber, String userPhone, String userEmail, String userTel,
                                                         String userJname, String userInvoice, String userInvoices,String userAddress ) {
        params = new RequestParams();
        params.addBodyParameter("userId", userId);
        params.addBodyParameter("token", token);
        params.addBodyParameter("userTName", userTName);
        params.addBodyParameter("userNumber", userNumber);
        params.addBodyParameter("userPhone", userPhone);
        params.addBodyParameter("userEmail", userEmail);
        params.addBodyParameter("userTel", userTel);
        params.addBodyParameter("userJname", userJname);
        params.addBodyParameter("userInvoice", userInvoice);
        params.addBodyParameter("userInvoices", userInvoices);
        params.addBodyParameter("userAddres ", userAddress );
        return params;
    }


    /**
     *
     * @param userId id
     * @param token token
     * @param userCaty 发票公司名称
     * @param userNnumb 纳税人识别号
     * @param userZcdz 注册公司地址
     * @param userTels 公司联系电话
     * @param userBanks 增值税发票开户行:
     * @param userBnumb 增值税发票银行账户
     * @return
     */
    public static RequestParams postUpDateBillInfoUrl(String userId, String token,String userCaty,String  userNnumb,String userZcdz,
                                                      String userTels,String userBanks,String userBnumb){
        params = new RequestParams();
        params.addBodyParameter("userId", userId);
        params.addBodyParameter("token", token);
        params.addBodyParameter("userCaty", userCaty);
        params.addBodyParameter("userNnumb", userNnumb);
        params.addBodyParameter("userZcdz", userZcdz);
        params.addBodyParameter("userTels", userTels);
        params.addBodyParameter("userBanks", userBanks);
        params.addBodyParameter("userBnumb", userBnumb);
        return params;
    }

}
