package com.weisj.fx.manager;

import com.weisj.fx.manager.listener.IOnManagerListener;

/**
 * Created by Administrator on 2016/8/4 0004.
 */
public interface INavManager {

    void getNavData(IOnManagerListener listener);

    void uploadPhoneSn(String sn);

}
