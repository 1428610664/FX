package com.weisj.fx.viewinterface;

import com.weisj.fx.bean.OrderLogisticsBean;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
public interface IOrderDetailView {
    void getData(OrderLogisticsBean data);

    int getOrderId();
}
