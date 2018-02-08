package com.weisj.fx.adapter;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.activity.CheckLogisticsActivity;
import com.weisj.fx.bean.OrderBean;
import com.weisj.fx.main.fragment.order.DistributionCommissionView;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.TextViewUtils;

import java.util.List;


public class ItemDistributionCommissionAdapter extends BaseExpandableListAdapter implements View.OnClickListener {
    private LayoutInflater mInflater;
    private List<OrderBean.DataEntity> list;
    private DistributionCommissionView view;

    public ItemDistributionCommissionAdapter(LayoutInflater mInflater, List<OrderBean.DataEntity> list, DistributionCommissionView view) {
        this.mInflater = mInflater;
        this.list = list;
        this.view = view;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        try {
            return list.get(groupPosition).getOrder_info_domain_list().size();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        try {
            return list.get(groupPosition).getOrder_info_domain_list().get(childPosition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        BrandViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_distribution_commission, null);
            viewHolder = new BrandViewHolder();
            viewHolder.brandName = (TextView) convertView.findViewById(R.id.order_brand_name);
            viewHolder.orderState = (TextView) convertView.findViewById(R.id.order_state);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (BrandViewHolder) convertView.getTag();
        }
        OrderBean.DataEntity data = (OrderBean.DataEntity) getGroup(groupPosition);
        TextViewUtils.setText(viewHolder.brandName, data.getBrand_name());
        switch (data.getOrder_brand_state()) {
            case 0:// 0等待买家待付款,1买家已付款,2卖家已发货，待买家收货,3交易成功, 5交易关闭
                viewHolder.orderState.setText("待付款");
                break;
            case 1:
                viewHolder.orderState.setText("待发货");
                break;
            case 2:
                viewHolder.orderState.setText("待收货");
                break;
            case 3:
                viewHolder.orderState.setText("交易完成");
                break;
            case 4:
                viewHolder.orderState.setText("交易取消");
                break;
            case 5:
                viewHolder.orderState.setText("交易取消");
                break;
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        GoodViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_distribution_commission_good, null);
            viewHolder = new GoodViewHolder();
            viewHolder.bottomLinear = (LinearLayout) convertView.findViewById(R.id.bottom_linear);
            viewHolder.goodImage = (ImageView) convertView.findViewById(R.id.good_image);
            viewHolder.goodName = (TextView) convertView.findViewById(R.id.good_name);
            viewHolder.goodPrice = (TextView) convertView.findViewById(R.id.good_price);
            viewHolder.goodBuyName = (TextView) convertView.findViewById(R.id.good_buy_name);
            viewHolder.goodCommission = (TextView) convertView.findViewById(R.id.good_commission);
            viewHolder.orderNum = (TextView) convertView.findViewById(R.id.order_num);
            viewHolder.orderCommission = (TextView) convertView.findViewById(R.id.order_commission_all);
            viewHolder.goodNum = (TextView) convertView.findViewById(R.id.good_num);
            viewHolder.goodNormal = (LinearLayout) convertView.findViewById(R.id.order_normal);
            viewHolder.goodDelete = (RelativeLayout) convertView.findViewById(R.id.order_delete);
            viewHolder.goodDeleteBt = (ImageView) convertView.findViewById(R.id.order_delete_bt);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GoodViewHolder) convertView.getTag();
        }
        OrderBean.DataEntity.OrderInfoDomainListEntity childData = (OrderBean.DataEntity.OrderInfoDomainListEntity) getChild(groupPosition, childPosition);
        if (childData != null) {
            OrderBean.DataEntity dataEntity = (OrderBean.DataEntity) getGroup(groupPosition);
            ImageLoaderUtils.getInstance().display(viewHolder.goodImage, childData.getSpec_pic());
            TextViewUtils.setText(viewHolder.goodName, childData.getGoods_name());
            TextViewUtils.setTextAndleftOther(viewHolder.goodPrice, childData.getPrice(), "价格:");
            TextViewUtils.setTextAndleftOther(viewHolder.goodBuyName, dataEntity.getNickname(), "买家:");
            TextViewUtils.setTextAndleftOther(viewHolder.goodCommission, SystemConfig.moneymulti(childData.getCommission_money()), "");
            TextViewUtils.setTextAndleftOther(viewHolder.goodNum, String.valueOf(childData.getNumber()), "x ");

            OrderBean.DataEntity data = (OrderBean.DataEntity) getGroup(groupPosition);
            if (childPosition == getChildrenCount(groupPosition) - 1) {
                viewHolder.bottomLinear.setVisibility(View.VISIBLE);
                viewHolder.goodNormal.setVisibility(View.GONE);
                viewHolder.goodDelete.setVisibility(View.GONE);
                if (data.getOrder_brand_state() != 4 && data.getOrder_brand_state() != 5) {
                    viewHolder.goodNormal.setVisibility(View.VISIBLE);
                    if (data.getOrder_info_domain_list() != null && data.getOrder_info_domain_list().size() > 0) {
                        int num = 0;
                        for (int i = 0; i < data.getOrder_info_domain_list().size(); i++) {
                            num += data.getOrder_info_domain_list().get(i).getNumber();
                        }
                        TextViewUtils.setTextAndallOtherIsZero(viewHolder.orderNum, String.valueOf(num), "共计", "件");
                    }
                    TextViewUtils.setTextAndleftOther(viewHolder.orderCommission, SystemConfig.moneymulti(dataEntity.getCommission_money()), "");
                } else {
                    viewHolder.goodDelete.setVisibility(View.VISIBLE);
                    if (data.getOrder_info_domain_list() != null && data.getOrder_info_domain_list().size() > 0) {
                        int num = 0;
                        for (int i = 0; i < data.getOrder_info_domain_list().size(); i++) {
                            num += data.getOrder_info_domain_list().get(i).getNumber();
                        }
                        TextViewUtils.setTextAndallOtherIsZero(viewHolder.orderNum, String.valueOf(num), "共计", "件");
                    }
                    viewHolder.goodDeleteBt.setOnClickListener(this);
                    viewHolder.goodDeleteBt.setTag(data);
                }
            } else {
                viewHolder.bottomLinear.setVisibility(View.GONE);
            }
            viewHolder.bottomLinear.setOnClickListener(this);
            viewHolder.bottomLinear.setTag(dataEntity);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_linear:
                OrderBean.DataEntity dataEntity = (OrderBean.DataEntity) v.getTag();
                Intent intent = new Intent(mInflater.getContext(), CheckLogisticsActivity.class);
                intent.putExtra("orderId", dataEntity.getOrder_brand_id());
                intent.putExtra("orderType", dataEntity.getOrder_brand_state());
                mInflater.getContext().startActivity(intent);
                break;
            case R.id.order_delete_bt:
                OrderBean.DataEntity data = (OrderBean.DataEntity) v.getTag();
                deleteAlert(data);
                break;
        }
    }

    private void deleteAlert(final OrderBean.DataEntity dataEntity) {
        new AlertDialog.Builder(mInflater.getContext()).setTitle("金一提示")//设置对话框标题
                .setMessage("请确认是否删除该订单记录")//设置显示的内容
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                        view.presenter.deleteOrder(dataEntity.getOrder_brand_id());
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加返回按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {//响应事件
                dialog.dismiss();
            }
        }).show();//在按键响应事件中显示此对话框
    }


    private class BrandViewHolder {
        TextView brandName;
        TextView orderState;
    }

    private class GoodViewHolder {
        ImageView goodImage;
        TextView goodName;
        TextView goodPrice;
        TextView goodBuyName;
        TextView goodCommission;
        TextView orderNum;
        TextView orderCommission;
        TextView goodNum;
        LinearLayout bottomLinear;
        LinearLayout goodNormal;
        RelativeLayout goodDelete;
        ImageView goodDeleteBt;
    }

}
