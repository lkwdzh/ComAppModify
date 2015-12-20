package com.aglook.comapp.Activity;

import android.os.Handler;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.view.CustomProgress;

public class HangDetailActivity extends BaseActivity {


    private WebView web_hangdetail;
    private String url;
    private String className;

    private CustomProgress customProgress;
    @Override
    public void initView() {
        setContentView(R.layout.activity_hang_detail);
        className=getIntent().getStringExtra("className");
        setTitleBar(className);
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init() {
        customProgress = CustomProgress.show(this, "", true);
        url=getIntent().getStringExtra("url");
        web_hangdetail = (WebView) findViewById(R.id.web_hangdetail);

        web_hangdetail.getSettings().setJavaScriptEnabled(true);// 支持运行javascript

        web_hangdetail.setWebChromeClient(new WebChromeClient());// 支持运行特殊的javascript（例如：alert()）

        web_hangdetail.setWebViewClient(new WebViewClient());// 当点击超链地址后不会新打开浏览器来访问，而是始终在本app中浏览页面
        web_hangdetail.loadUrl(url);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        },2000);

    }

    public void click() {
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {

        }
    }

}
