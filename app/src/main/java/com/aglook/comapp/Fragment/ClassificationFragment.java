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
import android.widget.ListView;

import com.aglook.comapp.Activity.GoodsDetailActivity;
import com.aglook.comapp.R;
import com.aglook.comapp.adapter.ClassificationLeftAdapter;
import com.aglook.comapp.adapter.ClassificationRightAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aglook on 2015/10/26.
 */
public class ClassificationFragment extends Fragment {
    private List<String> leftList = new ArrayList<>();
    private List<String> rightList = new ArrayList<>();
    private ListView mLeftList;
    private GridView gv_right;
    private ClassificationLeftAdapter leftAdapter;
    private ClassificationRightAdapter rightAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.layout_classification_fragment, null);
        initView(view);
        click();
        return view;
    }


    //初始化控件
    public void initView(View view) {
        mLeftList = ((ListView) view.findViewById(R.id.lv_left));
        gv_right = (GridView) view.findViewById(R.id.gv_right);
        for (int i = 0; i < 20; i++) {
            leftList.add("左边" + i);
            rightList.add("右边" + i);
        }
        leftAdapter = new ClassificationLeftAdapter(leftList, getActivity());
        mLeftList.setAdapter(leftAdapter);
//        mLeftList.setSelection(3);
        rightAdapter = new ClassificationRightAdapter(getActivity());
        gv_right.setAdapter(rightAdapter);
    }

    //点击事件
    public void click() {
        mLeftList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                leftAdapter.setSelectItem(i);
                leftAdapter.notifyDataSetChanged();
            }
        });

        gv_right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
                startActivity(intent);
            }
        });
    }


}
