package com.weisj.fx.presenter;

import com.weisj.fx.base.BaseViewState;
import com.weisj.fx.bean.OrderLogisticsBean;
import com.weisj.fx.manager.ILogisticsManager;
import com.weisj.fx.manager.impl.LogisticsManger;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.viewinterface.IOrderDetailView;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
public class OrderDetailPresenter implements IOnManagerListener {
    private ILogisticsManager manager;
    private BaseViewState viewState;
    private IOrderDetailView orderDetailView;

    public OrderDetailPresenter(IOrderDetailView orderDetailView, BaseViewState viewState) {
        this.orderDetailView = orderDetailView;
        this.viewState = viewState;
        manager = new LogisticsManger();
    }

    public void getData() {
        viewState.showInitLoading();
        manager.getOrderDetail(orderDetailView.getOrderId(), this);
    }

    @Override
    public void onSuccess(Object data, String url) {
        OrderLogisticsBean response = (OrderLogisticsBean) data;
        if (response.getCode().equals("1")) {
            viewState.showLoadFinish();
            orderDetailView.getData(response);
        } else {
            viewState.showNoData();
        }
    }

    @Override
    public void onFail(Exception e, String url) {
        viewState.showNoData();
    }
}
