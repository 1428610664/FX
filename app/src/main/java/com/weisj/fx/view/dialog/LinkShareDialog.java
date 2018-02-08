package com.weisj.fx.view.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.weisj.fx.R;
import com.weisj.fx.bean.ShareData;
import com.weisj.fx.utils.KeyboardUtil;
import com.weisj.fx.utils.TextViewUtils;
import com.weisj.fx.utils.UmengShareUtil;

/**
 * Created by Administrator on 2016/7/27 0027.
 */
public class LinkShareDialog extends AlertDialog implements View.OnClickListener {
    private ShareData data;
    private Context context;

    public LinkShareDialog(Context context, ShareData data) {
        super(context, R.style.dialog);
        this.data = data;
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_link_share);
        TextViewUtils.setText((TextView) findViewById(R.id.link_title), data.getTitle());
        TextViewUtils.setText((TextView) findViewById(R.id.link_content), data.getContent());
        if (data.getBitmap() != null) {
            ((ImageView) findViewById(R.id.link_image)).setImageBitmap(data.getBitmap());
        }else{
            ((ImageView) findViewById(R.id.link_image)).setImageResource(R.mipmap.icon_share_sf);
        }
        findViewById(R.id.link_wechat).setOnClickListener(this);
        findViewById(R.id.click_wechat_py).setOnClickListener(this);
        findViewById(R.id.click_qq).setOnClickListener(this);
        findViewById(R.id.click_link).setOnClickListener(this);
        findViewById(R.id.click_sina).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.link_wechat:
                MobclickAgent.onEvent(context, "weixin");
                new UmengShareUtil().share((Activity) context, SHARE_MEDIA.WEIXIN,
                        data.getTitle() != null ? data.getTitle() : "",
                        data.getContent() != null ? data.getContent() : "",
                        data.getUrl(),
                        data.getBitmap(),
                        data.getGoodId(),
                        data.getCouponId()
                );
                break;
            case R.id.click_wechat_py:
                MobclickAgent.onEvent(context, "weixinpy");
                new UmengShareUtil().share((Activity) context, SHARE_MEDIA.WEIXIN_CIRCLE,
                        data.getTitle() != null ? data.getTitle() : "",
                        data.getContent() != null ? data.getContent() : "",
                        data.getUrl(),
                        data.getBitmap()
                        ,
                        data.getGoodId(),
                        data.getCouponId()
                );
                break;
            case R.id.click_qq:
                MobclickAgent.onEvent(context, "qq");
                new UmengShareUtil().share((Activity) context, SHARE_MEDIA.QQ,
                        data.getTitle() != null ? data.getTitle() : "",
                        data.getContent() != null ? data.getContent() : "",
                        data.getUrl(),
                        data.getBitmap(),
                        data.getGoodId(),
                        data.getCouponId()
                );
                break;
            case R.id.click_sina:
                MobclickAgent.onEvent(context, "sina");
                new UmengShareUtil().share((Activity) context, SHARE_MEDIA.SINA,
                        data.getTitle() != null ? data.getTitle() : "",
                        data.getContent() != null ? data.getContent() : "",
                        data.getUrl(),
                        data.getBitmap(),
                        data.getGoodId(),
                        data.getCouponId()
                );
                break;
            case R.id.click_link:
                KeyboardUtil.copy(data.getUrl(), context);
                break;
        }
        dismiss();
    }
}
