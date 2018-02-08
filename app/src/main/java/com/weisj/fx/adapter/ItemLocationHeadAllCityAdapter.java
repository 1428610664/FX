package com.weisj.fx.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.weisj.fx.MainActivity;
import com.weisj.fx.R;
import com.weisj.fx.bean.LocationBean;
import com.weisj.fx.utils.PreferencesUtils;

import java.util.ArrayList;
import java.util.List;

public class ItemLocationHeadAllCityAdapter<T> extends BaseAdapter {

    private List<T> objects = new ArrayList<T>();

    private Activity context;
    private LayoutInflater layoutInflater;

    public ItemLocationHeadAllCityAdapter(Activity context, List<T> list) {
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
            convertView = layoutInflater.inflate(R.layout.item_location_all_city, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.locationText = (TextView) convertView.findViewById(R.id.location_text);
            viewHolder.listView = (ListView) convertView.findViewById(R.id.listview);
            convertView.setTag(viewHolder);
        }
        initializeViews((T) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(T object, ViewHolder holder) {
        //TODO implement
        final LocationBean.DataEntity.AllDistrictListEntity allDistrictListEntity = (LocationBean.DataEntity.AllDistrictListEntity) object;
        holder.locationText.setText(allDistrictListEntity.getType());
        holder.listView.setAdapter(new ItemLocationAllCityAdapter(context, allDistrictListEntity.getTypeCityList()));

        holder.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, allDistrictListEntity.getTypeCityList().get(position), Toast.LENGTH_SHORT).show();
                Intent mIntent = new Intent(context, MainActivity.class);
                mIntent.putExtra("city", allDistrictListEntity.getTypeCityList().get(position));
                context.setResult(Activity.RESULT_OK, mIntent);
                PreferencesUtils.putString("select_city", allDistrictListEntity.getTypeCityList().get(position));
                context.finish();
            }
        });

    }

    protected class ViewHolder {
        private TextView locationText;
        private ListView listView;
    }
}
