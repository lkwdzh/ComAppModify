package com.aglook.comapp.url;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/11/13.
 */
public class CardListUrl {

    private static RequestParams params;

    //银行卡列表
    public static RequestParams postBankCardListUrl(String userId, String token) {
        params = new RequestParams();
        params.addBodyParameter("userId", userId);
        params.addBodyParameter("token", token);
        return params;
    }

    //    设置默认银行卡
    public static RequestParams postBindDefaultUrl(String userId, String token, String bankCardId) {
        params = new RequestParams();
        params.addBodyParameter("userId", userId);
        params.addBodyParameter("token", token);
        params.addBodyParameter("bankCardId", bankCardId);
        return params;
    }

    //    删除银行卡
    public static RequestParams postDeleteUrl(String userId, String token, String bankCardId) {
        params = new RequestParams();
        params.addBodyParameter("userId", userId);
        params.addBodyParameter("token", token);
        params.addBodyParameter("bankCardId", bankCardId);
        return params;
    }

    //绑定银行卡
    public static RequestParams postBandUrl(String userId, String token, String cardNo, String userName, String cardType,
                                            String bankCode, String bankAlis, String cardPhone) {
        params = new RequestParams();
        params.addBodyParameter("userId", userId);
        params.addBodyParameter("token", token);
        params.addBodyParameter("cardNo", cardNo);
        params.addBodyParameter("userName", userName);
        params.addBodyParameter("cardType", cardType);
        params.addBodyParameter("bankCode", bankCode);
        params.addBodyParameter("bankAlis", bankAlis);
        params.addBodyParameter("cardPhone", cardPhone);

        return params;
    }
}
