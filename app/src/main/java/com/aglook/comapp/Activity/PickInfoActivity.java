package com.aglook.comapp.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.PickInfoAdapter;
import com.aglook.comapp.bean.DriverList;
import com.aglook.comapp.view.MyListView;

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
    private List<DriverList>mList=new ArrayList<>();

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
        lv_pick_info = (MyListView) findViewById(R.id.lv_pick_info);
        adapter = new PickInfoAdapter(PickInfoActivity.this,mList);
        lv_pick_info.setAdapter(adapter);
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
        List<DriverList>getList=new ArrayList<>();
        if (requestCode==1&&resultCode==1){
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
                intent.putExtra("canCheck",true);
                if (mList!=null&&mList.size()!=0){
                    intent.putExtra("ToSelect", (Serializable) mList);
                }
                startActivityForResult(intent, 1);
                break;
            case R.id.right_text:
                for (int i = 0; i < mList.size(); i++) {
                    Log.d("result_mList_display",mList.get(i).getWeitht()+"");
                }
                break;
        }
    }


}
