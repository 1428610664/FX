package com.weisj.fx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.weisj.fx.R;

import java.util.ArrayList;
import java.util.List;

public class ItemLocationAllCityAdapter<T> extends BaseAdapter {

    private List<T> objects = new ArrayList<T>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemLocationAllCityAdapter(Context context,List<T> list) {
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
            convertView = layoutInflater.inflate(R.layout.item_location_all, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.locationText = (TextView) convertView.findViewById(R.id.location_text);
            convertView.setTag(viewHolder);
        }
        initializeViews((T)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(T object, ViewHolder holder) {
        //TODO implement
        String typeCityList = (String) object;
        holder.locationText.setText(typeCityList);

    }

    protected class ViewHolder {
        private TextView locationText;
        private ListView listView;
    }
}
