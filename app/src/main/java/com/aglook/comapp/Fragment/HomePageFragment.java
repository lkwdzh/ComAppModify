package com.aglook.comapp.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.aglook.comapp.Activity.ClassifyActivity;
import com.aglook.comapp.Activity.LoginActivity;
import com.aglook.comapp.Activity.MainActivity;
import com.aglook.comapp.Activity.MyCangDanActivity;
import com.aglook.comapp.Activity.PlatformActivity;
import com.aglook.comapp.Activity.SearchActivity;
import com.aglook.comapp.Activity.ZiXunListActivity;
import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.HomePageEXGridView;
import com.aglook.comapp.adapter.HomePageZiAdapter;
import com.aglook.comapp.bean.HomePage;
import com.aglook.comapp.bean.HomePageList;
import com.aglook.comapp.bean.HomePageScroll;
import com.aglook.comapp.bean.Information;
import com.aglook.comapp.url.HomePageUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.aglook.comapp.view.MyExpandableListView;
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
    private List<Information> inList;
    private List<String> list = new ArrayList<>();
    //    private RecycleHomePageAdapter adapter;
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
                vp_home_page_head.setCurrentItem(index % 5);
                handler.sendEmptyMessageDelayed(1, 3000);
                index++;
            }
        }
    };
    private PullToRefreshScrollView sv_homepage;
    private RelativeLayout rl_search_homepage_fragment;

    private ComAppApplication comAppApplication;

    private String userId;
    private MyExpandableListView melv_homePage;
    private HomePageEXGridView homePageEXGridViewAdapter;
    private MyGridView mgv_homePage;
    private HomePageZiAdapter ziAdapter;
    private HomePageCallBack homePageCallBack;
    private TextView tv_cangdan_home;
    private TextView tv_plat_home;

    private String classId;
    private String className;

    private int scrollLength;
    // 点点资源数组
    private ImageView[] tips;
    private ViewGroup viewGroup;

    private boolean isLogin = false;
