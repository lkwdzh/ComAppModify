package com.aglook.comapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.aglook.comapp.Activity.LoginActivity;
import com.aglook.comapp.Application.ComAppApplication;
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
    private ComAppApplication comAppApplication;

    public void datePost(String url, RequestParams params, final Activity context) {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.configCurrentHttpCacheExpiry(1000 * 10);

        httpUtils.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                comAppApplication = (ComAppApplication) context.getApplication();
                String status = JsonUtils.getJsonParam(objectResponseInfo.result, "status");
                String message = JsonUtils.getJsonParam(objectResponseInfo.result, "message");
                if (status != null) {
                    initViews(objectResponseInfo);
                    if (!"1".equals(status)) {
                        String error = JsonUtils.getJsonParam(objectResponseInfo.result, "errCode");
                        String errCode = JsonUtils.getJsonParam(error, "errCode");
                        if (errCode != null && !"".equals(errCode)) {

                            if (errCode.equals("U1008")) {
                                //token已过期,请重新获取!
                                comAppApplication.setLogin(null);
                                Intent intent = new Intent(context, LoginActivity.class);
                                context.startActivityForResult(intent, 33);
                                DefineUtil.TOKEN=null;
                                DefineUtil.USERID=null;
                                AppUtils.toastText(context, "账号登录异常，请重新登录");
                                SharedPreferencesUtils.saveBoolean(context,"auto_login",false);
                            } else if (errCode.equals("W1026")) {
                                AppUtils.toastText(context, "不能购买自己出售的商品");
                            } else {
                                AppUtils.toastText(context, message);
                            }
                        } else {
                            AppUtils.toastText(context, message);
                        }
                    }
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                failureInitViews(e, s);
                AppUtils.toastText(context, "网络繁忙，请重新操作");
            }
        });
    }

    public void datePostHomepage(String url, RequestParams params, final Activity context) {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.configCurrentHttpCacheExpiry(1000 * 10);
        httpUtils.configDefaultHttpCacheExpiry(100 * 1000);
//        httpUtils.configCurrRequestExpiry(1000 * 10);
        httpUtils.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                comAppApplication = (ComAppApplication) context.getApplication();
                String status = JsonUtils.getJsonParam(objectResponseInfo.result, "status");
                String message = JsonUtils.getJsonParam(objectResponseInfo.result, "message");
                if (status != null) {
                    initViews(objectResponseInfo);

                    if (!"1".equals(status)) {
                        String error = JsonUtils.getJsonParam(objectResponseInfo.result, "errCode");
                        String errCode = JsonUtils.getJsonParam(error, "errCode");
                        if (errCode != null && !"".equals(errCode)) {

                            if (errCode.equals("U1008")) {
                                //token已过期,请重新获取!
                                comAppApplication.setLogin(null);
                                Intent intent = new Intent(context, LoginActivity.class);
                                context.startActivityForResult(intent, 33);
                                AppUtils.toastText(context, "账号登录异常，请重新登录");
                            } else {
                                AppUtils.toastText(context, message);
                            }
                        } else {
                            AppUtils.toastText(context, message);
                        }
                    }
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                failureInitViews(e, s);
                AppUtils.toastText(context, "网络繁忙，请重新操作");
            }
        });
    }

    public void datePostUp(String url, RequestParams params, final Activity context) {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.configCurrentHttpCacheExpiry(1000 * 10);

        httpUtils.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                comAppApplication = (ComAppApplication) context.getApplication();
                String status = JsonUtils.getJsonParam(objectResponseInfo.result, "status");
                String message = JsonUtils.getJsonParam(objectResponseInfo.result, "message");
                if (status != null) {
                    initViews(objectResponseInfo);
                    if (!"1".equals(status)) {
                        String error = JsonUtils.getJsonParam(objectResponseInfo.result, "errCode");
                        String errCode = JsonUtils.getJsonParam(error, "errCode");
                        if (errCode != null && !"".equals(errCode)) {

                            if (errCode.equals("U1008")) {
                                //token已过期,请重新获取!
                                comAppApplication.setLogin(null);
                                Intent intent = new Intent(context, LoginActivity.class);
                                context.startActivity(intent);
                                AppUtils.toastText(context, "账号登录异常，请重新登录");
                            } else {
                                AppUtils.toastText(context, message);
                            }
                        } else {
                            AppUtils.toastText(context, message);
                        }
                    }

                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                failureInitViews(e, s);
                AppUtils.toastText(context, "网络繁忙，请重新操作");
            }
        });
    }

    public void datePostCheck(String url, RequestParams params, final Activity context) {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.configCurrentHttpCacheExpiry(1000 * 10);

        httpUtils.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                String status = JsonUtils.getJsonParam(objectResponseInfo.result, "status");
                if (status != null) {
                    initViews(objectResponseInfo);

                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                failureInitViews(e, s);
                AppUtils.toastText(context, "网络繁忙，请重新操作");
            }
        });
    }

    public void datePostCheck(String url, final Activity context) {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.configCurrentHttpCacheExpiry(1000 * 10);

        httpUtils.send(HttpRequest.HttpMethod.POST, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                String status = JsonUtils.getJsonParam(objectResponseInfo.result, "status");
                if (status != null) {
                    initViews(objectResponseInfo);

                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                failureInitViews(e, s);
                AppUtils.toastText(context, "网络繁忙，请重新操作");
            }
        });
    }

    public void datePost(String url, final Context context) {
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.configCurrentHttpCacheExpiry(1000 * 10);

        httpUtils.send(HttpRequest.HttpMethod.POST, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                String status = JsonUtils.getJsonParam(objectResponseInfo.result, "status");
                if (status != null) {
                    initViews(objectResponseInfo);

                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                failureInitViews(e, s);
                AppUtils.toastText(context, "网络繁忙，请重新操作");
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
                AppUtils.toastText(context, "网络繁忙，请重新操作");
            }
        });
    }

    // 数据处理
    public abstract void initViews(ResponseInfo<String> arg0);

    public abstract void failureInitViews(HttpException arg0, String arg1);
}
