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
//    private List<HomePageScroll> list=HomePageFragment.scrollList;
    public int imageArray[]={R.drawable.viewpage1,R.drawable.viewpage2,R.drawable.viewpage3,R.drawable.viewpage4,R.drawable.viewpage5};
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
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        if (list.get(position).getAdLogo()!=null&&!"".equals(list.get(position).getAdLogo())) {
//            XBitmap.displayImage(imageView, list.get(position).getAdLogo(), getActivity());
//        }
        imageView.setImageResource(imageArray[position]);
        return imageView;
    }
}
