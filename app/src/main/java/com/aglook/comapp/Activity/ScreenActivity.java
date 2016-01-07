package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.ScreenAdapter;
import com.aglook.comapp.bean.Screen;
import com.aglook.comapp.url.ScreenUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.ArrayList;
import java.util.List;

public class ScreenActivity extends BaseActivity {


    private PullToRefreshListView gv_screen;
    private ScreenAdapter adapter;
    private TextView tv_all_screen;
    private LinearLayout ll_price_screen;
    private CheckBox cb_price_screen;
    private LinearLayout ll_sale_screen;
    private CheckBox cb_sale_screen;
    private boolean isPriceFirst = true;
    private boolean isPrice;
    private boolean isSaleFirst = true;
    private boolean isSale;
    private boolean isAll;
    private TextView tv_price_up_down_screen_popup;
    private TextView tv_price_down_up_screen_popup;
    private TextView tv_total_down_up_screen_popup;
    private TextView tv_total_up_down_screen_popup;

    //    private List<TextView> tvList = new ArrayList<>();
    private TextView tv_price_screen;
    private TextView tv_sale_screen;
    private CustomProgress customProgress;
    private List<Screen> mList = new ArrayList<>();
    private String productName;
    private boolean isSearch;
    private String descFlag = "0";//默认正序
    private String clickNum;//按销量排序
    private String productMoney;//按价格排序

    private String categoryId;//分类ID
    private int pageSize = 10;//每页数量
    private int pageNumber = 1;//第几页
    private View emptyView;
    private ImageView left_icon;


    @Override
    public void initView() {
        setContentView(R.layout.activity_screen);
        setTitleBar("筛选");
        ExitApplication.getInstance().addActivity(this);
        init();
        if (isSearch) {
            getSearchData();
        } else {
            getScreenData();
        }
        click();
    }

    public void init() {
        emptyView = LayoutInflater.from(this).inflate(R.layout.empty_view_layout, null);
        productName = getIntent().getStringExtra("productName");
        isSearch = getIntent().getBooleanExtra("isSearch", false);
        categoryId = getIntent().getStringExtra("categoryId");
        gv_screen = (PullToRefreshListView) findViewById(R.id.gv_screen);
        left_icon = (ImageView) findViewById(R.id.left_icon);
        adapter = new ScreenAdapter(ScreenActivity.this, mList);
        gv_screen.setAdapter(adapter);
        tv_all_screen = (TextView) findViewById(R.id.tv_all_screen);
        ll_price_screen = (LinearLayout) findViewById(R.id.ll_price_screen);
        cb_price_screen = (CheckBox) findViewById(R.id.cb_price_screen);
        tv_price_screen = (TextView) findViewById(R.id.tv_price_screen);
        ll_sale_screen = (LinearLayout) findViewById(R.id.ll_sale_screen);
        cb_sale_screen = (CheckBox) findViewById(R.id.cb_sale_screen);
        tv_sale_screen = (TextView) findViewById(R.id.tv_sale_screen);
        tv_all_screen.setTextColor(getResources().getColor(R.color.red_c91014));

    }

    public void click() {
        left_icon.setOnClickListener(this);
        tv_all_screen.setOnClickListener(this);
        ll_price_screen.setOnClickListener(this);
        ll_sale_screen.setOnClickListener(this);
        gv_screen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ScreenActivity.this, GoodsDetailActivity.class);
                intent.putExtra("productId", mList.get(position-1).getProductId());
                startActivity(intent);
            }
        });

        gv_screen.setMode(PullToRefreshBase.Mode.BOTH);
        gv_screen.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNumber = 1;
                if (isSearch) {
                    getSearchData();
                } else {
                    getScreenData();
                }
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNumber++;
                if (isSearch) {
                    getSearchData();
                } else {
                    getScreenData();
                }
            }
        });
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.tv_all_screen:
                clickNum = null;
                productMoney = null;
                tv_all_screen.setTextColor(getResources().getColor(R.color.red_c91014));
                tv_price_screen.setTextColor(getResources().getColor(R.color.textcolor_666666));
                tv_sale_screen.setTextColor(getResources().getColor(R.color.textcolor_666666));
                isPriceFirst = true;
                isSaleFirst = true;
                isAll = true;
                if (isSearch) {
                    getSearchData();
                } else {
                    getScreenData();
                }
                break;
            case R.id.ll_price_screen:
//                productName = null;
                productMoney = "1";
                clickNum = null;
                if (isPriceFirst) {
                    tv_all_screen.setTextColor(getResources().getColor(R.color.textcolor_666666));
                    tv_price_screen.setTextColor(getResources().getColor(R.color.red_c91014));
                    tv_sale_screen.setTextColor(getResources().getColor(R.color.textcolor_666666));
                    isPriceFirst = false;
                    isSaleFirst = true;
                    if (cb_price_screen.isChecked()) {
                        descFlag = "0";//正序
                    } else {
                        descFlag = "1";//倒叙
                    }
                } else {
                    if (cb_price_screen.isChecked()) {
                        cb_price_screen.setChecked(false);
                        descFlag = "1";//倒叙
                    } else {
                        descFlag = "0";//正序
                        cb_price_screen.setChecked(true);
                    }
                    isPriceFirst = false;
                    isSaleFirst = true;
                }
                isPrice = true;
                pageNumber=1;
                if (isSearch) {
                    getSearchData();
                } else {
                    getScreenData();
                }
                break;
            case R.id.ll_sale_screen:
                clickNum = "1";
                productMoney = null;
