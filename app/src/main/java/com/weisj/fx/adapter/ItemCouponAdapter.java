package com.weisj.fx.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.activity.GoodDetailActivity;
import com.weisj.fx.activity.SearchListActivity;
import com.weisj.fx.bean.HomeCouponbean;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.TextViewUtils;


public class ItemCouponAdapter<T> extends BaseAdapter implements View.OnClickListener {

    private List<T> objects;

    private Context context;
    private LayoutInflater layoutInflater;
    private View stateView;


    public ItemCouponAdapter(Context context, List<T> objects, View view) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = objects;
        stateView = view;
    }

    @Override
    public int getCount() {
//        if (objects != null) {
//            int count = objects.size();
//            if (count % 2 == 0) {
//                return count / 2 + 1;
//            } else {
//                return count / 2 + 2;
//            }
//        }
//        return 1;
        return 0;
    }

    @Override
    public T getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position == 0) {
            return stateView;
        }
        position--;
        if (convertView == null || convertView.getTag() == null) {
            convertView = layoutInflater.inflate(R.layout.item_coupon, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.couponLinear2 = (LinearLayout) convertView.findViewById(R.id.coupon_linear);
            viewHolder.couponLinear1 = (LinearLayout) convertView.findViewById(R.id.coupon_linear_1);
            viewHolder.image1 = (ImageView) convertView.findViewById(R.id.coupon_image);
            viewHolder.image2 = (ImageView) convertView.findViewById(R.id.coupon_image1);
            viewHolder.money1 = (TextView) convertView.findViewById(R.id.coupon_money);
            viewHolder.money2 = (TextView) convertView.findViewById(R.id.coupon_money1);
            viewHolder.brand1 = (TextView) convertView.findViewById(R.id.coupon_brand_name);
            viewHolder.brand2 = (TextView) convertView.findViewById(R.id.coupon_brand_name1);
            viewHolder.bt1 = (TextView) convertView.findViewById(R.id.coupon_bt);
            viewHolder.bt2 = (TextView) convertView.findViewById(R.id.coupon_bt1);
            convertView.setTag(viewHolder);
        }
        initializeViews(position, (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(int position, ViewHolder holder) {
        //TODO implement
        HomeCouponbean.DataEntity.SingleCouponListEntity data1 = (HomeCouponbean.DataEntity.SingleCouponListEntity) objects.get(position * 2);
        ImageLoaderUtils.getInstance().display(holder.image1, data1.getBrandLogo());
        TextViewUtils.setText(holder.money1, TextViewUtils.sprStringMoneyToInt(data1.getDelMoney()));
        TextViewUtils.setText(holder.brand1, data1.getBrandName());
        holder.couponLinear1.setOnClickListener(this);
        holder.couponLinear1.setTag(data1);
        if (objects.size() > position * 2 + 1) {
            holder.couponLinear2.setVisibility(View.VISIBLE);
            HomeCouponbean.DataEntity.SingleCouponListEntity data2 = (HomeCouponbean.DataEntity.SingleCouponListEntity) objects.get(position * 2 + 1);
            ImageLoaderUtils.getInstance().display(holder.image2, data2.getBrandLogo());
            TextViewUtils.setText(holder.brand2, data2.getBrandName());
            TextViewUtils.setText(holder.money2, TextViewUtils.sprStringMoneyToInt(data2.getDelMoney()));
            holder.couponLinear2.setOnClickListener(this);
            holder.couponLinear2.setTag(data2);
        } else {
            holder.couponLinear2.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.coupon_linear_1:
            case R.id.coupon_linear:
                HomeCouponbean.DataEntity.SingleCouponListEntity data = (HomeCouponbean.DataEntity.SingleCouponListEntity) v.getTag();
                if (data.getMutiFlag() > 0) {
                    Intent intent = new Intent(context, SearchListActivity.class);
                    intent.putExtra("couponId", data.getCouponId());
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, GoodDetailActivity.class);
                    intent.putExtra("goodId", data.getGoodsId());
                    context.startActivity(intent);
                }
                break;
        }
    }

    protected class ViewHolder {
        LinearLayout couponLinear2;
        LinearLayout couponLinear1;
        ImageView image1;
        TextView money1;
        TextView brand1;
        TextView bt1;
        ImageView image2;
        TextView money2;
        TextView brand2;
        TextView bt2;
    }
}

