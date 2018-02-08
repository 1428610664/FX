package com.weisj.fx.manager.impl;

import com.squareup.okhttp.Request;
import com.weisj.fx.bean.BaseBean;
import com.weisj.fx.manager.IRegisterManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/21 0021.
 */
public class RegisterManager implements IRegisterManager {
    @Override
    public void registerNumber(String phone, String password, String vCode, String staffCode, String staffCity, final IOnManagerListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        try {
            params.put("password", SystemConfig.md5Encode(password));
        } catch (Exception e) {
            e.printStackTrace();
            listener.onFail(new RuntimeException("获取数据为空"), Urls.register);
            return;
        }
        params.put("ticket", vCode);
        params.put("district_id", staffCity);
        params.put("staff_id", staffCode);
        OkHttpClientManager.postAsyn(Urls.register, params, new OkHttpClientManager.ResultCallback<BaseBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.register);
            }

            @Override
            public void onResponse(BaseBean response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.register);
                } else {
                    listener.onFail(new RuntimeException("获取数据为空"), Urls.register);
                }
            }
        });
    }

    @Override
    public void forgetPass(String phone, String password, String vCode, final IOnManagerListener listener) {
        Map<String, String> params = new HashMap<>();
        params.put("member", phone);
        try {
            params.put("change_password", SystemConfig.md5Encode(password));
        } catch (Exception e) {
            e.printStackTrace();
            listener.onFail(new RuntimeException("获取数据为空"), Urls.register);
            return;
        }
        params.put("ticket", vCode);
        OkHttpClientManager.postAsyn(Urls.lostpassword, params, new OkHttpClientManager.ResultCallback<BaseBean>() {
            @Override
            public void onError(Request request, Exception e) {
                listener.onFail(e, Urls.register);
            }

            @Override
            public void onResponse(BaseBean response) {
                if (response != null) {
                    listener.onSuccess(response, Urls.register);
                } else {
                    listener.onFail(new RuntimeException("获取数据为空"), Urls.register);
                }
            }
        });
    }
}
