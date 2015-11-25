package com.aglook.comapp.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.LinkMan;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GuaDanAddActivity extends BaseActivity {


    private TextView right_text;
    private TextView tv_in_time_gua_dan_add;

    private Calendar calendar;
    private SimpleDateFormat df;
    private String dateIn;
    private String dateUseful;
    private TextView tv_userful_time_gua_dan_add;
    //    private TextView tv_goods_image_gua_dan_add;
//    private TextView tv_huo_quan_image_gua_dan_add;
    private TextView et_goods_name_gua_dan;
    private TextView tv_cangdanhao_gua_dan_add;
    private TextView tv_goods_kind_gua_dan;
    private TextView tv_stock_weight_gua_dan;
    private TextView tv_use_weight_gua_dan;
    private TextView et_goods_area_gua_dan;
    private TextView tv_xue_tou_gua_dan_add;
    private TextView et_cang_name_gua_dan;
    private TextView et_cang_phone_gua_dan;
    private TextView et_cang_email_gua_dan;
    private TextView et_cang_address_gua_dan;
    private TextView et_goods_detail_gua_dan;
//    private TextView tv_buyer_gua_dan;
//    private MyGridView gv_gua_dan_add;

    private List<LinkMan> mList = new ArrayList<>();
    private LinearLayout ll_buyer_gua_dan;
    private TextView tv_1_gua_dan_add;
    private TextView tv_2_gua_dan_add;

    @Override
    public void initView() {
        setContentView(R.layout.activity_gua_dan_add);
        setTitleBar("我要挂单");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init() {
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setVisibility(View.VISIBLE);
        right_text.setText("完成");

        tv_in_time_gua_dan_add = (TextView) findViewById(R.id.tv_in_time_gua_dan_add);
        calendar = Calendar.getInstance();
        tv_userful_time_gua_dan_add = (TextView) findViewById(R.id.tv_userful_time_gua_dan_add);

//        tv_goods_image_gua_dan_add = (TextView) findViewById(R.id.tv_goods_image_gua_dan_add);
//        tv_huo_quan_image_gua_dan_add = (TextView) findViewById(R.id.tv_huo_quan_image_gua_dan_add);
        et_goods_name_gua_dan = (TextView) findViewById(R.id.et_goods_name_gua_dan);
        tv_cangdanhao_gua_dan_add = (TextView) findViewById(R.id.tv_cangdanhao_gua_dan_add);
        tv_goods_kind_gua_dan = (TextView) findViewById(R.id.tv_goods_kind_gua_dan);
        tv_stock_weight_gua_dan = (TextView) findViewById(R.id.tv_stock_weight_gua_dan);
        tv_use_weight_gua_dan = (TextView) findViewById(R.id.tv_use_weight_gua_dan);
        et_goods_area_gua_dan = (TextView) findViewById(R.id.et_goods_area_gua_dan);
        tv_xue_tou_gua_dan_add = (TextView) findViewById(R.id.tv_xue_tou_gua_dan_add);
        et_cang_name_gua_dan = (TextView) findViewById(R.id.et_cang_name_gua_dan);
        et_cang_phone_gua_dan = (TextView) findViewById(R.id.et_cang_phone_gua_dan);
        et_cang_email_gua_dan = (TextView) findViewById(R.id.et_cang_email_gua_dan);
        et_cang_address_gua_dan = (TextView) findViewById(R.id.et_cang_address_gua_dan);
        et_goods_detail_gua_dan = (TextView) findViewById(R.id.et_goods_detail_gua_dan);
//        tv_buyer_gua_dan = (TextView) findViewById(R.id.tv_buyer_gua_dan);
//        gv_gua_dan_add = (MyGridView) findViewById(R.id.gv_gua_dan_add);
        ll_buyer_gua_dan = (LinearLayout) findViewById(R.id.ll_buyer_gua_dan);
        tv_1_gua_dan_add = (TextView) findViewById(R.id.tv_1_gua_dan_add);
        tv_2_gua_dan_add = (TextView) findViewById(R.id.tv_2_gua_dan_add);
    }

    public void click() {
        tv_in_time_gua_dan_add.setOnClickListener(this);
        tv_userful_time_gua_dan_add.setOnClickListener(this);
        right_text.setOnClickListener(this);
        ll_buyer_gua_dan.setOnClickListener(this);
    }

    //    日期选择对话框的监听
    private DatePickerDialog.OnDateSetListener listenerIn = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            df = new SimpleDateFormat("yyyy-MM-dd");
            dateIn = df.format(calendar.getTime());
            tv_in_time_gua_dan_add.setText(dateIn);
        }
    };

    //    日期选择对话框的监听
    private DatePickerDialog.OnDateSetListener listenerUseful = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            df = new SimpleDateFormat("yyyy-MM-dd");
            dateUseful = df.format(calendar.getTime());
            tv_userful_time_gua_dan_add.setText(dateUseful);
        }
    };


    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_in_time_gua_dan_add:
                DatePickerDialog datePickerDialogIn = new DatePickerDialog(GuaDanAddActivity.this, listenerIn, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialogIn.show();
                break;
            case R.id.tv_userful_time_gua_dan_add:
                DatePickerDialog datePickerDialogUseful = new DatePickerDialog(GuaDanAddActivity.this, listenerUseful, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialogUseful.show();
                break;
            case R.id.right_text:
                intent.setClass(GuaDanAddActivity.this, GuaDanDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_buyer_gua_dan:
                intent.setClass(GuaDanAddActivity.this, BuyerListActivity.class);
                intent.putExtra("buyOrLink", true);
                if (mList!=null&&mList.size()!=0) {
                    intent.putExtra("ToSelect", (Serializable) mList);
                }
                startActivityForResult(intent,1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        List<LinkMan> getList = new ArrayList<>();
        if (requestCode == 1 && resultCode == 1) {
            getList = (List<LinkMan>) data.getSerializableExtra("setSelected");
            mList.clear();
            mList.addAll(getList);
            //根据list长度填充
            if (mList != null && mList.size() != 0) {
                if (mList.size() == 1) {
                    tv_1_gua_dan_add.setText(mList.get(0).getName());
                    tv_2_gua_dan_add.setText("");
                } else if (mList.size() >= 2) {
                    tv_1_gua_dan_add.setText(mList.get(0).getName());
                    tv_2_gua_dan_add.setText(mList.get(1).getName());
                }
            }else {
                tv_1_gua_dan_add.setText("");
                tv_2_gua_dan_add.setText("");
            }
        }
    }
}
