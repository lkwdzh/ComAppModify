package com.aglook.comapp.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.Fragment.ClassificationFragment;
import com.aglook.comapp.Fragment.HomePageFragment;
import com.aglook.comapp.Fragment.MineFragment;
import com.aglook.comapp.Fragment.ShoppingCartFragment;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.Login;
import com.aglook.comapp.bean.ShoppingCart;
import com.aglook.comapp.url.LoginUrl;
import com.aglook.comapp.url.ShoppingCartUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity implements ShoppingCartFragment.onShoppingCartClick {

    private FragmentTabHost mTabHost;
    //是否是第一次启动
    private boolean isFirst;
    private ComAppApplication comAppApplication;
    private SharedPreferences mShare;
    private SharedPreferences.Editor mEditor;
    private Login login;
    private String accountType;
    private String userName;
    private String password;
    //Fragment集合
    private Class fragmentArray[] = {
            HomePageFragment.class, ClassificationFragment.class, ShoppingCartFragment.class,
            MineFragment.class
    };
    //    图片集合
    private int iconArray[] = {R.drawable.home_page, R.drawable.classification, R.drawable.shopping_cart, R.drawable.mine};
    private String titleArray[] = {"首页", "分类", "购物车", "我的"};
    private TextView textView;

    private boolean isGoods = false;
    private TextView tv_shopping_point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExitApplication.getInstance().addActivity(this);
        comAppApplication = (ComAppApplication) getApplication();
        initView();
    }

    //初始化控件
    public void initView() {
        mTabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        setupTabView();
        mTabHost.setBackgroundColor(getResources().getColor(R.color.red_c91014));
        mTabHost.setOnTabChangedListener(new TabChangeListener());
        mTabHost.getCurrentTabView().setBackgroundColor(getResources().getColor(R.color.red_a50000));
        isGoods = getIntent().getBooleanExtra("isGoods", false);
        if (isGoods) {
            mTabHost.setCurrentTab(2);
        }
        mShare = getSharedPreferences("une_pwd", MainActivity.this.MODE_PRIVATE);
        mEditor = mShare.edit();
        accountType = mShare.getString("accountType", "");
        userName = mShare.getString("userName", "");
        password = mShare.getString("password", "");
        //假如已经存在，则登录
        if (!"".equals(accountType) && !"".equals(userName) && !"".equals(password)) {
            login();
        }

        tv_shopping_point = (TextView) findViewById(R.id.tv_shopping_point);

        if (comAppApplication.getLogin() == null || "".equals(comAppApplication.getLogin())) {
            tv_shopping_point.setVisibility(View.INVISIBLE);
        } else {
            if (DefineUtil.NUM != 0) {
                tv_shopping_point.setVisibility(View.VISIBLE);
                tv_shopping_point.setText(DefineUtil.NUM + "");
            }
        }

        IntentFilter filter = new IntentFilter();
        filter.addAction("MainActivity");
        registerReceiver(myReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (DefineUtil.NUM != 0) {
                tv_shopping_point.setVisibility(View.VISIBLE);
                tv_shopping_point.setText(DefineUtil.NUM + "");
            }
        }
    };


//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (comAppApplication.getLogin() == null || "".equals(comAppApplication.getLogin())) {
//            tv_shopping_point.setVisibility(View.INVISIBLE);
//        } else {
//            if (DefineUtil.NUM!=0) {
//                tv_shopping_point.setVisibility(View.VISIBLE);
//                tv_shopping_point.setText(DefineUtil.NUM + "");
//            }
//        }
//    }

    //    TabHost的改变
    private void setupTabView() {
        mTabHost.setup(MainActivity.this, getSupportFragmentManager(), R.id.fra_tabcontent);
        //去除分割线
        mTabHost.getTabWidget().setDividerDrawable(null);

        for (int i = 0; i < fragmentArray.length; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(titleArray[i]).setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
        }
    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//        isFirst= SharedPreferencesUtils.getBoolean(MainActivity.this,"first",true);
//        if (isFirst){
//            startActivity(new Intent(MainActivity.this,GuideActivity.class));
//            SharedPreferencesUtils.saveBoolean(MainActivity.this,"first",false);
//        }
//    }

    //TabHost的图片改变
    private View getTabItemView(int index) {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View view = inflater.inflate(R.layout.tab_bottom_nav, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_bottom);
        imageView.setImageResource(iconArray[index]);
        textView = (TextView) view.findViewById(R.id.tv_bottom);
        textView.setText(titleArray[index]);

        return view;
    }

    @Override
    public void onCartClick(int position) {
        mTabHost.setCurrentTab(0);
    }

    class TabChangeListener implements TabHost.OnTabChangeListener {

        @Override
        public void onTabChanged(String tabId) {
            mTabHost.setCurrentTabByTag(tabId);

            updateTab(mTabHost);
        }
    }

    public void updateTab(TabHost tabHost) {
        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            View view = tabHost.getTabWidget().getChildAt(i);
            view.setBackgroundResource(R.drawable.tabhost_background);
            TextView textView = (TextView) view.findViewById(R.id.tv_bottom);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_bottom);
            //如果选中
//            if (tabHost.getCurrentTab()==i){
////                imageView.setBackgroundResource(R.drawable.xuanzhong);
//                view.setBackgroundColor(getResources().getColor(R.color.black));
//            }else {
//                view.setBackgroundColor(getResources().getColor(R.color.red_c91014));
//            }
        }
    }

    //    返回键的监听
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                AppUtils.toastText(MainActivity.this, "再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
        }
        return true;
    }


    //登录账户
    public void login() {

        new XHttpuTools() {

            @Override
            public void initViews(ResponseInfo<String> arg0) {
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String str = JsonUtils.getJsonParam(arg0.result, "obj");
                login = JsonUtils.parse(str, Login.class);
                if (status.equals("1")) {//登录成功,跳转页面
                    DefineUtil.TOKEN = login.getToken();
                    DefineUtil.USERID = login.getPshUser().getUserId();
                    comAppApplication.setLogin(login);
//                    Intent intent = new Intent(MainActivity.this, MineFragment.class);
//                    MainActivity.this.setResult(1);
//                    MainActivity.this.finish();
                    getCartListData();
                }
                AppUtils.toastText(MainActivity.this, message);
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePost(DefineUtil.LOGIN_IN, LoginUrl.postLonginUrl(userName, password, accountType), MainActivity.this);
    }

    //    获取购物车列表
    public void getCartListData() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                Log.d("result_getCartList", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                List<ShoppingCart> list = new ArrayList<ShoppingCart>();
                list = JsonUtils.parseList(obj, ShoppingCart.class);
                if (status.equals("1")) {
                    if (list != null && list.size() != 0) {
                        for (int i = 0; i < list.size(); i++) {
                            DefineUtil.NUM += list.get(i).getProductNum();
                        }
                    }
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
            }
        }.datePost(DefineUtil.CARTLIST, ShoppingCartUrl.postCartListUrl(DefineUtil.USERID, DefineUtil.TOKEN), MainActivity.this);
    }
}
