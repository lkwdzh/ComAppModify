package com.aglook.comapp.Activity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.PlatformAdapter;
import com.aglook.comapp.bean.PlatformCangDan;
import com.aglook.comapp.bean.PlatformCangDanList;
import com.aglook.comapp.url.PlateFormUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.ArrayList;
import java.util.List;

public class PlatformActivity extends BaseActivity {


    private PullToRefreshListView lv_my_platform;
    private PlatformAdapter adapter;
    private String code = "2001";
    private int pageSize = 10;
    private int pageNum = 1;
    private String _sort;

    private PlatformCangDan platformCangDan;
    private List<PlatformCangDanList>mList=new ArrayList<>();
    private CustomProgress customProgress;
    private View emptyView;
    @Override
    public void initView() {
        setContentView(R.layout.activity_platform);
        setTitleBar("平台仓单");
        ExitApplication.getInstance().addActivity(this);
        init();
        click();
        getData();
    }

    public void init() {
        lv_my_platform = (PullToRefreshListView) findViewById(R.id.lv_my_platform);
        adapter = new PlatformAdapter(PlatformActivity.this,mList);
        lv_my_platform.setAdapter(adapter);
        lv_my_platform.setMode(PullToRefreshBase.Mode.BOTH);
        emptyView = LayoutInflater.from(this).inflate(R.layout.empty_view_layout, null);
        customProgress = CustomProgress.show(this, "加载中···", true);
    }

    public void click() {
//        lv_my_platform.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(PlatformActivity.this, PlatformDetailActivity.class);
//                startActivity(intent);
//            }
//        });

        lv_my_platform.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pageNum=1;
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
            pageNum++;
                getData();
            }
        });
    }

    @Override
    public void widgetClick(View view) {

    }

    //获取数据
    public void getData() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
                Log.d("result_plateform",arg0.result);
                String message= JsonUtils.getJsonParam(arg0.result,"message");
                String status=JsonUtils.getJsonParam(arg0.result,"status");
                String obj=JsonUtils.getJsonParam(arg0.result,"obj");
                platformCangDan=JsonUtils.parse(obj,PlatformCangDan.class);
                if (status.equals("1")){
                if (platformCangDan!=null){
                    if (platformCangDan.getList()!=null&&platformCangDan.getList().size()!=0){
                        if (pageNum==1){
                            mList.clear();
                        }
                        mList.addAll(platformCangDan.getList());
                    }
                }
                }else {
                    AppUtils.toastText(PlatformActivity.this,message);
                }
                adapter.notifyDataSetChanged();
                lv_my_platform.onRefreshComplete();
                lv_my_platform.setEmptyView(emptyView);
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.CANG_DAN, PlateFormUrl.postPlateformUrl(code, DefineUtil.TOKEN, DefineUtil.USERID, String.valueOf(pageSize), String.valueOf(pageNum), _sort), PlatformActivity.this);
    }

}
