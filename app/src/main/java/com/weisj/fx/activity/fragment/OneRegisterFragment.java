package com.weisj.fx.activity.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.weisj.fx.R;
import com.weisj.fx.activity.EditAddressActivity;
import com.weisj.fx.activity.RegisterActivity;
import com.weisj.fx.base.BaseFragment;
import com.weisj.fx.bean.BaseBean;
import com.weisj.fx.presenter.RegisterOnePresenter;
import com.weisj.fx.utils.KeyboardUtil;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.view.choosearea.ChooseAreaPop;
import com.weisj.fx.view.choosearea.IDataCallback;
import com.weisj.fx.view.dialog.UserProtocolDialog;
import com.weisj.fx.viewinterface.IRegisterOneView;

/**
 * Created by Administrator on 2016/7/21 0021.
 */
public class OneRegisterFragment extends BaseFragment implements View.OnClickListener, IRegisterOneView, IDataCallback {
    private EditText phone, staffCodeEdit;
    private RegisterOnePresenter presenter;
    private TextView staffText;
    private String areaId;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView.isHintHeadBar(true);
        View view = inflater.inflate(R.layout.fragment_one_register, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        phone = (EditText) view.findViewById(R.id.phone);
        staffCodeEdit = (EditText) view.findViewById(R.id.staff_number);
        staffText = (TextView) view.findViewById(R.id.staff_city);
        view.findViewById(R.id.nextBt).setOnClickListener(this);
        view.findViewById(R.id.staff_city).setOnClickListener(this);
        view.findViewById(R.id.user_protocol).setOnClickListener(this);
        if (((RegisterActivity) getActivity()).type > 0) {
            staffCodeEdit.setVisibility(View.GONE);
            staffCodeEdit.setText("123456");
        }
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
            case R.id.nextBt:
                if (getActivity() instanceof RegisterActivity) {
                    KeyboardUtil.closeKeyBoard(getActivity());
                    if (areaId != null) {
                        ((RegisterActivity) getActivity()).staffCity = areaId;
                        if (presenter == null) {
                            presenter = new RegisterOnePresenter(this, this);
                        }
                        presenter.getVerCode();
                    }
                }
                break;
            case R.id.staff_city:
                ChooseAreaPop areaDialog = new ChooseAreaPop(this.getActivity(), R.style.mystyle, this, 1);
                areaDialog.showDialog();
                break;
            case R.id.user_protocol:
                new UserProtocolDialog(this.getContext()).show();
                break;
        }
    }

    @Override
    public String getPhoneNumber() {
        return phone.getText().toString();
    }

    @Override
    public String getStaffCode() {
        return staffCodeEdit.getText().toString();
    }

    @Override
    public void successGetCode(BaseBean baseBean) {
        if (SystemConfig.isGetNetSuccess(baseBean)) {
            ((RegisterActivity) getActivity()).phone = phone.getText().toString();
            ((RegisterActivity) getActivity()).staffCode = getStaffCode();
            ((RegisterActivity) getActivity()).changeFragment(1);
        }
    }

    @Override
    public void callback(Object object) {
        if (object instanceof String) {
            String[] split = object.toString().split(",");
            staffText.setText(split[0] + "  " + split[1] + "  " + split[2]);
            areaId = split[5];
            Toast.makeText(getActivity(), areaId, Toast.LENGTH_LONG).show();
        }
    }
}
