package com.aglook.comapp.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.Fragment.ClassificationFragment;
import com.aglook.comapp.Fragment.HomePageFragment;
import com.aglook.comapp.Fragment.MineFragment;
import com.aglook.comapp.Fragment.ShoppingCartFragment;
import com.aglook.comapp.R;
import com.aglook.comapp.util.AppUtils;


public class MainActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;
    //是否是第一次启动
    private boolean isFirst;

    //Fragment集合
    private Class fragmentArray[] = {
            HomePageFragment.class, ClassificationFragment.class, ShoppingCartFragment.class,
            MineFragment.class
    };
    //    图片集合
    private int iconArray[] = {R.drawable.home_page, R.drawable.classification, R.drawable.shopping_cart, R.drawable.mine};
    private String titleArray[] = {"首页", "分类", "购物车", "我的"};
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ExitApplication.getInstance().addActivity(this);
        initView();
    }

    //初始化控件
    public void initView() {
        mTabHost = (FragmentTabHost) findViewById(R.id.tabhost);
        setupTabView();
        mTabHost.setBackgroundColor(getResources().getColor(R.color.red_c91014));
        mTabHost.setOnTabChangedListener(new TabChangeListener());
        mTabHost.getCurrentTabView().setBackgroundColor(getResources().getColor(R.color.red_a50000));
    }

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
}
