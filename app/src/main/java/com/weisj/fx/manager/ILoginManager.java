package com.weisj.fx.manager;

import com.weisj.fx.manager.listener.IOnManagerListener;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public interface ILoginManager {

    void login(String userName, String password, IOnManagerListener loginListerer);

}
