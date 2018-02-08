package com.weisj.fx.viewinterface;

import com.weisj.fx.bean.OneShareBean;
import com.weisj.fx.bean.ShareActiBean;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public interface IListView {

    void getJCRecordData(ShareActiBean actiBean);

    void getJCPartData(OneShareBean oneShareBean);

    void getMoreJcPartData(OneShareBean oneShareBean);

    int getState();

    int getActivityId();

    void getMoreFail();
}
