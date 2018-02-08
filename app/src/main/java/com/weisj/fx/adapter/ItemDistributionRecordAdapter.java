package com.weisj.fx.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.weisj.fx.R;
import com.weisj.fx.activity.FriendRecordActivity;
import com.weisj.fx.bean.BaseBean;
import com.weisj.fx.bean.OrderShareBean;
import com.weisj.fx.presenter.OrderSharePresenter;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.TextViewUtils;
import com.weisj.fx.utils.Urls;
import com.weisj.fx.view.AlertDialog;

import java.util.HashMap;
import java.util.Map;


public class ItemDistributionRecordAdapter<T> extends BaseExpandableListAdapter implements View.OnClickListener {

    private LayoutInflater mInflater;
    private OrderShareBean.DataEntity dataEntity;
    private OrderSharePresenter presenter;
    private AlertDialog alertDialog;
    public ItemDistributionRecordAdapter(LayoutInflater mInflater, OrderShareBean.DataEntity dataEntity,OrderSharePresenter presenter) {
        this.mInflater = mInflater;
        this.dataEntity = dataEntity;
        this.presenter = presenter;
    }

    @Override
    public int getGroupCount() {
        return dataEntity.getBrand_share_record_domain_list().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return dataEntity.getBrand_share_record_domain_list().get(groupPosition).getShare_record_domain_list().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return dataEntity.getBrand_share_record_domain_list().get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return dataEntity.getBrand_share_record_domain_list().get(groupPosition).getShare_record_domain_list().get(childPosition);
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
            convertView = mInflater.inflate(R.layout.item_distribution_record, null);
            viewHolder = new BrandViewHolder();
            viewHolder.brandName = (TextView) convertView.findViewById(R.id.brand_name);
            viewHolder.brandRatio = (TextView) convertView.findViewById(R.id.brand_ratio);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (BrandViewHolder) convertView.getTag();
        }
        OrderShareBean.DataEntity.BrandShareRecordDomainListEntity data = (OrderShareBean.DataEntity.BrandShareRecordDomainListEntity) getGroup(groupPosition);
        TextViewUtils.setText(viewHolder.brandName, data.getBrand_name());
        viewHolder.brandRatio.setText(String.format("兑换率:%d:1", data.getVisit_reward()));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        GoodViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_distribution_good, null);
            viewHolder = new GoodViewHolder();
            viewHolder.goodImage = (ImageView) convertView.findViewById(R.id.good_image);
            viewHolder.goodName = (TextView) convertView.findViewById(R.id.good_name);
            viewHolder.goodExchangePrice = (TextView) convertView.findViewById(R.id.brand_exchange_price);
            viewHolder.goodRecordCount = (TextView) convertView.findViewById(R.id.good_record_count);
            viewHolder.goodExchageBt = (TextView) convertView.findViewById(R.id.brand_exchange_bt);
            viewHolder.bottomLinear = (LinearLayout) convertView.findViewById(R.id.bottom_linear);
            viewHolder.goodLinear = (LinearLayout) convertView.findViewById(R.id.good_linear);
            viewHolder.delete_bt = convertView.findViewById(R.id.delete_bt);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GoodViewHolder) convertView.getTag();
        }
        OrderShareBean.DataEntity.BrandShareRecordDomainListEntity groupData = (OrderShareBean.DataEntity.BrandShareRecordDomainListEntity) getGroup(groupPosition);
        OrderShareBean.DataEntity.BrandShareRecordDomainListEntity.ShareRecordDomainListEntity childData = (OrderShareBean.DataEntity.BrandShareRecordDomainListEntity.ShareRecordDomainListEntity) getChild(groupPosition, childPosition);
        if (childData.getCoupon_id() > 0) {
            ImageLoaderUtils.getInstance().display(viewHolder.goodImage, childData.getCoupon_pic());
            TextViewUtils.setText(viewHolder.goodName, childData.getCoupon_title());
        } else {
            ImageLoaderUtils.getInstance().display(viewHolder.goodImage, childData.getGoods_pic());
            TextViewUtils.setText(viewHolder.goodName, childData.getGoods_name());
        }
        TextViewUtils.setTextAndleftOther(viewHolder.goodRecordCount, String.valueOf(childData.getTotal_count()), "浏览次数:");
        viewHolder.goodLinear.setOnClickListener(this);
        viewHolder.goodLinear.setTag(childData);
        viewHolder.delete_bt.setOnClickListener(this);
        viewHolder.delete_bt.setTag(childData);
