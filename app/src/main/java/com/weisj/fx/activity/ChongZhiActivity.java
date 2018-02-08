package com.weisj.fx.activity;

import android.os.Bundle;
import android.view.View;

import com.weisj.fx.R;
import com.weisj.fx.base.BaseActivity;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class ChongZhiActivity extends BaseActivity {
    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_chong_zhi,null);
        return view;
    }

    @Override
    public String setTitleStr() {
        return "充值";
    }

    @Override
    public void getRefreshData() {

    }
}
