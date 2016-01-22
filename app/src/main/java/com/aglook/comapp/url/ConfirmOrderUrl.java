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
     * 提交订单
     * @param userId 用户ID
     * @param token token
     * @param cartIds   购物车ID
     * @param money 商品总价格
     * @param totalFee  总手续费
     * @param orderText 买家留言
     * @param addressId 收货地址ID
     * @param fType 发票了类型:fType (0普通 ：1增值税)
     * @param userCaty  发票公司名称
     * @param userRise  发票抬头
     * @param userNnumb 纳税人识别号
     * @param userZcdz  注册公司地址
     * @param userTels  公司联系电话
     * @param userBanks 增值税发票开户行:
     * @param userBnumb 增值税发票银行账户
     * @return
     */
    public static RequestParams postConfirmOrderUrl(String userId,String token,String cartIds,String money,String totalFee,String orderText,
                                                    String addressId,String fType,String userCaty,String userRise,String userNnumb,
                                                    String userZcdz,String userTels,String userBanks,String userBnumb){
        params=new RequestParams();
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("token",token);
        params.addBodyParameter("cartIds",cartIds);
        params.addBodyParameter("money",money);
        params.addBodyParameter("totalFee",totalFee);
        params.addBodyParameter("orderText",orderText);
        params.addBodyParameter("addressId",addressId);
        params.addBodyParameter("fType",fType);
        params.addBodyParameter("userCaty",userCaty);
        params.addBodyParameter("userRise",userRise);
        params.addBodyParameter("userNnumb",userNnumb);
        params.addBodyParameter("userZcdz",userZcdz);
        params.addBodyParameter("userTels",userTels);
        params.addBodyParameter("userBanks",userBanks);
        params.addBodyParameter("userBnumb",userBnumb);
        Log.d("result_confirmOrder", new Gson().toJson(params));
        return params;
    }
}
