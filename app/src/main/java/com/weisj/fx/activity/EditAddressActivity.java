package com.weisj.fx.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.weisj.fx.R;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.bean.AdressBean;
import com.weisj.fx.bean.BaseBean;
import com.weisj.fx.bean.GetMoblieTicket;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.PreferencesUtils;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.Urls;
import com.weisj.fx.view.ClearEditText;
import com.weisj.fx.view.choosearea.ChooseAreaPop;
import com.weisj.fx.view.choosearea.IDataCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
public class EditAddressActivity extends BaseActivity implements View.OnClickListener, IDataCallback {
    ClearEditText userName, phone, address;
    LinearLayout area;
    TextView choosePlaceTv;
    Button newAddressBt;
    String intentValue;
    AdressBean.DataEntity dataEntity;

    String provinceId, cityId, areaId;

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_editaddress, null);
        intentValue = getIntent().getStringExtra("Title");
        initView(view);
        return view;
    }

    private void initView(View view) {
        userName = (ClearEditText) view.findViewById(R.id.username);
        phone = (ClearEditText) view.findViewById(R.id.phone);
        address = (ClearEditText) view.findViewById(R.id.address);
        area = (LinearLayout) view.findViewById(R.id.choose_place);

        newAddressBt = (Button) view.findViewById(R.id.loginBt);
        choosePlaceTv = (TextView) view.findViewById(R.id.choose_placeTv);
        if (intentValue.equals("edit")) {
            dataEntity = (AdressBean.DataEntity) getIntent().getSerializableExtra("Value");

            userName.setText(dataEntity.getRecipients());
            phone.setText(dataEntity.getPhone());
//                choosePlaceTv.setText(dataEntity.getProvince() + dataEntity.getCity() + dataEntity.getArea());
            address.setText(dataEntity.getAddress());
            newAddressBt.setText("修改");

        }

        area.setOnClickListener(this);
        newAddressBt.setOnClickListener(this);

    }

    @Override
    public String setTitleStr() {
        return "编辑收货地址";
    }

    @Override
    public void getRefreshData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choose_place:
                ChooseAreaPop areaDialog = new ChooseAreaPop(EditAddressActivity.this, R.style.mystyle, EditAddressActivity.this);
                areaDialog.showDialog();
                break;
            case R.id.loginBt:
                isNullValue();
                break;
        }
    }


    private void isNullValue() {
        String name = userName.getText().toString();
        String tel = phone.getText().toString();
        String area = choosePlaceTv.getText().toString();
        String add = address.getText().toString();

        if (!name.isEmpty() && SystemConfig.isPhone(tel) && !area.isEmpty() && !add.isEmpty()) {

            if (intentValue.equals("add")) {
                setdefaultconsignee();
            } else {
                delectAddress();
            }
        } else if (name.isEmpty()) {
            Toast.makeText(EditAddressActivity.this, "请填写收货人姓名", Toast.LENGTH_SHORT).show();
        } else if (area.isEmpty()) {
            Toast.makeText(EditAddressActivity.this, "请选择地区", Toast.LENGTH_SHORT).show();
        } else if (add.isEmpty()) {
            Toast.makeText(EditAddressActivity.this, "请填写详细收货地址", Toast.LENGTH_SHORT).show();
        }

    }


    //添加收货地址
    private void setdefaultconsignee() {
        showLoading();
        Map<String, String> map = new HashMap<String, String>();
        map.put("member", PersonMessagePreferencesUtils.getUid());
        map.put("name", userName.getText().toString());
        map.put("tel", phone.getText().toString());
        map.put("province", provinceId);
        map.put("city", cityId);
        SystemConfig.systemOut("area  == " + areaId);

        if (areaId != null && !areaId.equals("null")) {
            map.put("area", areaId);
        }
        map.put("address", address.getText().toString());

        OkHttpClientManager.postAsyn(Urls.addconsignee, map, new OkHttpClientManager.ResultCallback<BaseBean>() {
            @Override
            public void onError(Request request, Exception e) {
                showLoadFinish();
                Toast.makeText(EditAddressActivity.this, "创建失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(BaseBean response) {
                showLoadFinish();
                try {
                    if (response.getCode().equals("1")) {
                        PreferencesUtils.putInt("has_default_consignee", 1);
                        Toast.makeText(EditAddressActivity.this, response.getMsg(), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(EditAddressActivity.this, response.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    //删除收货地址
    private void delectAddress() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("member", PersonMessagePreferencesUtils.getUid());
        map.put("recipient_id", String.valueOf(dataEntity.getRecipient_id()));
        OkHttpClientManager.postAsyn(Urls.delconsignee, map, new OkHttpClientManager.ResultCallback<GetMoblieTicket>() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(GetMoblieTicket response) {
                try {
                    if (response.getCode().equals("1")) {
                        setdefaultconsignee();
                    } else {
                        Toast.makeText(EditAddressActivity.this, response.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void callback(Object object) {
        if (object instanceof String) {
            String[] split = object.toString().split(",");
            choosePlaceTv.setText(split[0] + split[1] + split[2]);
            provinceId = split[3];
            cityId = split[4];
            areaId = split[5];
        }
    }
}
