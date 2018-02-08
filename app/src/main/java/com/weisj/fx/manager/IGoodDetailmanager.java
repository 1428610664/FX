package com.weisj.fx.manager;

import com.weisj.fx.manager.listener.IOnManagerListener;

/**
 * Created by Administrator on 2016/8/1 0001.
 */
public interface IGoodDetailmanager {

    void getData(int good_id, IOnManagerListener listener);

    void getImageData(int good_id, IOnManagerListener listener);

    void getGoodPointData(int good_id,int page,IOnManagerListener listener);

    void getActiGoodData(int good_id,int activity_id,IOnManagerListener listener);
}
