package com.weisj.fx.presenter;

import com.weisj.fx.base.BaseViewState;
import com.weisj.fx.bean.OrderShareBean;
import com.weisj.fx.manager.IOrderManager;
import com.weisj.fx.manager.impl.OrderManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.viewinterface.IOrderShareView;

/**
 * Created by Administrator on 2016/8/5 0005.
 */
public class OrderSharePresenter implements IOnManagerListener {
    private BaseViewState viewState;
    private IOrderManager orderManager;
    private IOrderShareView orderShareView;

    public OrderSharePresenter(IOrderShareView orderShareView, BaseViewState viewState) {
        this.orderShareView = orderShareView;
        this.viewState = viewState;
        orderManager = new OrderManager();
    }

    public void getData() {
        viewState.showLoading();
        orderManager.getOrderRecordData(this);
    }

    @Override
    public void onSuccess(Object data, String url) {
        OrderShareBean bean = (OrderShareBean) data;
        viewState.showLoadFinish();
        if (bean.getCode().equals("1")) {
            orderShareView.getData(bean);
        } else {
            orderShareView.getFail();
        }
    }

    @Override
    public void onFail(Exception e, String url) {
        viewState.showNoNetWork();
    }
}
