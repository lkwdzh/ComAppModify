package com.aglook.comapp.Activity;

import android.graphics.Bitmap;
import android.view.View;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;

import uk.co.senab.photoview.PhotoView;

public class ImageActivity extends BaseActivity {
    private int num;
    private PhotoView scrollview;
    private Bitmap bitmap;

    @Override
    public void initView() {
        setContentView(R.layout.activity_image);
        ExitApplication.getInstance().addActivity(this);
        num = getIntent().getIntExtra("num", 0);
        scrollview = (PhotoView) findViewById(R.id.scrollview);
        switch (num) {
            case 1:
                setTitleBar("登录-注册");
                scrollview.setImageResource(R.drawable.image_login);
//                bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.image_login);
                break;
            case 2:
                setTitleBar("购买");
                scrollview.setImageResource(R.drawable.image_buy);
//                bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.image_buy);
                break;
            case 3:
                setTitleBar("挂单");
                scrollview.setImageResource(R.drawable.image_guadan);
//                bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.image_guadan);
                break;
            case 4:
                setTitleBar("融资买货");
                scrollview.setImageResource(R.drawable.image_rongzi);
//                bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.image_rongzi);
                break;
            case 5:
                setTitleBar("提货");
                scrollview.setImageResource(R.drawable.image_pick);
//                bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.image_pick);
                break;
        }
//        scrollview.setImageBitmap(bitmap);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bitmap!=null&&!bitmap.isRecycled()){
            bitmap.recycle();
            System.gc();
        }
    }

    @Override
    public void widgetClick(View view) {

    }


}
