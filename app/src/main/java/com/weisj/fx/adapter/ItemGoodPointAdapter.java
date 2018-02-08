package com.weisj.fx.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.bean.GoodPoint;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.TextViewUtils;
import com.weisj.fx.view.MyRatingBar;

import android.support.v7.widget.RecyclerView;

public class ItemGoodPointAdapter<T> extends BaseAdapter {

    private List<T> objects;

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemGoodPointAdapter(Context context, List<T> list) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = list;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public T getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_good_point, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((T) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(T object, ViewHolder holder) {
        //TODO implement
        if (object instanceof GoodPoint.DataBean) {
            GoodPoint.DataBean goodPoint = (GoodPoint.DataBean) object;
            TextViewUtils.setText(holder.pointName, goodPoint.getUser_name());
            TextViewUtils.setText(holder.pointContent, goodPoint.getContent());
            TextViewUtils.setText(holder.pointTime, goodPoint.getAdd_time());
            holder.pointBar.setPoint(goodPoint.getComment_rank());
            holder.recyclerView.setAdapter(new GoodPointImageAdapter(goodPoint.getPics()));
        }
    }

    protected class GoodPointImageAdapter extends BaseAdapter {
        private List<String> list;

        public GoodPointImageAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list != null ? list.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyViewHolder holder;
            if (convertView != null){
                holder = (MyViewHolder) convertView.getTag();
            }else{
                convertView = layoutInflater.inflate(R.layout.item_good_point_image,null);
                holder = new MyViewHolder(convertView);
                convertView.setTag(holder);
            }
            ImageLoaderUtils.getInstance().display(holder.imageView, (String) getItem(position));
            return convertView;
        }
    }

    public static class MyViewHolder {
        ImageView imageView;

        public MyViewHolder(View itemView) {
            imageView = (ImageView) itemView.findViewById(R.id.image);
            SystemConfig.dynamicSetWidthAndHeight(imageView, 120, 120);
        }
    }

    protected class ViewHolder {
        private TextView pointName;
        private TextView pointTime;
        private MyRatingBar pointBar;
        private TextView pointContent;
        private GridView recyclerView;

        public ViewHolder(View view) {
            pointName = (TextView) view.findViewById(R.id.point_name);
            pointTime = (TextView) view.findViewById(R.id.point_time);
            pointBar = (MyRatingBar) view.findViewById(R.id.point_bar);
            pointContent = (TextView) view.findViewById(R.id.point_content);
            recyclerView = (GridView) view.findViewById(R.id.recyclerView);
        }
    }
}
