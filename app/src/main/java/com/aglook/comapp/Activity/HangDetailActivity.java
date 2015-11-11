package com.aglook.comapp.Activity;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;

public class HangDetailActivity extends BaseActivity {


    private LinearLayout ll_top_hang_detail;
    private RelativeLayout rl_jiben;
    private RelativeLayout rl_jishu;
    private RelativeLayout rl_redian;
    private RelativeLayout rl_yuliu;
    private View view_jiben;
    private View view_jiben_red;
    private View view_jishu;
    private View view_jishu_red;
    private View view_redian;
    private View view_redian_red;
    private View view_yuliu;
    private View view_yuliu_red;
    private View viewArray[] = new View[4];
    private View view_redArray[] = new View[4];
    private WebView web_hangdetail;

    @Override
    public void initView() {
        setContentView(R.layout.activity_hang_detail);
        setTitleBar("专业分析");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init() {
        ll_top_hang_detail = (LinearLayout) findViewById(R.id.ll_top_hang_detail);
        rl_jiben = (RelativeLayout) findViewById(R.id.rl_jiben);
        rl_jishu = (RelativeLayout) findViewById(R.id.rl_jishu);
        rl_redian = (RelativeLayout) findViewById(R.id.rl_redian);
        rl_yuliu = (RelativeLayout) findViewById(R.id.rl_yuliu);
        view_jiben = (View) findViewById(R.id.view_jiben);
        view_jiben_red = (View) findViewById(R.id.view_jiben_red);
        view_jishu = (View) findViewById(R.id.view_jishu);
        view_jishu_red = (View) findViewById(R.id.view_jishu_red);
        view_redian = (View) findViewById(R.id.view_redian);
        view_redian_red = (View) findViewById(R.id.view_redian_red);
        view_yuliu = (View) findViewById(R.id.view_yuliu);
        view_yuliu_red = (View) findViewById(R.id.view_yuliu_red);
        web_hangdetail = (WebView) findViewById(R.id.web_hangdetail);

        web_hangdetail.getSettings().setJavaScriptEnabled(true);// 支持运行javascript

        web_hangdetail.setWebChromeClient(new WebChromeClient());// 支持运行特殊的javascript（例如：alert()）

        web_hangdetail.setWebViewClient(new WebViewClient());// 当点击超链地址后不会新打开浏览器来访问，而是始终在本app中浏览页面
        web_hangdetail.loadUrl("https://www.baidu.com/");

        viewArray[0] = view_jiben;
        viewArray[1] = view_jishu;
        viewArray[2] = view_redian;
        viewArray[3] = view_yuliu;
        view_redArray[0] = view_jiben_red;
        view_redArray[1] = view_jishu_red;
        view_redArray[2] = view_redian_red;
        view_redArray[3] = view_yuliu_red;


    }

    public void click() {
        rl_jiben.setOnClickListener(this);
        rl_jishu.setOnClickListener(this);
        rl_redian.setOnClickListener(this);
        rl_yuliu.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.rl_jiben:
                viewSelect(0);
                break;
            case R.id.rl_jishu:
                viewSelect(1);
                break;
            case R.id.rl_redian:
                viewSelect(2);
                break;
            case R.id.rl_yuliu:
                viewSelect(3);
                break;


        }
    }

    //    view选择
    public void viewSelect(int position) {
        for (int i = 0; i < 4; i++) {
            if (i == position) {
                view_redArray[i].setVisibility(View.VISIBLE);
                viewArray[i].setVisibility(View.GONE);
            } else {
                view_redArray[i].setVisibility(View.GONE);
                viewArray[i].setVisibility(View.VISIBLE);
            }
        }
    }

}
