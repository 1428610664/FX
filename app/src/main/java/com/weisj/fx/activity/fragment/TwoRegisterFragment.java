package com.weisj.fx.activity.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.activity.RegisterActivity;
import com.weisj.fx.base.BaseFragment;
import com.weisj.fx.bean.BaseBean;
import com.weisj.fx.bean.VerCodeBean;
import com.weisj.fx.presenter.RegisterTwoPresenter;
import com.weisj.fx.utils.KeyboardUtil;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.viewinterface.IRegisterTwoView;


/**
 * Created by Administrator on 2016/7/21 0021.
 */
public class TwoRegisterFragment extends BaseFragment implements View.OnClickListener, IRegisterTwoView {
    private Button codeBt;
    private EditText codeEv;
    private RegisterTwoPresenter presenter;

    private int time = 120;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    codeBt.setText("重新发送" + "(" + time + "秒)");
                    if (time == 0) {
                        codeBt.setClickable(true);
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
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView.isHintHeadBar(true);
        View view = inflater.inflate(R.layout.fragment_two_register, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ((TextView) view.findViewById(R.id.phone)).setText(((RegisterActivity) getActivity()).phone);
        codeBt = (Button) view.findViewById(R.id.codeBt);
        codeBt.setClickable(false);
        codeEv = (EditText) view.findViewById(R.id.codeEv);
        view.findViewById(R.id.loginBt).setOnClickListener(this);
        clickGetCode();
    }

    @Override
    public String setTitleStr() {
        return "";
    }

    @Override
    public void getRefreshData() {

    }

    public String getCode() {
        return codeEv.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBt:
                if (getActivity() instanceof RegisterActivity) {
                    KeyboardUtil.closeKeyBoard(getActivity());
                    if (presenter == null) {
                        presenter = new RegisterTwoPresenter(this, this);
                    }
                    presenter.verCode();
                }
                break;
            case R.id.codeBt:
                if (presenter == null) {
                    presenter = new RegisterTwoPresenter(this, this);
                }
                presenter.againGetCode();
                break;
        }
    }

    @Override
    public String getVerCode() {
        return getCode();
    }

    @Override
    public String getPhoneNumber() {
        return ((RegisterActivity) getActivity()).phone;
    }

    @Override
    public void successAgainGetCode(BaseBean baseBean) {
        if (SystemConfig.isGetNetSuccess(baseBean)) {
            SystemConfig.showToast("成功发送到手机");
            clickGetCode();
        }
    }

    @Override
    public void successVerCode(VerCodeBean baseBean) {
        if ("1".equals(baseBean.getCode())) {
            ((RegisterActivity) getActivity()).vCode = baseBean.getData().getTicket_code();
            ((RegisterActivity) getActivity()).changeFragment(2);
        } else {
            SystemConfig.showToast(baseBean.getMsg());
        }
    }

    // 获取验证码前的电话号码非空判断和计时器的启动
    private void clickGetCode() {
        time = 120;
        codeBt.setClickable(false);
        if (handler != null)
            handler.sendEmptyMessage(1);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeMessages(1);
        handler = null;
    }
}
