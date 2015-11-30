package com.aglook.comapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.aglook.comapp.Activity.SearchActivity;
import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.HomePageEXGridView;
import com.aglook.comapp.adapter.RecycleHomePageAdapter;
import com.aglook.comapp.bean.HomePage;
import com.aglook.comapp.bean.HomePageScroll;
import com.aglook.comapp.bean.Information;
import com.aglook.comapp.url.HomePageUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.aglook.comapp.view.MyExpandableListView;
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
    private List<Information>inList;
    private List<String> list = new ArrayList<>();
    private RecycleHomePageAdapter adapter;
    private ViewPager vp_home_page_head;
    private int index = 0;
    //    private static final int POINT_LENGTH = 4;
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
                vp_home_page_head.setCurrentItem(index % scrollList.size());
                handler.sendEmptyMessageDelayed(1, 3000);
                index++;
            }
            super.handleMessage(msg);
        }
    };
    private PullToRefreshScrollView sv_homepage;
    private RecyclerView my_recycler_view;
//    private MyGridView gv_homepage;
//    private HomePageGridViewAdapter gridViewAdapter;
    private RelativeLayout rl_search_homepage_fragment;

    private ComAppApplication comAppApplication;

    private String userId;
    private View view1;
    private View view2;
    private View view3;
    private ArrayList<View> viewArrayList;
    private MyExpandableListView melv_homePage;
    private HomePageEXGridView homePageEXGridViewAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.layout_homepage_fragment, null);
        comAppApplication = (ComAppApplication) getActivity().getApplication();
        initView(view);
        getData();
        getScroll();
        getInfo();
        click();
        fillData();
        return view;
    }

    //轮播图下面的小点
    private void setCurrentDot(int position) {
        for (int i = 0; i < viewArrayList.size(); i++) {
            if (position == i) {
                viewArrayList.get(i).setSelected(true);
            } else {
                viewArrayList.get(i).setSelected(false);
            }
        }

    }


    //初始化控件
    public void initView(View view) {
        mList = new ArrayList<>();
        inList=new ArrayList<>();
        scrollList = new ArrayList<>();
        adapter = new RecycleHomePageAdapter(getActivity(),inList);

        sv_homepage = (PullToRefreshScrollView) view.findViewById(R.id.sv_homepage);
        melv_homePage = (MyExpandableListView) view.findViewById(R.id.melv_homePage);
        homePageEXGridViewAdapter = new HomePageEXGridView(mList, getActivity());
        melv_homePage.setAdapter(homePageEXGridViewAdapter);

        //不能点击收缩
        melv_homePage.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
//        令scrollview显示顶部
        sv_homepage.getRefreshableView().smoothScrollBy(0, 0);
//        rv_homepage = (RecyclerView) view.findViewById(R.id.rv_homepage);

        customProgress = CustomProgress.show(getActivity(), "加载中···", true);
        vp_home_page_head = (ViewPager) view.findViewById(R.id.vp_home_page_head);


        vp_home_page_head.setOnPageChangeListener(this);
        vp_home_page_head.setCurrentItem(mCurrentPagePosition, false);


        sv_homepage.setMode(PullToRefreshBase.Mode.DISABLED);

        my_recycler_view = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        my_recycler_view.setLayoutManager(layoutManager);
        my_recycler_view.setAdapter(adapter);

//        gv_homepage = (MyGridView) view.findViewById(R.id.gv_homepage);
//        gridViewAdapter = new HomePageGridViewAdapter(getActivity(), mList);
//        gv_homepage.setAdapter(gridViewAdapter);
        rl_search_homepage_fragment = (RelativeLayout) view.findViewById(R.id.rl_search_homepage_fragment);


        //View 小点
        view1 = (View) view.findViewById(R.id.view1);
        view2 = (View) view.findViewById(R.id.view2);
        view3 = (View) view.findViewById(R.id.view3);
        view1.setSelected(true);
        viewArrayList = new ArrayList<>();
        viewArrayList.add(view1);
        viewArrayList.add(view2);
        viewArrayList.add(view3);
    }

    public void click() {
        rl_search_homepage_fragment.setOnClickListener(this);
//        gv_homepage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
//                intent.putExtra("productId", mList.get(position).getProductId());
//                intent.putExtra("pointUser", mList.get(position).getIsAppoint());
//                startActivity(intent);
//            }
//        });
        sv_homepage.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
//                getData();
                sv_homepage.onRefreshComplete();
            }
        });




    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setCurrentDot(position);
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

    class MyFramentPageAdapter extends FragmentPagerAdapter {
        public MyFramentPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return HomePageViewPagerFragment.myFragment(position);
        }

        @Override
        public int getCount() {
            return scrollList != null ? scrollList.size() : 0;
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
                if (obj != null && !"".equals(obj)) {
                    String pointProduct = JsonUtils.getJsonParam(obj, "pointProduct");
                    String ProductList = JsonUtils.getJsonParam(obj, "productList");
                    list = JsonUtils.parseList(ProductList, HomePage.class);
                    listAppoint = JsonUtils.parseList(pointProduct, HomePage.class);
                    if (status.equals("1")) {
                        //假如成功,给每个实体加上标识，并且将指定的list放在首位

                        //指定买家
                        if (listAppoint != null && listAppoint.size() != 0) {

                            for (int j = 0; j < listAppoint.size(); j++) {
                                if (listAppoint.get(j).getList() != null && listAppoint.get(j).getList().size() != 0) {
                                    for (int i = 0; i < listAppoint.get(j).getList().size(); i++) {
                                        listAppoint.get(j).getList().get(i).setIsAppoint("1");
                                    }
                                }
                            }
                            mList.addAll(listAppoint);
                        }

                        //未指定买家
                        if (list != null && list.size() != 0) {
                            for (int j = 0; j < list.size(); j++) {
                                if (list.get(j).getList() != null && list.get(j).getList().size() != 0) {
                                    for (int i = 0; i < list.get(j).getList().size(); i++) {
                                        list.get(j).getList().get(i).setIsAppoint("0");
                                    }

                                }
                            }
                            mList.addAll(list);
                        }
                    }
                }
                //expandableListview默认展开
                if (mList!=null&&mList.size()!=0){
                    for (int i = 0; i < mList.size(); i++) {
                        melv_homePage.expandGroup(i);
                    }
                }
                homePageEXGridViewAdapter.notifyDataSetChanged();
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

    public static List<HomePageScroll> scrollList;

    // 盛放图片
//    private List<Bitmap>bitmapList;
//    private ImageView imageView;
//    private List<Bitmap>bitmapList1;
    //首页轮播图
    public void getScroll() {
        scrollList = new ArrayList<>();

        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                Log.d("result_homePage_scroll", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                if (obj != null && !"".equals(obj)) {
                    scrollList = JsonUtils.parseList(obj, HomePageScroll.class);
                    if (scrollList != null && scrollList.size() != 0) {
                        MyFramentPageAdapter myViewPagerAdapter = new MyFramentPageAdapter(getChildFragmentManager());
                        vp_home_page_head.setAdapter(myViewPagerAdapter);
//                        handler.sendEmptyMessageDelayed(1, 3000);
                    }
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePost(DefineUtil.INDEX_SCROLLPIC, getActivity());
    }

    //获取首页资讯条
    public void getInfo(){
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                Log.d("result_infrmation",arg0.result);
                String message=JsonUtils.getJsonParam(arg0.result,"message");
                String status=JsonUtils.getJsonParam(arg0.result,"status");
                String obj=JsonUtils.getJsonParam(arg0.result,"obj");
                List<Information>sonList=new ArrayList<Information>();
                if (status.equals("1")){
                    sonList=JsonUtils.parseList(obj,Information.class);
                    if (sonList!=null&&sonList.size()!=0){
                        inList.addAll(sonList);
                    }
                }else {
                    AppUtils.toastText(getActivity(),message);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePost(DefineUtil.INFORMATION,getActivity());
    }

}
