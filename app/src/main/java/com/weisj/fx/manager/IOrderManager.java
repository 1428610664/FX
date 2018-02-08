package com.weisj.fx.manager;

import com.weisj.fx.manager.listener.IOnManagerListener;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public interface IOrderManager {

    void getOrderData(int filter_type, String wx_name,int state, int page, int pageNum, IOnManagerListener listener);

    void getOrderRecordData(IOnManagerListener listener);

    void getMyRecommend(IOnManagerListener listener);

    void deleteOrder(int id,IOnManagerListener listener);
}
