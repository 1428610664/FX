package com.weisj.fx.main.fragment.order;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.weisj.fx.R;
import com.weisj.fx.activity.OrderDetailActivity;
import com.weisj.fx.activity.WalletActivity;
import com.weisj.fx.adapter.ItemDistributionCommissionAdapter;
import com.weisj.fx.base.BaseViewState;
import com.weisj.fx.bean.OrderBean;
import com.weisj.fx.presenter.OrderPresenter;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.TextViewUtils;
import com.weisj.fx.utils.ViewStateUtil;
import com.weisj.fx.view.RootView;
import com.weisj.fx.view.abpullrefresh.AbPullToRefreshView;
import com.weisj.fx.viewinterface.IOrderView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class DistributionCommissionView implements View.OnClickListener, BaseViewState, IOrderView, AbPullToRefreshView.OnHeaderRefreshListener, AbPullToRefreshView.OnFooterLoadListener, RootView.NoWifiListener, ExpandableListView.OnChildClickListener {
    private LayoutInflater inflater;
    private View rootView, allview, noPayView, noSendView, noReceiveView, noEvaluateView, lastView;
    private RootView rootRela;
    private TextView myIncome;
    private ExpandableListView expandableListView;
    private List<OrderBean.DataEntity> dataList = new ArrayList<>();
    private ItemDistributionCommissionAdapter adapter;
    private AbPullToRefreshView refreshView;
    public OrderPresenter presenter;
    private int state = -1;//0等待买家待付款,1买家已付款,2卖家已发货，待买家收货,  12 待评价 -1表示全部
    private TextView noDataView;
    private int filter_type;
    private String wxName;

    public void setWxAndFilterType(int filter_type, String wxName) {
        this.filter_type = filter_type;
        this.wxName = wxName;
        presenter.getInitOrderData(state, filter_type, wxName);
    }

    public DistributionCommissionView(LayoutInflater inflater) {
        this.inflater = inflater;
        initView();
    }

    public View getRootView() {
        return rootRela;
    }

    private void initView() {
        rootRela = new RootView(inflater.getContext());
        presenter = new OrderPresenter(this, this);
        rootView = inflater.inflate(R.layout.view_distribution_commission, null);
        refreshView = (AbPullToRefreshView) rootView.findViewById(R.id.refresh_view);
        refreshView.setOnHeaderRefreshListener(this);
        refreshView.setOnFooterLoadListener(this);
        rootRela.addContentView(rootView);
        rootRela.isHintHeadBar(true);
        rootRela.setNoWifiListener(this);
        expandableListView = (ExpandableListView) rootView.findViewById(R.id.expand_list_view);
        expandableListView.setEmptyView(ViewStateUtil.getNoDataView(inflater.getContext(), "暂无数据", Color.parseColor("#ffffff")));
        expandableListView.setOnChildClickListener(this);
        allview = rootView.findViewById(R.id.order_commission_all);
        noPayView = rootView.findViewById(R.id.order_commission_no_pay);
        noSendView = rootView.findViewById(R.id.order_commission_no_send);
        noReceiveView = rootView.findViewById(R.id.order_commission_no_receive);
        noEvaluateView = rootView.findViewById(R.id.order_commission_no_evaluate);
        noDataView = (TextView) rootView.findViewById(R.id.no_data);
        myIncome = (TextView) rootView.findViewById(R.id.my_come);
        lastView = allview;
        allview.setSelected(true);
        allview.setOnClickListener(this);
        noPayView.setOnClickListener(this);
        noSendView.setOnClickListener(this);
        noReceiveView.setOnClickListener(this);
        noEvaluateView.setOnClickListener(this);
        rootView.findViewById(R.id.my_income_linear).setOnClickListener(this);
        presenter.getInitOrderData(state, filter_type, wxName);
    }

    public void getdata() {
        replaceData();
        presenter.getInitOrderData(state, filter_type, wxName);
    }

    public void changeOrderState(int state) {
        this.state = state;
        presenter.getInitOrderData(state, filter_type, wxName);
    }


    public void setExpandListView() {
        if (expandableListView != null) {
            expandableListView.setGroupIndicator(null);
            for (int i = 0; i < dataList.size(); i++) {
                expandableListView.expandGroup(i);
            }
            expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    OrderBean.DataEntity orderData = dataList.get(groupPosition);
                    Intent intent = new Intent(inflater.getContext(), OrderDetailActivity.class);
                    intent.putExtra("orderId", orderData.getOrder_brand_id());
                    inflater.getContext().startActivity(intent);
                    return true;
                }
            });
        }
    }

    public void replaceData() {
        adapter = new ItemDistributionCommissionAdapter(inflater, dataList, this);
        expandableListView.setAdapter(adapter);
        setExpandListView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_commission_all:
                lastView.setSelected(false);
                v.setSelected(true);
                lastView = allview;
                changeOrderState(-1);
                break;
            case R.id.order_commission_no_pay:
                lastView.setSelected(false);
                v.setSelected(true);
                lastView = noPayView;
                changeOrderState(0);
                break;
            case R.id.order_commission_no_send:
                lastView.setSelected(false);
                v.setSelected(true);
                lastView = noSendView;
                changeOrderState(1);
                break;
            case R.id.order_commission_no_receive:
                lastView.setSelected(false);
                v.setSelected(true);
                lastView = noReceiveView;
                changeOrderState(2);
                break;
            case R.id.order_commission_no_evaluate:
                lastView.setSelected(false);
                v.setSelected(true);
                lastView = noEvaluateView;
                changeOrderState(12);
                break;
            case R.id.my_income_linear:
                inflater.getContext().startActivity(new Intent(inflater.getContext(), WalletActivity.class));
                break;
        }
    }

    @Override
    public void showLoading() {
        changeState(RootView.ViewState.LOADING);
    }

    @Override
    public void showInitLoading() {
        changeState(RootView.ViewState.ONELOADING);
    }

    @Override
    public void showNoNetWork() {
        changeState(RootView.ViewState.NONETWORK);
    }

    @Override
    public void showNoData() {
        changeState(RootView.ViewState.NODATA);
    }

    @Override
    public void showLoadFinish() {
        changeState(RootView.ViewState.SUCCESS);
    }

    public void changeState(RootView.ViewState state) {
        rootRela.changeRootViewState(state);
    }

    @Override
    public void getOrderData(OrderBean orderBean) {
        if (orderBean != null) {
            dataList.addAll(orderBean.getData());
            adapter.notifyDataSetChanged();
            setExpandListView();
        }
        refreshView.onFooterLoadFinish();
        refreshView.onHeaderRefreshFinish();
    }

    @Override
    public void getInitOrderData(OrderBean orderBean) {
        dataList.clear();
        dataList.addAll(orderBean.getData());
        if (dataList.size() > 0) {
            noDataView.setVisibility(View.GONE);
        } else {
            noDataView.setVisibility(View.VISIBLE);
        }
        adapter = new ItemDistributionCommissionAdapter(inflater, dataList, this);
        expandableListView.setAdapter(adapter);
        setExpandListView();
        refreshView.onFooterLoadFinish();
        refreshView.onHeaderRefreshFinish();
    }

    @Override
    public void getOrderFail() {
        dataList.clear();
        if (dataList.size() > 0) {
            noDataView.setVisibility(View.GONE);
        } else {
            noDataView.setVisibility(View.VISIBLE);
        }
        if (adapter == null) {
            adapter = new ItemDistributionCommissionAdapter(inflater, dataList, this);
            expandableListView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        refreshView.onFooterLoadFinish();
        refreshView.onHeaderRefreshFinish();
    }

    @Override
    public void getRecommendStr(String money) {
        TextViewUtils.setTextAndleftOther(myIncome, SystemConfig.moneymulti(money), "");
    }

    @Override
    public void deleteOrderSuccess() {
        SystemConfig.showToast("订单删除成功,刷新中···");
        presenter.getInitOrderData(state, filter_type, wxName);
    }

    @Override
    public void deleteOrderFail() {
        SystemConfig.showToast("订单删除失败");
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        presenter.getInitOrderData(state, filter_type, wxName);
    }

    @Override
    public void onFooterLoad(AbPullToRefreshView view) {
        presenter.getOrderdata(state, filter_type, wxName);
    }

    @Override
    public void onNoWifiLister(View v) {
        getdata();
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        OrderBean.DataEntity orderData = dataList.get(groupPosition);
        Intent intent = new Intent(inflater.getContext(), OrderDetailActivity.class);
        intent.putExtra("orderId", orderData.getOrder_brand_id());
        inflater.getContext().startActivity(intent);
        return true;
    }

}
