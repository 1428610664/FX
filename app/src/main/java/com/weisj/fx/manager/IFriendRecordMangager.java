package com.weisj.fx.manager;

import com.weisj.fx.manager.listener.IOnManagerListener;

/**
 * Created by Administrator on 2016/8/5 0005.
 */
public interface IFriendRecordMangager {

    void getData(int goods_id, int coupon_id, IOnManagerListener listener);
}
