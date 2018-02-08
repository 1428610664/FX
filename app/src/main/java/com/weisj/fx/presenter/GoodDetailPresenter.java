package com.weisj.fx.presenter;

import com.weisj.fx.base.BaseViewState;
import com.weisj.fx.bean.GoodDetail;
import com.weisj.fx.bean.GoodDetailImageBean;
import com.weisj.fx.manager.IGoodDetailmanager;
import com.weisj.fx.manager.impl.GoodDetailManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.Urls;
import com.weisj.fx.viewinterface.IGoodDetailView;

/**
 * Created by Administrator on 2016/8/1 0001.
 */
public class GoodDetailPresenter implements IOnManagerListener {
    private BaseViewState viewState;
    private IGoodDetailmanager iGoodDetailmanager;
    private IGoodDetailView iGoodDetailView;

    public GoodDetailPresenter(IGoodDetailView iGoodDetailView, BaseViewState viewState) {
        this.iGoodDetailView = iGoodDetailView;
        this.viewState = viewState;
        iGoodDetailmanager = new GoodDetailManager();
    }

    public void getData(boolean isInit) {
        if (isInit) {
            viewState.showInitLoading();
        } else {
            viewState.showLoading();
        }
        if (iGoodDetailView.getState() == 0) {
            iGoodDetailmanager.getData(iGoodDetailView.getGoodId(), this);
        } else {
            iGoodDetailmanager.getActiGoodData(iGoodDetailView.getGoodId(), iGoodDetailView.getActivityId(), this);
        }
    }

    public void getImageData() {
        iGoodDetailmanager.getImageData(iGoodDetailView.getGoodId(), this);
    }

    @Override
    public void onSuccess(Object data, String url) {
        if (url.equals(Urls.goodsdetailbyapp)) {
            GoodDetail goodDetail = (GoodDetail) data;
            if (goodDetail.getCode().equals("1")) {
                iGoodDetailView.getData(goodDetail);
                viewState.showLoadFinish();
            } else {
                viewState.showNoData();
            }
        } else if (url.equals(Urls.getOneGuessActivity)) {
            GoodDetail goodDetail = (GoodDetail) data;
            if (goodDetail.getCode().equals("1")) {
                iGoodDetailView.getData(goodDetail);
                viewState.showLoadFinish();
            } else {
                viewState.showNoData();
            }
        } else {
            GoodDetailImageBean goodDetailImageBean = (GoodDetailImageBean) data;
            if (goodDetailImageBean.getCode().equals("1")) {
                iGoodDetailView.getImageData(goodDetailImageBean);
            }
        }
    }

    @Override
    public void onFail(Exception e, String url) {
        if (url.equals(Urls.goodsdetailbyapp) || url.equals(Urls.getOneGuessActivity)) {
            viewState.showNoNetWork();
        }
    }
}
