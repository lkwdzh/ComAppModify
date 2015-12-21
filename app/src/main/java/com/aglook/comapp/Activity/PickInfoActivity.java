package com.aglook.comapp.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.PickInfoAdapter;
import com.aglook.comapp.bean.CangDanDetail;
import com.aglook.comapp.bean.DriverList;
import com.aglook.comapp.url.CangDanUrl;
import com.aglook.comapp.url.PickInfoUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XBitmap;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.aglook.comapp.view.MyListView;
import com.aglook.comapp.view.Timestamp;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import net.sf.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PickInfoActivity extends BaseActivity {


    private TextView right_text;
    //    private TextView tv_goods_image_pick_info;
//    private TextView tv_huo_quan_image_pick_info;
    private TextView et_goods_name_pick_info;
    private TextView tv_cangdanhao_pick_infod;
    private TextView tv_goods_kind_pick_info;
    private TextView tv_use_weight_pick_info;
    private TextView tv_in_time_gua_pick_info;
    private TextView tv_goods_area_pick_info;
    private EditText et_pick_weight_pick_info;
    private TextView tv_xue_tou_pick_info;
    private TextView tv_cang_name_pick_info;
    private TextView tv_cang_phone_pick_info;
    private TextView tv_cang_email_pick_info;
    private TextView tv_cang_address_pick_info;
    private TextView tv_buyer_pick_info;
    private MyListView lv_pick_info;
    private PickInfoAdapter adapter;
    private List<DriverList> mList = new ArrayList<>();

    private ImageView iv_huoquan;
    private ImageView iv_huowu;

    private String codePick = "3002";
    private String originalListId;
    private String deliveryNum;
    private JSONArray dirverList;
    private CustomProgress customProgress;
    private boolean isPlate;
    private String code;
    private String orderdataId;
    private String originalId;
    private TextView tv_goods_zhiliang_gua_dan;

    @Override
    public void initView() {
        setContentView(R.layout.activity_pick_info);
        setTitleBar("填写提货信息");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
        if (isPlate){
            getPlatData();
        }else {
            getData();
        }
    }

    public void init() {
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setText("提交");
        right_text.setVisibility(View.VISIBLE);
        orderdataId = getIntent().getStringExtra("orderdataId");
        originalId=getIntent().getStringExtra("originalId");
        code=getIntent().getStringExtra("code");
        isPlate=getIntent().getBooleanExtra("isPlate",false);
//        tv_goods_image_pick_info = (TextView) findViewById(R.id.tv_goods_image_pick_info);
//        tv_huo_quan_image_pick_info = (TextView) findViewById(R.id.tv_huo_quan_image_pick_info);
        et_goods_name_pick_info = (TextView) findViewById(R.id.et_goods_name_pick_info);
        tv_cangdanhao_pick_infod = (TextView) findViewById(R.id.tv_cangdanhao_pick_infod);
        tv_goods_kind_pick_info = (TextView) findViewById(R.id.tv_goods_kind_pick_info);
        tv_use_weight_pick_info = (TextView) findViewById(R.id.tv_use_weight_pick_info);
        tv_in_time_gua_pick_info = (TextView) findViewById(R.id.tv_in_time_gua_pick_info);
        tv_goods_area_pick_info = (TextView) findViewById(R.id.tv_goods_area_pick_info);
        et_pick_weight_pick_info = (EditText) findViewById(R.id.et_pick_weight_pick_info);
        tv_xue_tou_pick_info = (TextView) findViewById(R.id.tv_xue_tou_pick_info);
        tv_cang_name_pick_info = (TextView) findViewById(R.id.tv_cang_name_pick_info);
        tv_cang_phone_pick_info = (TextView) findViewById(R.id.tv_cang_phone_pick_info);
        tv_cang_email_pick_info = (TextView) findViewById(R.id.tv_cang_email_pick_info);
        tv_cang_address_pick_info = (TextView) findViewById(R.id.tv_cang_address_pick_info);
        tv_buyer_pick_info = (TextView) findViewById(R.id.tv_buyer_pick_info);
        tv_goods_zhiliang_gua_dan = (TextView) findViewById(R.id.tv_goods_zhiliang_gua_dan);
        iv_huowu = (ImageView) findViewById(R.id.iv_huowu);
        iv_huoquan = (ImageView) findViewById(R.id.iv_huoquan);
        lv_pick_info = (MyListView) findViewById(R.id.lv_pick_info);
        adapter = new PickInfoAdapter(PickInfoActivity.this, mList);
        lv_pick_info.setAdapter(adapter);
    }

    //填充数据
    public void fillData() {
        XBitmap.displayImage(iv_huowu, cangDanDetail.getProductLogo(), PickInfoActivity.this);
        XBitmap.displayImage(iv_huoquan, cangDanDetail.getProductOwnerProve(), PickInfoActivity.this);
            et_goods_name_pick_info.setText(cangDanDetail.getProductName());
            tv_cangdanhao_pick_infod.setText(cangDanDetail.getOriginalListId());
            tv_goods_kind_pick_info.setText(cangDanDetail.getCategoryName());
            tv_use_weight_pick_info.setText(cangDanDetail.getWeightUseable()+"吨");
        if (cangDanDetail.getInnerTime()!=null&&!"".equals(cangDanDetail.getInnerTime())) {
            tv_in_time_gua_pick_info.setText(Timestamp.getDateToString(cangDanDetail.getInnerTime()));
        }
            tv_goods_area_pick_info.setText(cangDanDetail.getGoodsPlace());
            tv_xue_tou_pick_info.setText(cangDanDetail.getMark());
            tv_cang_name_pick_info.setText(cangDanDetail.getDepotResponsible());
            tv_cang_phone_pick_info.setText(cangDanDetail.getResponsibleMobile());
            tv_cang_email_pick_info.setText(cangDanDetail.getResponsibleEmail());
            tv_cang_address_pick_info.setText(cangDanDetail.getDepotAddress());
        tv_goods_zhiliang_gua_dan.setText(cangDanDetail.getDepotQuality());
    }

    //获取参数值
    public void getInput() {
        originalListId = cangDanDetail.getOriginalListId();
        orderdataId=cangDanDetail.getOrderdataId();
        deliveryNum = AppUtils.toStringTrim_ET(et_pick_weight_pick_info);

    }


    public void click() {
        tv_buyer_pick_info.setOnClickListener(this);
        lv_pick_info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(PickInfoActivity.this, DriverInfoActivity.class);
                startActivity(intent);
            }
        });
        right_text.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        List<DriverList> getList = new ArrayList<>();
        if (requestCode == 1 && resultCode == 1) {
            getList = (List<DriverList>) data.getSerializableExtra("Selected");
            mList.clear();
            mList.addAll(getList);
            adapter.notifyDataSetChanged();
        }else if (requestCode==33&&resultCode==1){
            if (isPlate){
                getPlatData();
            }else {
                getData();
            }
        }
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.tv_buyer_pick_info:
                intent.setClass(PickInfoActivity.this, DriverListActivity.class);
                intent.putExtra("canCheck", true);
                if (mList != null && mList.size() != 0) {
                    intent.putExtra("ToSelect", (Serializable) mList);
                }
                startActivityForResult(intent, 1);
                break;
            case R.id.right_text:
                for (int i = 0; i < mList.size(); i++) {
//                    Log.d("result_mList_display", mList.get(i).getWeitht() + "");
                }

                getInput();
                pickUp();
                break;
        }
    }

    //申请提货
    public void pickUp() {
        customProgress = CustomProgress.show(this, "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("result_pickinfo", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                if (status.equals("1")) {
                    PickInfoActivity.this.setResult(RESULT_OK);
                    PickInfoActivity.this.finish();
                }

            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePostUp(DefineUtil.CANG_DAN, PickInfoUrl.postPickInfoUrl(codePick, DefineUtil.TOKEN, DefineUtil.USERID, originalListId, orderdataId, deliveryNum,mList), PickInfoActivity.this);
    }

    private CangDanDetail cangDanDetail;
    //获取详情
    public void getData(){
        customProgress = CustomProgress.show(this, "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("result_detail",originalId+"____"+arg0.result);
                String message=JsonUtils.getJsonParam(arg0.result,"message");
                String status=JsonUtils.getJsonParam(arg0.result,"status");
                String obj=JsonUtils.getJsonParam(arg0.result,"obj");
                cangDanDetail=JsonUtils.parse(obj,CangDanDetail.class);
                if (status.equals("1")){
                    if (cangDanDetail!=null){
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
        }.datePost(DefineUtil.CANG_DAN, CangDanUrl.postCangDanDetailUrl(code, DefineUtil.TOKEN, DefineUtil.USERID, originalId),PickInfoActivity.this);
    }


    //获取平台详情
    public void getPlatData(){
        customProgress = CustomProgress.show(this, "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("result_Platdetail",arg0.result);
                String message=JsonUtils.getJsonParam(arg0.result,"message");
                String status=JsonUtils.getJsonParam(arg0.result,"status");
                String obj=JsonUtils.getJsonParam(arg0.result,"obj");
                cangDanDetail=JsonUtils.parse(obj,CangDanDetail.class);
                if (status.equals("1")){
                    if (cangDanDetail!=null){
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
        }.datePost(DefineUtil.CANG_DAN, CangDanUrl.postPlatCangDanDetailUrl(code, DefineUtil.TOKEN, DefineUtil.USERID, orderdataId),PickInfoActivity.this);
    }
}
