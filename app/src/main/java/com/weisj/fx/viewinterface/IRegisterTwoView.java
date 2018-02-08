package com.weisj.fx.viewinterface;

import com.weisj.fx.bean.BaseBean;
import com.weisj.fx.bean.VerCodeBean;

/**
 * Created by Administrator on 2016/7/21 0021.
 */
public interface IRegisterTwoView {
    // 获取验证码
    String getVerCode();

    String getPhoneNumber();

    void successAgainGetCode(BaseBean baseBean);

    void successVerCode(VerCodeBean baseBean);


}