//        if (childPosition == getChildrenCount(groupPosition) - 1) {
//            viewHolder.bottomLinear.setVisibility(View.VISIBLE);
//            int price = groupData.getBrand_available_count() / groupData.getVisit_reward();
//            TextViewUtils.setTextAndleftOther(viewHolder.goodExchangePrice, String.valueOf(price), "￥");
//            viewHolder.goodExchageBt.setOnClickListener(this);
//            if (price > 0) {
//                viewHolder.goodExchageBt.setAlpha(1);
//                viewHolder.goodExchageBt.setTag(groupData.getBrand_id());
//            } else {
//                viewHolder.goodExchageBt.setAlpha(0.3f);
//                viewHolder.goodExchageBt.setTag(null);
//            }
//        } else {
//            viewHolder.bottomLinear.setVisibility(View.GONE);
//        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.good_linear:
                OrderShareBean.DataEntity.BrandShareRecordDomainListEntity.ShareRecordDomainListEntity childData = (OrderShareBean.DataEntity.BrandShareRecordDomainListEntity.ShareRecordDomainListEntity) v.getTag();
                Intent intent = new Intent(mInflater.getContext(), FriendRecordActivity.class);
                intent.putExtra("goodId", childData.getGoods_id());
                intent.putExtra("couponId", childData.getCoupon_id());
                mInflater.getContext().startActivity(intent);
                break;
            case R.id.brand_exchange_bt:
                if (v.getTag() != null) {
                    exchange((Integer) v.getTag());
                }
                break;
            case R.id.delete_bt:
                if (v.getTag() != null) {
                    if (alertDialog == null){
                        alertDialog = new AlertDialog(mInflater.getContext());
                        alertDialog.setMessage("确认删除这条记录");
                        alertDialog.setTitle("提示");
                        alertDialog.setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alertDialog.dismiss();
                            }
                        });
                        alertDialog.setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                OrderShareBean.DataEntity.BrandShareRecordDomainListEntity.ShareRecordDomainListEntity data = (OrderShareBean.DataEntity.BrandShareRecordDomainListEntity.ShareRecordDomainListEntity) v.getTag();
                                deleteRecord(data.getShare_record_id());
                                alertDialog.dismiss();
                            }
                        });
                    }
                    alertDialog.show();
                }
                break;
        }
    }

    private void deleteRecord(int sharedId){
        Map<String, String> params = new HashMap<>();
        params.put("share_record_id", String.valueOf(sharedId));
        OkHttpClientManager.postAsyn(Urls.delShareRecord, params, new OkHttpClientManager.ResultCallback<BaseBean>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(BaseBean response) {
                if (response != null && response.getCode().equals("1")) {
                    presenter.getData();
                }
            }
        });
    }



    private void exchange(int brandId) {
        Map<String, String> params = new HashMap<>();
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        params.put("brand_id", String.valueOf(brandId));
        OkHttpClientManager.postAsyn(Urls.exchange, params, new OkHttpClientManager.ResultCallback<BaseBean>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(BaseBean response) {
                if (response != null) {
                    SystemConfig.showToast(response.getMsg());
                }
            }
        });

    }

    private class BrandViewHolder {
        TextView brandName;
        TextView brandRatio;
    }

    private class GoodViewHolder {
        ImageView goodImage;
        TextView goodName;
        TextView goodRecordCount;
        TextView goodExchangePrice;
        TextView goodExchageBt;
        LinearLayout bottomLinear;
        LinearLayout goodLinear;
        View delete_bt;
    }
}
