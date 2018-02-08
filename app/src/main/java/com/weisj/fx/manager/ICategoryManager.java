package com.weisj.fx.manager;

import com.weisj.fx.manager.listener.IOnManagerListener;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public interface ICategoryManager {
    void getCateContent(int id, IOnManagerListener onManagerListener);

    void getCateTitle(IOnManagerListener listener);
}
