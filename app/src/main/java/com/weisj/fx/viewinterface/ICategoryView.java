package com.weisj.fx.viewinterface;

import com.weisj.fx.bean.CategoryBean;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public interface ICategoryView {

    void getTitle(CategoryBean categoryTitleBean);

    void getContent(CategoryBean categoryContentBean);

    void cateFail();

}
