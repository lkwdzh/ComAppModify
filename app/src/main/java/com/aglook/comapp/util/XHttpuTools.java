package com.aglook.comapp.util;

import android.content.Context;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by aglook on 2015/10/26.
 */
public abstract class XHttpuTools {

    public void datePost(String url, RequestParams params, final Context context) {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.configCurrentHttpCacheExpiry(1000 * 10);

        httpUtils.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                initViews(objectResponseInfo);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                failureInitViews(e, s);
                AppUtils.toastText(context, "服务器请求失败");
            }
        });
    }

    public void datePost(String url, final Context context) {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.configCurrentHttpCacheExpiry(1000 * 10);

        httpUtils.send(HttpRequest.HttpMethod.POST, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                initViews(objectResponseInfo);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                failureInitViews(e, s);
                AppUtils.toastText(context, "服务器请求失败");
            }
        });
    }

    public void dateGet(String url, final Context context) {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.configCurrentHttpCacheExpiry(1000 * 10);
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                initViews(objectResponseInfo);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                failureInitViews(e, s);
                AppUtils.toastText(context, "服务器请求失败");
            }
        });
    }

    // 数据处理
    public abstract void initViews(ResponseInfo<String> arg0);

    public abstract void failureInitViews(HttpException arg0, String arg1);
}
