package com.weisj.fx;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.squareup.okhttp.Request;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;
import com.umeng.socialize.UMShareAPI;
import com.weisj.fx.activity.GoodDetailActivity;
import com.weisj.fx.activity.WebViewActivity;
import com.weisj.fx.bean.ADBean;
import com.weisj.fx.bean.VersionInfo;
import com.weisj.fx.main.TabDb;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.PreferencesUtils;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.Urls;
import com.weisj.fx.view.dialog.NewVersionDialog;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends FragmentActivity implements TabHost.OnTabChangeListener, View.OnClickListener {
    private FragmentTabHost tabHost;
    private boolean b; // 是否初始化菜单栏完成
    public static ADBean.DataEntity adBean;
    private long firstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PushAgent mPushAgent = PushAgent.getInstance(this);
        if (PreferencesUtils.getBoolean("is_open_send",true)) {
            mPushAgent.enable();
        }else{
            mPushAgent.disable();
        }
        PushAgent.getInstance(this).onAppStart();
        checkNewVersion();
        b = TabDb.isNet;
        tabHost = (FragmentTabHost) super.findViewById(android.R.id.tabhost);
        tabHost.setup(this, super.getSupportFragmentManager()
                , R.id.contentLayout);
        tabHost.getTabWidget().setDividerDrawable(null);
        tabHost.setOnTabChangedListener(this);
        initTab();
        if (adBean != null) {
            ImageLoaderUtils.getInstance().display((ImageView) findViewById(R.id.main_image), adBean.getAd_pic(), new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    Map<Integer, Integer> map = new HashMap<>();
                    map.put(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                    SystemConfig.dynamicSetRelayoutViewWidthAndHeight(view, 750 * 2 / 3, 750.0 * 2 / 3 * loadedImage.getHeight() / loadedImage.getWidth(), 0, 0, 0, 0, map);
                    displayAd();
                }
            });
        }
    }

    private void checkNewVersion() {
        OkHttpClientManager.postAsyn(Urls.updateVersion, new OkHttpClientManager.ResultCallback<VersionInfo>() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(VersionInfo response) {
                try {
                    if (response != null && response.getCode().equals("1")) {
                        Log.e("VersionInfo", "Service Verssion：" + response.getData().getCode() + "=================Location Version：" + SystemConfig.getVersionCode());
                        if (response.getData().getCode() > SystemConfig.getVersionCode()) {
                            new NewVersionDialog(MainActivity.this, response.getData().getWebsite(), response.getData().getVersion(), response.getData().getDes()).show();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void displayAd() {
        findViewById(R.id.main_ad).setVisibility(View.VISIBLE);
        findViewById(R.id.main_ad).setOnClickListener(this);
        findViewById(R.id.main_image).setOnClickListener(this);
    }

    private void initTab() {
        String tabs[] = TabDb.getTabsTxt();
        for (int i = 0; i < tabs.length; i++) {
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabs[i]).setIndicator(getTabView(i));
            tabHost.addTab(tabSpec, TabDb.getFragments()[i], null);
            tabHost.setTag(i);
        }
    }

    private View getTabView(int idx) {
        View view = LayoutInflater.from(this).inflate(R.layout.footer_tabs, null);
        ((TextView) view.findViewById(R.id.tvTab)).setText(TabDb.getTabsTxt()[idx]);
        if (idx == 0) {
            ((TextView) view.findViewById(R.id.tvTab)).setTextColor(getResources().getColor(R.color.colorHome));
            if (b) {
                ((ImageView) view.findViewById(R.id.ivImg)).setImageBitmap(TabDb.selectImage.get(idx));
            } else {
                ((ImageView) view.findViewById(R.id.ivImg)).setImageResource(TabDb.getTabsImgLight()[idx]);
            }
        } else {
            if (b) {
                ((ImageView) view.findViewById(R.id.ivImg)).setImageBitmap(TabDb.tabImage.get(idx));
            } else {
                ((ImageView) view.findViewById(R.id.ivImg)).setImageResource(TabDb.getTabsImg()[idx]);
            }
        }
        return view;
    }


    @Override
    public void onTabChanged(String tabId) {
        updateTab();
    }

    private void updateTab() {
        TabWidget tabw = tabHost.getTabWidget();
        for (int i = 0; i < tabw.getChildCount(); i++) {
            View view = tabw.getChildAt(i);
            if (i == tabHost.getCurrentTab()) {
                ((TextView) view.findViewById(R.id.tvTab)).setTextColor(getResources().getColor(R.color.colorHome));
                if (b) {
                    ((ImageView) view.findViewById(R.id.ivImg)).setImageBitmap(TabDb.selectImage.get(i));
                } else {
                    ((ImageView) view.findViewById(R.id.ivImg)).setImageResource(TabDb.getTabsImgLight()[i]);
                }
            } else {
                ((TextView) view.findViewById(R.id.tvTab)).setTextColor(getResources().getColor(R.color.color999999));
                if (b) {
                    ((ImageView) view.findViewById(R.id.ivImg)).setImageBitmap(TabDb.tabImage.get(i));
                } else {
                    ((ImageView) view.findViewById(R.id.ivImg)).setImageResource(TabDb.getTabsImg()[i]);
                }
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_ad:
                v.setVisibility(View.GONE);
                adBean = null;
                break;
            case R.id.main_image:
                findViewById(R.id.main_ad).setVisibility(View.GONE);
                if (adBean.getAd_type() > 0) {
                    Intent intent = new Intent(this, GoodDetailActivity.class);
                    intent.putExtra("goodId", Integer.valueOf(adBean.getAd_link()));
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, WebViewActivity.class);
                    intent.putExtra("url", adBean.getAd_link());
                    intent.putExtra("web_title", adBean.getAd_name());
                    startActivity(intent);
                }
                adBean = null;
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            // 判断是否在两秒之内连续点击返回键，是则退出，否则不退出
            if (System.currentTimeMillis() - firstTime > 1000) {
                Toast.makeText(getApplicationContext(), "连按两次才能退出金一哦，亲！",
                        Toast.LENGTH_SHORT).show();
                // 将系统当前的时间赋值给exitTime
                firstTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
