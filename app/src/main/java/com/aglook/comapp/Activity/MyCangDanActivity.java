package com.aglook.comapp.Activity;

import android.util.Log;
import android.view.View;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.MyCangDanAdapter;
import com.aglook.comapp.url.CangDanUrl;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.XHttpuTools;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class MyCangDanActivity extends BaseActivity {


    private PullToRefreshListView lv_my_cang_dan;
    private MyCangDanAdapter adapter;
    private int pageSize = 10;
    private int pageNum = 1;
    private String _sort;


    @Override
    public void initView() {
        setContentView(R.layout.activity_my_cang_dan);
        setTitleBar("仓单");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
        getData();
//        String str = CangDanUrl.postCangDanUrl(DefineUtil.TOKEN, DefineUtil.USERID, String.valueOf(pageSize), String.valueOf(pageNum), _sort).toString();
//        Log.d("str",new Gson().toJson(CangDanUrl.postCangDanUrl(DefineUtil.TOKEN, DefineUtil.USERID, String.valueOf(pageSize), String.valueOf(pageNum), _sort)));
    }

    public void init() {
        lv_my_cang_dan = (PullToRefreshListView) findViewById(R.id.lv_my_cang_dan);
        lv_my_cang_dan.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new MyCangDanAdapter(MyCangDanActivity.this);
        lv_my_cang_dan.setAdapter(adapter);
    }

    public void click() {

    }

    @Override
    public void widgetClick(View view) {

    }

    //获取数据
    public void getData() {
//        new XHttpuTools() {
//            @Override
//            public void initViews(ResponseInfo<String> arg0) {
//                Log.d("result_myCangdan", arg0.result);
//            }
//
//            @Override
//            public void failureInitViews(HttpException arg0, String arg1) {
//
//            }
//        }.dateGet(CangDanUrl.getCangDanUrl(DefineUtil.TOKEN, DefineUtil.USERID, String.valueOf(pageSize), String.valueOf(pageNum), _sort),this);

        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                Log.d("result_myCangdan", arg0.result);
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePost(DefineUtil.CANG_DAN, CangDanUrl.postCangDanUrl(DefineUtil.TOKEN, DefineUtil.USERID, String.valueOf(pageSize), String.valueOf(pageNum), _sort),this);

//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("token", DefineUtil.TOKEN);
//            jsonObject.put("userId", DefineUtil.USERID);
//            jsonObject.put("pageSize", pageSize);
//            jsonObject.put("pageNum", pageNum);
//            jsonObject.put("_sort", _sort);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        Map<String ,Object>params=new HashMap<>();
//        params.put("version","1.0");
//        params.put();
//        params.put();
//        params.put();
    }

}
