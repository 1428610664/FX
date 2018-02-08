package com.weisj.fx.presenter;

import android.graphics.Bitmap;
import android.view.View;

import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.umeng.message.UmengRegistrar;
import com.weisj.fx.MainActivity;
import com.weisj.fx.bean.ADBean;
import com.weisj.fx.bean.NavBean;
import com.weisj.fx.main.TabDb;
import com.weisj.fx.manager.IADManager;
import com.weisj.fx.manager.INavManager;
import com.weisj.fx.manager.impl.ADManager;
import com.weisj.fx.manager.impl.NavManager;
import com.weisj.fx.manager.listener.IOnManagerListener;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.Urls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/4 0004.
 */
public class NavPresenter implements IOnManagerListener {
    private INavManager navManager;
    private Map<String, Bitmap> map = new HashMap<>();
    private List<String> imageList = new ArrayList<>();
    private List<String> list = new ArrayList<>();
    private int count = 5;
    private IADManager adManager;
    private NavBean navBean;

    public NavPresenter() {
        this.navManager = new NavManager();
        this.adManager = new ADManager();
    }

    public void getNavData() {
        navManager.getNavData(this);
        try {
            navManager.uploadPhoneSn(UmengRegistrar.getRegistrationId(SystemConfig.getContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        adManager.getAdData(this);
    }

    @Override
    public void onSuccess(Object data, String url) {
        if (url.equals(Urls.getmenu)) {
            getMenu((NavBean) data);
        } else if (url.equals(Urls.beginad)) {
            getAd((ADBean) data);
        }
    }

    private void getAd(ADBean data) {
        if (data.getCode().equals("1") && data.getData() != null && data.getData().get(0).getEnabled() > 0) {
            final ADBean.DataEntity dataEntity = data.getData().get(0);
            ImageLoaderUtils.getInstance().display(dataEntity.getAd_pic(), new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    MainActivity.adBean = dataEntity;
                }
            });
        }
    }

    private void getMenu(NavBean data) {
        navBean = data;
        if (navBean.getCode().equals("1") && navBean.getData() != null && navBean.getData().size() > 0) {
            count = navBean.getData().size();
            for (int j = 0; j < count; j++) {
                NavBean.DataEntity dataEntity = navBean.getData().get(j);
                list.add(dataEntity.getContent());
                if (!imageList.contains(dataEntity.getImage())) {
                    imageList.add(dataEntity.getImage());
                }
                if (!imageList.contains(dataEntity.getImageClick())) {
                    imageList.add(dataEntity.getImageClick());
                }
            }

            for (NavBean.DataEntity dataEntity : navBean.getData()) {
                ImageLoaderUtils.getInstance().display(dataEntity.getImage(), new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        map.put(imageUri, loadedImage);
                        if (map.size() == imageList.size()) {
                            isFinish();
                        }
                    }
                });
                ImageLoaderUtils.getInstance().display(dataEntity.getImageClick(), new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        map.put(imageUri, loadedImage);
                        if (map.size() == imageList.size()) {
                            isFinish();
                        }
                    }
                });
            }
        }
    }

    private void isFinish() {
        for (NavBean.DataEntity dataEntity : navBean.getData()) {
            TabDb.tabImage.add(map.get(dataEntity.getImage()));
            TabDb.selectImage.add(map.get(dataEntity.getImageClick()));
        }
        TabDb.isNet = true;
        String[] str = new String[count];
        for (int i = 0; i < list.size(); i++) {
            str[i] = list.get(i);
        }
        TabDb.setTabsTxt(str);
    }

    @Override
    public void onFail(Exception e, String url) {

    }
}
