package com.weisj.fx.presenter;

import com.weisj.fx.base.BaseViewState;
import com.weisj.fx.bean.BaseBean;
import com.weisj.fx.bean.UserBean;
import com.weisj.fx.manager.ILoginManager;
import com.weisj.fx.manager.IRegisterManager;
import com.weisj.fx.manager.impl.LoginManager;
import com.weisj.fx.manager.impl.RegisterManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.Urls;
import com.weisj.fx.viewinterface.IRegisterThreeView;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
public class RegisterThreePresenter implements IOnManagerListener {
    private BaseViewState viewState;
    private IRegisterManager iRegisterManager;
    private IRegisterThreeView iRegisterThreeView;
    private ILoginManager loginManager;

    public RegisterThreePresenter(IRegisterThreeView iRegisterThreeView, BaseViewState viewState) {
        this.iRegisterThreeView = iRegisterThreeView;
        this.viewState = viewState;
        iRegisterManager = new RegisterManager();
        loginManager = new LoginManager();
    }

    public void register() {
        if (SystemConfig.isPassword(iRegisterThreeView.getPassword()) && iRegisterThreeView.getPassword().equals(iRegisterThreeView.getTwoPassword())) {
            viewState.showLoading();
            iRegisterManager.registerNumber(iRegisterThreeView.getPhoneNumber(), iRegisterThreeView.getPassword(), iRegisterThreeView.getVCode(), iRegisterThreeView.getStaffCode(), iRegisterThreeView.getStaffCity(), this);
        } else {
            SystemConfig.showToast("两次密码不一致");
        }
    }

    public void forgetPass() {
        if (SystemConfig.isPassword(iRegisterThreeView.getPassword()) && iRegisterThreeView.getPassword().equals(iRegisterThreeView.getTwoPassword())) {
            viewState.showLoading();
            iRegisterManager.forgetPass(iRegisterThreeView.getPhoneNumber(), iRegisterThreeView.getPassword(), iRegisterThreeView.getVCode(), this);
        } else {
            SystemConfig.showToast("两次密码不一致");
        }
    }


    @Override
    public void onSuccess(Object data, String url) {
        if (url.equals(Urls.register)) {
            if (SystemConfig.isGetNetSuccess((BaseBean) data)) {
                loginManager.login(iRegisterThreeView.getPhoneNumber(), iRegisterThreeView.getPassword(), this);
            }
        } else {
            viewState.showLoadFinish();
            UserBean userBean = (UserBean) data;
            if (userBean.getCode().equals("1")) {
                PersonMessagePreferencesUtils.storeInfo(userBean);
                iRegisterThreeView.successSetPass();
            } else {
                SystemConfig.showToast(userBean.getMsg());
            }
        }
    }

    @Override
    public void onFail(Exception e, String url) {
        viewState.showLoadFinish();
        SystemConfig.showToast("网络失败");
    }
}
