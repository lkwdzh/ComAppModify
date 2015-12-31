package com.aglook.comapp.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.bean.CangDanDetail;
import com.aglook.comapp.bean.LinkMan;
import com.aglook.comapp.url.CangDanUrl;
import com.aglook.comapp.url.GuaDanUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XBitmap;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
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
//    private TextView tv_userful_time_gua_dan_add;
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

    private ImageView iv_huowu;
    private ImageView iv_huoquan;
    private EditText et_price_gua_dan;


    private String originalListid;
    private String tradeNum;
    private String limitDate;
    private String tradePrice;
    private String productName;
    private String designatedUserid;
    private String code;
    private String codeGua;
    private String productText;

    private String orderdataId;
    private String originalId;
    private boolean isPlate;
    private TextView tv_goods_zhiliang_gua_dan;
    private CustomProgress customProgress;
    @Override
    public void initView() {
        setContentView(R.layout.activity_gua_dan_add);
        setTitleBar("我要挂单");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
        if (isPlate) {
            customProgress = CustomProgress.show(this, "", true);
            getPlatData();
        } else {

            customProgress = CustomProgress.show(this, "", true);
            getData();
        }
    }

    public void init() {
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setVisibility(View.VISIBLE);
        right_text.setText("完成");
        orderdataId = getIntent().getStringExtra("orderdataId");
        originalId = getIntent().getStringExtra("originalId");
        code = getIntent().getStringExtra("code");
        codeGua = getIntent().getStringExtra("codeGua");
        isPlate = getIntent().getBooleanExtra("isPlate", false);
        tv_in_time_gua_dan_add = (TextView) findViewById(R.id.tv_in_time_gua_dan_add);
        calendar = Calendar.getInstance();
//        tv_userful_time_gua_dan_add = (TextView) findViewById(R.id.tv_userful_time_gua_dan_add);

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
        tv_goods_zhiliang_gua_dan = (TextView) findViewById(R.id.tv_goods_zhiliang_gua_dan);
        et_price_gua_dan = (EditText) findViewById(R.id.et_price_gua_dan);
//        tv_buyer_gua_dan = (TextView) findViewById(R.id.tv_buyer_gua_dan);
//        gv_gua_dan_add = (MyGridView) findViewById(R.id.gv_gua_dan_add);
        ll_buyer_gua_dan = (LinearLayout) findViewById(R.id.ll_buyer_gua_dan);
        tv_1_gua_dan_add = (TextView) findViewById(R.id.tv_1_gua_dan_add);
        tv_2_gua_dan_add = (TextView) findViewById(R.id.tv_2_gua_dan_add);
        iv_huowu = (ImageView) findViewById(R.id.iv_huowu);
        iv_huoquan = (ImageView) findViewById(R.id.iv_huoquan);
        et_price_gua_dan.addTextChangedListener(new MyTextWatcher());
    }

    class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // TODO Auto-generated method stub

        }

        @Override
        public void afterTextChanged(Editable s) {
            String temp = s.toString();
            int d = temp.indexOf(".");
            if (d < 0) {
//                if (temp.length() > 3) {
//
//                    s.delete(3, 4);
//                }
                return;
            }
            if (temp.length() - d - 1 > 2) {
                s.delete(d + 3, d + 4);
            } else if (d == 0) {
                s.delete(d, d + 1);
            }

        }
    }

    //填充数据
    public void fillData() {
            XBitmap.displayImage(iv_huowu, cangDanDetail.getProductLogo(), GuaDanAddActivity.this);
            XBitmap.displayImage(iv_huoquan, cangDanDetail.getProductOwnerProve(), GuaDanAddActivity.this);
        et_goods_name_gua_dan.setText(cangDanDetail.getProductName());
        tv_cangdanhao_gua_dan_add.setText(cangDanDetail.getOriginalListId());
        tv_goods_kind_gua_dan.setText(cangDanDetail.getCategoryName());
        tv_stock_weight_gua_dan.setText(cangDanDetail.getWeightUseable() + "吨");
        if (cangDanDetail.getInnerTime() != null && !"".equals(cangDanDetail.getInnerTime())) {
            tv_in_time_gua_dan_add.setText(Timestamp.getDateToString(cangDanDetail.getInnerTime()));
        }
        et_goods_area_gua_dan.setText(cangDanDetail.getGoodsPlace());
        tv_xue_tou_gua_dan_add.setText(cangDanDetail.getMark());
        et_cang_name_gua_dan.setText(cangDanDetail.getDepotResponsible());
        et_cang_phone_gua_dan.setText(cangDanDetail.getResponsibleMobile());
        et_cang_email_gua_dan.setText(cangDanDetail.getResponsibleEmail());
        et_cang_address_gua_dan.setText(cangDanDetail.getDepotAddress());
        tv_goods_zhiliang_gua_dan.setText(cangDanDetail.getDepotQuality());
        //监听Edittext数据变化
        tv_use_weight_gua_dan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String temp = s.toString();
                int d = temp.indexOf(".");
                if (d < 0) {
//                if (temp.length() > 3) {
//
//                    s.delete(3, 4);
//                }
                    return;
                }
                if (temp.length() - d - 1 > 2) {
                    s.delete(d + 3, d + 4);
                } else if (d == 0) {
                    s.delete(d, d + 1);
                }
            }
        });
    }

    //获取输入的值
    public void getInput() {
        originalListid = cangDanDetail.getOriginalListId();
        orderdataId = cangDanDetail.getOrderdataId();
        tradeNum = AppUtils.toStringTrim_ET(tv_use_weight_gua_dan);
        tradePrice = AppUtils.toStringTrim_ET(et_price_gua_dan);
//        productName = cangDan.getPshCategory().getCategoryName();
        productName = AppUtils.toStringTrim_ET(et_goods_name_gua_dan);
        productText = AppUtils.toStringTrim_ET(et_goods_detail_gua_dan);
//        if (limitDate != null) {
//            limitDate = limitDate.replaceAll("-", "");
//        }
        designatedUserid = null;
        if (mList.size() != 0) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < mList.size(); i++) {
                list.add(String.valueOf(mList.get(i).getUserId()));
            }
            String string = list.toString();
            designatedUserid = string.substring(1, string.length() - 1).replaceAll(" ", "");

        }
        //判断参数是否为空
        if (productName==null||"".equals(productName)){
            AppUtils.toastText(GuaDanAddActivity.this,"商品名称不能为空");
            return;
        }

        if (tradeNum==null||"".equals(tradeNum)){
            AppUtils.toastText(GuaDanAddActivity.this,"商品交易数量不能为空");
            return;
        }
        if (tradePrice==null||"".equals(tradePrice)){
            AppUtils.toastText(GuaDanAddActivity.this,"商品交易价格不能为空");
            return;
        }

