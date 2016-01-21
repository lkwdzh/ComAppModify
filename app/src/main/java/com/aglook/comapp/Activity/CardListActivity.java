package com.aglook.comapp.Activity;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.aglook.comapp.Application.ComAppApplication;
import com.aglook.comapp.Application.ExitApplication;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.CardListAdapter;
import com.aglook.comapp.bean.CardList;
import com.aglook.comapp.bean.Login;
import com.aglook.comapp.url.CardListUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.aglook.comapp.view.SelectPopupWindow;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.ArrayList;
import java.util.List;

public class CardListActivity extends BaseActivity {


    private TextView right_text;
    private PullToRefreshListView lv_card_list;
    private CardListAdapter adapter;
    private List<CardList> mList = new ArrayList<>();
    private List<CardList> list;
    private SelectPopupWindow popupWindow;
    private String bankCardId;
    //添加银行卡
    private boolean isAdded = false;
    //设置默认银行卡
    private boolean isMoRen = false;
    //删除
    private boolean isDelete = false;
    private CustomProgress customProgress;
    private View emptyView;
    //    private ImageView left_icon;
    private String defaut;

    private String bankName;
    private ComAppApplication comAppApplication;
    private Login login;

    @Override
    public void initView() {
        setContentView(R.layout.activity_card_list);
        setTitleBar("银行账户列表");
        ExitApplication.getInstance().addActivity(this);
        comAppApplication = (ComAppApplication) getApplication();
        login = comAppApplication.getLogin();
        init();
        getCardListData();
        click();
    }

    public void init() {
//        left_icon = (ImageView) findViewById(R.id.left_icon);
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setText("添加");
        right_text.setVisibility(View.VISIBLE);

        emptyView = LayoutInflater.from(this).inflate(R.layout.empty_view_layout, null);
        lv_card_list = (PullToRefreshListView) findViewById(R.id.lv_card_list);
        lv_card_list.setMode(PullToRefreshBase.Mode.BOTH);
        adapter = new CardListAdapter(CardListActivity.this, mList);
        lv_card_list.setAdapter(adapter);
        lv_card_list.setMode(PullToRefreshBase.Mode.DISABLED);
    }

    public void click() {
        //添加银行卡
        right_text.setOnClickListener(this);

        lv_card_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                defaut = mList.get(position - 1).getDefaultType();
                bankCardId = mList.get(position - 1).getBankCardId();
                bankName = mList.get(position - 1).getBankAlis();
                popupWindow = new SelectPopupWindow(CardListActivity.this, itemsOnClick);
                // 显示窗口
                popupWindow.showAtLocation(CardListActivity.this.findViewById(R.id.waww),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
    }

    @Override
    public void widgetClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.right_text:
                intent.setClass(CardListActivity.this, BandCardActivity.class);
                isAdded = true;
               if (mList==null||mList.size()==0){
                   intent.putExtra("isFirst",true);
               }
                startActivityForResult(intent, 1);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == 1) {
            getCardListData();
        } else if (requestCode == 33 && resultCode == 1) {
            mList.clear();
            getCardListData();
        }
    }

    //获取银行卡列表
    public void getCardListData() {
        customProgress = CustomProgress.show(this, "", true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
//                Log.d("result_cardList", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                list = JsonUtils.parseList(obj, CardList.class);
                if (status.equals("1")) {
                    //假如成功
                    if (isAdded) {
                        mList.clear();
                        isAdded = false;
                    } else if (isMoRen) {
                        mList.clear();
                        isMoRen = false;
                    } else if (isDelete) {
                        mList.clear();
//                        Log.d("result_isDelete",isDelete+"");
                        isDelete = false;
                    }
                    if (list.size() != 0 && list != null) {
                        mList.addAll(list);
                        DefineUtil.BANKBAND = true;
                        //遍历mlist，判断默认银行卡是否是兴业
                        for (int i = 0; i < mList.size(); i++) {
                            if (mList.get(i).getDefaultType().equals("1") && mList.get(i).getBankAlis().equals("兴业银行")) {
                                login.setXingYe(true);
                            }
                        }
                        comAppApplication.setLogin(login);
                    } else {
                        DefineUtil.BANKBAND = false;
                    }
                }

                adapter.notifyDataSetChanged();
                lv_card_list.setEmptyView(emptyView);
                lv_card_list.onRefreshComplete();
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.dismiss();
                }
            }
        }.datePost(DefineUtil.BANKCARD_LIST, CardListUrl.postBankCardListUrl(DefineUtil.USERID, DefineUtil.TOKEN), CardListActivity.this);
    }


    //为弹出框实现监听
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_moren_select_popup:
                    popupWindow.dismiss();
                    setDefault();
                    break;
                case R.id.tv_delete_select_popup:
                    popupWindow.dismiss();
                    if (defaut.equals("1")) {
                        //默认，无法删除
                        AppUtils.toastText(CardListActivity.this, "无法删除默认银行卡");
                        return;
                    }
                    customProgress = CustomProgress.show(CardListActivity.this, "", true);
                    isDelete = true;
                    deleteCard();
                    break;
            }
        }
    };

    // 设置默认银行卡
    public void setDefault() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                if (status.equals("1")) {
                    isMoRen = true;
                    getCardListData();
                    //如果是兴业银行，
                    if (bankName.equals("兴业银行")) {

                    }
                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePostUp(DefineUtil.BINDING_DEFAULT, CardListUrl.postBindDefaultUrl(DefineUtil.USERID, DefineUtil.TOKEN, bankCardId), CardListActivity.this);
    }

    //删除银行卡
    public void deleteCard() {

        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
//                Log.d("result_delete", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                if (status.equals("1")) {


                    getCardListData();
                }
                AppUtils.toastText(CardListActivity.this, message);
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {

            }
        }.datePostUp(DefineUtil.DELETE_BANKCARD, CardListUrl.postDeleteUrl(DefineUtil.USERID, DefineUtil.TOKEN, bankCardId), CardListActivity.this);
    }

}
