package com.aglook.comapp.Activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglook.comapp.R;

public class PayResultActivity extends BaseActivity {
    private boolean isSecceed=true;
    private TextView right_text;
    private ImageView iv_pay_result;
    private TextView tv_pay_result;

    @Override
    public void initView() {
        setContentView(R.layout.activity_pay_result);
        setTitleBar("支付页面");
//        ExitApplication.getInstance().addActivity(this);
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setText("完成");
        iv_pay_result = (ImageView) findViewById(R.id.iv_pay_result);
        tv_pay_result = (TextView) findViewById(R.id.tv_pay_result);
        if (isSecceed){
            right_text.setVisibility(View.VISIBLE);
            iv_pay_result.setImageResource(R.drawable.zhifuchenggong);
            tv_pay_result.setText("支付成功");
        }else {
            right_text.setVisibility(View.GONE);
            iv_pay_result.setImageResource(R.drawable.zhifushibai);
            tv_pay_result.setText("支付失败");
        }
    }

    @Override
    public void widgetClick(View view) {

    }


}
