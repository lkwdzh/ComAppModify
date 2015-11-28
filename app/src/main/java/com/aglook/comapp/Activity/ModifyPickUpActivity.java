package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.adapter.ModifyPickUpAdapter;
import com.aglook.comapp.bean.DriverList;

import java.util.ArrayList;
import java.util.List;

public class ModifyPickUpActivity extends BaseActivity {


    private TextView tv_house_num_my_cangdan;
    private TextView tv_in_time_my_cangdan;
    private ListView lv_modify_pick_up;
    private TextView tv_name_modify_pick_up;
    private TextView tv_weight_modify_pick_up;
    private TextView tv_type_modify_pick_up;
    private LinearLayout ll_modify_pick_up;
    private ModifyPickUpAdapter adapter;
    private int position;
    private List<DriverList> mList = new ArrayList<>();
    private ImageView left_icon;

    @Override
    public void initView() {
        setContentView(R.layout.activity_modify_pick_up_activity2);
        setTitleBar("修改");
        init();
        click();
    }

    public void init() {
        DriverList driverList;
        for (int i = 0; i < 10; i++) {
            driverList=new DriverList();
            driverList.setId(i);
            driverList.setName("王大锤"+i);
            driverList.setStatus("未提货");
            driverList.setWeitht(i+"0吨");
            mList.add(driverList);
        }

        left_icon = (ImageView) findViewById(R.id.left_icon);
        tv_house_num_my_cangdan = (TextView) findViewById(R.id.tv_house_num_my_cangdan);
        tv_in_time_my_cangdan = (TextView) findViewById(R.id.tv_in_time_my_cangdan);
        lv_modify_pick_up = (ListView) findViewById(R.id.lv_modify_pick_up);
        View view = LayoutInflater.from(ModifyPickUpActivity.this).inflate(R.layout.layout_modify_pick_up, null);
        tv_name_modify_pick_up = (TextView) view.findViewById(R.id.tv_name_modify_pick_up);
        tv_name_modify_pick_up.setText("姓名");
        tv_weight_modify_pick_up = (TextView) view.findViewById(R.id.tv_weight_modify_pick_up);
        tv_weight_modify_pick_up.setText("重量");
        tv_type_modify_pick_up = (TextView) view.findViewById(R.id.tv_type_modify_pick_up);
        tv_type_modify_pick_up.setText("提货状态");
        ll_modify_pick_up = (LinearLayout) view.findViewById(R.id.ll_modify_pick_up);
        ll_modify_pick_up.setVisibility(View.INVISIBLE);
        lv_modify_pick_up.addHeaderView(view);
        adapter = new ModifyPickUpAdapter(ModifyPickUpActivity.this, new ModifyPickUpAdapter.CallBackData() {
            @Override
            public void callBackIndex(int index) {
                position = index;
            }
        });
        lv_modify_pick_up.setAdapter(adapter);
    }

    public void click() {
        left_icon.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            DriverList driverList = (DriverList) data.getSerializableExtra("driver");
            mList.remove(position);
            mList.add(position, driverList);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.left_icon:
//        intent.setClass(ModifyPickUpActivity.this, PickUpActivity.class);
                ModifyPickUpActivity.this.setResult(RESULT_OK);
                ModifyPickUpActivity.this.finish();
                break;
        }
    }

    //监听返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            ModifyPickUpActivity.this.setResult(RESULT_OK);
            ModifyPickUpActivity.this.finish();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }

}
