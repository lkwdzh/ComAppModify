package com.aglook.comapp.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
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
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.ArrayList;
import java.util.List;

public class ScreenActivity extends BaseActivity {


    private PullToRefreshGridView gv_screen;
    private ScreenAdapter adapter;
    private TextView tv_all_screen;
    private LinearLayout ll_price_screen;
    private CheckBox cb_price_screen;
    private LinearLayout ll_sale_screen;
    private CheckBox cb_sale_screen;
    private boolean isPriceFirst = true;
    private boolean isSaleFirst = true;
    private TextView tv_price_up_down_screen_popup;
    private TextView tv_price_down_up_screen_popup;
    private TextView tv_total_down_up_screen_popup;
    private TextView tv_total_up_down_screen_popup;

    //    private List<TextView> tvList = new ArrayList<>();
    private String categoryId;
    private TextView tv_price_screen;
    private TextView tv_sale_screen;
    private CustomProgress customProgress;
    private List<Screen> mList = new ArrayList<>();

    @Override
    public void initView() {
        setContentView(R.layout.activity_screen);
        setTitleBar("筛选");
        ExitApplication.getInstance().addActivity(this);
        init();
        getData();
        click();
    }

    public void init() {
        gv_screen = (PullToRefreshGridView) findViewById(R.id.gv_screen);
        adapter = new ScreenAdapter(ScreenActivity.this, mList);
        gv_screen.setAdapter(adapter);
        categoryId = getIntent().getStringExtra("categoryId");
        tv_all_screen = (TextView) findViewById(R.id.tv_all_screen);
        ll_price_screen = (LinearLayout) findViewById(R.id.ll_price_screen);
        cb_price_screen = (CheckBox) findViewById(R.id.cb_price_screen);
        tv_price_screen = (TextView) findViewById(R.id.tv_price_screen);
        ll_sale_screen = (LinearLayout) findViewById(R.id.ll_sale_screen);
        cb_sale_screen = (CheckBox) findViewById(R.id.cb_sale_screen);
        tv_sale_screen = (TextView) findViewById(R.id.tv_sale_screen);
        tv_all_screen.setTextColor(getResources().getColor(R.color.red_c91014));
        customProgress = CustomProgress.show(ScreenActivity.this, "加载中···", true);
    }