//    private int count=0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.layout_homepage_fragment, null);
        comAppApplication = (ComAppApplication) getActivity().getApplication();
        initView(view);
        Log.d("result_login", isLogin + "___");
        getData();

        IntentFilter filter2 = new IntentFilter();
        filter2.addAction("HomeMain");
        getActivity().registerReceiver(myReceiver2, filter2);
        getScroll();
        getInfo();
        click();
        fillData();
        return view;
    }


    //轮播图下面的小点
    private void setCurrentDot(int position) {
        for (int i = 0; i < scrollLength; i++) {
            if (i == position) {
                tips[i].setBackgroundResource(R.drawable.checked);
            } else {
                tips[i].setBackgroundResource(R.drawable.checked_no);
            }
        }

    }


    //初始化控件
    public void initView(View view) {
        mList = new ArrayList<>();
        inList = new ArrayList<>();
        scrollList = new ArrayList<>();
//        adapter = new RecycleHomePageAdapter(getActivity(),inList);
        vp_home_page_head = (ViewPager) view.findViewById(R.id.vp_home_page_head);
        sv_homepage = (PullToRefreshScrollView) view.findViewById(R.id.sv_homepage);
        mgv_homePage = (MyGridView) view.findViewById(R.id.mgv_homePage);
        melv_homePage = (MyExpandableListView) view.findViewById(R.id.melv_homePage);
        homePageEXGridViewAdapter = new HomePageEXGridView(mList, getActivity());
        ziAdapter = new HomePageZiAdapter(getActivity());
        melv_homePage.setAdapter(homePageEXGridViewAdapter);
        mgv_homePage.setAdapter(ziAdapter);
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


        viewGroup = (ViewGroup) view.findViewById(R.id.viewGroup);
        vp_home_page_head.setOnPageChangeListener(this);
        vp_home_page_head.setCurrentItem(mCurrentPagePosition, false);


        sv_homepage.setMode(PullToRefreshBase.Mode.PULL_FROM_START);


        rl_search_homepage_fragment = (RelativeLayout) view.findViewById(R.id.rl_search_homepage_fragment);


    }

    //接收广播
    private BroadcastReceiver myReceiver2 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            isLogin = true;
            getData();
        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (MainActivity.instance!=null) {
            getActivity().unregisterReceiver(myReceiver2);
        }
    }

    public void click() {
        rl_search_homepage_fragment.setOnClickListener(this);
        sv_homepage.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                customProgress = CustomProgress.show(getActivity(), "", true);
                mList.clear();
                getData();

            }
        });
        Log.d("result_confirm_Home", comAppApplication.getLogin() + "");
        mgv_homePage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //点击我要买跳转到分类
                //点击我要卖，弹出选择框，并跳转
                Intent intent = new Intent(getActivity(), ZiXunListActivity.class);
                switch (position) {
                    case 0:
//                        classId="9";
                        className = "每日看点";
                        getId(className);
                        intent.putExtra("classId", classId);
                        intent.putExtra("className", className);
                        startActivity(intent);
                        break;
                    case 1:
//                        classId="10";
                        className = "行情报告";
                        getId(className);
                        intent.putExtra("classId", classId);
                        intent.putExtra("className", className);
                        startActivity(intent);
                        break;
                    case 2:
                        Intent intent1 = new Intent(getActivity(), ClassifyActivity.class);
                        className = "价格走势";
                        getId(className);
                        intent1.putExtra("classId", classId);
                        intent1.putExtra("className", className);
                        startActivity(intent1);
                        break;
                    case 3:
//                        classId="11";
                        className = "品种公告";
                        getId(className);
                        intent.putExtra("classId", classId);
                        intent.putExtra("className", className);
                        startActivity(intent);
                        break;
                    case 4:
//                        classId="1";
                        className = "网站公告";
                        getId(className);
                        intent.putExtra("classId", classId);
                        intent.putExtra("className", className);
                        startActivity(intent);
                        break;
                    case 5:
//                        classId="2";
                        className = "知识学堂";
                        getId(className);
                        intent.putExtra("classId", classId);
                        intent.putExtra("className", className);
                        startActivity(intent);
                        break;
                    case 6:
                        showDailog();
                        break;
                    case 7:
                        homePageCallBack.callBack(1);
                        break;
                }


            }
        });
    }

    //根据className获取classId;
    public void getId(String name) {
        Information information = null;
        for (int i = 0; i < inList.size(); i++) {
            information = inList.get(i);
            if (information.getClassName().equals(name)) {
                classId = information.getClassId();
                Log.d("result_classId", classId);
                return;
            } else {
                classId = null;
            }
        }
    }

    //回调接口，向MainActivity传递数据
    public interface HomePageCallBack {
        public void callBack(int position);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (getActivity() instanceof HomePageCallBack) {
            homePageCallBack = (HomePageCallBack) getActivity();
        }
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
            case R.id.tv_cangdan_home:
                if (comAppApplication.getLogin() == null || "".equals(comAppApplication.getLogin())) {
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, 1);
                } else {
                    intent.setClass(getActivity(), MyCangDanActivity.class);
                    startActivity(intent);
                }
                dialog.dismiss();
                break;
            case R.id.tv_plat_home:
                if (comAppApplication.getLogin() == null || "".equals(comAppApplication.getLogin())) {
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivityForResult(intent, 2);
                } else {
                    intent.setClass(getActivity(), PlatformActivity.class);
                    startActivity(intent);
                }
                dialog.dismiss();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent intent = new Intent();
        if (requestCode == 1 && resultCode == 1) {
            intent.setClass(getActivity(), MyCangDanActivity.class);
            startActivity(intent);
        } else if (requestCode == 2 && resultCode == 1) {
            intent.setClass(getActivity(), PlatformActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeMessages(1);
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


    //设置点数
    public void setTips() {
        if (scrollLength > 0) {

            // 将小点点装入ViewGroup
            tips = new ImageView[scrollLength];
            for (int i = 0; i < tips.length; i++) {
                ImageView imageTip = new ImageView(getActivity());
                imageTip.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
                tips[i] = imageTip;
                if (i == 0) {
                    tips[i].setBackgroundResource(R.drawable.checked);
                } else {
                    tips[i].setBackgroundResource(R.drawable.checked_no);
                }

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                layoutParams.leftMargin = 20;
                layoutParams.rightMargin = 20;
                viewGroup.addView(imageTip, layoutParams);
            }
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
                List<HomePage> newListAppoint = new ArrayList<HomePage>();
                List<HomePageList> listAppoint = new ArrayList<HomePageList>();
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                if (obj != null && !"".equals(obj)) {
                    String pointProduct = JsonUtils.getJsonParam(obj, "pointProduct");
                    String ProductList = JsonUtils.getJsonParam(obj, "productList");
                    list = JsonUtils.parseList(ProductList, HomePage.class);
                    listAppoint = JsonUtils.parseList(pointProduct, HomePageList.class);
                    if (status.equals("1")) {
                        //假如成功,给每个实体加上标识，并且将指定的list放在首位
                        if (isLogin) {
                            Log.d("result_mList", "mList.toString()");
//                            mList.clear();
                        }
                        mList.clear();
                        //指定买家
                        HomePage newHomePage = new HomePage();
                        if (listAppoint != null && listAppoint.size() != 0) {
                            for (int j = 0; j < listAppoint.size(); j++) {
                                listAppoint.get(j).setIsAppoint("1");
                            }
                            newHomePage.setCategoryName("定向交易");
                            newHomePage.setList(listAppoint);
                            newListAppoint.add(newHomePage);
                            mList.addAll(newListAppoint);
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
                } else {
                    AppUtils.toastText(getActivity(), message);
                }
                //expandableListview默认展开
                if (mList != null && mList.size() != 0) {
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
        }.datePostCheck(DefineUtil.HOT_LIST, HomePageUrl.postHomePageCategoryUrl(DefineUtil.USERID), getActivity());
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
                        scrollLength = scrollList.size();
                        setTips();
                        handler.sendEmptyMessageDelayed(1, 3000);
                    }
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePostCheck(DefineUtil.INDEX_SCROLLPIC, getActivity());
    }

    //获取首页资讯条
    public void getInfo() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                Log.d("result_infrmation", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                List<Information> sonList = new ArrayList<Information>();
                if (status.equals("1")) {
                    sonList = JsonUtils.parseList(obj, Information.class);
                    if (sonList != null && sonList.size() != 0) {
                        inList.addAll(sonList);
                    }
                }

//                adapter.notifyDataSetChanged();
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePostCheck(DefineUtil.INFORMATION, getActivity());
    }


    //选择平台仓单与仓单的对话框
    private Dialog dialog;

    public void showDailog() {
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_home_dialog, null);
        tv_cangdan_home = (TextView) view.findViewById(R.id.tv_cangdan_home);
        tv_plat_home = (TextView) view.findViewById(R.id.tv_plat_home);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.create();
        builder.setView(view);
//        builder.setCancelable(false);
        dialog = builder.show();
        tv_cangdan_home.setOnClickListener(this);
        tv_plat_home.setOnClickListener(this);
    }


}
