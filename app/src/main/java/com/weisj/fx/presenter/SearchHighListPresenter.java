package com.weisj.fx.presenter;

import com.weisj.fx.base.BaseViewState;
import com.weisj.fx.bean.GoodBean;
import com.weisj.fx.bean.Region;
import com.weisj.fx.bean.SearchBrand;
import com.weisj.fx.manager.ISearchHighListGoodManager;
import com.weisj.fx.manager.ISearchListGoodManager;
import com.weisj.fx.manager.impl.SearchHighListGoodManager;
import com.weisj.fx.manager.impl.SearchListGoodManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.Urls;
import com.weisj.fx.viewinterface.ISearchHighListView;
import com.weisj.fx.viewinterface.ISearchListView;

/**
 * Created by Administrator on 2016/7/28 0028.
 */
public class SearchHighListPresenter implements IOnManagerListener {
    private ISearchHighListGoodManager searchListGoodManager;
    private ISearchHighListView iSearchListView;
    private BaseViewState viewState;
    private int page = 1;
    private int pageNum = 10;

    public SearchHighListPresenter(ISearchHighListView iSearchListView, BaseViewState viewState) {
        this.iSearchListView = iSearchListView;
        this.viewState = viewState;
        searchListGoodManager = new SearchHighListGoodManager();
    }

    public void getInitData() {
        viewState.showLoading();
        page = 1;
        searchListGoodManager.getGoods(iSearchListView.getOrderField(), iSearchListView.getOrderType(), page, pageNum, this);
    }

    public void getData() {
        viewState.showLoading();
        page++;
        searchListGoodManager.getGoods(iSearchListView.getOrderField(), iSearchListView.getOrderType(), page, pageNum, this);
    }


    @Override
    public void onSuccess(Object data, String url) {
        viewState.showLoadFinish();
        GoodBean goodBean = (GoodBean) data;
        String page = url.split("&page=")[1];
        if (goodBean.getCode().equals("1")) {
            if (page.equals("1")) {
                iSearchListView.getInitData(goodBean);
            } else {
                iSearchListView.getData(goodBean);
            }
        } else {
            if (page.equals("1")) {
                viewState.showNoData();
            }
        }

    }

    @Override
    public void onFail(Exception e, String url) {
        SystemConfig.showToast("网络错误");
        viewState.showNoNetWork();
    }
}
