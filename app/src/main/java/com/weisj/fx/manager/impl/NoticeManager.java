package com.weisj.fx.manager.impl;

import com.squareup.okhttp.Request;
import com.weisj.fx.bean.NoticeBean;
import com.weisj.fx.manager.INoticeManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/6 0006.
 */
public class NoticeManager implements INoticeManager {
    @Override
    public void getData(final int page, int pageNum, int cateGory, final IOnManagerListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("category_id", String.valueOf(cateGory));
        params.put("page", String.valueOf(page));
        params.put("page_num", String.valueOf(pageNum));
        OkHttpClientManager.postAsyn(Urls.getarticlelist, params, new OkHttpClientManager.ResultCallback<NoticeBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.getarticlelist + "&page=" + page);
            }

            @Override
            public void onResponse(NoticeBean response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.getarticlelist + "&page=" + page);
                } else {
                    listener.onFail(new RuntimeException("null"), Urls.getarticlelist + "&page=" + page);
                }
            }
        });
    }
}
