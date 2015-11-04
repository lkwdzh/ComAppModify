package com.aglook.comapp.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aglook.comapp.R;
import com.aglook.comapp.adapter.HomePageGridViewAdapter;
import com.aglook.comapp.adapter.RecycleHomePageAdapter;
import com.aglook.comapp.view.MyGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aglook on 2015/10/26.
 */
public class HomePageFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private List<String> list = new ArrayList<>();
    private RecycleHomePageAdapter adapter;
    private PullToRefreshGridView exlv;
    private ViewPager vp_home_page_head;
        private int imageArray[]={R.drawable.startup01,R.drawable.startup02,R.drawable.startup03,R.drawable.startup04};
//    private int imageArray[] = {R.drawable.startup01, R.drawable.startup02, R.drawable.startup03};
    private List<View> mViewPagerList;

    private List<ImageView> listImage = new ArrayList<>();
    private int index = 1;
    private static final int POINT_LENGTH = 4;
    private int mCurrentIndex;
    private int mCurrentPagePosition = FIRST_ITEM_INDEX;
    private static final int FIRST_ITEM_INDEX = 1;
    private boolean mIsChanged = false;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                vp_home_page_head.setCurrentItem(index % 3);
                handler.sendEmptyMessageDelayed(1, 2000);
                index++;
            }
            super.handleMessage(msg);
        }
    };
    private PullToRefreshScrollView sv_homepage;
    private RecyclerView my_recycler_view;
    private MyGridView gv_homepage;
    private HomePageGridViewAdapter gridViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.layout_homepage_fragment, null);
        initView(view);



        return view;
    }

    private void setCurrentDot(int position) {
        position = position - 1;
        if (position < 0 || position > imageArray.length - 1 || mCurrentIndex == position) {
            return;
        }
        mCurrentIndex = position;
    }


    //初始化控件
    public void initView(View view) {
        adapter = new RecycleHomePageAdapter( );
        mViewPagerList = new ArrayList<>();
        addImageView(POINT_LENGTH - 1);
        for (int i = 0; i < 4; i++) {
            addImageView(i);
        }
        addImageView(0);
        sv_homepage = (PullToRefreshScrollView) view.findViewById(R.id.sv_homepage);
//        rv_homepage = (RecyclerView) view.findViewById(R.id.rv_homepage);
        List<String>list2=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list2.add("大分类"+i);
        }



//        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getChildFragmentManager());
//        myViewPagerAdapter = new MyViewPagerAdapter();
//        vp_home_page_head.setAdapter(myViewPagerAdapter);
//        handler.sendEmptyMessageDelayed(1, 2000);
//        vp_home_page_head.setOnPageChangeListener(this);
//        vp_home_page_head.setCurrentItem(mCurrentPagePosition, false);
        sv_homepage.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        my_recycler_view=(RecyclerView)view.findViewById(R.id.my_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        my_recycler_view.setLayoutManager(layoutManager);
        my_recycler_view.setAdapter(adapter);

        gv_homepage = (MyGridView) view.findViewById(R.id.gv_homepage);
        gridViewAdapter = new HomePageGridViewAdapter(getActivity());
        gv_homepage.setAdapter(gridViewAdapter);

    }


    public void addImageView(int position) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(imageArray[position]);
        mViewPagerList.add(imageView);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mIsChanged = true;
        if (position > POINT_LENGTH) {
            mCurrentPagePosition = FIRST_ITEM_INDEX;
        } else if (position < FIRST_ITEM_INDEX) {
            mCurrentPagePosition = POINT_LENGTH;
        } else {
            mCurrentPagePosition = position;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (ViewPager.SCROLL_STATE_IDLE == state) {
            if (mIsChanged) {
                mIsChanged = false;
                vp_home_page_head.setCurrentItem(mCurrentPagePosition, false);
            }
        }
    }

    //ViewPager的适配器
//    class MyViewPagerAdapter extends FragmentPagerAdapter{
//
//        public MyViewPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return new HomePageViewPagerFragment().myFragment(position);
//        }
//
//        @Override
//        public int getCount() {
//            return 4;
//        }
//    }

    class MyViewPagerAdapter extends PagerAdapter {
        MyViewPagerAdapter() {
        }

        @Override
        public int getCount() {
            return mViewPagerList != null ? mViewPagerList.size() : 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        //销毁
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewPagerList.get(position));
        }

        //设置viewpager指定位置要显示的view
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewPagerList.get(position), 0);
            return mViewPagerList.get(position);
        }
    }
}
