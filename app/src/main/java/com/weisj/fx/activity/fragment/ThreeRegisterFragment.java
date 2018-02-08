package com.weisj.fx.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.weisj.fx.MainActivity;
import com.weisj.fx.R;
import com.weisj.fx.activity.LoginActivity;
import com.weisj.fx.activity.RegisterActivity;
import com.weisj.fx.base.BaseFragment;
import com.weisj.fx.presenter.RegisterThreePresenter;
import com.weisj.fx.viewinterface.IRegisterThreeView;

/**
 * Created by Administrator on 2016/7/21 0021.
 */
public class ThreeRegisterFragment extends BaseFragment implements IRegisterThreeView, View.OnClickListener {
    private View view;
    private RegisterThreePresenter presenter;
    private long currentTime;

    private EditText password, twoPassword;


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView.isHintHeadBar(true);
        View view = inflater.inflate(R.layout.fragment_three_register, null);
        this.view = view;
        view.findViewById(R.id.nextBt).setOnClickListener(this);
        password = ((EditText) view.findViewById(R.id.password));
        twoPassword = ((EditText) view.findViewById(R.id.confirmPass));
        password.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
        twoPassword.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
        return view;
    }

    @Override
    public String setTitleStr() {
        return "";
    }

    @Override
    public void getRefreshData() {

    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public String getTwoPassword() {
        return password.getText().toString();
    }

    @Override
    public String getPhoneNumber() {
        return ((RegisterActivity) getActivity()).phone;
    }

    @Override
    public String getVCode() {
        return ((RegisterActivity) getActivity()).vCode;
    }

    @Override
    public String getStaffCode() {
        return ((RegisterActivity) getActivity()).staffCode;
    }

    @Override
    public void successSetPass() {
        startActivity(new Intent(this.getContext(), MainActivity.class));
        getActivity().finish();
        Intent intent = new Intent();
        intent.setAction(LoginActivity.BROADCAST_ACTION);
        getContext().sendBroadcast(intent);
    }

    @Override
    public String getStaffCity() {
        return ((RegisterActivity) getActivity()).staffCity;
    }

    @Override
    public void onClick(View v) {
        if (System.currentTimeMillis() - currentTime > 500) {
            if (presenter == null) {
                presenter = new RegisterThreePresenter(this, this);
            }
            if (((RegisterActivity) getActivity()).type == 0) {
                presenter.register();
            } else {
                presenter.forgetPass();
            }
        }
        currentTime = System.currentTimeMillis();

    }
}
