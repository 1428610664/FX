package com.weisj.fx.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sina.weibo.sdk.utils.MD5;
import com.squareup.okhttp.Request;
import com.umeng.socialize.UMShareAPI;
import com.weisj.fx.R;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.bean.BaseBean;
import com.weisj.fx.bean.ProvinceCityBean;
import com.weisj.fx.utils.BitmapUtil;
import com.weisj.fx.utils.CameraUtil;
import com.weisj.fx.utils.CommenString;
import com.weisj.fx.utils.CropperImageScale;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.KeyboardUtil;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.TextViewUtils;
import com.weisj.fx.utils.Urls;
import com.weisj.fx.view.CircleImageView;
import com.weisj.fx.view.CustomBottomPopWindow;
import com.weisj.fx.view.choosearea.ChooseAreaPop;
import com.weisj.fx.view.choosearea.IDataCallback;
import com.weisj.fx.view.dialog.InputPassDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/17 0017.
 */
public class UserInfoActivity extends BaseActivity implements View.OnClickListener, CustomBottomPopWindow.PopupListener, IDataCallback, InputPassDialog.InputListener {
    private String imageUrl;
    private String name;
    private int sex;
    private TextView userName;
    private TextView userSex, userStaff, userCity;
    private EditText userNameEdit, userStaffEdit;
    private CustomBottomPopWindow customPopWindow;
    private CameraUtil cameraUtil;
    private ImageView userImage;
    private Bitmap headBitmap;
    private CustomBottomPopWindow sexPopWin;
    private String staff_id;
    private int district_id;
    private ChooseAreaPop chooseAreaPop;
    private int province, city, area;
    private InputPassDialog dialog;


    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_user_info, null);
        cameraUtil = new CameraUtil(this);
        if (getIntent() != null) {
            imageUrl = getIntent().getStringExtra("member_pic");
            name = getIntent().getStringExtra("member_name");
            sex = getIntent().getIntExtra("sex", 0);
            district_id = getIntent().getIntExtra("district_id", 0);
            staff_id = getIntent().getStringExtra("staff_id");
        }
        initView(view);
        dialog = new InputPassDialog(this, this);
        return view;
    }

    @Override
    public String setTitleStr() {
        return "个人信息";
    }

    @Override
    public void getRefreshData() {

    }

    private void initView(View view) {
        userImage = (CircleImageView) view.findViewById(R.id.user_image);
        ImageLoaderUtils.getInstance().display(userImage, imageUrl);
        userImage.setOnClickListener(this);
        userName = (TextView) view.findViewById(R.id.user_name);
        TextViewUtils.setText(userName, name, "暂无");
        userSex = (TextView) view.findViewById(R.id.user_sex);
        userStaff = (TextView) view.findViewById(R.id.user_staff);
        userCity = (TextView) view.findViewById(R.id.user_district);
        view.findViewById(R.id.user_name_linear).setOnClickListener(this);
        view.findViewById(R.id.user_sex_linear).setOnClickListener(this);
        view.findViewById(R.id.user_staff_linear).setOnClickListener(this);
        view.findViewById(R.id.user_district_linear).setOnClickListener(this);
        userNameEdit = (EditText) view.findViewById(R.id.user_name_edit);
        userStaffEdit = (EditText) view.findViewById(R.id.user_staff_edit);
        userNameEdit.setVisibility(View.GONE);
        userNameEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    completeUserName();
                    return true;
                }
                return false;
            }
        });
        userStaffEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    completeUserStaff();
                    return true;
                }
                return false;
            }
        });

        if (name != null && !name.equals("null")) {
            userNameEdit.setText(name);
        }
        if (sex == 0) {
            userSex.setText("保密");
        } else if (sex == 1) {
            userSex.setText("男性");
        } else if (sex == 2) {
            userSex.setText("女性");
        }
        if (staff_id != null) {
            userStaff.setText(staff_id);
        }
        chooseAreaPop = new ChooseAreaPop(this, R.style.mystyle, this, 1);
        if (district_id == 0) {
            userCity.setText("中国");
        } else {
            for (int i = 0; i < chooseAreaPop.provinceCityBean.getProvince_list().size(); i++) {
                ProvinceCityBean.ProvinceListEntity provinceListEntity = chooseAreaPop.provinceCityBean.getProvince_list().get(i);
                for (int j = 0; j < provinceListEntity.getCity_list().size(); j++) {
                    ProvinceCityBean.ProvinceListEntity.CityListEntity cityListEntity = provinceListEntity.getCity_list().get(j);
                    for (int n = 0; n < cityListEntity.getArea_list().size(); n++) {
                        ProvinceCityBean.ProvinceListEntity.CityListEntity.AreaListEntity areaListEntity = cityListEntity.getArea_list().get(n);
                        if (areaListEntity.getRegion_id() == district_id) {
                            userCity.setText(String.format("%s %s %s", provinceListEntity.getProvince_name(), cityListEntity.getCity_name(), areaListEntity.getArea_name()));
                            province = i;
                            city = j;
                            area = n;
                            break;
                        }
                    }
                }
            }
        }
    }

    // 完成用户昵称
    private void completeUserName() {
        if (userNameEdit.getText().toString().trim().equals("")) {
            SystemConfig.showToast("昵称不能为空");
        } else {
            userName.setText(userNameEdit.getText().toString());
            userName.setVisibility(View.VISIBLE);
            userNameEdit.setVisibility(View.GONE);
            KeyboardUtil.closeKeyBoard(this);
            updateUserName(userName.getText().toString());
        }
    }

    // 完成用户证件号码
    private void completeUserStaff() {
        if (userStaffEdit.getText().toString().trim().equals("") || userStaffEdit.getText().length() < 8) {
            userStaff.setVisibility(View.VISIBLE);
            userStaffEdit.setVisibility(View.GONE);
            KeyboardUtil.closeKeyBoard(this);
        } else {
            dialog.show();
//            KeyboardUtil.closeKeyBoard(this);
        }
    }


    @Override
    public void onClick(View v) {
        if (userStaff.getVisibility() == View.GONE) {
            return;
        }
        switch (v.getId()) {
            case R.id.user_name_linear:
                clickUserName();
                break;
            case R.id.user_image:
                clickImage(v);
                break;
            case R.id.user_district_linear:// 地区
                chooseAreaPop.showDialog(province, city, area);
                break;
            case R.id.user_staff_linear: // 证件号码
                clickStaff();
                break;
            case R.id.user_sex_linear:
                clickSex(v);
                break;
        }
    }

    // 点击用户头像
    private void clickSex(View view) {
        if (sexPopWin == null) {
            List<String> list = new ArrayList<String>();
            list.add("男性");
            list.add("女性");
            sexPopWin = new CustomBottomPopWindow(this, list, this, 1);
        }
        sexPopWin.show(this);
    }

    // 点击用户头像
    private void clickImage(View view) {
        if (customPopWindow == null) {
            customPopWindow = new CustomBottomPopWindow(this, this);
        }
        customPopWindow.show(this);
    }


    // 点击用户昵称
    private void clickUserName() {
        userNameEdit.setText(userName.getText());
        userNameEdit.setVisibility(View.VISIBLE);
        userName.setVisibility(View.GONE);
        userNameEdit.setFocusable(true);
        userNameEdit.setSelection(userNameEdit.getText().length());
        userNameEdit.setFocusableInTouchMode(true);
        userNameEdit.requestFocus();
        KeyboardUtil.openKeyBoard(userNameEdit, this);
    }

    // 点击用户昵称
    private void clickStaff() {
        userStaffEdit.setText(userStaff.getText());
        userStaffEdit.setVisibility(View.VISIBLE);
        userStaff.setVisibility(View.GONE);
        userStaffEdit.setFocusable(true);
        userStaffEdit.setSelection(userStaffEdit.getText().length());
        userStaffEdit.setFocusableInTouchMode(true);
        userStaffEdit.requestFocus();
        KeyboardUtil.openKeyBoard(userStaffEdit, this);
    }

    // 更新用户昵称
    private void updateUserName(String name) {
        Map<String, String> params = new HashMap<>();
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        params.put("nickname", name);
        OkHttpClientManager.postAsyn(Urls.updateUsernName, params, null);
    }

    // 更新用户地区
    private void updateUserCity(String name) {
        Map<String, String> params = new HashMap<>();
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        params.put("district_id", name);
        OkHttpClientManager.postAsyn(Urls.modifydistrict, params, null);
    }

    // 更新用户性别
    private void updateUserSex(String sex) {
        Map<String, String> params = new HashMap<>();
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        params.put("sex", sex);
        OkHttpClientManager.postAsyn(Urls.updateUserSex, params, null);
    }

    // 更新用户证件号码
    private void updateUserStaff(String password, String staff_id) {
        Map<String, String> params = new HashMap<>();
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        try {
            params.put("password", SystemConfig.md5Encode(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        params.put("staff_id", staff_id);
        OkHttpClientManager.postAsyn(Urls.modifystaffid, params, new OkHttpClientManager.ResultCallback<BaseBean>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(BaseBean response) {
                SystemConfig.showToast(response.getMsg());
            }
        });
    }

    // 更新头像
    private void updateUserImage() {
        File file = BitmapUtil.saveBitmapFile(headBitmap);
        if (file != null) {
            OkHttpClientManager.Param params = new OkHttpClientManager.Param("member_id", PersonMessagePreferencesUtils.getUid());
            try {
                OkHttpClientManager.postAsyn(Urls.updateUserImage, new OkHttpClientManager.ResultCallback<BaseBean>() {
                    @Override
                    public void onError(Request request, Exception e) {
                    }

                    @Override
                    public void onResponse(BaseBean response) {
                    }
                }, file, "image", params);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                if (userNameEdit.getVisibility() == View.VISIBLE) {
                    completeUserName();
                } else {
                    completeUserStaff();
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            // 获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CommenString.TAKE_PHOTO) {
                cameraUtil.photoZoomFromTake(CropperImageScale.square);
            } else if (requestCode == CommenString.LOCAL_PHOTO) {
                List<String> selectedImage = data.getStringArrayListExtra("paths");
                cameraUtil.photoZoomFromMapStorage(selectedImage.get(0),
                        CropperImageScale.square);
            } else if (requestCode == CommenString.PHOTO_RESULT) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    headBitmap = extras.getParcelable("data");
                    userImage.setImageBitmap(headBitmap);
                    updateUserImage();
                }
            } else {
                UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
            }
        }
    }


    @Override
    public void onItemClick(View v, int position, int flag) {
        if (flag == 0) {
            if (position == 0) {
                cameraUtil.callCamera();
            } else {
                cameraUtil.callLocalPhotos(1);
            }
        } else {
            if (position == 0) {
                updateUserSex("1");
                userSex.setText("男性");
            } else {
                updateUserSex("2");
                userSex.setText("女性");
            }
        }
    }

    @Override
    public void callback(Object object) {
        if (object instanceof String) {
            String[] split = object.toString().split(",");
            userCity.setText(String.format("%s %s %s", split[0], split[1], split[2]));
            updateUserCity(split[5]);
        }
    }

    @Override
    public void cancel() {
        userStaff.setVisibility(View.VISIBLE);
        userStaffEdit.setVisibility(View.GONE);
    }

    @Override
    public void confirm(String pass) {
        userStaff.setText(userStaffEdit.getText());
        userStaff.setVisibility(View.VISIBLE);
        userStaffEdit.setVisibility(View.GONE);
        updateUserStaff(pass, userStaff.getText().toString());
    }
}
