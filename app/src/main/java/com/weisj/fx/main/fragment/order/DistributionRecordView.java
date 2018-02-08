package com.weisj.fx.main.fragment.order;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.adapter.ItemDistributionCommissionAdapter;
import com.weisj.fx.adapter.ItemDistributionRecordAdapter;
import com.weisj.fx.base.BaseViewState;
import com.weisj.fx.bean.OrderBean;
import com.weisj.fx.bean.OrderShareBean;
import com.weisj.fx.presenter.OrderPresenter;
import com.weisj.fx.presenter.OrderSharePresenter;
import com.weisj.fx.utils.TextViewUtils;
import com.weisj.fx.view.RootView;
import com.weisj.fx.view.abpullrefresh.AbPullToRefreshView;
import com.weisj.fx.viewinterface.IOrderShareView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class DistributionRecordView implements BaseViewState, IOrderShareView, AbPullToRefreshView.OnHeaderRefreshListener, ExpandableListView.OnGroupClickListener, RootView.NoWifiListener {
    private LayoutInflater inflater;
    private View rootView;
    private RootView rootRela;
    private int count;
    private ExpandableListView listView;
    private TextView shareCount, sharePrice;
    private OrderSharePresenter presenter;
    private AbPullToRefreshView refreshView;
    private ItemDistributionRecordAdapter adapter;
    private TextView no_data;

    public DistributionRecordView(LayoutInflater inflater) {
        this.inflater = inflater;
        initView();
    }

    public View getRootView() {
        return rootRela;
    }

    private void initView() {
        rootRela = new RootView(inflater.getContext());
        rootView = inflater.inflate(R.layout.view_distribution_record, null);
        rootRela.addContentView(rootView);
        rootRela.isHintHeadBar(true);
        rootRela.setNoWifiListener(this);
        listView = (ExpandableListView) rootView.findViewById(R.id.expand_list_view);
        shareCount = (TextView) rootView.findViewById(R.id.share_count);
        sharePrice = (TextView) rootView.findViewById(R.id.share_price);
        no_data = (TextView) rootView.findViewById(R.id.no_data);
        refreshView = (AbPullToRefreshView) rootView.findViewById(R.id.refresh_view);
        refreshView.setOnHeaderRefreshListener(this);
        refreshView.setLoadMoreEnable(false);
        presenter = new OrderSharePresenter(this, this);
        presenter.getData();
        listView.setOnGroupClickListener(this);
    }

    private void setExpandListView() {
        listView.setGroupIndicator(null);
        for (int i = 0; i < count; i++) {
            listView.expandGroup(i);
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
    public void getData(OrderShareBean data) {
        count = data.getData().getBrand_share_record_domain_list().size();
        if (count > 0) {
            no_data.setVisibility(View.GONE);
            adapter = new ItemDistributionRecordAdapter(inflater, data.getData(),presenter);
            listView.setAdapter(adapter);
            TextViewUtils.setTextAndallOtherIsZero(shareCount, String.valueOf(data.getData().getAll_total_count()), "总访问量:", "次");
            TextViewUtils.setTextAndleftOther(sharePrice, data.getData().getAccount_money(), "￥");
            setExpandListView();
            refreshView.onHeaderRefreshFinish();
        } else {
            no_data.setVisibility(View.VISIBLE);
        }
    }

    public void getdata() {
        presenter.getData();
    }

    @Override
    public void getFail() {
        no_data.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        presenter.getData();
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return true;
    }

    @Override
    public void onNoWifiLister(View v) {
        getdata();
    }
}
