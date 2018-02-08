package com.weisj.fx.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.activity.WebViewActivity;
import com.weisj.fx.bean.NoticeBean;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.TextViewUtils;

import java.util.ArrayList;
import java.util.List;

public class ItemNoticeAdapter<T> extends BaseAdapter{

    private List<T> objects;

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemNoticeAdapter(Context context, List<T> list) {
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
            convertView = layoutInflater.inflate(R.layout.item_notice, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.noticeImage = (ImageView) convertView.findViewById(R.id.notice_image);
            viewHolder.noticeTitle = (TextView) convertView.findViewById(R.id.notice_title);
            viewHolder.noticeContent = (TextView) convertView.findViewById(R.id.notice_content);
            viewHolder.noticeTime = (TextView) convertView.findViewById(R.id.notice_time);
            convertView.setTag(viewHolder);
        }
        initializeViews((T) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(T object, ViewHolder holder) {
        //TODO implement
        if (object instanceof NoticeBean.DataEntity) {
            NoticeBean.DataEntity dataEntity = (NoticeBean.DataEntity) object;
            ImageLoaderUtils.getInstance().display(holder.noticeImage, dataEntity.getFile_url(),R.mipmap.icon_head);
            TextViewUtils.setText(holder.noticeContent, dataEntity.getKeywords());
            TextViewUtils.setText(holder.noticeTitle, dataEntity.getTitle());
            TextViewUtils.setText(holder.noticeTime, dataEntity.getCreate_time());
        }
    }



    protected class ViewHolder {
        private ImageView noticeImage;
        private TextView noticeTitle;
        private TextView noticeContent;
        private TextView noticeTime;
    }
}
