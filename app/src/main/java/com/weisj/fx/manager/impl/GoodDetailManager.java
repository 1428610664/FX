package com.weisj.fx.manager.impl;

import com.squareup.okhttp.Request;
import com.weisj.fx.bean.GoodDetail;
import com.weisj.fx.bean.GoodDetailImageBean;
import com.weisj.fx.bean.GoodPoint;
import com.weisj.fx.manager.IGoodDetailmanager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/1 0001.
 */
public class GoodDetailManager implements IGoodDetailmanager {
    @Override
    public void getData(int good_id, final IOnManagerListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("goods_id", String.valueOf(good_id));
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        OkHttpClientManager.postAsyn(Urls.goodsdetailbyapp, params, new OkHttpClientManager.ResultCallback<GoodDetail>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.goodsdetailbyapp);
            }

            @Override
            public void onResponse(GoodDetail response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.goodsdetailbyapp);
                } else {
                    listener.onFail(new RuntimeException("null"), Urls.goodsdetailbyapp);
                }
            }
        });
    }

    @Override
    public void getImageData(int good_id, final IOnManagerListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("goods_id", String.valueOf(good_id));
        OkHttpClientManager.postAsyn(Urls.goodscontent, params, new OkHttpClientManager.ResultCallback<GoodDetailImageBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.goodscontent);
            }

            @Override
            public void onResponse(GoodDetailImageBean response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.goodscontent);
                } else {
                    listener.onFail(new RuntimeException("null"), Urls.goodscontent);
                }
            }
        });
    }

    @Override
    public void getGoodPointData(int good_id, final int page, final IOnManagerListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("goods_id", String.valueOf(good_id));
        params.put("page", String.valueOf(page));
        params.put("page_num", "10");
        OkHttpClientManager.postAsyn(Urls.goodscomment, params, new OkHttpClientManager.ResultCallback<GoodDetailImageBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.goodscomment + "=page=" + page);
            }

            @Override
            public void onResponse(GoodDetailImageBean response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.goodscomment + "=page=" + page);
                } else {
                    listener.onFail(new RuntimeException("null"), Urls.goodscomment + "=page=" + page);
                }
            }
        });
    }

    @Override
    public void getActiGoodData(int good_id, int activity_id, final IOnManagerListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("goods_id", String.valueOf(good_id));
        params.put("activity_id", String.valueOf(activity_id));
        params.put("member_id", PersonMessagePreferencesUtils.getUid() != null ? PersonMessagePreferencesUtils.getUid() : "0");
        OkHttpClientManager.postAsyn(Urls.getOneGuessActivity, params, new OkHttpClientManager.ResultCallback<GoodDetail>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.getOneGuessActivity);
            }

            @Override
            public void onResponse(GoodDetail response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.getOneGuessActivity);
                } else {
                    listener.onFail(new RuntimeException("null"), Urls.getOneGuessActivity);
                }
            }
        });
    }
}
