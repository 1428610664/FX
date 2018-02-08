package com.weisj.fx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.presenter.BingPresenter;
import com.weisj.fx.utils.PreferencesUtils;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.viewinterface.IBingPhoneView;

public class BingPhoneActivity extends BaseActivity implements View.OnClickListener, IBingPhoneView {
    private EditText phone, staff, userNumber, password;
    private TextView textView;
    private BingPresenter presenter;

    private int time = 120;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    textView.setText("重新发送" + "(" + time + "秒)");
                    if (time == 0) {
                        textView.setClickable(true);
                    } else {
                        time--;
                        if (handler != null)
                            handler.sendEmptyMessageDelayed(1, 1000);
                    }
                    break;
            }
        }
    };


    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_bing_phone, null);
        initView(view);
        presenter = new BingPresenter(this, this);
        return view;
    }

    private void initView(View view) {
        phone = (EditText) view.findViewById(R.id.phone);
        staff = (EditText) view.findViewById(R.id.staff_number);
        userNumber = (EditText) view.findViewById(R.id.user_number);
        password = (EditText) view.findViewById(R.id.password);
        textView = (TextView) view.findViewById(R.id.show_staff);
        view.findViewById(R.id.bingBt).setOnClickListener(this);
        textView.setOnClickListener(this);
    }

    @Override
    public String setTitleStr() {
        return "绑定手机号";
    }

    @Override
    public void getRefreshData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bingBt:
                presenter.verPhone();
                break;
            case R.id.show_staff:
                presenter.getStaff();
                textView.setClickable(false);
                handler.sendEmptyMessage(1);
                break;
        }
    }

    @Override
    public String getPhone() {
        return phone.getText().toString();
    }

    @Override
    public String getStaff() {
        return staff.getText().toString();
    }

    @Override
    public String getUserNumber() {
        return userNumber.getText().toString();
    }

    @Override
    public String getUSerPassWord() {
        return password.getText().toString();
    }

    @Override
    public void getStaffFail(String msg) {
        SystemConfig.showToast(msg);
        handler.removeMessages(1);
        textView.setText("重新发送");
        textView.setClickable(true);
    }

    @Override
    public void getStaffSuccess() {
        SystemConfig.showToast("获取验证码成功");
    }

    @Override
    public void bingPhoneFail(String msg) {
        SystemConfig.showToast(msg);
    }

    @Override
    public void bingPHoneSuccess() {
        SystemConfig.showToast("成功绑定手机号");
        PreferencesUtils.putString("cellphone", getPhone());
        startActivity(new Intent(this, WalletActivity.class));
        finish();
    }
}
