package com.aglook.comapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aglook.comapp.Activity.ScreenActivity;
import com.aglook.comapp.Activity.SearchActivity;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.ClassificationLeftAdapter;
import com.aglook.comapp.adapter.ClassificationRightAdapter;
import com.aglook.comapp.bean.Classify;
import com.aglook.comapp.bean.ClassifyGV;
import com.aglook.comapp.util.AppUtils;
import com.aglook.comapp.util.DefineUtil;
import com.aglook.comapp.util.JsonUtils;
import com.aglook.comapp.util.XBitmap;
import com.aglook.comapp.util.XHttpuTools;
import com.aglook.comapp.view.CustomProgress;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aglook on 2015/10/26.
 */
public class ClassificationFragment extends Fragment implements View.OnClickListener {
    private ListView mLeftList;
    private GridView gv_right;
    private ClassificationLeftAdapter leftAdapter;
    private ClassificationRightAdapter rightAdapter;
    private RelativeLayout rl_search_classify;
    private List<Classify> mList;
    private List<Classify> sonList;
    private List<ClassifyGV> rightList;
    private CustomProgress customProgress;
    private ImageView iv_nation_flag;
    private TextView tv_nation_flag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.layout_classification_fragment, null);
        initView(view);
        click();
        getData();
        return view;
    }


    //初始化控件
    public void initView(View view) {
        customProgress = CustomProgress.show(getActivity(), "", true);
        mList = new ArrayList<>();
        sonList = new ArrayList<>();
        rightList = new ArrayList<>();
        mLeftList = ((ListView) view.findViewById(R.id.lv_left));
        gv_right = (GridView) view.findViewById(R.id.gv_right);
        leftAdapter = new ClassificationLeftAdapter(mList, getActivity());
        mLeftList.setAdapter(leftAdapter);
//        mLeftList.setSelection(3);
        iv_nation_flag = (ImageView) view.findViewById(R.id.iv_nation_flag);
        tv_nation_flag = (TextView) view.findViewById(R.id.tv_nation_flag);

        rightAdapter = new ClassificationRightAdapter(getActivity(), rightList);
        gv_right.setAdapter(rightAdapter);
        rl_search_classify = (RelativeLayout) view.findViewById(R.id.rl_search_classify);
    }


    //点击事件
    public void click() {
        mLeftList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //listview选中项
                leftAdapter.setSelectItem(i);
                leftAdapter.notifyDataSetChanged();
//                填充右边的list，并刷新
                rightList.clear();
                rightList.addAll(mList.get(i).getContent());
//                Log.d("result_right", i + "_______" + mList.get(i).getNationalFlag());
                XBitmap.displayImage(iv_nation_flag, mList.get(i).getNationalFlag(), getActivity());
                tv_nation_flag.setText(mList.get(i).getName());
                rightAdapter.notifyDataSetChanged();
            }
        });

        gv_right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                跳转到筛选界面
                Intent intent = new Intent(getActivity(), ScreenActivity.class);
                intent.putExtra("categoryId", rightList.get(i).getCategoryId());
                startActivity(intent);
            }
        });

        rl_search_classify.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.rl_search_classify:
                intent.setClass(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
        }
    }

    //    获取数据
    public void getData() {
        new XHttpuTools() {
            @Override
            public void initViews(ResponseInfo<String> arg0) {
                if (customProgress != null && customProgress.isShowing()) {
                    customProgress.cancle();
                }
//                Log.d("result_Classify", arg0.result);
                String message = JsonUtils.getJsonParam(arg0.result, "message");
                String status = JsonUtils.getJsonParam(arg0.result, "status");
                String obj = JsonUtils.getJsonParam(arg0.result, "obj");
                sonList = JsonUtils.parseList(obj, Classify.class);
                mList.clear();
                if (status.equals("1")) {
//                    假如成功则分别添加到list中
                    if (sonList != null && sonList.size() != 0) {
                        mList.addAll(sonList);
                    }
                    if (mList != null && mList.size() != 0) {
                        rightList.addAll(sonList.get(0).getContent());
                        XBitmap.displayImage(iv_nation_flag, mList.get(0).getNationalFlag(), getActivity());
                        tv_nation_flag.setText(mList.get(0).getName());
                    }
                } else {
                    AppUtils.toastText(getActivity(), message);
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
        }.datePost(DefineUtil.CATEGORY, getActivity());
    }


}
