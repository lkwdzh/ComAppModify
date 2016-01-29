package com.aglook.comapp.Activity;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aglook.comapp.R;

public class GuideActivity extends BaseActivity {


    private ViewPager vp_guide;
    private int imageArray[]={R.drawable.startup01,R.drawable.startup02,R.drawable.startup03,R.drawable.startup04};
    @Override
    public void initView() {
        setContentView(R.layout.activity_guide);
//        ExitApplication.getInstance().addActivity(this);
        vp_guide = (ViewPager) findViewById(R.id.vp_guide);
        MyViewPagerAdapter adapter = new MyViewPagerAdapter();
        vp_guide.setAdapter(adapter);
    }

    @Override
    public void widgetClick(View view) {

    }

    class MyViewPagerAdapter extends PagerAdapter{
        @Override
        public int getCount() {
            return imageArray.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView=new ImageView(GuideActivity.this);
            imageView.setImageResource(imageArray[position]);
            container.addView(imageView);
            return imageView;
        }
    }


}
