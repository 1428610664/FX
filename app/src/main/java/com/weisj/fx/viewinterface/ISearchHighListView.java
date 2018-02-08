package com.weisj.fx.viewinterface;

import com.weisj.fx.bean.GoodBean;
import com.weisj.fx.bean.Region;
import com.weisj.fx.bean.SearchBrand;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public interface ISearchHighListView {

    int getOrderField();

    int getOrderType();

    void getData(GoodBean goodBean);

    void getInitData(GoodBean goodBean);


}
