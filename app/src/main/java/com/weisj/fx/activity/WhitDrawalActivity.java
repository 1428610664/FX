package com.weisj.fx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.weisj.fx.R;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.bean.BaseBean;
import com.weisj.fx.bean.BrandDetailBean;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.PreferencesUtils;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.Urls;
import com.weisj.fx.view.AlertDialog;
import com.weisj.fx.view.ClearEditText;
import com.weisj.fx.view.CustomBottomPopWindow;
import com.weisj.fx.view.KeyboardPopWindow;
import com.weisj.fx.view.choosearea.IDataCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BBMJ on 2016/1/18.
 */
public class WhitDrawalActivity extends BaseActivity implements View.OnClickListener {

    TextView BandName, BandNo;
    ClearEditText PriceEt;
    Button postBt;

    KeyboardPopWindow keyboardPopWindow;
    private double money;
    private int maxMoney;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (PriceEt.getText() != null && !PriceEt.getText().toString().trim().equals("") && PriceEt.getText().length() >= 2) {
                int money = Integer.valueOf(PriceEt.getText().toString());
                if (money > maxMoney) {
                    PriceEt.setText(String.valueOf(maxMoney));
                    return;
                }
                int temp = money % 50;
                if (temp != 0) {
                    if (temp >= 25) {
                        money = money + 50 - temp;
                    } else {
                        money = money - temp;
                    }
                } else {
                    return;
                }
                PriceEt.setText(String.valueOf(money));
            }
        }
    };

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_whitdraw, null);
        money = Double.valueOf(getIntent().getStringExtra("money"));
        maxMoney = (((int) (money / 50)) * 50);
        initView(view);
        Getbindbankcardinfo();
        return view;
    }

    @Override
    public String setTitleStr() {
        return "兑现";
    }

    @Override
    public void onHeadClick(View v) {
        if (v.getId() == R.id.root_head_right_text) {
            List<String> list = new ArrayList<String>();
            list.add("修改支付密码");
            list.add("忘记支付密码");
            CustomBottomPopWindow customBottomPopWindow = new CustomBottomPopWindow(WhitDrawalActivity.this, list, new CustomBottomPopWindow.PopupListener() {
                @Override
                public void onItemClick(View v, int position, int flag) {
                    if (position == 0) {
                        startActivity(new Intent(WhitDrawalActivity.this, ModifyPayPasswordActivity.class));
                    } else {
                        Intent intent = new Intent(WhitDrawalActivity.this, PayPasswordActivity.class);
                        intent.putExtra("isForget", 1);
                        startActivity(intent);
                    }
                }
            });

            customBottomPopWindow.show(WhitDrawalActivity.this);
        }
    }

    @Override
    public void getRefreshData() {

    }

    private void initView(View view) {
        rootView.setRightText("修改密码", true);
        BandName = (TextView) view.findViewById(R.id.whitdraw_bandname);
        BandNo = (TextView) view.findViewById(R.id.whitdraw_bandno);
        PriceEt = (ClearEditText) view.findViewById(R.id.whitdraw_price);
        postBt = (Button) view.findViewById(R.id.loginBt);
        PriceEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (PriceEt.getText().toString().trim().equals("0")) {
                    PriceEt.setText("");
                }
                if (PriceEt.getText().toString().length() > 1) {
                    postBt.setAlpha(1.0f);
                    postBt.setClickable(true);
                } else {
                    postBt.setAlpha(0.5f);
                    postBt.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                PriceEt.setSelection(PriceEt.getText().length());
                handler.removeMessages(1);
                try {
                    if (PriceEt.getText().length() > 0) {
                        money = Integer.valueOf(PriceEt.getText().toString());
                        if (money <= maxMoney && money % 50 == 0 && money > 25) {
                            return;
                        }
                        handler.sendEmptyMessageDelayed(1, 600);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    PriceEt.setText(String.valueOf(maxMoney));
                }

            }
        });

        postBt.setOnClickListener(this);
        postBt.setClickable(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(1);
    }

    // 1.21 获取会员绑定的银行卡信息
    private void Getbindbankcardinfo() {
        showLoading();
        Map<String, String> recommendParams = new HashMap<String, String>();
        recommendParams.put("member_id", PersonMessagePreferencesUtils.getUid());
        OkHttpClientManager.postAsyn(Urls.getbindbankcardinfo, recommendParams, new OkHttpClientManager.ResultCallback<BrandDetailBean>() {
            @Override
            public void onError(Request request, Exception e) {
                showNoNetWork();
            }

            @Override
            public void onResponse(BrandDetailBean response) {
                showLoadFinish();
                if (response.getCode().equals("1")) {
                    BandName.setText(response.getData().getBank_name());
                    BandNo.setText(response.getData().getBank_account_no());

                } else {
                    final AlertDialog ad = new AlertDialog(WhitDrawalActivity.this);
                    ad.setMessage("亲爱的客户,因为您还没有绑定银行卡，暂时无法提现。如需提现请先绑定银行卡");
                    ad.setTitle("温馨提示");
                    ad.setPositiveButton("好的", new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            ad.dismiss();
                            Intent intent = new Intent(WhitDrawalActivity.this, BindingActivity.class);
                            startActivity(intent);
                            finish();

                        }
                    });

                    ad.setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ad.dismiss();
                            finish();
                        }
                    });
                    ad.show();
                }

            }
        });
    }


    //    // 1.23 会员加密提现
    private void Withdrawusermoney(final String pass) {
        try {
            showLoading();
            Map<String, String> recommendParams = new HashMap<>();
            recommendParams.put("member_id", PersonMessagePreferencesUtils.getUid());
            int money = Integer.valueOf(PriceEt.getText().toString()) / 50;
            recommendParams.put("withdraw_money", String.valueOf(money));
            recommendParams.put("pay_password", SystemConfig.md5Encode(pass));
            OkHttpClientManager.postAsyn(Urls.withdrawusermoney, recommendParams, new OkHttpClientManager.ResultCallback<BaseBean>() {
                @Override
                public void onError(Request request, Exception e) {
                    showLoadFinish();
                    try {
                        Toast.makeText(WhitDrawalActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                }

                @Override
                public void onResponse(BaseBean response) {
                    try {
                        showLoadFinish();
                        if (response.getCode().equals("1")) {
                            Toast.makeText(WhitDrawalActivity.this, response.getMsg(), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Withdrawusermoney2(pass);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
        }
    }

    //    // 1.23 会员普通提现
    private void Withdrawusermoney2(final String pass) {
        try {
            showLoading();
            Map<String, String> recommendParams = new HashMap<>();
            recommendParams.put("member_id", PersonMessagePreferencesUtils.getUid());
            int money = Integer.valueOf(PriceEt.getText().toString()) / 50;
            recommendParams.put("withdraw_money", String.valueOf(money));
            recommendParams.put("pay_password", pass);
            OkHttpClientManager.postAsyn(Urls.withdrawusermoney, recommendParams, new OkHttpClientManager.ResultCallback<BaseBean>() {
                @Override
                public void onError(Request request, Exception e) {
                    showLoadFinish();
                    try {
                        Toast.makeText(WhitDrawalActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                }

                @Override
                public void onResponse(BaseBean response) {
                    try {
                        showLoadFinish();
                        if (response.getCode().equals("1")) {
                            Toast.makeText(WhitDrawalActivity.this, response.getMsg(), Toast.LENGTH_SHORT).show();
                            changePaypassword(pass);
                            finish();
                        } else {
                            Toast.makeText(WhitDrawalActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
        }
    }

    // 设置支付密码
    private void changePaypassword(String pass) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("member", PreferencesUtils.getString("cellphone"));
            map.put("password", pass);
            map.put("change_password", SystemConfig.md5Encode(pass));
            OkHttpClientManager.postAsyn(Urls.changepaypassword, map, new OkHttpClientManager.ResultCallback<BaseBean>() {
                @Override
                public void onError(Request request, Exception e) {
                }

                @Override
                public void onResponse(BaseBean response) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.loginBt:
                keyboardPopWindow = new KeyboardPopWindow(WhitDrawalActivity.this, new IDataCallback() {
                    @Override
                    public void callback(Object object) {
                        if (object instanceof String) {
                            String pass = object.toString();
                            Withdrawusermoney(pass);
                            keyboardPopWindow.dismiss();
                        }
                    }
                });
                keyboardPopWindow.show(WhitDrawalActivity.this);
                break;

        }


    }
}
