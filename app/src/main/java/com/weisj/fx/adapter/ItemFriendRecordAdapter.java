package com.weisj.fx.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.weisj.fx.R;
import com.weisj.fx.bean.FriendRecordBean;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.TextViewUtils;
import com.weisj.fx.view.CircleImageView;

import android.widget.TextView;

public class ItemFriendRecordAdapter<T> extends BaseAdapter {

    private List<T> objects;

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemFriendRecordAdapter(Context context, List<T> list) {
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
            convertView = layoutInflater.inflate(R.layout.item_friend_record, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.friendImage = (CircleImageView) convertView.findViewById(R.id.friend_image);
            viewHolder.friendName = (TextView) convertView.findViewById(R.id.friend_name);
            viewHolder.friendFrom = (TextView) convertView.findViewById(R.id.friend_from);
            viewHolder.friendTime = (TextView) convertView.findViewById(R.id.friend_time);

            convertView.setTag(viewHolder);
        }
        initializeViews((T) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(T object, ViewHolder holder) {
        //TODO implement
        if (object instanceof FriendRecordBean.DataEntity) {
            FriendRecordBean.DataEntity dataEntity = (FriendRecordBean.DataEntity) object;
            ImageLoaderUtils.getInstance().display(holder.friendImage, dataEntity.getImg_small());
            TextViewUtils.setText(holder.friendName, dataEntity.getNickname());
        }
    }

    protected class ViewHolder {
        private CircleImageView friendImage;
        private TextView friendName;
        private TextView friendFrom;
        private TextView friendTime;
    }
}
