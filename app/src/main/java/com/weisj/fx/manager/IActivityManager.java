package com.weisj.fx.manager;

import com.weisj.fx.manager.listener.IOnManagerListener;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public interface IActivityManager {
    void getAllShareActivity(IOnManagerListener listener);

    void getOneShareActivity(int page, int activityId, IOnManagerListener listener);
}
