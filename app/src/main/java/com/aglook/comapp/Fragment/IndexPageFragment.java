package com.aglook.comapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aglook.comapp.Activity.IndexActivity;
import com.aglook.comapp.Activity.MainActivity;
import com.aglook.comapp.R;

/**
 * Created by aglook on 2016/1/6.
 */
public class IndexPageFragment extends Fragment {
    private int imageArray[]={R.drawable.index1,R.drawable.index2,R.drawable.index3};
    public static IndexPageFragment myFragment(int position){
        IndexPageFragment fragment=new IndexPageFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("position",position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.index_fragment,null);
        ImageView iv_index=(ImageView)view.findViewById(R.id.iv_index);
        View view_click=(View)view.findViewById(R.id.view_click);
        Bundle bundle=getArguments();
        int position = bundle.getInt("position");
        if (position==2){
            view_click.setVisibility(View.VISIBLE);
        }else {
            view_click.setVisibility(View.GONE);
        }
        iv_index.setImageResource(imageArray[position]);
        view_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                if (IndexActivity.instance!=null){
                    IndexActivity.instance.finish();
                }
            }
        });
        return view;
    }
}
