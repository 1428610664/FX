package com.weisj.fx.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.bean.CategoryBean;
import com.weisj.fx.utils.TextViewUtils;

public class ItemCategoryTextAdapter<T> extends BaseAdapter {

    private List<T> objects = new ArrayList<T>();
    private Context context;
    private LayoutInflater layoutInflater;
    private int selectId;

    public int getSelectId() {
        return selectId;
    }

    public void setSelectId(int selectId) {
        this.selectId = selectId;
    }

    public ItemCategoryTextAdapter(Context context, List<T> list) {
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
            convertView = layoutInflater.inflate(R.layout.item_category_text, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.categoryText = (TextView) convertView.findViewById(R.id.category_text);
            convertView.setTag(viewHolder);
        }
        initializeViews((T) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(T object, ViewHolder holder) {
        //TODO implement
        if (object instanceof CategoryBean.DataEntity) {
            CategoryBean.DataEntity data = (CategoryBean.DataEntity) object;
            if (selectId == 0) {
                selectId = data.getCategoryId();
            }
//            TextViewUtils.setText(holder.categoryText, data.getCategoryName());
            holder.categoryText.setText(data.getCategoryName());
            if (selectId == data.getCategoryId()){
                holder.categoryText.setSelected(true);
            }else{
                holder.categoryText.setSelected(false);
            }
        }
    }

    protected class ViewHolder {
        private TextView categoryText;
    }
}

