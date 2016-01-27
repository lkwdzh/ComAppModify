package com.aglook.comapp.Activity;

import android.os.Handler;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.aglook.comapp.R;
import com.aglook.comapp.view.CustomProgress;

public class RegisterAgreementActivity extends BaseActivity {
    private WebView web_hangdetail;
    private String url;
    private String className;

    private CustomProgress customProgress;

    @Override
    public void initView() {
        setContentView(R.layout.activity_register_agreement);
        className = getIntent().getStringExtra("className");
        setTitleBar(className);
        customProgress = CustomProgress.show(this, "", true);
        url = getIntent().getStringExtra("url");
        web_hangdetail = (WebView) findViewById(R.id.web_hangdetail);

        web_hangdetail.getSettings().setJavaScriptEnabled(true);// 支持运行javascript

        web_hangdetail.setWebChromeClient(new WebChromeClient());// 支持运行特殊的javascript（例如：alert()）

        web_hangdetail.setWebViewClient(new WebViewClient());// 当点击超链地址后不会新打开浏览器来访问，而是始终在本app中浏览页面
//web_hangdetail.setWebViewClient(new MyWebViewClient());
        // 设置可以支持缩放
        web_hangdetail.getSettings().setSupportZoom(true);
// 设置出现缩放工具
        web_hangdetail.getSettings().setBuiltInZoomControls(true);
//        web_hangdetail.getSettings().setBlockNetworkImage(false);
//扩大比例的缩放
        web_hangdetail.getSettings().setUseWideViewPort(true);
//自适应屏幕
        web_hangdetail.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        web_hangdetail.getSettings().setLoadWithOverviewMode(true);
//        web_hangdetail.getSettings().setDefaultFixedFontSize(100);
//        web_hangdetail.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
        web_hangdetail.loadUrl(url);
//        Log.d("result_url",url);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }, 2000);
    }

    @Override
    public void widgetClick(View view) {

    }


}
