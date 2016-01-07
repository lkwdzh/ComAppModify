package com.aglook.comapp.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
import com.aglook.comapp.bean.AllOrder;
import com.aglook.comapp.bean.Login;
import com.aglook.comapp.bean.ShoppingCart;
import com.aglook.comapp.url.AllOrderUrl;
import com.aglook.comapp.url.LoginUrl;
import com.aglook.comapp.url.MainUrl;
import com.aglook.comapp.url.ShoppingCartUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity implements ShoppingCartFragment.onShoppingCartClick, HomePageFragment.HomePageCallBack, View.OnClickListener {

    private FragmentTabHost mTabHost;
    //是否是第一次启动
    private boolean isFirst;
    private ComAppApplication comAppApplication;
    private SharedPreferences mShare;
    private SharedPreferences.Editor mEditor;
    private Login login;
    private String accountType;
    private String userName;
    private boolean isJpush;

    private String password;
    private boolean isLogin;

    private String appName = "com.aglook.comapp";
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
    public static MainActivity instance=null;

    //private DbUtils db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance=this;
        ExitApplication.getInstance().addActivity(this);
        comAppApplication = (ComAppApplication) getApplication();
        isJpush = getIntent().getBooleanExtra("isJpush", false);
        initView();
    }

    //初始化控件
    public void initView() {
//        db=DbUtils.create(this,"MESSAGE");
//        comAppApplication.setDb(db);

        mShare = getSharedPreferences("une_pwd", MainActivity.this.MODE_PRIVATE);
        mEditor = mShare.edit();
        accountType = mShare.getString("accountType", "");
        password = mShare.getString("password", "");
        if (!"".equals(accountType)) {
            if (accountType.equals("1")) {
                //用户名登录
                userName = mShare.getString("userName", "");
                if (!"".equals(userName) && !"".equals(password)) {
                    if (comAppApplication.getLogin() == null) {
//                        Log.d("result_acc", accountType + "____" + userName + "____" + password);
                        login();
                    }
                }

            } else if (accountType.equals("0")) {
                //席位号登录
                userName = mShare.getString("setName", "");
                if (!"".equals(userName) && !"".equals(password)) {
                    if (comAppApplication.getLogin() == null) {
//                        Log.d("result_acc", accountType + "____" + userName + "____" + password);
                        login();
                    }
                }
            }
        }


        mTabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        setupTabView();
//        mTabHost.setBackgroundColor(getResources().getColor(R.color.red_c91014));
        mTabHost.setOnTabChangedListener(new TabChangeListener());
//        mTabHost.getCurrentTabView().setBackgroundColor(getResources().getColor(R.color.red_a50000));
        isGoods = getIntent().getBooleanExtra("isGoods", false);
        if (isGoods) {
            mTabHost.setCurrentTab(2);
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

        if (DefineUtil.FLAG == 2) {
//            Log.d("result_DefineUtil.FLAG_cr", DefineUtil.FLAG + "");
            mTabHost.setCurrentTab(3);
        }

        if (isJpush) {
            Intent intent1 = new Intent(MainActivity.this, ZiXunListActivity.class);
            intent1.putExtra("className", "消息");
            intent1.putExtra("isMessage", true);
            intent1.putExtra("isReceiver", true);
            startActivity(intent1);
        }
        checkUpdate();
    }

    private ApplicationInfo info;
    private String channel;
    private boolean isForce;
    private String url;

    //获取渠道并且检查更新
    public void checkUpdate() {
        //获取发布渠道
        try {
            info = this.getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        channel = info.metaData.getString("channel");

        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
//                Log.d("result_check", arg0.result);
                if (arg0.result != null && !"".equals(arg0.result)) {
                    String status = JsonUtils.getJsonParam(arg0.result, "status");
                    if (status.equals("wait")) {
                        //审核中
                    } else if (status.equals("success")) {
                        //再判断versionCode
                        //获取versionName
                        String packageName = MainActivity.this.getPackageName();
                        int myCode = 0;
                        try {
                            PackageInfo packageInfo = MainActivity.this.getPackageManager().getPackageInfo(packageName, 0);
                            myCode = packageInfo.versionCode;
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                        //如果本地code小于获取到的，则更新
                        int versionCode = Integer.parseInt(JsonUtils.getJsonParam(arg0.result, "versionCode"));
                        if (myCode < versionCode) {
                            //更新
                            url = JsonUtils.getJsonParam(arg0.result, "downloadUrl");
                            //判断是否强制
                            int forcedUpdate = Integer.parseInt(JsonUtils.getJsonParam(arg0.result, "forcedUpdate"));
                            if (forcedUpdate == 0) {
                                //非强制
                                isForce = false;
                            } else if (forcedUpdate == 1) {
                                //强制
                                isForce = true;
                            }
                            showDailog(isForce);
                        }
                    }
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePostCheck(DefineUtil.CHECK_UPDATE, MainUrl.postCheckUpUrl(appName, channel), MainActivity.this);
    }

    private Dialog dialog;
    private TextView tv_delete_order;

    public void showDailog(boolean isforce) {
        LayoutInflater layoutInflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_order, null);
        btn_cancel_delete = (Button) view.findViewById(R.id.btn_cancel_delete);
        btn_confirm_delete = (Button) view.findViewById(R.id.btn_confirm_delete);
        tv_delete_order = (TextView) view.findViewById(R.id.tv_delete_order);
        tv_delete_order.setText("检测到有新版本，确认更新?");
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.create();
        builder.setView(view);
        builder.setCancelable(isforce);
        dialog = builder.show();
//        dialog.setTitle("版本更新");
        btn_cancel_delete.setOnClickListener(this);
        btn_confirm_delete.setOnClickListener(this);
    }

    private Button btn_cancel_delete;
    private Button btn_confirm_delete;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (myReceiver!=null) {
            unregisterReceiver(myReceiver);
        }
        DefineUtil.IS_LAUNCH = false;
    }

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (DefineUtil.NUM != 0) {
                tv_shopping_point.setVisibility(View.VISIBLE);
                tv_shopping_point.setText(DefineUtil.NUM + "");
            } else {
                tv_shopping_point.setVisibility(View.INVISIBLE);
            }



        }
    };


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


    //TabHost的图片改变
    private View getTabItemView(int index) {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View view = inflater.inflate(R.layout.tab_bottom_nav, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_bottom);
        imageView.setImageResource(iconArray[index]);
        textView = (TextView) view.findViewById(R.id.tv_bottom);
        textView.setText(titleArray[index]);
        if (index==0){
            textView.setTextColor(getResources().getColor(R.color.red_c91014));
        }
        return view;
    }

    @Override
    public void onCartClick(int position) {
        mTabHost.setCurrentTab(0);
    }

    @Override
    public void callBack(int position) {
        mTabHost.setCurrentTab(position);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel_delete:
                if (isForce) {

                } else {
                    dialog.dismiss();
                }
                break;
            case R.id.btn_confirm_delete:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(url);
                intent.setData(content_url);
                startActivity(intent);
                dialog.dismiss();
                break;
        }
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
//            view.setBackgroundResource(R.drawable.tabhost_background);
            TextView textView = (TextView) view.findViewById(R.id.tv_bottom);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_bottom);
            if (tabHost.getCurrentTab() == i) {// 选中
                textView.setTextColor(this.getResources()
                        .getColor(R.color.red_c91014));
            } else {// 不选中
                textView.setTextColor(this.getResources().getColor(R.color.textcolor_999999));
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        if (comAppApplication.getLogin() != null) {
            getNotPayData();
        }
        if (comAppApplication.getLogin()==null){
            tv_shopping_point.setVisibility(View.INVISIBLE);
        }
        if (DefineUtil.FLAG == 2) {
//            Log.d("result_DefineUtil.FLAG_re", DefineUtil.FLAG + "");
            mTabHost.setCurrentTab(3);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
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
    // 868227025178724

    //登录账户
    public void login() {
//        TelephonyManager TelephonyMgr = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
//        String szImei = TelephonyMgr.getDeviceId();
//        Log.d("szImei",szImei);
        new XHttpuTools() {

            @Override
            public void initViews(ResponseInfo<String> arg0) {
//                Log.d("result_main_login", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String str = JsonUtils.getJsonParam(arg0.result, "obj");
                login = JsonUtils.parse(str, Login.class);
                if (status.equals("1")) {//登录成功,跳转页面
                    DefineUtil.TOKEN = login.getToken();
                    DefineUtil.USERID = login.getPshUser().getUserId();
                    DefineUtil.BANKBAND = login.isBankBind();
//                    Log.d("result_login_main", DefineUtil.BANKBAND + "");
                    comAppApplication.setLogin(login);
                    getCartListData();
                    getNotPayData();
                    isLogin = true;
//                    Log.d("result_main_islog——2", isLogin + "");
                    DefineUtil.ISUSERID = true;
                    Intent intent1 = new Intent();
                    intent1.setAction("HomeMain");
                    MainActivity.this.sendBroadcast(intent1);
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
            }
        }.datePostCheck(DefineUtil.LOGIN_IN, LoginUrl.postLonginUrl(userName, password, accountType, DefineUtil.DEVICE_NUM), MainActivity.this);
    }

    //    获取购物车列表
    public void getCartListData() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
//                Log.d("result_getCartList", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                List<ShoppingCart> list = new ArrayList<ShoppingCart>();
                list = JsonUtils.parseList(obj, ShoppingCart.class);
                if (status.equals("1")) {
                    DefineUtil.NUM=0;
                    if (list != null && list.size() != 0) {
                        for (int i = 0; i < list.size(); i++) {
                            DefineUtil.NUM +=list.get(i).getProductNum();
                        }
                        if (DefineUtil.NUM != 0) {
                            tv_shopping_point.setVisibility(View.VISIBLE);
                            tv_shopping_point.setText(DefineUtil.NUM + "");
                        }
                    }
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
            }
        }.datePostCheck(DefineUtil.CARTLIST, ShoppingCartUrl.postCartListUrl(DefineUtil.USERID, DefineUtil.TOKEN), MainActivity.this);
    }


    private String orderStatus = "1";
    private int pageNum = 1;
    private int pageSize = 100;
    private String orderId;

    //获取未支付
    //获取数据
    public void getNotPayData() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
//                Log.d("result_all_order", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                List<AllOrder> sonList = new ArrayList<AllOrder>();
                sonList = JsonUtils.parseList(obj, AllOrder.class);
                if (status.equals("1")) {
                    if (sonList != null && sonList.size() != 0) {
                        if (orderStatus.equals("1")) {
                            DefineUtil.NOTPAY_NUM = sonList.size();
                        }
                    }
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
            }
        }.datePostCheck(DefineUtil.ORDER_LIST, AllOrderUrl.postAllOrderUrl(DefineUtil.USERID, DefineUtil.TOKEN, orderStatus, String.valueOf(pageSize), String.valueOf(pageNum), orderId), MainActivity.this);
    }
}
