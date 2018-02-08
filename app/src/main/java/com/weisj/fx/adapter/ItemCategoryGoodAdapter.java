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
import com.weisj.fx.bean.CategoryBean;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.TextViewUtils;

public class ItemCategoryGoodAdapter<T> extends BaseAdapter {

    private List<T> objects = new ArrayList<T>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemCategoryGoodAdapter(Context context, List<T> list) {
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
            convertView = layoutInflater.inflate(R.layout.item_category_good, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.goodImage = (ImageView) convertView.findViewById(R.id.good_image);
            viewHolder.goodName = (TextView) convertView.findViewById(R.id.good_name);

            convertView.setTag(viewHolder);
        }
        initializeViews((T) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(T object, ViewHolder holder) {
        //TODO implement
        CategoryBean.DataEntity data = (CategoryBean.DataEntity) object;
        TextViewUtils.setText(holder.goodName,data.getCategoryName());
        ImageLoaderUtils.getInstance().display(holder.goodImage,data.getCategoryPic());
    }

    protected class ViewHolder {
        private ImageView goodImage;
        private TextView goodName;
    }
}

