package com.weisj.fx.manager.impl;

import com.squareup.okhttp.Request;
import com.weisj.fx.bean.BaseBean;
import com.weisj.fx.bean.NavBean;
import com.weisj.fx.manager.INavManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.Urls;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2016/8/4 0004.
 */
public class NavManager implements INavManager {
    @Override
    public void getNavData(final IOnManagerListener listener) {
        OkHttpClientManager.postAsyn(Urls.getmenu, null, new OkHttpClientManager.ResultCallback<NavBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.getmenu);
            }

            @Override
            public void onResponse(NavBean response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.getmenu);
                } else {
                    listener.onFail(new RuntimeException("null"), Urls.getmenu);
                }
            }
        });
    }

    @Override
    public void uploadPhoneSn(String sn) {
        try {
            Map<String,String> params = new HashMap<>();
            params.put("member_id", PersonMessagePreferencesUtils.getUid());
            params.put("mobile_id", sn);
            OkHttpClientManager.postAsyn(Urls.refreshmobilesn, params, new OkHttpClientManager.ResultCallback<BaseBean>() {
                @Override
                public void onError(Request request, Exception e) {
                }

                @Override
                public void onResponse(BaseBean response) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
