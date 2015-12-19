package com.aglook.comapp.url;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/11/30.
 */
public class ZiXunUrl {
    private static RequestParams params;


    /**
     * 资讯列表
     * @param classId
     * @return
     */
    public static RequestParams postZiXunListUrl(String classId) {
        params = new RequestParams();
        params.addBodyParameter("classId", classId);
        return params;
    }
    /**
     * 价格走势
     * @param categoryId 种类id
     * @return
     */
    public static RequestParams postTrendUrl(String categoryId){
        params=new RequestParams();
        params.addBodyParameter("categoryId",categoryId);
        return params;
    }

    public static RequestParams postMessageUrl(String pageSize,String pageNum){
        params=new RequestParams();
        params.addBodyParameter("pageSize",pageSize);
        params.addBodyParameter("pageNum",pageNum);
        return params;
    }
}
