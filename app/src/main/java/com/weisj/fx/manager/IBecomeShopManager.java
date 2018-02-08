package com.weisj.fx.manager;

import com.weisj.fx.manager.listener.IOnManagerListener;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2016/8/6 0006.
 */
public interface IBecomeShopManager {

    void becomeShop(String district, String name, String tel, String mainProduce, List<File> images, IOnManagerListener listener) throws Exception;
}
