package com.weisj.fx.viewinterface;

import com.weisj.fx.bean.HomeBanner;
import com.weisj.fx.bean.HomeBean;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public interface IHomeView {

    void getData(HomeBean homeBean);

    void getBannerData(HomeBanner data);

    void getBannerFail();

}
