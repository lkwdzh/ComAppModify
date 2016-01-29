package com.aglook.comapp.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.aglook.comapp.Fragment.IndexPageFragment;
import com.aglook.comapp.R;

public class IndexActivity extends FragmentActivity {

    private ViewPager view_index;
    public static IndexActivity instance;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
//        ExitApplication.getInstance().addActivity(this);
        instance = this;
        init();
        click();
    }


    public void init() {
        view_index = (ViewPager) findViewById(R.id.view_index);
        MyFramentPageAdapter myViewPagerAdapter = new MyFramentPageAdapter(getSupportFragmentManager());
        view_index.setAdapter(myViewPagerAdapter);
    }

    public void click() {

    }


    class MyFramentPageAdapter extends FragmentPagerAdapter {
        public MyFramentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return IndexPageFragment.myFragment(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
