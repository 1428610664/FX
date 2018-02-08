package com.weisj.fx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.weisj.fx.R;
import com.weisj.fx.adapter.ItemConsigneeAddress;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.bean.AdressBean;
import com.weisj.fx.presenter.ShowAddressPresenter;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.PreferencesUtils;
import com.weisj.fx.viewinterface.IShowAddressView;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
public class ConsigneeAddressActivity extends BaseActivity implements IShowAddressView {
    private RecyclerView recyclerView;
    private ShowAddressPresenter presenter;
    private AdressBean adressBean;

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_consignee_address, null);
        initView(view);
        return view;
    }

    public ShowAddressPresenter getPresenter() {
        return presenter;
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        rootView.setRightText("添加",true);
        presenter = new ShowAddressPresenter(this, this);
    }

    @Override
    public String setTitleStr() {
        return "我的收货地址";
    }

    @Override
    public void showAddress(AdressBean adressBean) {
        this.adressBean = adressBean;
        recyclerView.setAdapter(new ItemConsigneeAddress(ConsigneeAddressActivity.this, adressBean));
        recyclerView.setLayoutManager(new LinearLayoutManager(ConsigneeAddressActivity.this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getAddress();
    }

    @Override
    public void successAddress(String addressId) {
        AdressBean.DataEntity data = null;
        for (AdressBean.DataEntity dataEntity : adressBean.getData()) {
            if (String.valueOf(dataEntity.getRecipient_id()).equals(addressId)) {
                data = dataEntity;
                break;
            }
        }
        adressBean.getData().remove(data);
        recyclerView.getAdapter().notifyDataSetChanged();
        if (!(adressBean.getData().size() > 0)) {
            showNoData();
            PreferencesUtils.putInt("has_default_consignee", 0);
        }
    }

    @Override
    public void onHeadClick(View v) {
        if (v.getId() == R.id.root_head_right_text) {
            Intent intent = new Intent(this, EditAddressActivity.class);
            intent.putExtra("Title", "add");
            startActivity(intent);
        }
    }

    @Override
    public void getRefreshData() {
        presenter.getAddress();
    }
}
