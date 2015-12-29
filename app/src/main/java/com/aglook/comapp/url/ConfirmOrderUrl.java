package com.aglook.comapp.url;

import android.util.Log;

import com.google.gson.Gson;
import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/11/18.
 */
public class ConfirmOrderUrl {
    private static RequestParams params;

    /**
     *
     * @param userId
     * @param token
     * @param cartIds 购物车id:cartIds(多个id)
     * @param money 商品总共价格：money
     * @param text
     * @param totalFee 总手续费：totalFee
     * @param addressId 用户地址
     * @param invoiceTitle 发票抬头
     * @param invoiceContent 发票内容
     * @return
     */
    public static RequestParams postConfirmOrderUrl(String userId,String token,String cartIds,String money,String text,String totalFee,
                                                    String addressId,String invoiceTitle,String invoiceContent){
        params=new RequestParams();
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("token",token);
        params.addBodyParameter("cartIds",cartIds);
        params.addBodyParameter("money",money);
        params.addBodyParameter("text",text);
        params.addBodyParameter("totalFee",totalFee);
        params.addBodyParameter("addressId",addressId);
        params.addBodyParameter("invoiceTitle",invoiceTitle);
        params.addBodyParameter("invoiceContent",invoiceContent);
        Log.d("result_confirmOrder", new Gson().toJson(params));
        return params;
    }
}
