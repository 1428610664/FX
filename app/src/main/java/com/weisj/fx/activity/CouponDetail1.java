package com.weisj.fx.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.weisj.fx.R;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.SystemConfig;

/**
 * Created by Administrator on 2016/8/1 0001.
 */
public class CouponDetail1 extends BaseActivity {
    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_coupon_detail1, null);
        ImageView image = (ImageView) view.findViewById(R.id.image);
        ImageLoaderUtils.getInstance().display(image, "http://img.redocn.com/sheji/20160709/huodongxinxituiguangweimanhuasheji_6657907.jpg", new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                SystemConfig.dynamicSetWidthAndHeight(view, -1, 750.0 * loadedImage.getHeight() / loadedImage.getWidth());
            }
        });
        return view;
    }

    @Override
    public String setTitleStr() {
        return "";
    }

    @Override
    public void getRefreshData() {

    }
}
