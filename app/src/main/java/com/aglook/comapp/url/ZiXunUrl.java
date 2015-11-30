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

}
