package com.weisj.fx.activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.weisj.fx.R;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.bean.ShareData;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.view.dialog.ShareViewDialog;


public class WebViewActivity extends BaseActivity {
    private WebView webView;
    private String imageUrl;
    private String url;
    private String title;
    private String content;
    private int id;

    class myWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.contains("goods_id")) {
                String goodId = url.split("goods_id=", 2)[1];
                if (goodId.contains("&")) {
                    goodId = goodId.split("&", 2)[0];
                }
                Intent intent = new Intent(WebViewActivity.this, GoodDetailActivity.class);
                intent.putExtra("goodId", Integer.valueOf(goodId));
                WebViewActivity.this.startActivity(intent);
            } else if (url.contains("/shop/goods/view/") && !url.contains("fx=1")) {
                if (url.contains("?")) {
                    url = url + "&fx=1";
                } else {
                    url = url + "?fx=1";
                }
                webView.loadUrl(url);
            } else {
                return false;
            }
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }


    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_web, null);
        initView(view);
        load();
        return view;
    }

    @Override
    public String setTitleStr() {
        title = getIntent().getStringExtra("web_title");
        content = getIntent().getStringExtra("content");
        if (content == null) {
            content = title;
        }
        return title;
    }

    private void initView(View view) {
        imageUrl = getIntent().getStringExtra("imageUrl");
        if (imageUrl != null) {
            rootView.setRightText("分享", true);
            id = getIntent().getIntExtra("coupon_id", 0);
        }
        webView = (WebView) view.findViewById(R.id.webView);
    }

    @Override
    public void onHeadClick(View v) {
        if (v.getId() == R.id.root_head_right_text) {
            ImageLoaderUtils.getInstance().display(imageUrl, new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    if (!url.contains("sell_member_id")) {
                        if (url.contains("?")) {
                            url = url + "&sell_member_id=" + PersonMessagePreferencesUtils.getUid();
                        } else {
                            url = url + "?sell_member_id=" + PersonMessagePreferencesUtils.getUid();
                        }
                    }
                    ShareData shareData = new ShareData(loadedImage, content, title, url, 0, id);
                    new ShareViewDialog(WebViewActivity.this, shareData).show();
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    if (!url.contains("sell_member_id")) {
                        if (url.contains("?")) {
                            url = url + "&sell_member_id=" + PersonMessagePreferencesUtils.getUid();
                        } else {
                            url = url + "?sell_member_id=" + PersonMessagePreferencesUtils.getUid();
                        }
                    }
                    ShareData shareData = new ShareData(BitmapFactory.decodeResource(WebViewActivity.this.getResources(), R.mipmap.icon_share_sf), content, title, url, 0, id);
                    new ShareViewDialog(WebViewActivity.this, shareData).show();
                }
            });
        }
    }

    @Override
    public void getRefreshData() {

    }

    class myWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            // TODO Auto-generated method stub
            if (newProgress == 100) {
                showLoadFinish();
            } else {
                showLoading();
            }
            super.onProgressChanged(view, newProgress);
        }


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        webView.stopLoading();
    }


    private void load() {
        // TODO Auto-generated method stub
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new myWebViewClient());
        webView.setWebChromeClient(new myWebChromeClient());

        // 页面适应屏幕
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setJavaScriptEnabled(true);
        String oldUrl = getIntent().getStringExtra("url");
        if (oldUrl != null) {
            if (!oldUrl.contains("http://") && !oldUrl.contains("https://")) {
                oldUrl = "http://" + oldUrl;
            }
            url = oldUrl;
            if (oldUrl.contains("/shop/goods/view/") && !oldUrl.contains("fx=1")) {
                if (oldUrl.contains("?")) {
                    oldUrl = oldUrl + "&fx=1";
                } else {
                    oldUrl = oldUrl + "?fx=1";
                }
            }
            webView.loadUrl(oldUrl);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


}
