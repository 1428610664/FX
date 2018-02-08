package com.weisj.fx.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.activity.SearchActivity;


public class ItemSearchAdapter<T> extends BaseAdapter implements View.OnClickListener {

    private List<T> objects;

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemSearchAdapter(Context context, List<T> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = objects;
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
            convertView = layoutInflater.inflate(R.layout.item_search, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.text = (TextView) convertView.findViewById(R.id.content);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        }
        initializeViews((T) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(T object, ViewHolder holder) {
        //TODO implement
        holder.text.setText((String) object);
        holder.image.setOnClickListener(this);
        holder.image.setTag(object);
    }

    @Override
    public void onClick(View v) {
        ((SearchActivity) context).setTitle((String) v.getTag());
    }

    protected class ViewHolder {
        TextView text;
        ImageView image;
    }
}

