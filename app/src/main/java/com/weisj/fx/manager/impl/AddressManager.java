package com.weisj.fx.manager.impl;


import com.weisj.fx.bean.AdressBean;
import com.weisj.fx.bean.BaseBean;
import com.weisj.fx.manager.IAddressManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.manager.listener.OnResultCallBack;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
public class AddressManager implements IAddressManager {
    @Override
    public void getAddress(IOnManagerListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        if (PersonMessagePreferencesUtils.getUid() == null) return;
        map.put("member", PersonMessagePreferencesUtils.getUid());
        OkHttpClientManager.postAsyn(Urls.getAddress, map, new OnResultCallBack(listener, Urls.getAddress, AdressBean.class));
    }

    @Override
    public void deleteAddress(String addressId, final IOnManagerListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        if (PersonMessagePreferencesUtils.getUid() == null) return;
        map.put("member", PersonMessagePreferencesUtils.getUid());
        map.put("recipient_id", addressId);
        OkHttpClientManager.postAsyn(Urls.delconsignee, map, new OnResultCallBack(listener, Urls.delconsignee + "&recipient_id=" + addressId, BaseBean.class));
    }

    @Override
    public void setDefaultAddress(String addressId, IOnManagerListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        if (PersonMessagePreferencesUtils.getUid() == null) return;
        map.put("member", PersonMessagePreferencesUtils.getUid());
        map.put("recipient_id", addressId);
        OkHttpClientManager.postAsyn(Urls.setdefaultconsignee, map, new OnResultCallBack(listener, Urls.setdefaultconsignee, BaseBean.class));
    }

    @Override
    public void addAddress(String name, String phone, String province, String city, String area, String address, final IOnManagerListener listener) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("member", PersonMessagePreferencesUtils.getUid());
        map.put("name", name);
        map.put("tel", phone);
        map.put("province", province);
        map.put("city", city);
        if (area != null && !area.equals("null")) {
            map.put("area", area);
        }
        map.put("address", address);
        OkHttpClientManager.postAsyn(Urls.addconsignee, map, new OnResultCallBack(listener, Urls.addconsignee, BaseBean.class));
    }
}
