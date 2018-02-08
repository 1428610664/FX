package com.weisj.fx.manager.impl;

import com.squareup.okhttp.Request;
import com.weisj.fx.bean.ActiBean;
import com.weisj.fx.bean.CouponBean;
import com.weisj.fx.bean.HomeCouponbean;
import com.weisj.fx.manager.ICouponManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class CouponManager implements ICouponManager {
    @Override
    public void getHomeCouponData(String pronvin, final IOnManagerListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("city", pronvin);
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        OkHttpClientManager.postAsyn(Urls.couponpage, params, new OkHttpClientManager.ResultCallback<HomeCouponbean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.couponpage);
            }

            @Override
            public void onResponse(HomeCouponbean response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.couponpage);
                } else {
                    listener.onFail(new RuntimeException("null"), Urls.couponpage);
                }
            }
        });
    }

    @Override
    public void getCouponData(int categoryId, final int page, int pageNum, final IOnManagerListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("category_id", String.valueOf(categoryId));
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        params.put("page", String.valueOf(page));
        params.put("page_num", String.valueOf(pageNum));
        OkHttpClientManager.postAsyn(Urls.couponcategory, params, new OkHttpClientManager.ResultCallback<CouponBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.couponcategory + "&page=" + page);
            }

            @Override
            public void onResponse(CouponBean response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.couponcategory + "&page=" + page);
                } else {
                    listener.onFail(new RuntimeException("null"), Urls.couponcategory + "&page=" + page);
                }
            }
        });
    }

    @Override
    public void getActiData(final IOnManagerListener listener) {
        OkHttpClientManager.postAsyn(Urls.getAllGuessActivitys, null, new OkHttpClientManager.ResultCallback<ActiBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.getAllGuessActivitys);
            }

            @Override
            public void onResponse(ActiBean response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.getAllGuessActivitys);
                } else {
                    listener.onFail(new RuntimeException("null"), Urls.getAllGuessActivitys);
                }
            }
        });
    }
}
