package com.weisj.fx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.okhttp.Request;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.IUmengUnregisterCallback;
import com.umeng.message.PushAgent;
import com.weisj.fx.R;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.bean.VersionInfo;
import com.weisj.fx.presenter.CenterPresenter;
import com.weisj.fx.utils.FileUtil;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.PreferencesUtils;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.Urls;
import com.weisj.fx.view.SwitchView;
import com.weisj.fx.view.dialog.NewVersionDialog;


/**
 * Created by Administrator on 2015/12/22 0022.
 */
public class SetUpActivity extends BaseActivity implements View.OnClickListener, SwitchView.OnStateChangedListener {
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    showLoadFinish();
                    String size = FileUtil.getFileSize(ImageLoader.getInstance().getDiskCache().getDirectory());
                    ImageLoader.getInstance().clearDiskCache();
                    if (size != null) {
                        Toast.makeText(SystemConfig.getContext(), "清除缓存" + size, Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    };
    private SwitchView switchView;
    private PushAgent mPushAgent;

    private PushAgent getmPushAgent() {
        if (mPushAgent == null) {
            mPushAgent = PushAgent.getInstance(this);
        }
        return mPushAgent;
    }


    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_setup, null);
        initView(view);
        return view;
    }

    @Override
    public String setTitleStr() {
        return "设置";
    }

    @Override
    public void getRefreshData() {

    }

    private void initView(View view) {
        view.findViewById(R.id.login_button).setOnClickListener(this);
        view.findViewById(R.id.set_about).setOnClickListener(this);
        view.findViewById(R.id.clear_cache).setOnClickListener(this);
        view.findViewById(R.id.change_password).setOnClickListener(this);
        view.findViewById(R.id.new_version).setOnClickListener(this);
        ((TextView) view.findViewById(R.id.app_name)).setText("金一" + SystemConfig.getVersion());
        switchView = (SwitchView) view.findViewById(R.id.switch_bt);
        switchView.setOpened(PreferencesUtils.getBoolean("is_open_send", true));
        switchView.setOnStateChangedListener(this);
        switchView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                PersonMessagePreferencesUtils.deleteInfo();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            case R.id.set_about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.clear_cache:
                showLoading();
                handler.sendEmptyMessageDelayed(1, 2000);
                break;
            case R.id.back:
                finish();
                break;
            case R.id.new_version:
                checkNewVersion();
                break;

            case R.id.change_password:
                intent = new Intent(this, ModifyPayPasswordActivity.class);
                intent.putExtra("isCForgetPasswor", 1);
                startActivity(intent);
                break;

            case R.id.switch_bt:
                switchView.setOpened(!switchView.isOpened());
                break;
        }
    }

    private void checkNewVersion() {
        showLoading();
        OkHttpClientManager.postAsyn(Urls.updateVersion, new OkHttpClientManager.ResultCallback<VersionInfo>() {
            @Override
            public void onError(Request request, Exception e) {
                showLoadFinish();
            }

            @Override
            public void onResponse(VersionInfo response) {
                showLoadFinish();
                try {
                    if (response != null && response.getCode().equals("1")) {
                        if (response.getData().getCode() > SystemConfig.getVersionCode()) {
                            new NewVersionDialog(SetUpActivity.this, response.getData().getWebsite(), response.getData().getVersion(), response.getData().getDes()).show();
                        } else {
                            Toast.makeText(SetUpActivity.this, "最新版本", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SetUpActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void toggleToOn(SwitchView view) {
        PreferencesUtils.putBoolean("is_open_send", true);
        getmPushAgent().enable();
    }

    @Override
    public void toggleToOff(SwitchView view) {
        PreferencesUtils.putBoolean("is_open_send", false);
        getmPushAgent().disable();
    }
}