//                productName = null;
                if (isSaleFirst) {
                    tv_all_screen.setTextColor(getResources().getColor(R.color.textcolor_666666));
                    tv_price_screen.setTextColor(getResources().getColor(R.color.textcolor_666666));
                    tv_sale_screen.setTextColor(getResources().getColor(R.color.red_c91014));
                    isPriceFirst = true;
                    isSaleFirst = false;
                    if (cb_sale_screen.isChecked()) {
                        descFlag = "0";//正序
                    } else {
                        descFlag = "1";//倒序
                    }
                } else {
                    if (cb_sale_screen.isChecked()) {
                        cb_sale_screen.setChecked(false);
                        descFlag = "1";//倒序
                    } else {
                        cb_sale_screen.setChecked(true);
                        descFlag = "0";//正序
                    }
                    isSaleFirst = false;
                    isPriceFirst = true;
                }
                isSale = true;
                pageNumber=1;
                if (isSearch) {
                    getSearchData();
                } else {
                    getScreenData();
                }
                break;
            case R.id.left_icon:
                ScreenActivity.this.setResult(RESULT_OK);
                ScreenActivity.this.finish();
                break;
        }
    }
    //监听返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            ScreenActivity.this.setResult(RESULT_OK);
            ScreenActivity.this.finish();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==33&&resultCode==1){
            mList.clear();
            if (isSearch) {
                getSearchData();
            } else {
                getScreenData();
            }
        }
    }

    //获取搜索信息
    public void getSearchData() {
        customProgress = CustomProgress.show(ScreenActivity.this, "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
//                Log.d("result_descFlag, clickNum, productMoney, productName", descFlag + "____" + clickNum + "____" + productMoney + "____" + productName);
//                Log.d("result_Search", arg0.result);
                List<Screen> sonList = new ArrayList<>();
                List<Screen> sonListAppoint = new ArrayList<Screen>();
//                List<Screen> llList = new ArrayList<Screen>();
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                if (obj != null && !"".equals(obj)) {
                    String pointProduct = JsonUtils.getJsonParam(obj, "pointProduct");
                    String ProductList = JsonUtils.getJsonParam(obj, "ProductList");
                    sonList = JsonUtils.parseList(ProductList, Screen.class);
                    sonListAppoint = JsonUtils.parseList(pointProduct, Screen.class);
                    if (status.equals("1")) {

                        if (pageNumber == 1) {
                            mList.clear();
                        }
                        if (isAll) {
                            isAll = false;
                            mList.clear();
                        }

                        if (isPrice) {
                            isPrice = false;
                            mList.clear();
                        }

                        if (isSale) {
                            isSale = false;
                            mList.clear();
                        }
                        //指定买家
                        if (sonListAppoint != null && sonListAppoint.size() != 0) {
                            for (int i = 0; i < sonListAppoint.size(); i++) {
                                sonListAppoint.get(i).setIsAppoint("1");
                            }
                            mList.addAll(sonListAppoint);
                        }
                        //未指定买家
                        if (sonList != null && sonList.size() != 0) {
                            for (int i = 0; i < sonList.size(); i++) {
                                sonList.get(i).setIsAppoint("0");
                            }
                            mList.addAll(sonList);
                        }

                    }


                }
                adapter.notifyDataSetChanged();
                gv_screen.onRefreshComplete();
                gv_screen.setEmptyView(emptyView);
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.SEARCH, ScreenUrl.postSearchUrl(DefineUtil.USERID, descFlag, String.valueOf(pageSize),
                String.valueOf(pageNumber), clickNum, productMoney, productName), ScreenActivity.this);
    }


    //获取分类xinx
    public void getScreenData() {
        customProgress = CustomProgress.show(ScreenActivity.this, "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
//                Log.d("result_categoryId,descFlag, clickNum, productMoney, productName", categoryId + "____" + descFlag + "____" + clickNum + "____" + productMoney + "____" + productName);
//                Log.d("result_Screen", arg0.result);
                List<Screen> sonList = new ArrayList<>();
                List<Screen> sonListAppoint = new ArrayList<Screen>();
//                List<Screen> llList = new ArrayList<Screen>();
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                if (obj != null && !"".equals(obj)) {
                    String pointProduct = JsonUtils.getJsonParam(obj, "pointProduct");
                    String ProductList = JsonUtils.getJsonParam(obj, "ProductList");
                    sonList = JsonUtils.parseList(ProductList, Screen.class);
                    sonListAppoint = JsonUtils.parseList(pointProduct, Screen.class);
                    if (status.equals("1")) {

                        if (pageNumber == 1) {
                            mList.clear();
                        }

                        if (isAll) {
                            isAll = false;
                            mList.clear();
                        }

                        if (isPrice) {
                            isPrice = false;
                            mList.clear();
                        }

                        if (isSale) {
                            isSale = false;
                            mList.clear();
                        }
                        //指定买家
                        if (sonListAppoint != null && sonListAppoint.size() != 0) {
                            for (int i = 0; i < sonListAppoint.size(); i++) {
                                sonListAppoint.get(i).setIsAppoint("1");
                            }
                            mList.addAll(sonListAppoint);
                        }
                        //未指定买家
                        if (sonList != null && sonList.size() != 0) {
                            for (int i = 0; i < sonList.size(); i++) {
                                sonList.get(i).setIsAppoint("0");
                            }
                            mList.addAll(sonList);
                        }

                    } else {
                        AppUtils.toastText(ScreenActivity.this, message);
                    }

                }
                adapter.notifyDataSetChanged();
                gv_screen.onRefreshComplete();
                gv_screen.setEmptyView(emptyView);
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.CATEGORY_DETAIL, ScreenUrl.postScreenUrl(DefineUtil.USERID, categoryId, descFlag, String.valueOf(pageSize),
                String.valueOf(pageNumber), clickNum, productMoney, productName), ScreenActivity.this);
    }

}
