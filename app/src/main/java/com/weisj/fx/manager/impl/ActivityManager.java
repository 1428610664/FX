package com.weisj.fx.manager.impl;

import com.squareup.okhttp.Request;
import com.weisj.fx.bean.ActiBean;
import com.weisj.fx.bean.OneShareBean;
import com.weisj.fx.bean.ShareActiBean;
import com.weisj.fx.manager.IActivityManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public class ActivityManager implements IActivityManager {
    @Override
    public void getAllShareActivity(final IOnManagerListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        OkHttpClientManager.postAsyn(Urls.getAllShareActivitys, params, new OkHttpClientManager.ResultCallback<ShareActiBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.getAllShareActivitys);
            }

            @Override
            public void onResponse(ShareActiBean response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.getAllShareActivitys);
                } else {
                    listener.onFail(new RuntimeException("null"), Urls.getAllShareActivitys);
                }
            }
        });
    }

    @Override
    public void getOneShareActivity(final int page, int activityId, final IOnManagerListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        params.put("activity_id", String.valueOf(activityId));
        params.put("page", String.valueOf(page));
        params.put("page_num", "10");
        OkHttpClientManager.postAsyn(Urls.getOneShareActivity, params, new OkHttpClientManager.ResultCallback<OneShareBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.getOneShareActivity);
            }

            @Override
            public void onResponse(OneShareBean response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.getOneShareActivity+"&page="+page);
                } else {
                    listener.onFail(new RuntimeException("null"), Urls.getOneShareActivity+"&page="+page);
                }
            }
        });
    }
}
