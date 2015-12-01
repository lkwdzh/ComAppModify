package com.aglook.comapp.url;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/11/13.
 */
public class ScreenUrl {
    private static RequestParams params;

    /**
     *搜索
     * @param userId
     * @param descFlag
     * @param clickNum
     * @param productMoney
     * @param productName
     * @return
     */
    public static RequestParams postSearchUrl(String  userId,String descFlag,String pageSize,String pageNumber,String clickNum,String productMoney,String productName){
        params=new RequestParams();
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("descFlag",descFlag);
        params.addBodyParameter("pageSize",pageSize);
        params.addBodyParameter("pageNumber",pageNumber);
        params.addBodyParameter("clickNum",clickNum);
        params.addBodyParameter("productMoney",productMoney);
        params.addBodyParameter("productName",productName);
        return params;
    }

    /**
     * 分类
     * @param userId
     * @param descFlag
     * @param pageSize
     * @param pageNumber
     * @param clickNum
     * @param productMoney
     * @param productName
     * @return
     */
    public static RequestParams postScreenUrl(String  userId,String categoryId,String descFlag,String pageSize,String pageNumber,String clickNum,String productMoney,String productName){
        params=new RequestParams();
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("categoryId",categoryId);
        params.addBodyParameter("descFlag",descFlag);
        params.addBodyParameter("pageSize",pageSize);
        params.addBodyParameter("pageNumber",pageNumber);
        params.addBodyParameter("clickNum",clickNum);
        params.addBodyParameter("productMoney",productMoney);
        params.addBodyParameter("productName",productName);
        return params;
    }
}
