package com.aglook.comapp.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aglook.comapp.R;

/**
 * Created by aglook on 2015/10/27.
 */
public class HomePageViewPagerFragment extends Fragment {
    public int imageArray[]={R.drawable.startup01,R.drawable.startup02,R.drawable.startup03,R.drawable.startup04};
    public static HomePageViewPagerFragment myFragment(int position){
        HomePageViewPagerFragment fragment=new HomePageViewPagerFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("position",position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ImageView imageView=new ImageView(getActivity());
        Bundle bundle=getArguments();
        int position = bundle.getInt("position");
        imageView.setImageResource(imageArray[position]);
        return imageView;
    }
}