    public void click() {
        tv_all_screen.setOnClickListener(this);
        ll_price_screen.setOnClickListener(this);
        ll_sale_screen.setOnClickListener(this);
        gv_screen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ScreenActivity.this, GoodsDetailActivity.class);
                intent.putExtra("productId", mList.get(position).getProductId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.tv_all_screen:
                tv_all_screen.setTextColor(getResources().getColor(R.color.red_c91014));
                tv_price_screen.setTextColor(getResources().getColor(R.color.textcolor_666666));
                tv_sale_screen.setTextColor(getResources().getColor(R.color.textcolor_666666));
                isPriceFirst = true;
                isSaleFirst = true;
                break;
            case R.id.ll_price_screen:
                if (isPriceFirst) {
                    tv_all_screen.setTextColor(getResources().getColor(R.color.textcolor_666666));
                    tv_price_screen.setTextColor(getResources().getColor(R.color.red_c91014));
                    tv_sale_screen.setTextColor(getResources().getColor(R.color.textcolor_666666));
                    isPriceFirst = false;
                    isSaleFirst = true;
                } else {
                    if (cb_price_screen.isChecked()) {
                        cb_price_screen.setChecked(false);
                    } else {
                        cb_price_screen.setChecked(true);
                    }
                    isPriceFirst = false;
                    isSaleFirst = true;
                }
                break;
            case R.id.ll_sale_screen:
                if (isSaleFirst) {
                    tv_all_screen.setTextColor(getResources().getColor(R.color.textcolor_666666));
                    tv_price_screen.setTextColor(getResources().getColor(R.color.textcolor_666666));
                    tv_sale_screen.setTextColor(getResources().getColor(R.color.red_c91014));
                    isPriceFirst = true;
                    isSaleFirst = false;
                } else {
                    if (cb_sale_screen.isChecked()) {
                        cb_sale_screen.setChecked(false);
                    } else {
                        cb_sale_screen.setChecked(true);
                    }
                    isSaleFirst = false;
                    isPriceFirst = true;
                }
                break;
            //弹出popupwindow

//            case R.id.tv_price_down_up_screen_popup:
//                closeWindow(0);
//                tv_price_screen.setText("价格");
//                iv_price_screen.setImageResource(R.drawable.jiage);
//                break;
//            case R.id.tv_price_up_down_screen_popup:
//                closeWindow(1);
//                tv_price_screen.setText("价格");
//                iv_price_screen.setImageResource(R.drawable.jiage_down);
//                break;
//            case R.id.tv_total_down_up_screen_popup:
//                closeWindow(2);
//                tv_price_screen.setText("总价");
//                iv_price_screen.setImageResource(R.drawable.jiage);
//                break;
//            case R.id.tv_total_up_down_screen_popup:
//                closeWindow(3);
//                tv_price_screen.setText("总价");
//                iv_price_screen.setImageResource(R.drawable.jiage_down);
//                break;
        }
    }

    public void getData() {

        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("result_Screen", categoryId + "------" + arg0.result);
                List<Screen> sonList = new ArrayList<>();
                List<Screen> sonListAppoint = new ArrayList<Screen>();
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                if (obj != null && !"".equals(obj)) {
                    String pointProduct = JsonUtils.getJsonParam(obj, "pointProduct");
                    String ProductList = JsonUtils.getJsonParam(obj, "ProductList");
                    sonList = JsonUtils.parseList(ProductList, Screen.class);
                    sonListAppoint = JsonUtils.parseList(pointProduct, Screen.class);
                    if (status.equals("1")) {
                        //指定买家
                        if (sonListAppoint!=null&&sonListAppoint.size()!=0){
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
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.CATEGORY_DETAIL, ScreenUrl.postClassificationUrl(categoryId), ScreenActivity.this);
    }


//    //popupWindow
//    //    popupwindow
//    private PopupWindow popupWindow;
//    private View popupView;
//
//    public void showWindow(View view) {
//        if (popupWindow == null) {
//            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            popupView = layoutInflater.inflate(R.layout.layout_screen_popupwindow, null);
//            tv_price_down_up_screen_popup = (TextView) popupView.findViewById(R.id.tv_price_down_up_screen_popup);
//            tv_price_up_down_screen_popup = (TextView) popupView.findViewById(R.id.tv_price_up_down_screen_popup);
//            tv_total_down_up_screen_popup = (TextView) popupView.findViewById(R.id.tv_total_down_up_screen_popup);
//            tv_total_up_down_screen_popup = (TextView) popupView.findViewById(R.id.tv_total_up_down_screen_popup);
//            tvList.add(tv_price_down_up_screen_popup);
//            tvList.add(tv_price_up_down_screen_popup);
//            tvList.add(tv_total_down_up_screen_popup);
//            tvList.add(tv_total_up_down_screen_popup);
//            popupWindow = new PopupWindow(popupView, 150, 150);
//        }
////        使其聚焦
//        popupWindow.setFocusable(true);
//        //允许在外点击消失
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.update();
////        点击back 返回
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        popupWindow.showAsDropDown(view, 40, -20);
//
//        tv_price_down_up_screen_popup.setOnClickListener(this);
//        tv_price_up_down_screen_popup.setOnClickListener(this);
//        tv_total_down_up_screen_popup.setOnClickListener(this);
//        tv_total_up_down_screen_popup.setOnClickListener(this);
//    }
//
//    public void closeWindow(int position) {
//        for (int i = 0; i < 4; i++) {
//            if (i == position) {
//                tvList.get(i).setTextColor(getResources().getColor(R.color.red_c91014));
//            } else {
//                tvList.get(i).setTextColor(getResources().getColor(R.color.textcolor_999999));
//            }
//        }
//        if (popupWindow != null) {
//            popupWindow.dismiss();
//        }
//    }
}
