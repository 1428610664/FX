package com.weisj.fx.presenter;

import com.weisj.fx.base.BaseViewState;
import com.weisj.fx.bean.CenterBean;
import com.weisj.fx.manager.ICenterManager;
import com.weisj.fx.manager.impl.CenterManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.viewinterface.ICenterView;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
public class CenterPresenter implements IOnManagerListener {
    private ICenterManager iCenterManager;
    private ICenterView iCenterView;
    private BaseViewState viewState;

    public CenterPresenter(ICenterView iCenterView, BaseViewState viewState) {
        this.iCenterView = iCenterView;
        this.viewState = viewState;
        iCenterManager = new CenterManager();
    }

    public void getMemberCenter() {
        viewState.showLoading();
        iCenterManager.getCenterInfo(this);
    }

    @Override
    public void onSuccess(Object data, String url) {
        viewState.showLoadFinish();
        CenterBean centerBean = (CenterBean) data;
        if ("1".equals(centerBean.getCode())) {
            iCenterView.getCenter(centerBean);
        }
    }

    @Override
    public void onFail(Exception e, String url) {
        viewState.showLoadFinish();
        SystemConfig.showToast("网络错误");
    }
}
