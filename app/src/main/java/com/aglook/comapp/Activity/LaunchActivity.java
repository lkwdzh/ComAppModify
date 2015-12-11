package com.aglook.comapp.Activity;

import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.aglook.comapp.R;

import cn.jpush.android.api.JPushInterface;

public class LaunchActivity extends BaseActivity {


    @Override
    public void initView() {
        setContentView(R.layout.activity_launch);
        init();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                startActivity(intent);
                LaunchActivity.this.finish();
            }
        },2000);
    }

    // 初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
    private void init(){
        JPushInterface.init(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    @Override
    public void widgetClick(View view) {

    }


}
