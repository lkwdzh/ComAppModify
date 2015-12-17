package com.aglook.comapp.Activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.ClassificationLeftAdapter;
import com.aglook.comapp.adapter.ClassificationRightAdapter;
import com.aglook.comapp.bean.Classify;
import com.aglook.comapp.bean.ClassifyGV;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.ArrayList;
import java.util.List;

public class ClassifyActivity extends BaseActivity {

    private ListView mLeftList;
    private ListView gv_right;
    private ClassificationLeftAdapter leftAdapter;
    private ClassificationRightAdapter rightAdapter;
    private List<Classify> mList;
    private List<Classify> sonList;
    private List<ClassifyGV> rightList;
    private CustomProgress customProgress;
    @Override
    public void initView() {
        setContentView(R.layout.activity_classify);
        setTitleBar("价格走势");
        ExitApplication.getInstance().addActivity(this);
        init();
        getData();
        click();
    }

    //初始化控件
    public void init(){
        customProgress = CustomProgress.show(ClassifyActivity.this, "加载中···", true);
        mList = new ArrayList<>();
        sonList = new ArrayList<>();
        rightList = new ArrayList<>();
        mLeftList = ((ListView) findViewById(R.id.lv_left));
        gv_right = (ListView) findViewById(R.id.gv_right);
        leftAdapter = new ClassificationLeftAdapter(mList, ClassifyActivity.this);
        mLeftList.setAdapter(leftAdapter);
//        mLeftList.setSelection(3);
        rightAdapter = new ClassificationRightAdapter(ClassifyActivity.this, rightList);
        gv_right.setAdapter(rightAdapter);
    }

    //点击控件
    public void click(){
        mLeftList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //listview选中项
                leftAdapter.setSelectItem(i);
                leftAdapter.notifyDataSetChanged();
//                填充右边的list，并刷新
                rightList.clear();
                rightList.addAll(sonList.get(i).getContent());
                rightAdapter.notifyDataSetChanged();
            }
        });

        gv_right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                跳转到筛选界面
                Intent intent = new Intent(ClassifyActivity.this, HangDetailActivity.class);
                intent.putExtra("url",DefineUtil.TREND+"?categoryId="+rightList.get(i).getCategoryId());
                intent.putExtra("className","价格走势");
                Log.d("result_url",DefineUtil.TREND+"?categoryId="+rightList.get(i).getCategoryId());
                startActivity(intent);
            }
        });

    }

    @Override
    public void widgetClick(View view) {

    }

    //    获取数据
    public void getData() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("result_Classify", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                sonList = JsonUtils.parseList(obj, Classify.class);
                if (status.equals("1")) {
//                    假如成功则分别添加到list中
                    mList.addAll(sonList);
                    rightList.addAll(sonList.get(0).getContent());
                } else {
                    AppUtils.toastText(ClassifyActivity.this, message);
                }
                rightAdapter.notifyDataSetChanged();
                leftAdapter.notifyDataSetChanged();
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.CATEGORY, ClassifyActivity.this);
    }

}
