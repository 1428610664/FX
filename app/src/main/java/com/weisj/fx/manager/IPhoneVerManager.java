package com.weisj.fx.manager;

import com.weisj.fx.manager.listener.IOnManagerListener;

/**
 * Created by Administrator on 2016/7/21 0021.
 */
public interface IPhoneVerManager {
    /**
     * 获取手机验证码
     * @param phoneNumber
     * @param type 类型：1-登录,2-修改密码,3-忘记密码
     */
    void getPhoneVerCode(String phoneNumber,int type,IOnManagerListener listener);

    /**
     * 验证手机验证码
     * @param phoneNumber
     * @param type 类型：1-登录,2-修改密码,3-忘记密码
     * @param vcode
     */
    void verifactionCode(String phoneNumber,int type,String vcode,IOnManagerListener listener);

    /**
     * 绑定手机号
     * @param phoneNumber
     * @param password
     * @param staffNumber
     * @param listener
     */
    void bingPhone(String phoneNumber,String password,String staffNumber,IOnManagerListener listener);
}
