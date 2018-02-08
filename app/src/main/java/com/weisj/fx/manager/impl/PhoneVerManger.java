package com.weisj.fx.manager.impl;

import com.squareup.okhttp.Request;
import com.weisj.fx.bean.BaseBean;
import com.weisj.fx.bean.VerCodeBean;
import com.weisj.fx.manager.IPhoneVerManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/21 0021.
 */
public class PhoneVerManger implements IPhoneVerManager {
    @Override
    public void getPhoneVerCode(String phoneNumber, int type, final IOnManagerListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("member", phoneNumber);
        params.put("type", String.valueOf(type));
        OkHttpClientManager.postAsyn(Urls.getmoblieticket, params, new OkHttpClientManager.ResultCallback<BaseBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e,Urls.getmoblieticket);
            }

            @Override
            public void onResponse(BaseBean response) {
                if (response != null) {
                    listener.onSuccess(response,Urls.getmoblieticket);
                } else {
                    listener.onFail(new RuntimeException("获取数据为空"),Urls.getmoblieticket);
                }
            }
        });
    }

    @Override
    public void verifactionCode(String phoneNumber, int type, String vcode, final IOnManagerListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("member", phoneNumber);
        params.put("type", String.valueOf(type));
        params.put("vcode", vcode);
        OkHttpClientManager.postAsyn(Urls.checkticket, params, new OkHttpClientManager.ResultCallback<VerCodeBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e,Urls.checkticket);
            }

            @Override
            public void onResponse(VerCodeBean response) {
                if (response != null) {
                    listener.onSuccess(response,Urls.checkticket);
                } else {
                    listener.onFail(new RuntimeException("获取数据为空"),Urls.checkticket);
                }
            }
        });
    }

    @Override
    public void bingPhone(String phoneNumber, String password, String staffNumber, final IOnManagerListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("cellphone", phoneNumber);
        try {
            params.put("password", SystemConfig.md5Encode(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        params.put("staff_id", staffNumber);

        OkHttpClientManager.postAsyn(Urls.bindcellphonebypassword, params, new OkHttpClientManager.ResultCallback<BaseBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e,Urls.bindcellphonebypassword);
            }

            @Override
            public void onResponse(BaseBean response) {
                if (response != null) {
                    listener.onSuccess(response,Urls.bindcellphonebypassword);
                } else {
                    listener.onFail(new RuntimeException("获取数据为空"),Urls.bindcellphonebypassword);
                }
            }
        });
    }
}
