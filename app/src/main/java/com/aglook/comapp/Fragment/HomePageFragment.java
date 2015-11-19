package com.aglook.comapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.aglook.comapp.Activity.GoodsDetailActivity;
import com.aglook.comapp.Activity.SearchActivity;
import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.HomePageGridViewAdapter;
import com.aglook.comapp.adapter.RecycleHomePageAdapter;
import com.aglook.comapp.bean.HomePage;
import com.aglook.comapp.url.HomePageUrl;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.aglook.comapp.view.MyGridView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aglook on 2015/10/26.
 */
public class HomePageFragment extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private List<String> list = new ArrayList<>();
    private RecycleHomePageAdapter adapter;
    private ViewPager vp_home_page_head;
    private int imageArray[] = {R.drawable.startup01, R.drawable.startup02, R.drawable.startup03, R.drawable.startup04};
    //    private int imageArray[] = {R.drawable.startup01, R.drawable.startup02, R.drawable.startup03};
    private List<View> mViewPagerList;

    private List<ImageView> listImage = new ArrayList<>();
    private int index = 1;
    private static final int POINT_LENGTH = 4;
    private int mCurrentIndex;
    private int mCurrentPagePosition = FIRST_ITEM_INDEX;
    private static final int FIRST_ITEM_INDEX = 1;
    private boolean mIsChanged = false;
    private CustomProgress customProgress;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
//                viewpager自动切换
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
    private RelativeLayout rl_search_homepage_fragment;

    private ComAppApplication comAppApplication;

    private String userId;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.layout_homepage_fragment, null);
        comAppApplication = (ComAppApplication) getActivity().getApplication();
        initView(view);
        click();
        getData();
        fillData();
        return view;
    }

    //轮播图下面的小点
    private void setCurrentDot(int position) {
        position = position - 1;
        if (position < 0 || position > imageArray.length - 1 || mCurrentIndex == position) {
            return;
        }
        mCurrentIndex = position;
    }


    //初始化控件
    public void initView(View view) {
        mList = new ArrayList<>();
        adapter = new RecycleHomePageAdapter(getActivity());
        mViewPagerList = new ArrayList<>();
        addImageView(POINT_LENGTH - 1);
        for (int i = 0; i < 4; i++) {
            addImageView(i);
        }
        addImageView(0);
        sv_homepage = (PullToRefreshScrollView) view.findViewById(R.id.sv_homepage);
//        令scrollview显示顶部
        sv_homepage.getRefreshableView().smoothScrollBy(0, 0);
//        rv_homepage = (RecyclerView) view.findViewById(R.id.rv_homepage);

        customProgress = CustomProgress.show(getActivity(), "加载中···", true);
//        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getChildFragmentManager());
//        myViewPagerAdapter = new MyViewPagerAdapter();
//        vp_home_page_head.setAdapter(myViewPagerAdapter);
//        handler.sendEmptyMessageDelayed(1, 2000);
//        vp_home_page_head.setOnPageChangeListener(this);
//        vp_home_page_head.setCurrentItem(mCurrentPagePosition, false);
        sv_homepage.setMode(PullToRefreshBase.Mode.PULL_FROM_END);

        my_recycler_view = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        my_recycler_view.setLayoutManager(layoutManager);
        my_recycler_view.setAdapter(adapter);

        gv_homepage = (MyGridView) view.findViewById(R.id.gv_homepage);
        gridViewAdapter = new HomePageGridViewAdapter(getActivity(), mList);
        gv_homepage.setAdapter(gridViewAdapter);
        rl_search_homepage_fragment = (RelativeLayout) view.findViewById(R.id.rl_search_homepage_fragment);

    }

    public void click() {
        rl_search_homepage_fragment.setOnClickListener(this);
        gv_homepage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
                intent.putExtra("productId", mList.get(position).getProductId());
                intent.putExtra("pointUser",mList.get(position).getIsAppoint());
                startActivity(intent);
            }
        });
        sv_homepage.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                getData();
            }
        });
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

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.rl_search_homepage_fragment:
                intent.setClass(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
        }
    }


    //ViewPager自定义适配器
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

    private List<HomePage> mList;

    //    获取商品列表
    public void getData() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("result_HomePage", arg0.result);
//                未指定买家
                List<HomePage> list = new ArrayList<HomePage>();
//                指定买家
                List<HomePage> listAppoint = new ArrayList<HomePage>();
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                if (obj!=null&&!"".equals(obj)) {
                    String pointProduct = JsonUtils.getJsonParam(obj, "pointProduct");
                    String ProductList = JsonUtils.getJsonParam(obj, "ProductList");
                    list = JsonUtils.parseList(ProductList, HomePage.class);
                    listAppoint = JsonUtils.parseList(pointProduct, HomePage.class);
                    if (status.equals("1")) {
                        //假如成功,给每个实体加上标识，并且将指定的list放在首位

                        //指定买家
                        if (listAppoint != null && listAppoint.size() != 0) {
                            for (int i = 0; i < listAppoint.size(); i++) {
                                listAppoint.get(i).setIsAppoint("1");
                            }
                            mList.addAll(listAppoint);
                        }

                        //未指定买家
                        if (list != null && list.size() != 0) {
                            for (int i = 0; i < list.size(); i++) {
                                list.get(i).setIsAppoint("0");
                            }
                            mList.addAll(list);
                        }
                    }
                }
                gridViewAdapter.notifyDataSetChanged();
                sv_homepage.onRefreshComplete();
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.HOT_LIST, HomePageUrl.postHomePageCategoryUrl(userId), getActivity());
    }

    public void fillData() {

    }
}
