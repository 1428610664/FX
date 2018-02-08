package com.weisj.fx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.weisj.fx.R;
import com.weisj.fx.bean.LogisticsData;
import com.weisj.fx.utils.SystemConfig;

import java.util.List;

/**
 * Created by Administrator on 2016/1/9 0009.
 */
public class ItemLogisticsContextAdapter<T> extends RecyclerView.Adapter<ItemLogisticsContextAdapter.MyViewHolder> {
    private Context context;
    private List<T> list;

    public ItemLogisticsContextAdapter(List<T> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_logistics_context,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (holder.getLayoutPosition() == 0){
            holder.linearView.setVisibility(View.INVISIBLE);
            holder.text.setSelected(true);
            holder.date.setSelected(true);
            SystemConfig.dynamicSetWidthAndHeight(holder.logisticsIcon, 30, 30);
            holder.logisticsIcon.setImageResource(R.drawable.logistics_icons_new);
            SystemConfig.dynamicSetWidthAndHeight(holder.linearView,2,38);
        }else{
            holder.linearView.setVisibility(View.VISIBLE);
            holder.logisticsIcon.setImageResource(R.drawable.logistics_icons);
            holder.text.setSelected(false);
            holder.date.setSelected(false);
            SystemConfig.dynamicSetWidthAndHeight(holder.logisticsIcon, 24, 24);
            SystemConfig.dynamicSetWidthAndHeight(holder.linearView,2,42);
        }
        if (list.get(0) instanceof LogisticsData.DataEntity){
            LogisticsData.DataEntity data = (LogisticsData.DataEntity) list.get(holder.getLayoutPosition());
            holder.text.setText(data.getContext());
            holder.date.setText(data.getTime());
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        View linearView;
        ImageView logisticsIcon;
        TextView text;
        TextView date;
        public MyViewHolder(View itemView) {
            super(itemView);
            linearView = itemView.findViewById(R.id.view);
            logisticsIcon = (ImageView) itemView.findViewById(R.id.logistics_icons);
            text = (TextView) itemView.findViewById(R.id.logistics_text);
            date = (TextView) itemView.findViewById(R.id.logistics_date);
            SystemConfig.dynamicSetWidthAndHeight(itemView.findViewById(R.id.view2),2,-1);
        }
    }
}
