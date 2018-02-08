package com.weisj.fx.manager.impl;

import com.squareup.okhttp.Request;
import com.weisj.fx.bean.CategoryBean;
import com.weisj.fx.manager.ICategoryManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class CategoryManager implements ICategoryManager {

    @Override
    public void getCateContent(final int id, final IOnManagerListener onManagerListener) {
        Map<String, String> params = new HashMap<>();
        params.put("parent_id", String.valueOf(id));
        OkHttpClientManager.postAsyn(Urls.getcategorybychildfilter, params, new OkHttpClientManager.ResultCallback<CategoryBean>() {
            @Override
            public void onError(Request request, Exception e) {
                onManagerListener.onFail(e, Urls.getcategorybychildfilter);
            }

            @Override
            public void onResponse(CategoryBean response) {
                if (response != null) {
                    onManagerListener.onSuccess(response, Urls.getcategorybychildfilter);
                } else {
                    onManagerListener.onFail(new RuntimeException("null"), Urls.getcategorybychildfilter);
                }
            }
        });
    }

    @Override
    public void getCateTitle(final IOnManagerListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("parent_id", "0");
        OkHttpClientManager.postAsyn(Urls.getcategorybytopfilter, params, new OkHttpClientManager.ResultCallback<CategoryBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.getcategorybytopfilter);
            }

            @Override
            public void onResponse(CategoryBean response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.getcategorybytopfilter);
                } else {
                    listener.onFail(new RuntimeException("null"), Urls.getcategorybytopfilter);
                }
            }
        });
    }
}
