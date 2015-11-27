package com.aglook.comapp.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.CangDanList;
import com.aglook.comapp.bean.LinkMan;
import com.aglook.comapp.url.GuaDanUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XBitmap;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.Timestamp;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

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
    private EditText et_goods_name_gua_dan;
    private TextView tv_cangdanhao_gua_dan_add;
    private TextView tv_goods_kind_gua_dan;
    private TextView tv_stock_weight_gua_dan;
    private EditText tv_use_weight_gua_dan;
    private TextView et_goods_area_gua_dan;
    private TextView tv_xue_tou_gua_dan_add;
    private TextView et_cang_name_gua_dan;
    private TextView et_cang_phone_gua_dan;
    private TextView et_cang_email_gua_dan;
    private TextView et_cang_address_gua_dan;
    private EditText et_goods_detail_gua_dan;
//    private TextView tv_buyer_gua_dan;
//    private MyGridView gv_gua_dan_add;

    private List<LinkMan> mList = new ArrayList<>();
    private LinearLayout ll_buyer_gua_dan;
    private TextView tv_1_gua_dan_add;
    private TextView tv_2_gua_dan_add;

    private CangDanList cangDan;
    private ImageView iv_huowu;
    private ImageView iv_huoquan;
    private EditText et_price_gua_dan;


    private String originalListid;
    private String tradeNum;
    private String limitDate;
    private String tradePrice;
    private String productName;
    private String designatedUserid;
    private String code = "1003";
    private String productText;


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
        cangDan = (CangDanList) getIntent().getSerializableExtra("guadan");
        tv_in_time_gua_dan_add = (TextView) findViewById(R.id.tv_in_time_gua_dan_add);
        calendar = Calendar.getInstance();
        tv_userful_time_gua_dan_add = (TextView) findViewById(R.id.tv_userful_time_gua_dan_add);

//        tv_goods_image_gua_dan_add = (TextView) findViewById(R.id.tv_goods_image_gua_dan_add);
//        tv_huo_quan_image_gua_dan_add = (TextView) findViewById(R.id.tv_huo_quan_image_gua_dan_add);
        et_goods_name_gua_dan = (EditText) findViewById(R.id.et_goods_name_gua_dan);
        tv_cangdanhao_gua_dan_add = (TextView) findViewById(R.id.tv_cangdanhao_gua_dan_add);
        tv_goods_kind_gua_dan = (TextView) findViewById(R.id.tv_goods_kind_gua_dan);
        tv_stock_weight_gua_dan = (TextView) findViewById(R.id.tv_stock_weight_gua_dan);
        tv_use_weight_gua_dan = (EditText) findViewById(R.id.tv_use_weight_gua_dan);
        et_goods_area_gua_dan = (TextView) findViewById(R.id.et_goods_area_gua_dan);
        tv_xue_tou_gua_dan_add = (TextView) findViewById(R.id.tv_xue_tou_gua_dan_add);
        et_cang_name_gua_dan = (TextView) findViewById(R.id.et_cang_name_gua_dan);
        et_cang_phone_gua_dan = (TextView) findViewById(R.id.et_cang_phone_gua_dan);
        et_cang_email_gua_dan = (TextView) findViewById(R.id.et_cang_email_gua_dan);
        et_cang_address_gua_dan = (TextView) findViewById(R.id.et_cang_address_gua_dan);
        et_goods_detail_gua_dan = (EditText) findViewById(R.id.et_goods_detail_gua_dan);
        et_price_gua_dan = (EditText) findViewById(R.id.et_price_gua_dan);
