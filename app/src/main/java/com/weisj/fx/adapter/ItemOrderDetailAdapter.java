package com.weisj.fx.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.bean.OrderLogisticsBean;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.TextViewUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
public class ItemOrderDetailAdapter extends BaseAdapter {
    private OrderLogisticsBean orderLogisticsBean;
    private LayoutInflater inflater;

    public ItemOrderDetailAdapter(LayoutInflater inflater, OrderLogisticsBean list) {
        this.inflater = inflater;
        this.orderLogisticsBean = list;
    }

    @Override
    public int getCount() {
        return orderLogisticsBean.getData().getOrder_info_domain_list().size();
    }

    @Override
    public Object getItem(int position) {
        return orderLogisticsBean.getData().getOrder_info_domain_list().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.item_distribution_commission_good, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        OrderLogisticsBean.DataEntity.OrderInfoDomainListEntity data = (OrderLogisticsBean.DataEntity.OrderInfoDomainListEntity) getItem(position);
        ImageLoaderUtils.getInstance().display(viewHolder.image, data.getSpec_pic());
        TextViewUtils.setText(viewHolder.goodName, data.getGoods_name());
        TextViewUtils.setTextAndleftOther(viewHolder.goodPrice, data.getPrice(), "价格:");
        TextViewUtils.setTextAndleftOther(viewHolder.goodCommission, SystemConfig.moneymulti(data.getCommission_money()), "");
        TextViewUtils.setTextAndleftOther(viewHolder.goodBuyName, orderLogisticsBean.getData().getNickname(), "买家:");
        TextViewUtils.setTextAndleftOther(viewHolder.goodNum, String.valueOf(data.getNumber()), "x ");
        return convertView;
    }

    private class ViewHolder {
        ImageView image;
        TextView goodName, goodPrice, goodBuyName, goodCommission, goodNum;

        public ViewHolder(View itemView) {
            image = (ImageView) itemView.findViewById(R.id.good_image);
            goodName = (TextView) itemView.findViewById(R.id.good_name);
            goodPrice = (TextView) itemView.findViewById(R.id.good_price);
            goodBuyName = (TextView) itemView.findViewById(R.id.good_buy_name);
            goodCommission = (TextView) itemView.findViewById(R.id.good_commission);
            goodNum = (TextView) itemView.findViewById(R.id.good_num);
        }
    }
}
