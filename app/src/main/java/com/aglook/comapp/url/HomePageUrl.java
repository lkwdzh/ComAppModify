package com.aglook.comapp.url;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/11/13.
 */
public class HomePageUrl {
    private static RequestParams params;

    /**
     * 首页商品列表
     * @param userId
     * @return
     */
    public static RequestParams postHomePageCategoryUrl(String userId){
        params=new RequestParams();
        params.addBodyParameter("userId",userId);
        return params;
    }


}
