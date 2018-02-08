package com.weisj.fx.presenter;

import com.weisj.fx.base.BaseViewState;
import com.weisj.fx.bean.UserBean;
import com.weisj.fx.manager.ILoginManager;
import com.weisj.fx.manager.impl.LoginManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.viewinterface.ILoginView;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class LoginPresenter implements IOnManagerListener {
    private ILoginView loginView;
    private ILoginManager loginManager;
    private BaseViewState viewState;

    public LoginPresenter(ILoginView loginView, BaseViewState viewState) {
        this.loginView = loginView;
        this.loginManager = new LoginManager();
        this.viewState = viewState;
    }

    public void login() {
        if (loginView.getUserName() != null) {
            if (  SystemConfig.isPassword(loginView.getUserPassWord())) {
                viewState.showLoading();
                loginManager.login(loginView.getUserName(), loginView.getUserPassWord(), this);
            }
        } else {
            SystemConfig.showToast("请输入正确手机号");
        }
    }


    @Override
    public void onSuccess(Object data, String url) {
        viewState.showLoadFinish();
        UserBean userBean = (UserBean) data;
        if (userBean.getCode().equals("1")) {
            PersonMessagePreferencesUtils.storeInfo(userBean);
            loginView.successLogin();
        } else {
            SystemConfig.showToast(userBean.getMsg());
        }
    }

    @Override
    public void onFail(Exception e, String url) {
        viewState.showLoadFinish();
        SystemConfig.showToast("网络错误");
    }
}
