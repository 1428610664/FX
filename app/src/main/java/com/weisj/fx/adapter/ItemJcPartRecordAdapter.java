package com.weisj.fx.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.weisj.fx.R;
import com.weisj.fx.bean.OneShareBean;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.TextViewUtils;
import com.weisj.fx.view.CircleImageView;

import android.widget.TextView;
import android.widget.ImageView;

public class ItemJcPartRecordAdapter<T> extends BaseAdapter {

    private List<T> objects;

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemJcPartRecordAdapter(Context context, List<T> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects != null ? objects.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return objects != null ? objects.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_jc_part_record, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((T) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(T object, ViewHolder holder) {
        //TODO implement
        if (object instanceof OneShareBean.DataBean) {
            OneShareBean.DataBean oneShareBean = (OneShareBean.DataBean) object;
            ImageLoaderUtils.getInstance().display(holder.image, oneShareBean.getHeaderPic());
            TextViewUtils.setText(holder.name, oneShareBean.getNickname());
            TextViewUtils.setTextAndleftOther(holder.price, oneShareBean.getCurrentGuessPrice(), "￥");
            holder.time.setText(stampToDate(oneShareBean.getUdpateTime()));
            if (oneShareBean.getGuessAwardId() > 0) {
                holder.jcWinImage.setVisibility(View.VISIBLE);
            } else {
                holder.jcWinImage.setVisibility(View.GONE);
            }
        }
    }

    protected class ViewHolder {
        private CircleImageView image;
        private TextView name;
        private TextView price;
        private TextView time;
        private ImageView jcWinImage;

        public ViewHolder(View view) {
            image = (CircleImageView) view.findViewById(R.id.image);
            name = (TextView) view.findViewById(R.id.name);
            price = (TextView) view.findViewById(R.id.price);
            time = (TextView) view.findViewById(R.id.time);
            jcWinImage = (ImageView) view.findViewById(R.id.jc_win_image);
        }
    }

    /*
    * 将时间戳转换为时间
    */
    public String stampToDate(long s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(s);
        res = simpleDateFormat.format(date);
        return res;
    }
}
