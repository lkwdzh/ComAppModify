package com.aglook.comapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.BuyerListAdapter;
import com.aglook.comapp.bean.LinkMan;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BuyerListActivity extends BaseActivity {

    private TextView right_text;
    private PullToRefreshListView lv_buyer_list;
    private TextView tv_num_buyer_list;
    private TextView tv_confirm_buyer_list;
    private boolean isBuyer;
    private BuyerListAdapter adapter;
    private RelativeLayout rl_bottom;
    private List<LinkMan>mList=new ArrayList<>();
    private List<LinkMan>getList=new ArrayList<>();
    private int total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_buyer_list);
        setTitleBar("联系人列表");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
        getData();
    }

    public void init(){
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setText("添加");
        right_text.setVisibility(View.VISIBLE);
        lv_buyer_list = (PullToRefreshListView) findViewById(R.id.lv_buyer_list);
        tv_num_buyer_list = (TextView) findViewById(R.id.tv_num_buyer_list);
        tv_confirm_buyer_list = (TextView) findViewById(R.id.tv_confirm_buyer_list);
        isBuyer=getIntent().getBooleanExtra("buyOrLink", false);
        adapter = new BuyerListAdapter(BuyerListActivity.this, isBuyer,mList,new BuyerListAdapter.CallBackData() {
            @Override
            public void callBack(int num) {
                tv_num_buyer_list.setText(num+"");
            }
        });
        lv_buyer_list.setAdapter(adapter);
        rl_bottom = (RelativeLayout) findViewById(R.id.rl_bottom);
        if (isBuyer){
            rl_bottom.setVisibility(View.VISIBLE);
        }else {
            rl_bottom.setVisibility(View.GONE);
        }

        getList= (List<LinkMan>) getIntent().getSerializableExtra("ToSelect");
    }

    public void click(){
        right_text.setOnClickListener(this);
        lv_buyer_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BuyerListActivity.this, FriendsDetailActivity.class);
                startActivity(intent);
            }
        });
        tv_confirm_buyer_list.setOnClickListener(this);
    }


    //比较从网络获取的list与接收的list
    public void compareList(){
        if ((getList!=null&&getList.size()!=0)&&(mList!=null&&mList.size()!=0)){
            for (int i = 0; i < getList.size(); i++) {
                for (int j = 0; j < mList.size(); j++) {
                    if (getList.get(i).getId()==mList.get(j).getId()){
                        mList.get(j).setChecked(getList.get(i).isChecked());
                        total++;
                    }
                }
            }
        }
        tv_num_buyer_list.setText(total+"");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1&&resultCode==1){
            getData();
        }
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.right_text:
                intent.setClass(BuyerListActivity.this,BuyerAddActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_confirm_buyer_list:
                //取出所有选中项
                List<LinkMan>setList=new ArrayList<>();
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).isChecked()){
                        setList.add(mList.get(i));
                    }
                }

                intent.setClass(BuyerListActivity.this,GuaDanAddActivity.class);
                intent.putExtra("setSelected", (Serializable) setList);
                Log.d("11111",setList.size()+"");
                BuyerListActivity.this.setResult(1,intent);
                BuyerListActivity.this.finish();
                break;
        }
    }


    //获取数据
    public void getData(){
        List<LinkMan>llList=new ArrayList<>();
        LinkMan linkMan=null;
        for (int i = 0; i < 30; i++) {
            linkMan=new LinkMan();
            linkMan.setId(i);
            linkMan.setName("白客"+i+"号");
            llList.add(linkMan);
        }
        mList.addAll(llList);
        compareList();
        adapter.notifyDataSetChanged();
    }

}
