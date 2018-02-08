package com.weisj.fx.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.weisj.fx.MainActivity;
import com.weisj.fx.R;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.presenter.LoginPresenter;
import com.weisj.fx.utils.KeyboardUtil;
import com.weisj.fx.viewinterface.ILoginView;

/**
 * Created by Administrator on 2016/6/29 0029.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, ILoginView {
    private EditText phoneEdit, passwordEdit;
    private LoginPresenter presenter;
    private long firstTime;
    public static final String BROADCAST_ACTION = "com.zzc.fx";
    private BroadcastReceiver mBroadcastReceiver;

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_login, null);
        rootView.isHintHeadBar(true);
        initView(view);
        return view;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBroadcastReceiver != null) {
            unregisterReceiver(mBroadcastReceiver);
        }
    }

    private class MyBroadcastReceiver extends BroadcastReceiver {
        //Intent intent = new Intent();
//        intent.setAction(MainActivity.BROADCAST_ACTION);
//        intent.putExtra(StringUtil.BUYNUMBER, StringUtil.BUYNUMBER);
//        context.sendBroadcast(intent);
        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    }

    private void initView(View view) {
        view.findViewById(R.id.registerBt).setOnClickListener(this);
        view.findViewById(R.id.loginBt).setOnClickListener(this);
        view.findViewById(R.id.delect).setOnClickListener(this);
        view.findViewById(R.id.forgetPass).setOnClickListener(this);
        phoneEdit = (EditText) view.findViewById(R.id.phone);
        passwordEdit = (EditText) view.findViewById(R.id.password);
        passwordEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
        mBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_ACTION);
        registerReceiver(mBroadcastReceiver, intentFilter);
    }

    @Override
    public String setTitleStr() {
        return "";
    }

    @Override
    public void getRefreshData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerBt:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.loginBt:
                if (presenter == null) {
                    presenter = new LoginPresenter(this, this);
                }
                presenter.login();
                KeyboardUtil.closeKeyBoard(this);
                break;
            case R.id.delect:
                finish();
                break;
            case R.id.forgetPass:
                Intent intent = new Intent(this, RegisterActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1 && resultCode == RESULT_OK) {
//            if (data != null) {
//                phoneEdit.setText(data.getStringExtra("phoneNumber"));
//                passwordEdit.setText(data.getStringExtra("password"));
//            }
//        }
    }

    @Override
    public String getUserName() {
        return phoneEdit.getText().toString();
    }

    @Override
    public String getUserPassWord() {
        return passwordEdit.getText().toString();
    }

    @Override
    public void successLogin() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
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
}
