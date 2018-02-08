package com.weisj.fx.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.weisj.fx.R;

public class ItemMyCollectionAdapter<T> extends BaseAdapter {

    private List<T> objects = new ArrayList<T>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemMyCollectionAdapter(Context context, List<T> list) {
        this.context = context;
        this.objects = list;
        this.layoutInflater = LayoutInflater.from(context);
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
            convertView = layoutInflater.inflate(R.layout.item_my_collection, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.goodImage = (ImageView) convertView.findViewById(R.id.good_image);
            viewHolder.goodName = (TextView) convertView.findViewById(R.id.good_name);
            viewHolder.goodFrom = (TextView) convertView.findViewById(R.id.good_from);
            viewHolder.goodPrice = (TextView) convertView.findViewById(R.id.good_price);
            viewHolder.goodBt = (TextView) convertView.findViewById(R.id.good_bt);

            convertView.setTag(viewHolder);
        }
        initializeViews((T) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(T object, ViewHolder holder) {
        //TODO implement
    }

    protected class ViewHolder {
        private ImageView goodImage;
        private TextView goodName;
        private TextView goodFrom;
        private TextView goodPrice;
        private TextView goodBt;
    }
}
