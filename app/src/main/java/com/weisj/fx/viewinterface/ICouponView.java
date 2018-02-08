package com.weisj.fx.viewinterface;

import com.weisj.fx.bean.ActiBean;
import com.weisj.fx.bean.CouponBean;
import com.weisj.fx.bean.HomeCouponbean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public interface ICouponView {

    void getInitData(HomeCouponbean homeCouponbean);

    void getData(CouponBean couponList);

    void getInitData(CouponBean couponList);

    void getCouponFail();

    void getActiFail();

    void getActiData(ActiBean actiBean);

}
