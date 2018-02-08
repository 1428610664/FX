package com.weisj.fx.bean;

import android.graphics.Bitmap;

import com.weisj.fx.utils.BitmapUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/8/4 0004.
 */
public class ShareData {
    private String title;
    private String content;
    private String url;
    private Bitmap bitmap;
    private int goodId;
    private int couponId;
    private List<String> listUrl;
    private boolean isShareHome;

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }


    public List<String> getListUrl() {
        return listUrl;
    }

    public void setListUrl(List<String> listUrl) {
        this.listUrl = listUrl;
    }

    public ShareData(Bitmap bitmap,List<String> listUrl, String content, String title, String url, int goodId, int couponId) {
        this.content = content;
        this.goodId = goodId;
        this.couponId = couponId;
        this.title = title;
        this.url = url;
        this.listUrl = listUrl;
        this.bitmap = bitmap;
    }

    public ShareData(Bitmap bitmap, String content, String title, String url, int goodId, int couponId) {
        this.content = content;
        this.goodId = goodId;
        this.couponId = couponId;
        this.title = title;
        this.url = url;
        this.bitmap = bitmap;
    }
    public ShareData(boolean isShareHome, String content, String title, String url) {
        this.content = content;
        this.title = title;
        this.url = url;
        this.isShareHome = isShareHome;
    }

    public boolean isShareHome() {
        return isShareHome;
    }

    public void setShareHome(boolean shareHome) {
        isShareHome = shareHome;
    }

    public ShareData() {

    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = BitmapUtil.ImageCrop(bitmap);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
