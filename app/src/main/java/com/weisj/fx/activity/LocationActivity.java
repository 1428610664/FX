package com.weisj.fx.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.weisj.fx.MainActivity;
import com.weisj.fx.R;
import com.weisj.fx.adapter.ItemLocationHeadAllCityAdapter;
import com.weisj.fx.adapter.ItemLocationHotCityAdapter;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.bean.LocationBean;
import com.weisj.fx.utils.CommenString;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.PreferencesUtils;
import com.weisj.fx.utils.Urls;
import com.weisj.fx.view.SideBar;

/**
 * Created by Administrator on 2016/6/28 0028.
 */
public class LocationActivity extends BaseActivity implements View.OnClickListener {

    private View headView;
    private GridView gridView;
    private ListView listView;
    private SideBar sideBar;
    private TextView dialog;
    private LocationBean locationBean;

    private TextView locationName;


    @Override
    public View initView(Bundle savedInstanceState) {

        View view = mLayoutInflater.inflate(R.layout.activity_location, null);
        setIsClosBack(true);
        initView(view);
        gethotregions();
        return view;
    }


    @Override
    public String setTitleStr() {
        return "城市列表";
    }


    private void initView(View view) {
        headView = mLayoutInflater.inflate(R.layout.head_location, null);
        gridView = (GridView) headView.findViewById(R.id.grid_view);
        locationName = (TextView) headView.findViewById(R.id.locationName);
        listView = (ListView) view.findViewById(R.id.listview);
        sideBar = (SideBar) view.findViewById(R.id.sidrbar);
        dialog = (TextView) view.findViewById(R.id.dialog);
        sideBar.setTextView(dialog);
        if (CommenString.locationState) {
            locationName.setText(CommenString.city);
            locationName.setOnClickListener(this);
        } else {
            locationName.setText("定位失败");
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    public void gethotregions() {
        showLoading();
        OkHttpClientManager.postAsyn(Urls.gethotregions, new OkHttpClientManager.ResultCallback<LocationBean>() {
            @Override
            public void onError(Request request, Exception e) {
                showNoNetWork();
            }

            @Override
            public void onResponse(LocationBean response) {
                showLoadFinish();
                if (response != null && response.getCode().equals("1")) {
                    locationBean = response;

                    gridView.setAdapter(new ItemLocationHotCityAdapter(LocationActivity.this, locationBean.getData().getHotDistrictList()));
                    listView.addHeaderView(headView);
                    listView.setAdapter(new ItemLocationHeadAllCityAdapter(LocationActivity.this, locationBean.getData().getAllDistrictList()));
                    sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

                        @Override
                        public void onTouchingLetterChanged(int s) {

                            listView.setSelection(s + 1);
                        }
                    });

                }
            }
        });
    }

    @Override
    public void onRootViewClick(View v) {
        super.onRootViewClick(v);
        Intent mIntent = new Intent(this, MainActivity.class);
        mIntent.putExtra("city", CommenString.selectCity);
        setResult(Activity.RESULT_OK, mIntent);
        PreferencesUtils.putString("select_city", CommenString.selectCity);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void getRefreshData() {
        gethotregions();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 创建退出对话框
            Intent mIntent = new Intent(this, MainActivity.class);
            mIntent.putExtra("city", CommenString.selectCity);
            setResult(Activity.RESULT_OK, mIntent);
            PreferencesUtils.putString("select_city", CommenString.selectCity);
            finish();
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.locationName) {
            Intent mIntent = new Intent(this, MainActivity.class);
            mIntent.putExtra("city", CommenString.city);
            setResult(Activity.RESULT_OK, mIntent);
            finish();
        }
    }
}
