package com.weisj.fx.presenter;

import com.weisj.fx.base.BaseViewState;
import com.weisj.fx.bean.BaseBean;
import com.weisj.fx.bean.VerCodeBean;
import com.weisj.fx.manager.IPhoneVerManager;
import com.weisj.fx.manager.impl.PhoneVerManger;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.Urls;
import com.weisj.fx.viewinterface.IBingPhoneView;

/**
 * Created by Administrator on 2016/12/23.
 */

public class BingPresenter implements IOnManagerListener {
    private IBingPhoneView bingPhoneView;
    private IPhoneVerManager phoneVerManager;
    private BaseViewState baseViewState;

    public BingPresenter(IBingPhoneView bingPhoneView, BaseViewState baseViewState) {
        this.bingPhoneView = bingPhoneView;
        this.baseViewState = baseViewState;
        phoneVerManager = new PhoneVerManger();
    }

    public void getStaff() {
        if (bingPhoneView.getPhone() != null && bingPhoneView.getPhone().length() == 11) {
            phoneVerManager.getPhoneVerCode(bingPhoneView.getPhone(), 1, this);
        } else {
            SystemConfig.showToast("请输入11位手机号");
        }
    }

    public void verPhone() {
        if (bingPhoneView.getPhone() != null && bingPhoneView.getPhone().length() == 11) {
            if (bingPhoneView.getUserNumber() != null && !bingPhoneView.getUserNumber().equals("")) {
                if (bingPhoneView.getUSerPassWord() != null && !bingPhoneView.getUSerPassWord().equals("")) {
                    if (bingPhoneView.getStaff() != null && !bingPhoneView.getStaff().equals("")) {
                        baseViewState.showLoading();
                        phoneVerManager.verifactionCode(bingPhoneView.getPhone(), 1, bingPhoneView.getStaff(), this);
                    } else {
                        SystemConfig.showToast("请输入验证码");
                    }
                } else {
                    SystemConfig.showToast("请输入登录密码");
                }
            } else {
                SystemConfig.showToast("请输入帐号");
            }
        } else {
            SystemConfig.showToast("请输入正确的手机号");
        }
    }

    @Override
    public void onSuccess(Object data, String url) {
        if (url.equals(Urls.getmoblieticket)) {
            BaseBean baseBean = (BaseBean) data;
            if (baseBean.getCode().equals("1")){
                bingPhoneView.getStaffSuccess();
            }else{
                bingPhoneView.getStaffFail(baseBean.getMsg());
            }
        } else if (url.equals(Urls.checkticket)) {
            VerCodeBean verCodeBean = (VerCodeBean) data;
            if (verCodeBean.getCode().equals("1")){
                phoneVerManager.bingPhone(bingPhoneView.getPhone(),bingPhoneView.getUSerPassWord(),bingPhoneView.getUserNumber(),this);
            }else{
                SystemConfig.showToast(verCodeBean.getMsg());
                baseViewState.showLoadFinish();
            }
        } else {
            baseViewState.showLoadFinish();
            BaseBean baseBean = (BaseBean) data;
            if (baseBean.getCode().equals("1")){
                bingPhoneView.bingPHoneSuccess();
            }else{
                bingPhoneView.bingPhoneFail(baseBean.getMsg());
            }
        }
    }

    @Override
    public void onFail(Exception e, String url) {
        baseViewState.showLoadFinish();
        SystemConfig.showToast("网络链接错误");
    }
}
