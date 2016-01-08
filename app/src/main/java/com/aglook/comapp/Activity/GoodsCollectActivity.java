package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.ScreenAdapter;
import com.aglook.comapp.bean.Screen;
import com.aglook.comapp.url.GoodsCollectUrl;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.ArrayList;
import java.util.List;

public class GoodsCollectActivity extends BaseActivity {


    private ScreenAdapter adapter;
    private PullToRefreshListView gv_goods;
    private List<Screen> mList = new ArrayList<>();
    private CustomProgress customProgress;
    private boolean isToDetail;
    private View emptyView;
    private int page=1;
    @Override
    public void initView() {
        setContentView(R.layout.activity_friends);
        ExitApplication.getInstance().addActivity(this);
        setTitleBar("我的收藏");
        init();
        click();
        getData();
    }

    public void init() {
        gv_goods = (PullToRefreshListView) findViewById(R.id.gv_goods);
        adapter = new ScreenAdapter(GoodsCollectActivity.this, mList);
        gv_goods.setAdapter(adapter);

        emptyView = LayoutInflater.from(this).inflate(R.layout.empty_view_layout, null);
    }

    public void click() {
        gv_goods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GoodsCollectActivity.this, GoodsDetailActivity.class);
                intent.putExtra("productId", mList.get(position-1).getProductId());
                startActivityForResult(intent, 1);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1&&resultCode==1){
            isToDetail=true;
            getData();
        }else if (requestCode==33&&resultCode==1){
            mList.clear();
            getData();
        }
    }

    @Override
    public void widgetClick(View view) {

    }

    //获取数据
    public void getData() {
        customProgress = CustomProgress.show(GoodsCollectActivity.this, "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }

//                Log.d("result_collect", DefineUtil.TOKEN + "____" + DefineUtil.USERID + "_____" + arg0.result);
                List<Screen> sonList = new ArrayList<>();
                List<Screen> sonListAppoint = new ArrayList<Screen>();
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");

//                if (obj != null && !"".equals(obj)) {
//                    String pointProduct = JsonUtils.getJsonParam(obj, "pointProduct");
//                    String ProductList = JsonUtils.getJsonParam(obj, "ProductList");
//                    sonList = JsonUtils.parseList(ProductList, Screen.class);
//                    sonListAppoint = JsonUtils.parseList(pointProduct, Screen.class);
//                    if (status.equals("1")) {
//                        //指定买家
//                        if (sonListAppoint!=null&&sonListAppoint.size()!=0){
//                            for (int i = 0; i < sonListAppoint.size(); i++) {
//                                sonListAppoint.get(i).setIsAppoint("1");
//                            }
//                            mList.addAll(sonListAppoint);
//                        }
//                        //未指定买家
//                        if (sonList != null && sonList.size() != 0) {
//                            for (int i = 0; i < sonList.size(); i++) {
//                                sonList.get(i).setIsAppoint("0");
//                            }
//                            mList.addAll(sonList);
//                        }
//                    } else {
//                        AppUtils.toastText(GoodsCollectActivity.this, message);
//                    }
//
//                }
                if (status.equals("1")) {
                    if (obj != null && !"".equals(obj)) {
                        sonList = JsonUtils.parseList(obj, Screen.class);
                            if (isToDetail){
                                mList.clear();
                                isToDetail=false;
                            }
                        if (sonList != null && sonList.size() != 0) {
                            mList.addAll(sonList);
//                            Log.d("mlist",mList.toString());
                        }
                    }

                }

                adapter.notifyDataSetChanged();
                gv_goods.onRefreshComplete();
                gv_goods.setEmptyView(emptyView);
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.COLLECT_LIST, GoodsCollectUrl.postGoodsCollectUrl(DefineUtil.USERID, DefineUtil.TOKEN), GoodsCollectActivity.this);
    }

}
