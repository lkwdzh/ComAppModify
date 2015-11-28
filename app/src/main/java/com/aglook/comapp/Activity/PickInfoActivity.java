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
import com.aglook.comapp.bean.CangDanList;
import com.aglook.comapp.bean.DriverList;
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
    private TextView tv_quality_pick_info;
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

    private CangDanList cangDan;
    private ImageView iv_huoquan;
    private ImageView iv_huowu;

    private String code = "3002";
    private String originalListId;
    private String orderdataId;
    private String deliveryNum;
    private JSONArray dirverList;
    private CustomProgress customProgress;
    @Override
    public void initView() {
        setContentView(R.layout.activity_pick_info);
        setTitleBar("填写提货信息");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init() {
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setText("提交");
        right_text.setVisibility(View.VISIBLE);
        cangDan = (CangDanList) getIntent().getSerializableExtra("tihuo");
//        tv_goods_image_pick_info = (TextView) findViewById(R.id.tv_goods_image_pick_info);
//        tv_huo_quan_image_pick_info = (TextView) findViewById(R.id.tv_huo_quan_image_pick_info);
        et_goods_name_pick_info = (TextView) findViewById(R.id.et_goods_name_pick_info);
        tv_cangdanhao_pick_infod = (TextView) findViewById(R.id.tv_cangdanhao_pick_infod);
        tv_goods_kind_pick_info = (TextView) findViewById(R.id.tv_goods_kind_pick_info);
        tv_use_weight_pick_info = (TextView) findViewById(R.id.tv_use_weight_pick_info);
        tv_in_time_gua_pick_info = (TextView) findViewById(R.id.tv_in_time_gua_pick_info);
        tv_goods_area_pick_info = (TextView) findViewById(R.id.tv_goods_area_pick_info);
        tv_quality_pick_info = (TextView) findViewById(R.id.tv_quality_pick_info);
        et_pick_weight_pick_info = (EditText) findViewById(R.id.et_pick_weight_pick_info);
        tv_xue_tou_pick_info = (TextView) findViewById(R.id.tv_xue_tou_pick_info);
        tv_cang_name_pick_info = (TextView) findViewById(R.id.tv_cang_name_pick_info);
        tv_cang_phone_pick_info = (TextView) findViewById(R.id.tv_cang_phone_pick_info);
        tv_cang_email_pick_info = (TextView) findViewById(R.id.tv_cang_email_pick_info);
        tv_cang_address_pick_info = (TextView) findViewById(R.id.tv_cang_address_pick_info);
        tv_buyer_pick_info = (TextView) findViewById(R.id.tv_buyer_pick_info);
        iv_huowu = (ImageView) findViewById(R.id.iv_huowu);
        iv_huoquan = (ImageView) findViewById(R.id.iv_huoquan);
        lv_pick_info = (MyListView) findViewById(R.id.lv_pick_info);
        adapter = new PickInfoAdapter(PickInfoActivity.this, mList);
        lv_pick_info.setAdapter(adapter);
        fillData();
    }

    //填充数据
    public void fillData() {
        if (cangDan != null) {
            XBitmap.displayImage(iv_huowu, cangDan.getGetlistPic(), PickInfoActivity.this);
            XBitmap.displayImage(iv_huoquan, cangDan.getGoodsOwnerProve(), PickInfoActivity.this);
            et_goods_name_pick_info.setText(cangDan.getPshCategory().getCategoryName());
            tv_cangdanhao_pick_infod.setText(cangDan.getListId());
            tv_goods_kind_pick_info.setText(cangDan.getGoodsType());
            tv_use_weight_pick_info.setText(cangDan.getWeightUseable() + "吨");
            tv_in_time_gua_pick_info.setText(Timestamp.getDateToString(cangDan.getInnerTime()));
            tv_goods_area_pick_info.setText(cangDan.getGoodsPlace());
            tv_xue_tou_pick_info.setText(cangDan.getMark());
            tv_cang_name_pick_info.setText(cangDan.getDepotResponsible());
            tv_cang_phone_pick_info.setText(cangDan.getResponsiblePhone());
            tv_cang_email_pick_info.setText(cangDan.getResponsibleEmail());
            tv_cang_address_pick_info.setText(cangDan.getDepotAddr());
        }
    }

    //获取参数值
    public void getInput() {
        originalListId = cangDan.getListId();
//        originalListId="201511045639c51f390fc";
//        orderdataId = "63";
        deliveryNum = AppUtils.toStringTrim_ET(et_pick_weight_pick_info);
//        JSONObject jsonObject = new JSONObject();
//        JSONArray jsonArray = new JSONArray();
//        jsonObject.put("id","1");
//        jsonObject.put("getWeight",2);
//        jsonArray.add(jsonObject);


//        dirverList=jsonArray;

        Log.d("aaaaa", originalListId + "___" + orderdataId + "____" + deliveryNum + "____" + dirverList);
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
                    Log.d("result_mList_display", mList.get(i).getWeitht() + "");
                }
                customProgress = CustomProgress.show(this, "提交中···", true);
                getInput();
                pickUp();
                break;
        }
    }

    //申请提货
    public void pickUp() {
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
                } else {
                    AppUtils.toastText(PickInfoActivity.this, message);
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.CANG_DAN, PickInfoUrl.postPickInfoUrl(code, DefineUtil.TOKEN, DefineUtil.USERID, originalListId, orderdataId, deliveryNum, dirverList), PickInfoActivity.this);
    }


}
