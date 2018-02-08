package com.weisj.fx.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.weisj.fx.R;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.bean.BaseBean;
import com.weisj.fx.bean.BrandDetailBean;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.Urls;
import com.weisj.fx.view.choosearea.ChooseAreaPop;
import com.weisj.fx.view.choosearea.IDataCallback;
import com.weisj.fx.view.dialog.ChooseBankDialog;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by BBMJ on 2016/1/18.
 */
public class BindingActivity extends BaseActivity implements View.OnClickListener, TextWatcher, IDataCallback {

    EditText BandCardNum, bandSub, UserName;
    TextView BandName, bandCity;
    int isAdd = 0;
    Button postBt;
    private ChooseBankDialog dialog;
    private ChooseAreaPop chooseAreaPop;
    private String mCurrentProviceId, mCurrentCityId;

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_binding, null);
        initView(view);
        Getbindbankcardinfo();
        return view;
    }

    @Override
    public String setTitleStr() {
        return "绑定银行卡";
    }

    @Override
    public void getRefreshData() {

    }

    private void initView(View view) {
        BandCardNum = (EditText) view.findViewById(R.id.my_wallet_bandcard_num_et);
        UserName = (EditText) view.findViewById(R.id.bind_truename);
        BandName = (TextView) view.findViewById(R.id.bind_bandname);
        bandSub = (EditText) view.findViewById(R.id.bind_band_sub);
        bandCity = (TextView) view.findViewById(R.id.bind_band_city);
        postBt = (Button) view.findViewById(R.id.loginBt);
        postBt.setOnClickListener(this);
        BandName.setOnClickListener(this);
        bandCity.setOnClickListener(this);
        BandCardNum.addTextChangedListener(new myWatcher());
        UserName.addTextChangedListener(this);
        bandSub.addTextChangedListener(this);
        postBt.setClickable(false);
    }


    // 1.21 获取会员绑定的银行卡信息
    private void Getbindbankcardinfo() {
        showLoading();
        Map<String, String> recommendParams = new HashMap<String, String>();
        recommendParams.put("member_id", PersonMessagePreferencesUtils.getUid());
        OkHttpClientManager.postAsyn(Urls.getbindbankcardinfo, recommendParams, new OkHttpClientManager.ResultCallback<BrandDetailBean>() {
            @Override
            public void onError(Request request, Exception e) {
                showLoadFinish();
            }

            @Override
            public void onResponse(BrandDetailBean response) {
                showLoadFinish();
                if (response.getCode().equals("1")) {
                    if (response.getData() != null) {
                        BandCardNum.setText(response.getData().getBank_account_no());
                        UserName.setText(response.getData().getBank_account_name());
                        BandName.setText(response.getData().getBank_name());
                        bandSub.setText(response.getData().getBranch_bank_name());
                        bandCity.setText(response.getData().getCity_name());
                        postBt.setText("修改");
                        isAdd = 1;
                    }
                } else {
                    isAdd = 0;
                    postBt.setText("添加");
                }

            }
        });
    }


    // 1.22 绑定的银行卡信息
    private void Bindbankcard() {
        showLoading();
        Map<String, String> recommendParams = new HashMap<>();
        recommendParams.put("member_id", PersonMessagePreferencesUtils.getUid());
        recommendParams.put("truename", UserName.getText().toString());
        recommendParams.put("card_no", BandCardNum.getText().toString());
        recommendParams.put("bank_name", BandName.getText().toString());
        recommendParams.put("province_id", mCurrentProviceId);
        recommendParams.put("city_id", mCurrentCityId);
        recommendParams.put("branch_bank_name", bandSub.getText().toString());

        OkHttpClientManager.postAsyn(Urls.bindbankcard, recommendParams, new OkHttpClientManager.ResultCallback<BaseBean>() {
            @Override
            public void onError(Request request, Exception e) {
                showLoadFinish();
            }

            @Override
            public void onResponse(BaseBean response) {
                showLoadFinish();
                SystemConfig.showToast(response.getMsg());
                if (response.getCode().equals("1")) {
                    finish();
                }
            }
        });
    }


    // 1.23 修改会员绑定的银行卡信息
    private void Editbankcard() {
        showLoading();
        Map<String, String> recommendParams = new HashMap<>();
        recommendParams.put("member_id", PersonMessagePreferencesUtils.getUid());
        recommendParams.put("truename", UserName.getText().toString());
        recommendParams.put("card_no", BandCardNum.getText().toString());
        recommendParams.put("bank_name", BandName.getText().toString());
        recommendParams.put("province_id", mCurrentProviceId);
        recommendParams.put("city_id", mCurrentCityId);
        recommendParams.put("branch_bank_name", bandSub.getText().toString());


        OkHttpClientManager.postAsyn(Urls.editbankcard, recommendParams, new OkHttpClientManager.ResultCallback<BaseBean>() {
            @Override
            public void onError(Request request, Exception e) {
                showLoadFinish();
            }

            @Override
            public void onResponse(BaseBean response) {
                showLoadFinish();
                SystemConfig.showToast(response.getMsg());
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBt:
                if (isAdd > 0) {
                    Editbankcard();
                } else {
                    Bindbankcard();
                }
                break;
            case R.id.bind_bandname:
                if (dialog == null) {
                    dialog = new ChooseBankDialog(this, R.style.mystyle, this);
                }
                dialog.showDialog();
                break;
            case R.id.bind_band_city:
                if (chooseAreaPop == null) {
                    chooseAreaPop = new ChooseAreaPop(this, R.style.mystyle, this, 2);
                }
                chooseAreaPop.showDialog();
                break;
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (BandCardNum.getText().toString().length() > 0 &&
                UserName.getText().toString().length() > 0 &&
                BandName.getText().toString().length() > 0 && bandCity.getText().toString().length() > 0 && bandSub.getText().toString().length() > 0) {
            postBt.setAlpha(1.0f);
            postBt.setClickable(true);
        } else {
            postBt.setAlpha(0.5f);
            postBt.setClickable(false);
        }
    }

    private void changeBtAble() {
        if (BandCardNum.getText().toString().length() > 0 &&
                UserName.getText().toString().length() > 0 &&
                BandName.getText().toString().length() > 0 && bandCity.getText().toString().length() > 0 && bandSub.getText().toString().length() > 0) {
            postBt.setAlpha(1.0f);
            postBt.setClickable(true);
        } else {
            postBt.setAlpha(0.5f);
            postBt.setClickable(false);
        }
    }


    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void callback(Object object) {
        String str = object.toString();
        if (str.contains(",")) {
            String[] infos = str.split(",");
            bandCity.setText(infos[0] + "  " + infos[1]);
            mCurrentCityId = infos[4];
            mCurrentProviceId = infos[3];
        } else {
            BandName.setText(object.toString());
        }
        changeBtAble();
    }


    class myWatcher implements TextWatcher {
        int beforeTextLength = 0;
        int onTextLength = 0;
        boolean isChanged = false;

        int location = 0;// 记录光标的位置
        private char[] tempChar;
        private StringBuffer buffer = new StringBuffer();
        int konggeNumberB = 0;

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // TODO Auto-generated method stub
            onTextLength = s.length();
            buffer.append(s.toString());
            if (onTextLength == beforeTextLength || onTextLength <= 3
                    || isChanged) {
                isChanged = false;
                return;
            }
            isChanged = true;


            if (BandCardNum.getText().toString().length() > 0 &&
                    UserName.getText().toString().length() > 0 &&
                    BandName.getText().toString().length() > 0) {

                postBt.setAlpha(1.0f);
                postBt.setClickable(true);
            } else {
                postBt.setAlpha(0.5f);
                postBt.setClickable(false);
            }

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
            beforeTextLength = s.length();
            if (buffer.length() > 0) {
                buffer.delete(0, buffer.length());
            }
            konggeNumberB = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') {
                    konggeNumberB++;
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
            if (isChanged) {
                location = BandCardNum.getSelectionEnd();
                int index = 0;
                while (index < buffer.length()) {
                    if (buffer.charAt(index) == ' ') {
                        buffer.deleteCharAt(index);
                    } else {
                        index++;
                    }
                }

                index = 0;
                int konggeNumberC = 0;
                while (index < buffer.length()) {
                    if ((index == 4 || index == 9 || index == 14 || index == 19)) {
                        buffer.insert(index, ' ');
                        konggeNumberC++;
                    }
                    index++;
                }

                if (konggeNumberC > konggeNumberB) {
                    location += (konggeNumberC - konggeNumberB);
                }

                tempChar = new char[buffer.length()];
                buffer.getChars(0, buffer.length(), tempChar, 0);
                String str = buffer.toString();
                if (location > str.length()) {
                    location = str.length();
                } else if (location < 0) {
                    location = 0;
                }

                BandCardNum.setText(str);
                Editable etable = BandCardNum.getText();
                Selection.setSelection(etable, location);
                isChanged = false;
            }
        }

    }

}
