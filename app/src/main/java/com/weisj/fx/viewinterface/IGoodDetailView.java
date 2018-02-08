package com.weisj.fx.viewinterface;

import com.weisj.fx.bean.GoodDetail;
import com.weisj.fx.bean.GoodDetailImageBean;

/**
 * Created by Administrator on 2016/8/1 0001.
 */
public interface IGoodDetailView {

    int getGoodId();

    void getData(GoodDetail goodDetail);

    void getImageData(GoodDetailImageBean goodDetailImageBean);

    int getState();

    int getActivityId();

}
