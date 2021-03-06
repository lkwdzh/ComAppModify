package com.aglook.comapp.Activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.url.FriendsUrl;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

public class FriendsAddActivity extends BaseActivity {


    private TextView right_text;
    private EditText et_seatNO_Friends_add;
    private String seatNo;
    private CustomProgress customProgress;

    @Override
    public void initView() {
        setContentView(R.layout.activity_buyer_add);
        setTitleBar("添加联系人");
//        ExitApplication.getInstance().addActivity(this);
        init();
        click();
    }

    public void init(){
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setText("完成");
        right_text.setVisibility(View.VISIBLE);
        et_seatNO_Friends_add = (EditText) findViewById(R.id.et_seatNO_Friends_add);
    }

    public void click(){
        right_text.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View view) {
        switch (view.getId()){
            case R.id.right_text:
                //假如成功，则返回
                seatNo= AppUtils.toStringTrim_ET(et_seatNO_Friends_add);
                if (seatNo==null||"".equals(seatNo)){
                    AppUtils.toastText(this,"席位号不能为空");
                    return;
                }
                addFriend();
                break;
        }
    }


    //添加联系人
    public void addFriend(){
        customProgress=CustomProgress.show(FriendsAddActivity.this,"",true);
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress!=null&&customProgress.isShowing()){
                    customProgress.cancle();
                }
//                Log.d("result_add_friend",arg0.result);
                String message= JsonUtils.getJsonParam(arg0.result,"message");
                String status=JsonUtils.getJsonParam(arg0.result,"status");
                if (status!=null&&!"".equals(status)){
                    if (status.equals("1")) {
                        FriendsAddActivity.this.setResult(1);
                        FriendsAddActivity.this.finish();
                    }

                }
            }

            @Override
            public void failureInitViews(HttpException arg0, String arg1) {
                if (customProgress!=null&&customProgress.isShowing()){
                    customProgress.cancle();
                }
            }
        }.datePost(DefineUtil.ADD_CONTACT, FriendsUrl.postAddUrl(DefineUtil.USERID,DefineUtil.TOKEN,seatNo),FriendsAddActivity.this);
    }
}
