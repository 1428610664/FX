package com.weisj.fx.presenter;

import com.weisj.fx.base.BaseViewState;
import com.weisj.fx.bean.LogisticsData;
import com.weisj.fx.bean.OrderLogisticsBean;
import com.weisj.fx.manager.ILogisticsManager;
import com.weisj.fx.manager.impl.LogisticsManger;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.Urls;
import com.weisj.fx.viewinterface.ILogisticsView;

/**
 * Created by Administrator on 2016/8/5 0005.
 */
public class LogisticsPresenter implements IOnManagerListener {
    private ILogisticsManager logisticsManager;
    private BaseViewState viewState;
    private ILogisticsView logisticsView;

    public LogisticsPresenter(ILogisticsView logisticsView, BaseViewState viewState) {
        this.logisticsView = logisticsView;
        this.viewState = viewState;
        this.logisticsManager = new LogisticsManger();
    }

    public void getData() {
        viewState.showInitLoading();
        logisticsManager.getOrderDetail(logisticsView.getOrderId(), this);
    }

    @Override
    public void onSuccess(Object data, String url) {
        if (url.equals(Urls.getoneorderbyid)) {
            OrderLogisticsBean bean = (OrderLogisticsBean) data;
            if (bean.getCode().equals("1") && bean.getData() != null) {
                logisticsManager.getLogisticsData(bean.getData().getShipping_company_code(), bean.getData().getShipping_sn(), this);
                logisticsView.setSendCompany(bean.getData().getShipping_company_name(),bean.getData().getShipping_sn());
            } else {
                viewState.showLoadFinish();
                logisticsView.getDataFail();
            }
        } else {
            viewState.showLoadFinish();
            logisticsView.getData((LogisticsData) data);
        }
    }

    @Override
    public void onFail(Exception e, String url) {
        viewState.showNoNetWork();
    }
}
