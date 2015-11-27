package com.aglook.comapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.aglook.comapp.R;
import com.aglook.comapp.bean.Screen;
import com.aglook.comapp.util.XBitmap;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.List;

/**
 * Created by aglook on 2015/11/6.
 */
public class ScreenAdapter extends BaseAdapter {
    private Context context;
    private List<Screen> list;
    private PullToRefreshGridView gv;

    public ScreenAdapter(Context context, List<Screen> list, PullToRefreshGridView gv) {
        this.context = context;
        this.list = list;
        this.gv = gv;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_screen_gridview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.iv_screen_gridview.setTag(position);
        holder.tv_address_screen_gridview.setTag(position);
        holder.tv_grade_screen_gridview.setTag(position);
        Screen screen = list.get(position);
        XBitmap.displayImage(holder.iv_screen_gridview, screen.getProductLogo(), context);
        holder.tv_address_screen_gridview.setText(screen.getGoodPlace());
        holder.tv_grade_screen_gridview.setText(screen.getGoodType());
        holder.tv_weight_screen_gridview.setText(screen.getProductSellNum());
        holder.tv_price_screen_gridview.setText(screen.getProductMoney());
        holder.tv_name_screen_gridview.setText(screen.getProductName());
        return convertView;
    }

    class ViewHolder {

        ImageView iv_screen_gridview;
        TextView tv_address_screen_gridview;
        TextView tv_grade_screen_gridview;
        TextView tv_weight_screen_gridview;
        TextView tv_price_screen_gridview;
        TextView tv_name_screen_gridview;

        ViewHolder(View view) {
            iv_screen_gridview = (ImageView) view.findViewById(R.id.iv_screen_gridview);
            tv_address_screen_gridview = (TextView) view.findViewById(R.id.tv_address_screen_gridview);
            tv_grade_screen_gridview = (TextView) view.findViewById(R.id.tv_grade_screen_gridview);
            tv_weight_screen_gridview = (TextView) view.findViewById(R.id.tv_weight_screen_gridview);
            tv_price_screen_gridview = (TextView) view.findViewById(R.id.tv_price_screen_gridview);
            tv_name_screen_gridview = (TextView) view.findViewById(R.id.tv_name_screen_gridview);
        }

        public void update() {
            // 精确计算GridView的item高度
            iv_screen_gridview.getViewTreeObserver().addOnGlobalLayoutListener(
                    new ViewTreeObserver.OnGlobalLayoutListener() {
                        public void onGlobalLayout() {
                            int position = (Integer) tv_address_screen_gridview.getTag();
                            // 这里是保证同一行的item高度是相同的！！也就是同一行是齐整的 height相等
                            if (position > 0 && position % 2 == 1) {
                                View v = (View) tv_grade_screen_gridview.getTag();
                                int height = v.getHeight();

                                View view = gv.getChildAt(position - 1);
                                int lastheight = view.getHeight();
                                // 得到同一行的最后一个item和前一个item想比较，把谁的height大，就把两者中                                                                // height小的item的高度设定为height较大的item的高度一致，也就是保证同一                                                                 // 行高度相等即可
                                if (height > lastheight) {
                                    view.setLayoutParams(new GridView.LayoutParams(
                                            GridView.LayoutParams.FILL_PARENT,
                                            height));
                                } else if (height < lastheight) {
                                    v.setLayoutParams(new GridView.LayoutParams(
                                            GridView.LayoutParams.FILL_PARENT,
                                            lastheight));
                                }
                            }
                        }
                    });
        }
    }
}
