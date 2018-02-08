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
public class SelectCityDialog extends AlertDialog implements View.OnClickListener {
    private Context context;
    private HomeFragment homeFragment;

    public SelectCityDialog(Context context, HomeFragment homeFragment) {
        super(context, R.style.dialog);
        this.context = context;
        this.homeFragment = homeFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setContentView(R.layout.dialog_select_city);
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) (SystemConfig.getScreenWidth() * 0.72);
        ((TextView) findViewById(R.id.current_city)).setText(CommenString.selectCity);
        findViewById(R.id.other_bt).setOnClickListener(this);
        findViewById(R.id.finishBt).setOnClickListener(this);
        findViewById(R.id.current_city).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.other_bt:
                this.cancel();
                Intent intent = new Intent(this.context, LocationActivity.class);
                homeFragment.startActivityForResult(intent, 10);
                break;
            case R.id.finishBt:
            case R.id.current_city:
                this.cancel();
                PreferencesUtils.putString("select_city", CommenString.city);
                break;
        }
    }

}
