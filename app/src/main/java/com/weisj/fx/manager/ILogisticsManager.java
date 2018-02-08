package com.weisj.fx.manager;

import com.weisj.fx.manager.listener.IOnManagerListener;

/**
 * Created by Administrator on 2016/8/5 0005.
 */
public interface ILogisticsManager {
    void getOrderDetail(int orderId, IOnManagerListener listener);

    void getLogisticsData(String sn, String code, IOnManagerListener listener);

}
