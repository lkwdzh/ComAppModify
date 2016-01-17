package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.HelpCenterAdapter;
import com.aglook.comapp.bean.Information;
import com.aglook.comapp.url.SettingUrl;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.ArrayList;
import java.util.List;

public class HelpCenterActivity extends BaseActivity {



    private String className;
    private String classId;
    private CustomProgress customProgress;
    private int pageSize = 10;
    private int pageNumber = 1;
    private List<Information> mList = new ArrayList<>();
    private ListView lv_help_center;
    private HelpCenterAdapter adapter;

    //    private TextView tv_lc_help;
    private View emptyView;

    @Override
    public void initView() {
        setContentView(R.layout.activity_help_center);
        ExitApplication.getInstance().addActivity(this);
        setTitleBar("帮助中心");
        init();
        click();
        getData();
    }

    public void init() {
//        tv_lc_help = (TextView) findViewById(R.id.tv_lc_help);
        lv_help_center = (ListView) findViewById(R.id.lv_help_center);
        adapter = new HelpCenterAdapter(mList, this);
        lv_help_center.setAdapter(adapter);
        emptyView = LayoutInflater.from(this).inflate(R.layout.empty_view_layout, null);
    }

    public void click() {
        lv_help_center.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(HelpCenterActivity.this, ZiXunListActivity.class);
                intent.putExtra("className", mList.get(position).getClassName());
                intent.putExtra("isWebview",false);
                intent.putExtra("classId", mList.get(position).getClassId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void widgetClick(View view) {
    }

    //根据className获取classId;
    public void getId(String name) {
        Information information = null;
        for (int i = 0; i < mList.size(); i++) {
            information = mList.get(i);
            if (information.getClassName().equals(name)) {
                classId = information.getClassId();
//                Log.d("result_classId", classId);
                return;
            } else {
                classId=null;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==33&&resultCode==1){
            mList.clear();
            getData();
        }
    }

    //获取数据
    public void getData() {
        customProgress = CustomProgress.show(this, "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
//                Log.d("result_help", arg0.result);

                if (customProgress!=null&&customProgress.isShowing()){
                    customProgress.dismiss();
                }
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                List<Information> sonList = new ArrayList<Information>();
                if (status.equals("1")) {
                    sonList = JsonUtils.parseList(obj, Information.class);
                    if (sonList != null && sonList.size() != 0) {
                        mList.addAll(sonList);
                    }
//                    if (mList!=null&&mList.size()!=0){
//                        for (int i = 0; i < mList.size(); i++) {
//                            if (mList.get(i).getClassName().equals("软件下载")){
//                                mList.remove(i);
//                            }
//                        }
//                    }
                }
                lv_help_center.setEmptyView(emptyView);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress!=null&&customProgress.isShowing()){
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.SETTING_HELP, SettingUrl.postHelpUrl(String.valueOf(pageSize), String.valueOf(pageNumber)), HelpCenterActivity.this);
    }

}
