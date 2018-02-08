package com.weisj.fx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.adapter.ItemOrderDetailAdapter;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.bean.OrderLogisticsBean;
import com.weisj.fx.presenter.OrderDetailPresenter;
import com.weisj.fx.utils.TextViewUtils;
import com.weisj.fx.view.MyListView;
import com.weisj.fx.viewinterface.IOrderDetailView;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
public class OrderDetailActivity extends BaseActivity implements IOrderDetailView, View.OnClickListener {
    private TextView orderStateText, orderName, orderAddress, orderPhone, orderBrandTitle, orderSendMoney, orderMoney, orderNumber, orderCreateTime, orderBuyTime;
    private MyListView mylist;
    private Button logisticsBt;
    private LinearLayout myorder_lv;
    private OrderDetailPresenter presenter;
    private OrderLogisticsBean orderLogisticsBean;

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_order_detail, null);
        initView(view);
        presenter = new OrderDetailPresenter(this, this);
        presenter.getData();
        return view;
    }

    private void initView(View view) {
        orderStateText = (TextView) view.findViewById(R.id.order_brand_state);
        orderName = (TextView) view.findViewById(R.id.order_name);
        orderAddress = (TextView) view.findViewById(R.id.order_address);
        orderPhone = (TextView) view.findViewById(R.id.order_phone);
        orderBrandTitle = (TextView) view.findViewById(R.id.brand_title);
        orderMoney = (TextView) view.findViewById(R.id.brand_total_money);
        orderSendMoney = (TextView) view.findViewById(R.id.order_send_money);
        orderNumber = (TextView) view.findViewById(R.id.order_brand_sn);
        orderCreateTime = (TextView) view.findViewById(R.id.create_time);
        orderBuyTime = (TextView) view.findViewById(R.id.pay_time);
        mylist = (MyListView) view.findViewById(R.id.mylist);
        logisticsBt = (Button) view.findViewById(R.id.b1);
        myorder_lv = (LinearLayout) view.findViewById(R.id.myorder_lv);
        logisticsBt.setOnClickListener(this);
    }

    @Override
    public String setTitleStr() {
        return "订单详情";
    }

    @Override
    public void getRefreshData() {
        if (presenter != null)
            presenter.getData();
    }

    @Override
    public void getData(OrderLogisticsBean data) {
        orderLogisticsBean = data;
        switch (data.getData().getOrder_brand_state()) {
            case 0:// 0等待买家待付款,1买家已付款,2卖家已发货，待买家收货,3交易成功, 5交易关闭
                orderStateText.setText("待付款");
                myorder_lv.setVisibility(View.GONE);
                break;
            case 1:
                orderStateText.setText("待发货");
                myorder_lv.setVisibility(View.GONE);
                break;
            case 2:
                orderStateText.setText("待收货");
                myorder_lv.setVisibility(View.VISIBLE);
                break;
            case 4:
                orderStateText.setText("交易取消");
                myorder_lv.setVisibility(View.GONE);
                break;
            case 3:
                orderStateText.setText("交易完成");
                myorder_lv.setVisibility(View.VISIBLE);
                break;
            case 5:
                orderStateText.setText("交易取消");
                myorder_lv.setVisibility(View.GONE);
                break;
        }
        if (data.getData().getRecipient_object() != null) {
            TextViewUtils.setText(orderName, data.getData().getRecipient_object().getRecipients());
            TextViewUtils.setText(orderAddress, String.format("%s%s%s%s%s", data.getData().getRecipient_object().getCity(), data.getData().getRecipient_object().getProvince(),
                    data.getData().getRecipient_object().getCity(), data.getData().getRecipient_object().getArea(), data.getData().getRecipient_object().getAddress()));
            TextViewUtils.setText(orderPhone, data.getData().getRecipient_object().getPhone());
        }
        TextViewUtils.setText(orderBrandTitle, data.getData().getBrand_name());
        TextViewUtils.setText(orderNumber, data.getData().getOrder_brand_sn());
        TextViewUtils.setText(orderCreateTime, data.getData().getCreate_time());
        TextViewUtils.setText(orderBuyTime, data.getData().getPay_time());
        if (data.getData().getShipping_money() == null) {
            orderSendMoney.setText("￥0");
        } else {
            TextViewUtils.setTextAndleftOther(orderSendMoney, data.getData().getShipping_money(), "￥");
        }
        TextViewUtils.setTextAndleftOther(orderMoney, data.getData().getBrand_total_money(), "￥");
        ItemOrderDetailAdapter adapter = new ItemOrderDetailAdapter(mLayoutInflater, data);
        mylist.setAdapter(adapter);

    }

    @Override
    public int getOrderId() {
        return getIntent().getIntExtra("orderId", 0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b1:
                Intent intent = new Intent(
                        this, CheckLogisticsActivity.class);
                intent.putExtra("orderId", orderLogisticsBean.getData().getOrder_brand_id());
                intent.putExtra("orderType", orderLogisticsBean.getData().getOrder_brand_state());
                startActivity(intent);
                break;
        }
    }
}
