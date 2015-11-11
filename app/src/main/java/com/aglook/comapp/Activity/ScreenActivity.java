package com.aglook.comapp.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.ScreenAdapter;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;
import java.util.List;

public class ScreenActivity extends BaseActivity {


    private PullToRefreshGridView gv_screen;
    private ScreenAdapter adapter;
    private TextView tv_sale_screen;
    private LinearLayout ll_price_screen;
    private TextView tv_price_screen;
    private LinearLayout ll_screen_screen;
    private TextView tv_screen_screen;
    private boolean isFirst = true;
    private TextView tv_price_up_down_screen_popup;
    private TextView tv_price_down_up_screen_popup;
    private TextView tv_total_down_up_screen_popup;
    private TextView tv_total_up_down_screen_popup;

    private List<TextView> tvList = new ArrayList<>();
    private ImageView iv_price_screen;

    @Override
    public void initView() {
        setContentView(R.layout.activity_screen);
        setTitleBar("筛选");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init() {
        gv_screen = (PullToRefreshGridView) findViewById(R.id.gv_screen);
        adapter = new ScreenAdapter(ScreenActivity.this);
        gv_screen.setAdapter(adapter);

        tv_sale_screen = (TextView) findViewById(R.id.tv_sale_screen);
        ll_price_screen = (LinearLayout) findViewById(R.id.ll_price_screen);
        tv_price_screen = (TextView) findViewById(R.id.tv_price_screen);
        iv_price_screen = (ImageView) findViewById(R.id.iv_price_screen);
        ll_screen_screen = (LinearLayout) findViewById(R.id.ll_screen_screen);
        tv_screen_screen = (TextView) findViewById(R.id.tv_screen_screen);
        tv_sale_screen.setTextColor(getResources().getColor(R.color.red_c91014));
    }

    public void click() {
        tv_sale_screen.setOnClickListener(this);
        ll_price_screen.setOnClickListener(this);
        ll_screen_screen.setOnClickListener(this);
        gv_screen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ScreenActivity.this, GoodsDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.tv_sale_screen:
                tv_sale_screen.setTextColor(getResources().getColor(R.color.red_c91014));
                tv_price_screen.setTextColor(getResources().getColor(R.color.textcolor_666666));
                tv_screen_screen.setTextColor(getResources().getColor(R.color.textcolor_666666));
                isFirst = true;
                break;
            case R.id.ll_price_screen:
                if (isFirst) {
                    tv_sale_screen.setTextColor(getResources().getColor(R.color.textcolor_666666));
                    tv_price_screen.setTextColor(getResources().getColor(R.color.red_c91014));
                    tv_screen_screen.setTextColor(getResources().getColor(R.color.textcolor_666666));
                    isFirst = false;
                } else {
                    showWindow(view);
                }
                break;
            case R.id.ll_screen_screen:
                tv_sale_screen.setTextColor(getResources().getColor(R.color.textcolor_666666));
                tv_price_screen.setTextColor(getResources().getColor(R.color.textcolor_666666));
                tv_screen_screen.setTextColor(getResources().getColor(R.color.red_c91014));
                isFirst = true;
                break;
            case R.id.tv_price_down_up_screen_popup:
                closeWindow(0);
                tv_price_screen.setText("价格");
                iv_price_screen.setImageResource(R.drawable.jiage);
                break;
            case R.id.tv_price_up_down_screen_popup:
                closeWindow(1);
                tv_price_screen.setText("价格");
                iv_price_screen.setImageResource(R.drawable.jiage_down);
                break;
            case R.id.tv_total_down_up_screen_popup:
                closeWindow(2);
                tv_price_screen.setText("总价");
                iv_price_screen.setImageResource(R.drawable.jiage);
                break;
            case R.id.tv_total_up_down_screen_popup:
                closeWindow(3);
                tv_price_screen.setText("总价");
                iv_price_screen.setImageResource(R.drawable.jiage_down);
                break;
        }
    }


    //popupWindow
    //    popupwindow
    private PopupWindow popupWindow;
    private View popupView;

    public void showWindow(View view) {
        if (popupWindow == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            popupView = layoutInflater.inflate(R.layout.layout_screen_popupwindow, null);
            tv_price_down_up_screen_popup = (TextView) popupView.findViewById(R.id.tv_price_down_up_screen_popup);
            tv_price_up_down_screen_popup = (TextView) popupView.findViewById(R.id.tv_price_up_down_screen_popup);
            tv_total_down_up_screen_popup = (TextView) popupView.findViewById(R.id.tv_total_down_up_screen_popup);
            tv_total_up_down_screen_popup = (TextView) popupView.findViewById(R.id.tv_total_up_down_screen_popup);
            tvList.add(tv_price_down_up_screen_popup);
            tvList.add(tv_price_up_down_screen_popup);
            tvList.add(tv_total_down_up_screen_popup);
            tvList.add(tv_total_up_down_screen_popup);
            popupWindow = new PopupWindow(popupView, 150, 150);
        }
//        使其聚焦
        popupWindow.setFocusable(true);
        //允许在外点击消失
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
//        点击back 返回
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(view, 40, -20);

        tv_price_down_up_screen_popup.setOnClickListener(this);
        tv_price_up_down_screen_popup.setOnClickListener(this);
        tv_total_down_up_screen_popup.setOnClickListener(this);
        tv_total_up_down_screen_popup.setOnClickListener(this);
    }

    public void closeWindow(int position) {
        for (int i = 0; i < 4; i++) {
            if (i == position) {
                tvList.get(i).setTextColor(getResources().getColor(R.color.red_c91014));
            } else {
                tvList.get(i).setTextColor(getResources().getColor(R.color.textcolor_999999));
            }
        }
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }
}
