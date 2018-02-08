package com.weisj.fx.manager;

import com.weisj.fx.manager.listener.IOnManagerListener;

/**
 * Created by Administrator on 2016/8/6 0006.
 */
public interface INoticeManager {
    void getData(int page, int pageNum, int cateGory, IOnManagerListener listener);
}
