package com.weisj.fx.viewinterface;

import com.weisj.fx.bean.OrderBean;
import com.weisj.fx.bean.ShareRecordBean;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public interface IOrderView {

    void getOrderData(OrderBean orderBean);

    void getInitOrderData(OrderBean orderBean);

    void getOrderFail();

    void getRecommendStr(String money);

    void deleteOrderSuccess();

    void deleteOrderFail();

}
