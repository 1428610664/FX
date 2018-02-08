package com.weisj.fx.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.bean.CategoryBean;
import com.weisj.fx.main.fragment.CouponFragment;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.TextViewUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class ItemCouponCategoryAdapter<T> extends RecyclerView.Adapter<ItemCouponCategoryAdapter.MyViewHolder> implements View.OnClickListener {
    private Context context;
    private List<T> list;
    private int state = 0;
    private CouponFragment fragment;

    public ItemCouponCategoryAdapter(Context context, List<T> list, CouponFragment fragment) {
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_coupon_category, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (list.size() == 2) {
            SystemConfig.dynamicSetWidthAndHeight(holder.text, 1 / 2.0, -1);
        } else if (list.size() == 3) {
            SystemConfig.dynamicSetWidthAndHeight(holder.text, 1 / 3.0, -1);
        } else if (list.size() > 3) {
            SystemConfig.dynamicSetWidthAndHeight(holder.text, 220, -1);
        }
        if (list.get(0) instanceof CategoryBean.DataEntity) {
            CategoryBean.DataEntity dataEntity = (CategoryBean.DataEntity) list.get(position);
            TextViewUtils.setText(holder.text, dataEntity.getCategoryName());
            if (state == position) {
                holder.text.setSelected(true);
            } else {
                holder.text.setSelected(false);
            }
            holder.text.setOnClickListener(this);
            holder.text.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        state = (int) v.getTag();
        fragment.getData(((CategoryBean.DataEntity) list.get(state)).getCategoryId());
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        public MyViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.coupon_item);
        }
    }
}
