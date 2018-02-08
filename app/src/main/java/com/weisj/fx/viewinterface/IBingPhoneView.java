package com.weisj.fx.viewinterface;

/**
 * Created by Administrator on 2016/12/22.
 */

public interface IBingPhoneView {

    String getPhone();

    String getStaff();

    String getUserNumber();

    String getUSerPassWord();

    void getStaffFail(String msg);

    void getStaffSuccess();

    void bingPhoneFail(String msg);

    void bingPHoneSuccess();

}
