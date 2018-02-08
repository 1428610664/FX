package com.weisj.fx.viewinterface;

import com.weisj.fx.bean.GoodBean;
import com.weisj.fx.bean.Region;
import com.weisj.fx.bean.SearchBrand;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public interface ISearchListView {

    int getCategoryId();

    int getOrderField();

    int getOrderType();

    int getBrandId();

    int getDirectId();

    void getData(GoodBean goodBean);

    void getInitData(GoodBean goodBean);

    void getRegions(Region region);

    void getBrandsByRegion(SearchBrand searchBrand);

    String getGoodName();


}
