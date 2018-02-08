package com.weisj.fx.presenter;

import com.weisj.fx.base.BaseViewState;
import com.weisj.fx.bean.BaseBean;
import com.weisj.fx.bean.OrderBean;
import com.weisj.fx.bean.RecommendBean;
import com.weisj.fx.manager.IOrderManager;
import com.weisj.fx.manager.impl.OrderManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.Urls;
import com.weisj.fx.viewinterface.IOrderView;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class OrderPresenter implements IOnManagerListener {
    private IOrderManager orderManager;
    private IOrderView orderView;
    private BaseViewState viewState;
    private int page = 1;
    private int pageNum = 10;

    public OrderPresenter(BaseViewState viewState, IOrderView orderView) {
        this.viewState = viewState;
        this.orderView = orderView;
        orderManager = new OrderManager();
    }

    public void getInitOrderData(int orderState, int filter_type, String wxName) {
        page = 1;
        viewState.showLoading();
        orderManager.getOrderData(filter_type, wxName, orderState, page, pageNum, this);
        orderManager.getMyRecommend(this);
    }

    public void getOrderdata(int orderState, int filter_type, String wxName) {
        page++;
        orderManager.getOrderData(filter_type, wxName, orderState, page, pageNum, this);
    }

    public void deleteOrder(int id) {
        orderManager.deleteOrder(id, this);
    }


    @Override
    public void onSuccess(Object data, String url) {
        if (url.equals(Urls.getcommissionincome)) {
            RecommendBean bean = (RecommendBean) data;
            if (((RecommendBean) data).getCode().equals("1")) {
                orderView.getRecommendStr(bean.getData());
            }
        } else if (url.equals(Urls.deleteorder)) {
            BaseBean baseBean = (BaseBean) data;
            if (baseBean.getCode().equals("1")) {
                orderView.deleteOrderSuccess();
            } else {
                orderView.deleteOrderFail();
            }
        } else {
            OrderBean orderBean = (OrderBean) data;
            viewState.showLoadFinish();
            String[] str = url.split("&page=");
            String page = str[1];
            if (page.equals("1")) {
                if (orderBean.getCode().equals("1") && orderBean.getData() != null) {
                    orderView.getInitOrderData(orderBean);
                } else {
                    orderView.getOrderFail();
                }
            } else {
                if (orderBean.getCode().equals("1") && orderBean.getData() != null) {
                    orderView.getOrderData(orderBean);
                } else {
                    orderView.getOrderData(null);
                }
            }
        }
    }

    @Override
    public void onFail(Exception e, String url) {
        viewState.showNoNetWork();
    }
}
