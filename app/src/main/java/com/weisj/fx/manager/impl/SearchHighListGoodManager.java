package com.weisj.fx.manager.impl;

import com.squareup.okhttp.Request;
import com.weisj.fx.bean.GoodBean;
import com.weisj.fx.bean.Region;
import com.weisj.fx.bean.SearchBrand;
import com.weisj.fx.manager.ISearchHighListGoodManager;
import com.weisj.fx.manager.ISearchListGoodManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.CommenString;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class SearchHighListGoodManager implements ISearchHighListGoodManager {
    @Override
    public void getGoods(int order_field, int order_type, final int page, int pageNum, final IOnManagerListener onManagerListener) {
        Map<String, String> params = new HashMap<>();
        params.put("order_field", String.valueOf(order_field));
        if (order_field != 0) {
            params.put("order_type", String.valueOf(order_type));
        }
        params.put("page", String.valueOf(page));
//        params.put("page_num", String.valueOf(pageNum));
        params.put("member_id", PersonMessagePreferencesUtils.getUid());

        OkHttpClientManager.postAsyn(Urls.getHighCommissionGoodsList, params, new OkHttpClientManager.ResultCallback<GoodBean>() {
            @Override
            public void onError(Request request, Exception e) {
                onManagerListener.onFail(e, Urls.getHighCommissionGoodsList + "&page=" + page);
            }

            @Override
            public void onResponse(GoodBean response) {
                if (response != null) {
                    onManagerListener.onSuccess(response, Urls.getHighCommissionGoodsList + "&page=" + page);
                } else {
                    onManagerListener.onFail(new RuntimeException("null"), Urls.getHighCommissionGoodsList + "&page=" + page);
                }
            }
        });
    }
}
