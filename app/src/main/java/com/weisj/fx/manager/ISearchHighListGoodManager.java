package com.weisj.fx.manager;

import com.weisj.fx.manager.listener.IOnManagerListener;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public interface ISearchHighListGoodManager {
    void getGoods(int order_field, int order_type, int page, int pageNum, IOnManagerListener onManagerListener);
}