//        if (limitDate==null||"".equals(limitDate)){
//            AppUtils.toastText(GuaDanAddActivity.this,"挂单有效期不能为空");
//            return;
//        }

        if (isPlate) {
            customProgress = CustomProgress.show(this, "", true);
            upPlatData();
        } else {
            customProgress = CustomProgress.show(this, "", true);
            upData();
        }

    }

    public void click() {
//        tv_userful_time_gua_dan_add.setOnClickListener(this);
        right_text.setOnClickListener(this);
        ll_buyer_gua_dan.setOnClickListener(this);
    }


    //    日期选择对话框的监听
    private DatePickerDialog.OnDateSetListener listenerUseful = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            df = new SimpleDateFormat("yyyy-MM-dd");
            limitDate = df.format(calendar.getTime());
//            tv_userful_time_gua_dan_add.setText(limitDate);
        }
    };


    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
//            case R.id.tv_userful_time_gua_dan_add:
//                DatePickerDialog datePickerDialogUseful = new DatePickerDialog(GuaDanAddActivity.this, listenerUseful, calendar.get(Calendar.YEAR),
//                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
//                datePickerDialogUseful.show();
//                break;
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
        }else if (requestCode==33&&resultCode==1){
            if (isPlate) {
                customProgress = CustomProgress.show(this, "", true);
                getPlatData();
            } else {

                customProgress = CustomProgress.show(this, "", true);
                getData();
            }
        }
    }

    //提交
    public void upData() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("result_add", orderdataId + "___" + originalListid + "__" + arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                if (status.equals("1")) {
                    //成功则跳转
                    GuaDanAddActivity.this.setResult(1);
                    GuaDanAddActivity.this.finish();
                }

            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.CANG_DAN, GuaDanUrl.postGuaDanAddUrl(codeGua, DefineUtil.TOKEN, DefineUtil.USERID, originalListid, tradeNum, limitDate, tradePrice, productName, designatedUserid, productText), GuaDanAddActivity.this);
    }

    //提交
    public void upPlatData() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("result_add_plate", orderdataId + "___" + originalListid + "__" + arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                if (status.equals("1")) {
                    //成功则跳转
                    GuaDanAddActivity.this.setResult(1);
                    GuaDanAddActivity.this.finish();
                }

            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.CANG_DAN, GuaDanUrl.postGuaDanPlateAddUrl(codeGua, DefineUtil.TOKEN, DefineUtil.USERID, originalListid, tradeNum, limitDate, tradePrice, productName, designatedUserid, productText, orderdataId), GuaDanAddActivity.this);
    }


    private CangDanDetail cangDanDetail;

    //获取详情
    public void getData() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("result_detail", originalId + "____" + arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                cangDanDetail = JsonUtils.parse(obj, CangDanDetail.class);
                if (status.equals("1")) {
                    if (cangDanDetail != null) {
                        fillData();
                    }
                }

            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.CANG_DAN, CangDanUrl.postCangDanDetailUrl(code, DefineUtil.TOKEN, DefineUtil.USERID, originalId), GuaDanAddActivity.this);
    }


    //获取平台详情
    public void getPlatData() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("result_Platdetail", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                cangDanDetail = JsonUtils.parse(obj, CangDanDetail.class);
                if (status.equals("1")) {
                    if (cangDanDetail != null) {
                        fillData();
                    }
                }

            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.CANG_DAN, CangDanUrl.postPlatCangDanDetailUrl(code, DefineUtil.TOKEN, DefineUtil.USERID, orderdataId), GuaDanAddActivity.this);
    }
}
