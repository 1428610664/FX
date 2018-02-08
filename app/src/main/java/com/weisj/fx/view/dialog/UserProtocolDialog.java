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
import com.weisj.fx.activity.LocationActivity;
import com.weisj.fx.main.fragment.HomeFragment;
import com.weisj.fx.utils.CommenString;
import com.weisj.fx.utils.PreferencesUtils;
import com.weisj.fx.utils.SystemConfig;


/**
 * Created by Administrator on 2015/12/9 0009.
 */
public class UserProtocolDialog extends AlertDialog {
    private Context context;
    private HomeFragment homeFragment;

    public UserProtocolDialog(Context context) {
        super(context, R.style.dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setContentView(R.layout.dialog_user_protocol);
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) (SystemConfig.getScreenWidth() * 0.8);
        lp.height = (int) (SystemConfig.getScreenHeight() * 0.8);
    }


}
