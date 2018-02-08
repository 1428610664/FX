package com.weisj.fx.manager.impl;

import com.squareup.okhttp.Request;
import com.weisj.fx.bean.BaseBean;
import com.weisj.fx.manager.IBecomeShopManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.Urls;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/6 0006.
 */
public class BecomeShopManager implements IBecomeShopManager {
    @Override
    public void becomeShop(String district, String name, String tel, String mainProduce, List<File> images, final IOnManagerListener listener) throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("district", district);
        params.put("company_name", name);
        params.put("company_tel", tel);
        params.put("main_product", mainProduce);
        params.put("member_id", PersonMessagePreferencesUtils.getUid());

        OkHttpClientManager.postAsyn(Urls.apply_company, params, images, "image", new OkHttpClientManager.ResultCallback<BaseBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.apply_company);
            }

            @Override
            public void onResponse(BaseBean response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.apply_company);
                } else {
                    listener.onFail(new RuntimeException("null"), Urls.apply_company);
                }
            }
        });
    }
}
