package com.aglook.comapp.Activity;

import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.aglook.comapp.R;

public class LaunchActivity extends BaseActivity {


    @Override
    public void initView() {
        setContentView(R.layout.activity_launch);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LaunchActivity.this, MainActivity.class);
                startActivity(intent);
                LaunchActivity.this.finish();
            }
        },2000);
    }

    @Override
    public void widgetClick(View view) {

    }


}