//        tv_buyer_gua_dan = (TextView) findViewById(R.id.tv_buyer_gua_dan);
//        gv_gua_dan_add = (MyGridView) findViewById(R.id.gv_gua_dan_add);
        ll_buyer_gua_dan = (LinearLayout) findViewById(R.id.ll_buyer_gua_dan);
        tv_1_gua_dan_add = (TextView) findViewById(R.id.tv_1_gua_dan_add);
        tv_2_gua_dan_add = (TextView) findViewById(R.id.tv_2_gua_dan_add);
        iv_huowu = (ImageView) findViewById(R.id.iv_huowu);
        iv_huoquan = (ImageView) findViewById(R.id.iv_huoquan);
        fillData();
    }

    //填充数据
    public void fillData() {
        if (cangDan != null) {
            XBitmap.displayImage(iv_huowu, cangDan.getGetlistPic(), GuaDanAddActivity.this);
            XBitmap.displayImage(iv_huoquan, cangDan.getGoodsOwnerProve(), GuaDanAddActivity.this);
            et_goods_name_gua_dan.setText(cangDan.getPshCategory().getCategoryName());
            tv_cangdanhao_gua_dan_add.setText(cangDan.getListId());
            tv_goods_kind_gua_dan.setText(cangDan.getGoodsType());
            tv_stock_weight_gua_dan.setText(cangDan.getInnerWeight());
            tv_in_time_gua_dan_add.setText(Timestamp.getDateToString(cangDan.getInnerTime()));
            et_goods_area_gua_dan.setText(cangDan.getGoodsPlace());
            tv_xue_tou_gua_dan_add.setText(cangDan.getMark());
            et_cang_name_gua_dan.setText(cangDan.getDepotResponsible());
            et_cang_phone_gua_dan.setText(cangDan.getResponsiblePhone());
            et_cang_email_gua_dan.setText(cangDan.getResponsibleEmail());
            et_cang_address_gua_dan.setText(cangDan.getDepotAddr());
            et_goods_detail_gua_dan.setText(cangDan.getPshCategory().getCategoryDesc());
        }
    }

    //获取输入的值
    public void getInput() {
        originalListid = cangDan.getListId();

        tradeNum = AppUtils.toStringTrim_ET(tv_use_weight_gua_dan);
        tradePrice = AppUtils.toStringTrim_ET(et_price_gua_dan);
//        productName = cangDan.getPshCategory().getCategoryName();
        productName=AppUtils.toStringTrim_ET(et_goods_name_gua_dan);
        productText=AppUtils.toStringTrim_ET(et_goods_detail_gua_dan);
        limitDate=limitDate.replaceAll("-", "");
        designatedUserid = null;
        if (mList.size()!=0) {
            List<String>list=new ArrayList<>();
            for (int i = 0; i < mList.size(); i++) {
            list.add(String.valueOf(mList.get(i).getUserId()));
            }
            String string = list.toString();
            designatedUserid=string.substring(1,string.length()-1).replaceAll(" ","");
            Log.d("designatedUserid",designatedUserid);
        }

        getData();
    }

    public void click() {
//        tv_in_time_gua_dan_add.setOnClickListener(this);
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
            limitDate = df.format(calendar.getTime());
            tv_userful_time_gua_dan_add.setText(limitDate);
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

                getInput();
                break;
            case R.id.ll_buyer_gua_dan:
                intent.setClass(GuaDanAddActivity.this, FriendsListActivity.class);
                intent.putExtra("buyOrLink", true);
                if (mList != null && mList.size() != 0) {
                    intent.putExtra("ToSelect", (Serializable) mList);
                }
                startActivityForResult(intent, 1);
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
                    tv_1_gua_dan_add.setText(mList.get(0).getUserName());
                    tv_2_gua_dan_add.setText("");
                } else if (mList.size() >= 2) {
                    tv_1_gua_dan_add.setText(mList.get(0).getUserName());
                    tv_2_gua_dan_add.setText(mList.get(1).getUserName());
                }
            } else {
                tv_1_gua_dan_add.setText("");
                tv_2_gua_dan_add.setText("");
            }
        }
    }

    //提交
    public void getData() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                Log.d("result_add",arg0.result);
                String message= JsonUtils.getJsonParam(arg0.result,"message");
                String status=JsonUtils.getJsonParam(arg0.result,"status");
                String obj=JsonUtils.getJsonParam(arg0.result,"obj");
                if (status.equals("1")){
                    //成功则跳转
//                  Intent intent=  new Intent(GuaDanAddActivity.this, GuaDanDetailActivity.class);
//                     startActivity(intent);
                GuaDanAddActivity.this.setResult(1);
                    GuaDanAddActivity.this.finish();
                }else {
                    AppUtils.toastText(GuaDanAddActivity.this,message);
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePost(DefineUtil.CANG_DAN, GuaDanUrl.postGuaDanAddUrl(code, DefineUtil.TOKEN, DefineUtil.USERID, originalListid, tradeNum, limitDate, tradePrice, productName, designatedUserid,productText), GuaDanAddActivity.this);
//


    }
}
