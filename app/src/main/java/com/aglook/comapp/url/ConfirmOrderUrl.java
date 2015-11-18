package com.aglook.comapp.url;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/11/18.
 */
public class ConfirmOrderUrl {
    private static RequestParams params;

    public static RequestParams postConfirmOrderUrl(String userId,String token,String cartIds,String money,String text){
        params=new RequestParams();
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("token",token);
        params.addBodyParameter("cartIds",cartIds);
        params.addBodyParameter("money",money);
        params.addBodyParameter("text",text);
        return params;
    }
}
