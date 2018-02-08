package com.weisj.fx.viewinterface;

import com.weisj.fx.bean.LogisticsData;
import com.weisj.fx.bean.OrderLogisticsBean;

/**
 * Created by Administrator on 2016/8/5 0005.
 */
public interface ILogisticsView {

    void getData(LogisticsData data);

    void getDataFail();

    int getOrderId();

    void setSendCompany(String name,String nu);
}
