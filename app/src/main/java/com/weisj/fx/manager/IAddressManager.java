package com.weisj.fx.manager;

import com.weisj.fx.manager.listener.IOnManagerListener;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
public interface IAddressManager {
    void getAddress(IOnManagerListener listener);

    void deleteAddress(String addressId, IOnManagerListener listener);

    void setDefaultAddress(String addressId, IOnManagerListener listener);

    void addAddress(String name,String phone,String province,String city,String area,String address,IOnManagerListener listener);
}
