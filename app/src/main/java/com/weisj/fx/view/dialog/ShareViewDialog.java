package com.weisj.fx.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.weisj.fx.R;
import com.weisj.fx.bean.ShareData;
import com.weisj.fx.main.fragment.HomeFragment;
import com.weisj.fx.utils.CommenString;
import com.weisj.fx.utils.ImageUtil;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.Urls;

/**
 * Created by Administrator on 2016/7/27 0027.
 */
public class ShareViewDialog extends AlertDialog implements View.OnClickListener, ImageUtil.ImageFinishListener {
    private Context context;
    private ShareData data;

    public ShareViewDialog(Context context, ShareData shareData) {
        super(context, R.style.dialog);
        this.context = context;
        this.data = shareData;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_share_view);
        findViewById(R.id.share_cancel).setOnClickListener(this);
        findViewById(R.id.link_share).setOnClickListener(this);
        findViewById(R.id.image_share).setOnClickListener(this);
        findViewById(R.id.image_share_2).setOnClickListener(this);
        if (data.isShareHome()) {
//            findViewById(R.id.image_share_2).setVisibility(View.GONE);
//            findViewById(R.id.linear_bottom).setVisibility(View.GONE);
            ((TextView)findViewById(R.id.image_text)).setText("分享商城主图，买家可长按购买");
            ((TextView)findViewById(R.id.link_text)).setText("分享商城链接，买家可点击进入购买");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_cancel:
                dismiss();
                break;
            case R.id.link_share:
                new LinkShareDialog(context, data).show();
                dismiss();
                break;
            case R.id.image_share:
                new ImageShareDialog(context, data).show();
                dismiss();
                break;
            case R.id.image_share_2:
                ShareData shareData = new ShareData();
                shareData.setTitle("金一-" + HomeFragment.shareCity + "生活馆");
                shareData.setContent("有担当，更爱家");
                shareData.setUrl(String.format("%s/Shop/Index/tuijian_list.html?city=%s&sell_member_id=%s", Urls.IP, CommenString.selectCity, PersonMessagePreferencesUtils.getUid()));
                shareData.setBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_share_sf));
                new LinkShareDialog(context, shareData).show();
//                new ChangImageShareDialog(context, data).show();
                dismiss();
                break;

        }
    }

    @Override
    public void finishImage(String fileAddr) {
        Toast.makeText(context, "完成" + fileAddr, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fail() {
        Toast.makeText(context, "失败", Toast.LENGTH_SHORT).show();
    }
}
