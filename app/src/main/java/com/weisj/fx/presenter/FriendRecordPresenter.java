package com.weisj.fx.presenter;


import com.weisj.fx.base.BaseViewState;
import com.weisj.fx.bean.FriendRecordBean;
import com.weisj.fx.manager.IFriendRecordMangager;
import com.weisj.fx.manager.impl.FriendRecordManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.viewinterface.IFriendRecordView;

/**
 * Created by Administrator on 2016/8/5 0005.
 */
public class FriendRecordPresenter implements IOnManagerListener {
    private IFriendRecordView friendRecordView;
    private IFriendRecordMangager mangager;
    private BaseViewState viewState;

    public FriendRecordPresenter(BaseViewState viewState, IFriendRecordView friendRecordView) {
        this.viewState = viewState;
        this.friendRecordView = friendRecordView;
        mangager = new FriendRecordManager();
    }

    public void getData() {
        viewState.showLoading();
        mangager.getData(friendRecordView.getGoodId(), friendRecordView.getCouponId(), this);
    }

    @Override
    public void onSuccess(Object data, String url) {
        FriendRecordBean bean = (FriendRecordBean) data;
        viewState.showLoadFinish();
        if (bean.getCode().equals("1")) {
            friendRecordView.getData(bean);
        } else {
            viewState.showNoData();
        }
    }

    @Override
    public void onFail(Exception e, String url) {
        viewState.showNoNetWork();
    }
}
