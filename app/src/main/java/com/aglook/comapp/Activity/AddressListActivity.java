package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.adapter.AddressListAdapter;

public class AddressListActivity extends BaseActivity {

    private final int ADDADDRESS = 1;

    private ListView lv_add_list;
    private TextView right_text;
    private AddressListAdapter adapter;


    @Override
    public void initView() {
        setContentView(R.layout.activity_address_list);
        setTitleBar("地址管理");
        init();
        click();
    }

    public void init() {
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setText("新增");
        right_text.setVisibility(View.VISIBLE);
        lv_add_list = (ListView) findViewById(R.id.lv_add_list);
        adapter = new AddressListAdapter(this);
        lv_add_list.setAdapter(adapter);
    }

    public void click() {
        right_text.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.right_text:
                intent.setClass(AddressListActivity.this, AddAddressActivity.class);
                startActivityForResult(intent, ADDADDRESS);
                break;
        }
    }


}
