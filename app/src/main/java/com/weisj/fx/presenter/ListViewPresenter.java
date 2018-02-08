package com.weisj.fx.presenter;

import com.weisj.fx.base.BaseViewState;
import com.weisj.fx.bean.CouponBean;
import com.weisj.fx.bean.OneShareBean;
import com.weisj.fx.bean.ShareActiBean;
import com.weisj.fx.manager.IActivityManager;
import com.weisj.fx.manager.impl.ActivityManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.Urls;
import com.weisj.fx.viewinterface.IListView;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public class ListViewPresenter implements IOnManagerListener {
    private BaseViewState viewState;
    private IActivityManager iActivityManager;
    private IListView iListView;
    private int page = 1;

    public ListViewPresenter(BaseViewState viewState, IListView iListView) {
        this.viewState = viewState;
        this.iListView = iListView;
        iActivityManager = new ActivityManager();
    }

    public void getData() {
        viewState.showLoading();
        if (iListView.getState() == 0) {
            iActivityManager.getAllShareActivity(this);
        } else {
            page = 1;
            iActivityManager.getOneShareActivity(page, iListView.getActivityId(), this);
        }
    }

    public void getMoreData() {
        page++;
        iActivityManager.getOneShareActivity(page, iListView.getActivityId(), this);
    }

    @Override
    public void onSuccess(Object data, String url) {
        if (url.equals(Urls.getAllShareActivitys)) {
            ShareActiBean actiBean = (ShareActiBean) data;
            if (actiBean.getCode().equals("1") && actiBean.getData() != null && actiBean.getData().size() > 0) {
                viewState.showLoadFinish();
                iListView.getJCRecordData(actiBean);
            } else {
                viewState.showNoData();
            }
        } else {
            String page = url.split("&page=")[1];
            OneShareBean oneShareBean = (OneShareBean) data;
            if (oneShareBean.getCode().equals("1")) {
                viewState.showLoadFinish();
                if (page.equals("1")) {
                    iListView.getJCPartData(oneShareBean);
                } else {
                    iListView.getMoreJcPartData(oneShareBean);
                }
            } else {
                if (page.equals("1")) {
                    viewState.showNoData();
                } else {
                    iListView.getMoreFail();
                }
            }
        }
    }

    @Override
    public void onFail(Exception e, String url) {
        viewState.showNoNetWork();
    }
}
