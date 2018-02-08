package com.weisj.fx.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.activity.fragment.OneRegisterFragment;
import com.weisj.fx.activity.fragment.ThreeRegisterFragment;
import com.weisj.fx.activity.fragment.TwoRegisterFragment;

/**
 * Created by Administrator on 2016/7/21 0021.
 */
public class RegisterActivity extends FragmentActivity implements View.OnClickListener {
    private Fragment oneFragment, twoFragment, threeFragment;
    private FragmentManager fragmentManager;
    private int number = 0;
    public String phone;
    public String vCode;
    public String staffCode;
    public String staffCity;
    public int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViewById(R.id.register_back).setOnClickListener(this);
        findViewById(R.id.register_finish).setOnClickListener(this);
        type = getIntent().getIntExtra("type", 0);
        if (type > 0) {
            ((TextView) findViewById(R.id.title)).setText("重设密码");
        }
        fragmentManager = getSupportFragmentManager();
        changeFragment(0);
    }


    public void changeFragment(int number) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        switch (number) {
            case 0:
                if (oneFragment == null) {
                    oneFragment = new OneRegisterFragment();
                }
                fragment = oneFragment;
                break;
            case 1:
                if (twoFragment == null) {
                    twoFragment = new TwoRegisterFragment();
                }
                fragment = twoFragment;
                break;
            case 2:
                if (threeFragment == null) {
                    threeFragment = new ThreeRegisterFragment();
                }
                fragment = threeFragment;
                break;
        }
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_back:
                if (number == 0) {
                    finish();
                } else {
                    changeFragment(number - 1);
                }
                break;
            case R.id.register_finish:
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (number == 0) {
            super.onBackPressed();
        } else {
            changeFragment(number - 1);
        }
    }


}
