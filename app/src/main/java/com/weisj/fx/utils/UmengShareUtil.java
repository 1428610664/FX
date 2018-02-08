package com.weisj.fx.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.squareup.okhttp.Request;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.weisj.fx.R;
import com.weisj.fx.activity.GoodImageDetailActivity;
import com.weisj.fx.bean.BaseBean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/27 0027.
 */
public class UmengShareUtil implements UMShareListener {

    public void share(Activity activity, SHARE_MEDIA platform, String title, String content, String url, String imageUrl) {
        new ShareAction(activity)
                .setPlatform(platform)
                .setCallback(this)
                .withTitle(title)
                .withTargetUrl(url)
                .withText(content)
                .withMedia(new UMImage(activity, imageUrl))
                .share();
    }

    public void share(Activity activity, SHARE_MEDIA platform, String title, String content, String url, Bitmap imageUrl, int goodId, int couponId) {
        if (platform == SHARE_MEDIA.WEIXIN && (goodId > 0 || couponId > 0)) {
            Map<String, String> params = new HashMap<>();
            params.put("goods_id", String.valueOf(goodId));
            params.put("coupon_id", String.valueOf(couponId));
            params.put("member_id", PersonMessagePreferencesUtils.getUid());
            OkHttpClientManager.postAsyn(Urls.record_share, params, new OkHttpClientManager.ResultCallback<BaseBean>() {
                @Override
                public void onError(Request request, Exception e) {

                }

                @Override
                public void onResponse(BaseBean response) {

                }
            });
        }
        if (platform == SHARE_MEDIA.WEIXIN && goodId == 0 && couponId == 0) {
            if (url.contains("/shop/goods/view/")) {
                String urls = url.split("/shop/goods/view/", 2)[1];
                StringBuffer urlSb = new StringBuffer();
                for (int i = 0; i < urls.length(); i++) {
                    if (urls.charAt(i) == '/' || urls.charAt(i) == '?' || urls.charAt(i) == '&') {
                        break;
                    }
                    urlSb.append(urls.charAt(i));
                }
                Map<String, String> params = new HashMap<>();
                params.put("goods_sn", urlSb.toString());
                params.put("goods_id", String.valueOf(goodId));
                params.put("coupon_id", String.valueOf(couponId));
                params.put("member_id", PersonMessagePreferencesUtils.getUid());
                OkHttpClientManager.postAsyn(Urls.record_share, params, new OkHttpClientManager.ResultCallback<BaseBean>() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(BaseBean response) {

                    }
                });
            }
        }

        if (imageUrl == null) {
            imageUrl = BitmapFactory.decodeResource(activity.getResources(), R.mipmap.icon_share_sf);
        }
        new ShareAction(activity)
                .setPlatform(platform)
                .setCallback(this)
                .withTitle(title)
                .withTargetUrl(url)
                .withText(content)
                .withMedia(new UMImage(activity, imageUrl))
                .share();
    }

    public void share(Activity activity, SHARE_MEDIA platform, Bitmap bitmap, int goodId, int couponId, String content, String url) {
        if (platform == SHARE_MEDIA.WEIXIN && (goodId > 0 || couponId > 0)) {
            Map<String, String> params = new HashMap<>();
            params.put("goods_id", String.valueOf(goodId));
            params.put("coupon_id", String.valueOf(couponId));
            params.put("member_id", PersonMessagePreferencesUtils.getUid());
            OkHttpClientManager.postAsyn(Urls.record_share, params, new OkHttpClientManager.ResultCallback<BaseBean>() {
                @Override
                public void onError(Request request, Exception e) {

                }

                @Override
                public void onResponse(BaseBean response) {

                }
            });
        }
        if (platform == SHARE_MEDIA.WEIXIN && goodId == 0 && couponId == 0) {
            if (url.contains("/shop/goods/view/")) {
                String urls = url.split("/shop/goods/view/", 2)[1];
                StringBuffer urlSb = new StringBuffer();
                for (int i = 0; i < urls.length(); i++) {
                    if (urls.charAt(i) == '/' || urls.charAt(i) == '?' || urls.charAt(i) == '&') {
                        break;
                    }
                    urlSb.append(urls.charAt(i));
                }
                Map<String, String> params = new HashMap<>();
                params.put("goods_sn", urlSb.toString());
                params.put("goods_id", String.valueOf(goodId));
                params.put("coupon_id", String.valueOf(couponId));
                params.put("member_id", PersonMessagePreferencesUtils.getUid());
                OkHttpClientManager.postAsyn(Urls.record_share, params, new OkHttpClientManager.ResultCallback<BaseBean>() {
                    @Override
                    public void onError(Request request, Exception e) {

                    }

                    @Override
                    public void onResponse(BaseBean response) {

                    }
                });
            }
        }

        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(activity.getResources(), R.mipmap.icon_share_sf);
        }
        if (platform == SHARE_MEDIA.SINA) {
            new ShareAction(activity)
                    .setPlatform(platform)
                    .setCallback(this)
                    .withText(content)
                    .withMedia(new UMImage(activity, bitmap))
                    .share();
        } else {
            new ShareAction(activity)
                    .setPlatform(platform)
                    .setCallback(this)
                    .withMedia(new UMImage(activity, bitmap))
                    .share();
        }
    }


    @Override
    public void onResult(SHARE_MEDIA platform) {
        SystemConfig.showToast("分享成功啦");
    }

    @Override
    public void onError(SHARE_MEDIA platform, Throwable t) {
        SystemConfig.showToast("分享失败啦");
    }

    @Override
    public void onCancel(SHARE_MEDIA platform) {
        SystemConfig.showToast("分享取消了");
    }
}
