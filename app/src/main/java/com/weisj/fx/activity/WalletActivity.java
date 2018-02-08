package com.weisj.fx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.weisj.fx.R;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.bean.MyWalletBean;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.PreferencesUtils;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.Urls;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BBMJ on 2016/1/13.
 */
public class WalletActivity extends BaseActivity implements View.OnClickListener {


    TextView all_income, user_money, not_confirm_money, withdrawing_money, withdraw_money;

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_my_wallet, null);
        initView(view);
        return view;
    }

    @Override
    public String setTitleStr() {
        return "我的钱包";
    }

    @Override
    public void getRefreshData() {
        Getallstatusmoney();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Getallstatusmoney();
    }

    private void initView(View view) {
        all_income = (TextView) view.findViewById(R.id.wallet_all_income);
        user_money = (TextView) view.findViewById(R.id.wallet_user_money);
        not_confirm_money = (TextView) view.findViewById(R.id.wallet_not_confirm_money);
        withdrawing_money = (TextView) view.findViewById(R.id.wallet_withdrawing_money);
        withdraw_money = (TextView) view.findViewById(R.id.wallet_withdraw_money);

        view.findViewById(R.id.wallet_binding).setOnClickListener(this);
        view.findViewById(R.id.wallet_withdrawals).setOnClickListener(this);
        view.findViewById(R.id.wallet_recharge).setOnClickListener(this);
        view.findViewById(R.id.billBt).setOnClickListener(this);

    }


    // 获取数据
    private void Getallstatusmoney() {
        Map<String, String> recommendParams = new HashMap<String, String>();
        recommendParams.put("member_id", PersonMessagePreferencesUtils.getUid());
        OkHttpClientManager.postAsyn(Urls.getallstatusmoney, recommendParams, new OkHttpClientManager.ResultCallback<MyWalletBean>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(MyWalletBean response) {
                if (response.getCode().equals("1")) {
                    all_income.setText("累计收入  " + SystemConfig.moneymulti(response.getData().getAll_income()));
                    user_money.setText(SystemConfig.moneymulti(response.getData().getUser_money()));
                    not_confirm_money.setText(SystemConfig.moneymulti(response.getData().getNot_confirm_money()));
                    withdrawing_money.setText(SystemConfig.moneymulti(response.getData().getWithdrawing_money()));
                    withdraw_money.setText(SystemConfig.moneymulti(response.getData().getWithdraw_money()));
                } else {
                    SystemConfig.showToast(response.getMsg());
                }
            }
        });
    }


    @Override
    public void onClick(View v) {

        try {
            switch (v.getId()) {

                case R.id.wallet_binding:
                    startActivity(new Intent(this, BindingActivity.class));
                    break;
                case R.id.wallet_withdrawals:
                    if (PreferencesUtils.getInt("has_set_pay_password", 0) == 0) {
                        startActivity(new Intent(this, PayPasswordActivity.class));
                        PreferencesUtils.putBoolean("has_reset_password", true);
                    } else {
                        Intent intent = new Intent(this, WhitDrawalActivity.class);
                        intent.putExtra("money", user_money.getText().toString());
                        startActivity(intent);
                    }
                    break;
                case R.id.wallet_recharge:
//                    startActivity(new Intent(this, RechargeActivity.class));
                    break;

                case R.id.billBt:
                    startActivity(new Intent(this, BillActivity.class));
                    break;

            }
        } catch (Exception e) {
        }

    }
}
