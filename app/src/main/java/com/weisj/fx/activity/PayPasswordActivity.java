package com.weisj.fx.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.weisj.fx.R;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.bean.BaseBean;
import com.weisj.fx.bean.GetMoblieTicket;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.PreferencesUtils;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.Urls;
import com.weisj.fx.view.ClearEditText;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by BBMJ on 2016/1/22.
 */
public class PayPasswordActivity extends BaseActivity implements View.OnClickListener, TextWatcher {


    ClearEditText phone, code, password, comfimpass;
    Button codeBt, finishBt;

    private int time = 120;
    private Timer timer;
    private MytTmerTask timerTask;

    int isForget = 0;


    private void initView(View view) {
        phone = (ClearEditText) view.findViewById(R.id.phone);
        code = (ClearEditText) view.findViewById(R.id.code);
        password = (ClearEditText) view.findViewById(R.id.password);
        comfimpass = (ClearEditText) view.findViewById(R.id.comfin_passw);

        code.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        password.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        comfimpass.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});

        codeBt = (Button) view.findViewById(R.id.codeBt);
        finishBt = (Button) view.findViewById(R.id.finishBt);

        phone.addTextChangedListener(this);
        code.addTextChangedListener(this);
        password.addTextChangedListener(this);
        comfimpass.addTextChangedListener(this);

        codeBt.setOnClickListener(this);
        finishBt.setOnClickListener(this);
        finishBt.setClickable(false);

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (phone.getText().toString().length() > 0 && code.getText().toString().length() > 0
                && password.getText().toString().length() > 0
                && comfimpass.getText().toString().length() > 0) {

            finishBt.setAlpha(1.0f);
            finishBt.setClickable(true);

        } else {
            finishBt.setAlpha(0.5f);
            finishBt.setClickable(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.codeBt:
                if (SystemConfig.isPhone(phone.getText().toString().trim())) {
                    getMobileTiket();
                }
                break;
            case R.id.finishBt:

                if (SystemConfig.isPhone(phone.getText().toString().trim())) {
                    if (password.getText().toString().trim().equals(comfimpass.getText().toString().trim())) {

                        if (isForget > 0) {
                            checkTicket();
                        } else {
                            setPaypassword();
                        }

                    } else {
                        Toast.makeText(PayPasswordActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
        }
    }


    // 设置密码
    private void setPaypassword() {
        try {
            showLoading();
            Map<String, String> map = new HashMap<String, String>();
            map.put("member_id", PersonMessagePreferencesUtils.getUid());
            map.put("mobile_no", phone.getText().toString().trim());
            map.put("vcode", code.getText().toString().trim());
            map.put("login_type", "0");
            map.put("pay_password", SystemConfig.md5Encode(comfimpass.getText().toString().trim()));

            OkHttpClientManager.postAsyn(Urls.setpaypassword, map, new OkHttpClientManager.ResultCallback<BaseBean>() {
                @Override
                public void onError(Request request, Exception e) {
                    showLoadFinish();
                    SystemConfig.showToast("设置失败");
                }

                @Override
                public void onResponse(BaseBean response) {
                    showLoadFinish();
                    SystemConfig.showToast(response.getMsg());
                    if (response.getCode().equals("1")) {
                        PreferencesUtils.putInt("has_set_pay_password", 1);
                        finish();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "网络数据错误", Toast.LENGTH_SHORT).show();
        }
    }


    //1.29忘记支付密码
    private void lostPaypassword(String ticket) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("member", phone.getText().toString().trim());
            map.put("ticket", ticket);
            map.put("change_password", SystemConfig.md5Encode(comfimpass.getText().toString().trim()));

            OkHttpClientManager.postAsyn(Urls.lostpaypassword, map, new OkHttpClientManager.ResultCallback<BaseBean>() {
                @Override
                public void onError(Request request, Exception e) {
                    showLoadFinish();
                    SystemConfig.showToast("设置失败");
                }

                @Override
                public void onResponse(BaseBean response) {
                    showLoadFinish();
                    SystemConfig.showToast(response.getMsg());
                    if (response.getCode().equals("1")) {
                        PreferencesUtils.putInt("has_set_pay_password", 1);
                        finish();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "网络数据错误", Toast.LENGTH_SHORT).show();
        }
    }


    //通过手机验证码后产生的ticket
    private void checkTicket() {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("member", phone.getText().toString().trim());
        map.put("type", "5");
        map.put("vcode", code.getText().toString().trim());
        OkHttpClientManager.postAsyn(Urls.checkTicket, map, new OkHttpClientManager.ResultCallback<GetMoblieTicket>() {
            @Override
            public void onError(Request request, Exception e) {
                showLoadFinish();
            }

            @Override
            public void onResponse(GetMoblieTicket response) {
                if (response.getCode().equals("1")) {
                    lostPaypassword(response.getData().getTicket_code());
                } else {
                    showLoadFinish();
                    SystemConfig.showToast(response.getMsg());
                }
            }
        });

    }


    // 获取验证码
    private void getMobileTiket() {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("member", phone.getText().toString().trim());
        map.put("type", "1");
        OkHttpClientManager.postAsyn(Urls.getmoblieticket, map, new OkHttpClientManager.ResultCallback<GetMoblieTicket>() {
            @Override
            public void onError(Request request, Exception e) {
                showLoadFinish();
            }

            @Override
            public void onResponse(GetMoblieTicket response) {
                showLoadFinish();
                SystemConfig.showToast(response.getMsg());
                if (response.getCode().equals("1")) {
                    clickGetCode();
                }
            }
        });
    }

    // 倒计时数字的显示
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            codeBt.setText("重新发送" + "(" + time + "秒)");
            if (time <= 0) {
                if (timerTask != null) {
                    timerTask.cancel();
                }
                codeBt.setText("获取验证码");
                codeBt.setClickable(true);
            }
        }
    };

    // 获取验证码前的电话号码非空判断和计时器的启动
    private void clickGetCode() {
        time = 120;
        timer = new Timer();
        timerTask = new MytTmerTask();
        timer.schedule(timerTask, 0, 1000);
        codeBt.setClickable(false);
    }

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_pay_password, null);
        isForget = getIntent().getIntExtra("isForget", 0);
        initView(view);
        return view;
    }

    @Override
    public String setTitleStr() {
        return getIntent().getIntExtra("isForget", 0) > 0 ? "忘记支付密码" : "设置支付密码";
    }

    @Override
    public void getRefreshData() {

    }


    // 计时器任务
    class MytTmerTask extends TimerTask {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            time--;
            handler.obtainMessage().sendToTarget();
        }
    }


}
