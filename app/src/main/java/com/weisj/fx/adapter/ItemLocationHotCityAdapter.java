package com.weisj.fx.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.weisj.fx.MainActivity;
import com.weisj.fx.R;
import com.weisj.fx.bean.LocationBean;
import com.weisj.fx.utils.CommenString;
import com.weisj.fx.utils.PreferencesUtils;

import java.util.ArrayList;
import java.util.List;

public class ItemLocationHotCityAdapter<T> extends BaseAdapter {

    private List<T> objects = new ArrayList<T>();

    private Activity context;
    private LayoutInflater layoutInflater;

    public ItemLocationHotCityAdapter(Activity context, List<T> list) {
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
            convertView = layoutInflater.inflate(R.layout.item_location_hot_city, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.locationText = (TextView) convertView.findViewById(R.id.location_text);

            convertView.setTag(viewHolder);
        }
        initializeViews((T) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(T object, ViewHolder holder) {
        //TODO implement
        final LocationBean.DataEntity.HotDistrictListEntity hotDistrictListEntity =(LocationBean.DataEntity.HotDistrictListEntity) object;
        holder.locationText.setText(hotDistrictListEntity.getDistrictName());


        holder.locationText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(context,MainActivity.class);
                mIntent.putExtra("city",hotDistrictListEntity.getDistrictName());
                context.setResult(Activity.RESULT_OK, mIntent);
                PreferencesUtils.putString("select_city", hotDistrictListEntity.getDistrictName());
                context.finish();
            }
        });
    }

    protected class ViewHolder {
        private TextView locationText;
    }
}
