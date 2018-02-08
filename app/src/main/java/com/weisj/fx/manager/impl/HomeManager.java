package com.weisj.fx.manager.impl;

import com.squareup.okhttp.Request;
import com.weisj.fx.bean.HomeBanner;
import com.weisj.fx.bean.HomeBean;
import com.weisj.fx.manager.IHomeManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class HomeManager implements IHomeManager {

    @Override
    public void getHomeInfo(String pronvin, final IOnManagerListener onHomeListener) {
        Map<String, String> params = new HashMap<>();
        params.put("city", pronvin);
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        OkHttpClientManager.postAsyn(Urls.homepage, params, new OkHttpClientManager.ResultCallback<HomeBean>() {
            @Override
            public void onError(Request request, Exception e) {
                onHomeListener.onFail(e, Urls.homepage);
            }

            @Override
            public void onResponse(HomeBean response) {
                if (response != null) {
                    onHomeListener.onSuccess(response, Urls.homepage);
                } else {
                    onHomeListener.onFail(new RuntimeException("null"), Urls.homepage);
                }
            }
        });
    }

    @Override
    public void getHomeBanner(final IOnManagerListener onHomeListener) {
        OkHttpClientManager.getAsyn(Urls.material, new OkHttpClientManager.ResultCallback<HomeBanner>() {
            @Override
            public void onError(Request request, Exception e) {
                onHomeListener.onFail(e, Urls.material);
            }

            @Override
            public void onResponse(HomeBanner response) {
                if (response != null) {
                    onHomeListener.onSuccess(response, Urls.material);
                } else {
                    onHomeListener.onFail(new RuntimeException("null"), Urls.material);
                }
            }
        });
    }


}
