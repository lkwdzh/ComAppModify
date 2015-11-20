package com.aglook.comapp.url;

import com.lidroid.xutils.http.RequestParams;

/**
 * Created by aglook on 2015/11/20.
 */
public class CangDanUrl {
    private static RequestParams params;

    /**
     * 用户仓单列表
     * @param token
     * @param userId
     * @param pageSize 页码
     * @param pageNum 每页大小
     * @param _sort 排序规则
     * @return
     */
    public static RequestParams postMyCangDanUrl(String token,String userId,String pageSize,String pageNum,String _sort){
        params=new RequestParams();
        params.addBodyParameter("token",token);
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("pageSize",pageSize);
        params.addBodyParameter("pageNum",pageNum);
        params.addBodyParameter("_sort",_sort);
        return params;
    }


    public static RequestParams postCangDanDetailUrl(String token,String userId,String originalid){
        params=new RequestParams();
        params.addBodyParameter("token",token);
        params.addBodyParameter("userId",userId);
        params.addBodyParameter("originalid",originalid);
        return params;
    }
}
