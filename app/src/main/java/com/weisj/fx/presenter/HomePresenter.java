package com.weisj.fx.presenter;


import com.weisj.fx.base.BaseViewState;
import com.weisj.fx.bean.HomeBanner;
import com.weisj.fx.bean.HomeBean;
import com.weisj.fx.manager.IHomeManager;
import com.weisj.fx.manager.impl.HomeManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.Urls;
import com.weisj.fx.viewinterface.IHomeView;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class HomePresenter implements IOnManagerListener {
    private IHomeManager iHomeManager;
    private IHomeView iHomeView;
    private BaseViewState viewState;


    public HomePresenter(IHomeView iHomeView, BaseViewState viewState) {
        this.iHomeView = iHomeView;
        this.viewState = viewState;
        this.iHomeManager = new HomeManager();
    }

    public void getInitData(String pronvin, boolean b) {
        if (b) {
            viewState.showInitLoading();
        } else {
            viewState.showLoading();
        }
        iHomeManager.getHomeInfo(pronvin, this);
        iHomeManager.getHomeBanner(this);
    }


    @Override
    public void onSuccess(Object data, String url) {
        if (url.equals(Urls.homepage)) {
            HomeBean homeBean = (HomeBean) data;
            if (homeBean.getCode().equals("1")) {
                viewState.showLoadFinish();
                iHomeView.getData(homeBean);
            } else {
                viewState.showNoNetWork();
            }
        } else {
            HomeBanner homeBanner = (HomeBanner) data;
            if ("success".equals(homeBanner.getMessage())) {
                iHomeView.getBannerData(homeBanner);
            } else {
                iHomeView.getBannerFail();
            }
        }
    }

    @Override
    public void onFail(Exception e, String url) {
        if (url.equals(Urls.homepage)) {
            viewState.showNoNetWork();
        } else {
            iHomeView.getBannerFail();
        }
    }
}
