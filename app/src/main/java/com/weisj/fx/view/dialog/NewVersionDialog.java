package com.weisj.fx.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.weisj.fx.R;
import com.weisj.fx.utils.SystemConfig;


/**
 * Created by Administrator on 2015/12/9 0009.
 */
public class NewVersionDialog extends AlertDialog implements View.OnClickListener {
    public String APK_URL;
    private String title;
    private String des;


    private Context context;

    public NewVersionDialog(Context context, String url, String title, String des) {
        super(context, R.style.dialog);
        this.context = context;
        this.title = title;
        this.des = des;
        APK_URL = url;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setContentView(R.layout.dialog_new_versions);
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) (SystemConfig.getScreenWidth() * 0.72);
        findViewById(R.id.version_cancel).setOnClickListener(this);
        findViewById(R.id.version_confirm).setOnClickListener(this);
        ((TextView) findViewById(R.id.verson_title)).setText("新版本" + title);
        ((TextView) findViewById(R.id.verson_des)).setText(des);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.version_cancel:
                this.cancel();
                break;
            case R.id.version_confirm:
                downLoad();
                this.cancel();
                break;
        }
    }

    private void downLoad() {
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra("downLoadUrl", APK_URL);
        context.startService(intent);
    }

}
