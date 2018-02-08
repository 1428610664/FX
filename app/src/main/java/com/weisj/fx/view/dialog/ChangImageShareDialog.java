package com.weisj.fx.view.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.weisj.fx.R;
import com.weisj.fx.bean.ShareData;
import com.weisj.fx.utils.BitmapUtil;
import com.weisj.fx.utils.ImageUtil;
import com.weisj.fx.utils.KeyboardUtil;
import com.weisj.fx.utils.QRCodeUtil;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.UmengShareUtil;

import java.io.File;

/**
 * Created by Administrator on 2016/7/27 0027.
 */
public class ChangImageShareDialog extends AlertDialog implements View.OnClickListener, ImageUtil.ImageFinishListener {
    private ImageView image;
    private Bitmap bitmap;
    private Context context;
    private ShareData data;
    private ProgressBar progressBar;
    private ScrollView scrollView;

    public ChangImageShareDialog(Context context, ShareData data) {
        super(context, R.style.dialog);
        this.context = context;
        this.data = data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_image_share_2);
        findViewById(R.id.click_wx).setOnClickListener(this);
        findViewById(R.id.click_wechat_py).setOnClickListener(this);
        findViewById(R.id.click_link).setOnClickListener(this);
        findViewById(R.id.click_qq).setOnClickListener(this);
        findViewById(R.id.click_download).setOnClickListener(this);
        findViewById(R.id.click_sina).setOnClickListener(this);
        image = (ImageView) findViewById(R.id.share_image);
//        bitmap = QRCodeUtil.getShareImage(data.getBitmap(), data.getUrl());
//        image.setImageBitmap(bitmap);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        new ImageUtil(this).getShareImage(data.getListUrl(),data.getUrl());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.click_download:
                String filePath = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap,  String.format("%s%d", "share", System.currentTimeMillis()), "");
                if (filePath != null) {
                    SystemConfig.showToast("保存到相册");
                } else {
                    SystemConfig.showToast("保存失败");
                }
                Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri uri = Uri.fromFile(new File(filePath));
                intent.setData(uri);
                context.sendBroadcast(intent);
                break;
            case R.id.click_link:
                KeyboardUtil.copy(data.getUrl(), getContext());
                break;
            case R.id.click_wechat_py:
                MobclickAgent.onEvent(context, "weixinpy");
                new UmengShareUtil().share((Activity) context, SHARE_MEDIA.WEIXIN_CIRCLE, bitmap, data.getGoodId(), data.getCouponId(),data.getTitle(),data.getUrl());
                break;
            case R.id.click_wx:
                MobclickAgent.onEvent(context, "weixin");
                new UmengShareUtil().share((Activity) context, SHARE_MEDIA.WEIXIN, bitmap, data.getGoodId(), data.getCouponId(),data.getTitle(),data.getUrl());
                break;
            case R.id.click_qq:
                MobclickAgent.onEvent(context, "qq");
                new UmengShareUtil().share((Activity) context, SHARE_MEDIA.QQ, bitmap, data.getGoodId(), data.getCouponId(),data.getTitle(),data.getUrl());
                break;
            case R.id.click_sina:
                MobclickAgent.onEvent(context, "sina");
                new UmengShareUtil().share((Activity) context, SHARE_MEDIA.SINA, bitmap, data.getGoodId(), data.getCouponId(),data.getTitle(),data.getUrl());
                break;
        }
        dismiss();
    }

    @Override
    public void finishImage(String fileAddr) {
        bitmap = BitmapUtil.getLoacalBitmap4(fileAddr);
        progressBar.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);
//        SystemConfig.dynamicSetWidthAndHeight(image,500,500/bitmap.getWidth()*bitmap.getHeight());
        image.setImageBitmap(bitmap);
    }

    @Override
    public void fail() {
        SystemConfig.showToast("分享失败,数据不完整");
    }
}
