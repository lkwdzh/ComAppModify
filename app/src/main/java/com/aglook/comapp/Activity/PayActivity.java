package com.aglook.comapp.Activity;

import android.net.Uri;
import android.net.http.SslError;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.url.PayUrl;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.view.CustomProgress;

public class PayActivity extends BaseActivity {


    private WebView webView;
    private String orderId;
    private String money;
    private ImageView left_icon;
    private CustomProgress customProgress;
    private Uri uri;
    private ComAppApplication comAppApplication;

    @Override
    public void initView() {
        setContentView(R.layout.activity_pay);
        setTitleBar("支付");
//        ExitApplication.getInstance().addActivity(this);
        comAppApplication = (ComAppApplication) getApplication();
        init();
        click();
    }

    public void init() {
        customProgress = CustomProgress.show(this, "", true);
        webView = (WebView) findViewById(R.id.webView);
        left_icon = (ImageView) findViewById(R.id.left_icon);
        orderId = getIntent().getStringExtra("orderId");
        money = getIntent().getStringExtra("money");
        webView.getSettings().setJavaScriptEnabled(true);// 支持运行javascript

        webView.setWebChromeClient(new WebChromeClient());// 支持运行特殊的javascript（例如：alert()）
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        if (comAppApplication.getLogin().getPshUser().getUserType() == 2) {
            //个人
            uri = Uri.parse(PayUrl.postPayPer(orderId, DefineUtil.USERID, money, money));
        } else if (comAppApplication.getLogin().getPshUser().getUserType() == 1){
            //企业
            uri = Uri.parse(PayUrl.postPay(orderId, DefineUtil.USERID, money, money));
        }
        // 设置可以支持缩放
        webView.getSettings().setSupportZoom(true);
// 设置出现缩放工具
        webView.getSettings().setBuiltInZoomControls(true);
//扩大比例的缩放
        webView.getSettings().setUseWideViewPort(true);
//自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.loadUrl(uri.toString());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
            }
        }, 2000);
    }

    public void click() {
        left_icon.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.left_icon:
                PayActivity.this.setResult(2);
                PayActivity.this.finish();
                break;
        }
    }

    //监听返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            PayActivity.this.setResult(2);
            PayActivity.this.finish();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }


}
