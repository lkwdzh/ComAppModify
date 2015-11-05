package com.aglook.comapp.Activity;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.aglook.comapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GuaDanAddActivity extends BaseActivity {


    private TextView right_text;
    private TextView tv_in_time_gua_dan_add;

    private Calendar calendar;
    private SimpleDateFormat df;
    private String dateIn;
    private String dateUseful;
    private TextView tv_userful_time_gua_dan_add;

    @Override
    public void initView() {
        setContentView(R.layout.activity_gua_dan_add);
        setTitleBar("我要挂单");
        init();
        click();
    }

    public void init() {
        right_text = (TextView) findViewById(R.id.right_text);
        right_text.setVisibility(View.VISIBLE);
        right_text.setText("完成");

        tv_in_time_gua_dan_add = (TextView) findViewById(R.id.tv_in_time_gua_dan_add);
        calendar = Calendar.getInstance();
        tv_userful_time_gua_dan_add = (TextView) findViewById(R.id.tv_userful_time_gua_dan_add);
    }

    public void click() {
        tv_in_time_gua_dan_add.setOnClickListener(this);
        tv_userful_time_gua_dan_add.setOnClickListener(this);
    }

    //    日期选择对话框的监听
    private DatePickerDialog.OnDateSetListener listenerIn = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            df = new SimpleDateFormat("yyyy-MM-dd");
            dateIn = df.format(calendar.getTime());
            tv_in_time_gua_dan_add.setText(dateIn);
        }
    };

    //    日期选择对话框的监听
    private DatePickerDialog.OnDateSetListener listenerUseful = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            df = new SimpleDateFormat("yyyy-MM-dd");
            dateUseful = df.format(calendar.getTime());
            tv_userful_time_gua_dan_add.setText(dateUseful);
        }
    };


    @Override
    public void widgetClick(View view) {
        switch (view.getId()) {
            case R.id.tv_in_time_gua_dan_add:
                DatePickerDialog datePickerDialogIn = new DatePickerDialog(GuaDanAddActivity.this, listenerIn, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialogIn.show();
                break;
            case R.id.tv_userful_time_gua_dan_add:
                DatePickerDialog datePickerDialogUseful = new DatePickerDialog(GuaDanAddActivity.this, listenerUseful, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialogUseful.show();
                break;
        }
    }


}
