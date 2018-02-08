package com.weisj.fx.presenter;

import com.weisj.fx.base.BaseViewState;
import com.weisj.fx.bean.BaseBean;
import com.weisj.fx.manager.IBecomeShopManager;
import com.weisj.fx.manager.impl.BecomeShopManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.view.RootView;
import com.weisj.fx.viewinterface.IBecomeShopView;

/**
 * Created by Administrator on 2016/8/6 0006.
 */
public class BecomeShopPresenter implements IOnManagerListener {
    private BaseViewState viewState;
    private IBecomeShopManager manager;
    private IBecomeShopView shopView;


    public BecomeShopPresenter(IBecomeShopView shopView, BaseViewState viewState) {
        this.shopView = shopView;
        this.viewState = viewState;
        this.manager = new BecomeShopManager();
    }

    public void upload() {
        if (shopView.getCity() != null && !shopView.getCity().trim().equals("")) {
            if (shopView.getMainProduct() != null && !shopView.getMainProduct().trim().equals("")) {
                if (shopView.getName() != null && !shopView.getName().trim().equals("")) {
                    if (shopView.getTel() != null && !shopView.getTel().trim().equals("")) {
                        if (shopView.getImages() != null && shopView.getImages().size() > 0) {
                            viewState.showLoading();
                            try {
                                manager.becomeShop(shopView.getCity(), shopView.getName(), shopView.getTel(), shopView.getMainProduct(), shopView.getImages(), this);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            SystemConfig.showToast("请完善数据");
                        }
                    } else {
                        SystemConfig.showToast("请完善数据");
                    }
                } else {
                    SystemConfig.showToast("请完善数据");
                }
            } else {
                SystemConfig.showToast("请完善数据");
            }
        } else {
            SystemConfig.showToast("请完善数据");
        }
    }

    @Override
    public void onSuccess(Object data, String url) {
        viewState.showLoadFinish();
        if (SystemConfig.isGetNetSuccess((BaseBean) data)) {
            shopView.becomeSuccess();
        }
    }

    @Override
    public void onFail(Exception e, String url) {

    }
}
