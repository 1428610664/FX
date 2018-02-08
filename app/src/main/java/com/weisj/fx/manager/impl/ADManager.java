package com.weisj.fx.manager.impl;

import com.squareup.okhttp.Request;
import com.weisj.fx.bean.ADBean;
import com.weisj.fx.manager.IADManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.Urls;

/**
 * Created by Administrator on 2016/8/6 0006.
 */
public class ADManager implements IADManager {
    @Override
    public void getAdData(final IOnManagerListener listener) {
        OkHttpClientManager.postAsyn(Urls.beginad, null, new OkHttpClientManager.ResultCallback<ADBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.beginad);
            }

            @Override
            public void onResponse(ADBean response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.beginad);
                } else {
                    listener.onFail(new RuntimeException("null"), Urls.beginad);
                }
            }
        });
    }
}
