package com.weisj.fx.viewinterface;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2016/8/6 0006.
 */
public interface IBecomeShopView {
    String getCity();

    String getTel();

    String getName();

    String getMainProduct();

    void becomeSuccess();

    List<File> getImages();
}
