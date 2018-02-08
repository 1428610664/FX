package com.weisj.fx.activity;

import android.os.Bundle;
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
import com.weisj.fx.utils.KeyboardUtil;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.PreferencesUtils;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.Urls;
import com.weisj.fx.view.ClearEditText;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BBMJ on 2016/1/25.
 */
public class ModifyPayPasswordActivity extends BaseActivity implements View.OnClickListener, TextWatcher {
    ClearEditText phone, old_pass, password, comfimpass;
    Button finishBt;

    private int isC = 0;

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_modifypaypassword, null);
        isC = getIntent().getIntExtra("isCForgetPasswor", 0);
        initView(view);
        return view;
    }

    @Override
    public String setTitleStr() {
        return getIntent().getIntExtra("isCForgetPasswor", 0) == 0 ? "修改支付密码" : "修改密码";
    }

    @Override
    public void getRefreshData() {

    }

    private void initView(View view) {
        phone = (ClearEditText) view.findViewById(R.id.phone);
        finishBt = (Button) view.findViewById(R.id.finishBt);
        if (isC == 0) {
            password = (ClearEditText) view.findViewById(R.id.password);
            comfimpass = (ClearEditText) view.findViewById(R.id.comfin_passw);
            view.findViewById(R.id.password2).setVisibility(View.GONE);
            old_pass = (ClearEditText) view.findViewById(R.id.orl_pasword);
            view.findViewById(R.id.comfin_passw2).setVisibility(View.GONE);
            view.findViewById(R.id.orl_pasword2).setVisibility(View.GONE);
            old_pass.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
            password.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
            comfimpass.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        } else {
            view.findViewById(R.id.password).setVisibility(View.GONE);
            view.findViewById(R.id.comfin_passw).setVisibility(View.GONE);
            view.findViewById(R.id.orl_pasword).setVisibility(View.GONE);
            password = (ClearEditText) view.findViewById(R.id.password2);
            old_pass = (ClearEditText) view.findViewById(R.id.orl_pasword2);
            comfimpass = (ClearEditText) view.findViewById(R.id.comfin_passw2);
            comfimpass.setHint("确认重置密码");
            password.setHint("重置密码");
        }
        old_pass.addTextChangedListener(this);
        password.addTextChangedListener(this);
        comfimpass.addTextChangedListener(this);
        finishBt.setOnClickListener(this);
        finishBt.setClickable(false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.finishBt:
                if (SystemConfig.isPhone(phone.getText().toString())) {
                    if (password.getText().toString().equals(comfimpass.getText().toString())) {
                        if (isC == 0) {
                            changePaypassword();
                        } else {
                            changePassword();
                        }
                        KeyboardUtil.closeKeyBoard(this);
                    } else {
                        Toast.makeText(ModifyPayPasswordActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (old_pass.getText().toString().length() > 0 && phone.getText().toString().length() > 0
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


    // 设置支付密码
    private void changePaypassword() {
        try {
            showLoading();
            Map<String, String> map = new HashMap<String, String>();
            map.put("member", phone.getText().toString().trim());
            map.put("password", SystemConfig.md5Encode(old_pass.getText().toString().trim()));
            map.put("change_password", SystemConfig.md5Encode(comfimpass.getText().toString().trim()));
            OkHttpClientManager.postAsyn(Urls.changepaypassword, map, new OkHttpClientManager.ResultCallback<BaseBean>() {
                @Override
                public void onError(Request request, Exception e) {
                    showLoadFinish();
                    SystemConfig.showToast("设置失败");
                }

                @Override
                public void onResponse(BaseBean response) {
                    showLoadFinish();
                    if (response.getCode().equals("1")) {
                        PreferencesUtils.putInt("has_set_pay_password", 1);
                        SystemConfig.showToast(response.getMsg());
                        finish();
                    } else {
                        changePaypassword2();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "网络数据错误", Toast.LENGTH_SHORT).show();
        }
    }

    // 设置支付密码
    private void changePaypassword2() {
        try {
            showLoading();
            Map<String, String> map = new HashMap<String, String>();
            map.put("member", phone.getText().toString().trim());
            map.put("password", old_pass.getText().toString().trim());
            map.put("change_password", SystemConfig.md5Encode(comfimpass.getText().toString().trim()));
            OkHttpClientManager.postAsyn(Urls.changepaypassword, map, new OkHttpClientManager.ResultCallback<BaseBean>() {
                @Override
                public void onError(Request request, Exception e) {
                    showLoadFinish();
                    SystemConfig.showToast("设置失败");
                }

                @Override
                public void onResponse(BaseBean response) {
                    showLoadFinish();
                    if (response.getCode().equals("1")) {
                        PreferencesUtils.putInt("has_set_pay_password", 1);
                        SystemConfig.showToast(response.getMsg());
                        finish();
                    } else {
                        SystemConfig.showToast(response.getMsg());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "网络数据错误", Toast.LENGTH_SHORT).show();
        }
    }



    // 设置用户密码
    private void changePassword() {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        try {
            map.put("member", phone.getText().toString().trim());
            map.put("password", SystemConfig.md5Encode(old_pass.getText().toString().trim()));
            map.put("change_password", SystemConfig.md5Encode(comfimpass.getText().toString().trim()));
        } catch (Exception e) {
        }
        OkHttpClientManager.postAsyn(Urls.changepassword, map, new OkHttpClientManager.ResultCallback<BaseBean>() {
            @Override
            public void onError(Request request, Exception e) {
                showLoadFinish();
                SystemConfig.showToast("设置失败");
            }

            @Override
            public void onResponse(BaseBean response) {
                showLoadFinish();
                if (response.getCode().equals("1")) {
                    SystemConfig.showToast(response.getMsg());
                    finish();
                } else {
                    SystemConfig.showToast(response.getMsg());
                }
            }
        });
    }


}
