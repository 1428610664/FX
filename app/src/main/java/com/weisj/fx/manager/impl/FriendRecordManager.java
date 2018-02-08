package com.weisj.fx.manager.impl;

import com.squareup.okhttp.Request;
import com.weisj.fx.bean.FriendRecordBean;
import com.weisj.fx.manager.IFriendRecordMangager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/5 0005.
 */
public class FriendRecordManager implements IFriendRecordMangager {
    @Override
    public void getData(int goods_id, int coupon_id, final IOnManagerListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("goods_id", String.valueOf(goods_id));
        params.put("coupon_id", String.valueOf(coupon_id));
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        OkHttpClientManager.postAsyn(Urls.getwxinfobythreeid, params, new OkHttpClientManager.ResultCallback<FriendRecordBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.getwxinfobythreeid);
            }

            @Override
            public void onResponse(FriendRecordBean response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.getwxinfobythreeid);
                } else {
                    listener.onFail(new RuntimeException("null"), Urls.getwxinfobythreeid);
                }
            }
        });
    }
}
