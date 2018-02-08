package com.weisj.fx.activity;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;


import com.weisj.fx.R;
import com.weisj.fx.activity.fragment.ExpenditureFragment;
import com.weisj.fx.activity.fragment.IncomeFragment;
import com.weisj.fx.base.BaseActivity;

import java.util.Calendar;

/**
 * Created by BBMJ on 2016/1/27.
 */
public class BillActivity extends BaseActivity implements View.OnClickListener {


    private Calendar c;
    public static int Year, Month;
    private TextView dataTv;


    private View goodLinear;
    private TextView brandText;
    private TextView goodText;
    private View linear;

    private Fragment expendFragment, incomeFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction bottomtransaction;

    int type = 0;

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_wallet_bill, null);
        fragmentManager = getFragmentManager();
        rootView.isHintHeadBar(true);
        initView(view);
        setChioceItem(type);
        return view;
    }

    @Override
    public String setTitleStr() {
        return "";
    }

    @Override
    public void getRefreshData() {

    }


    private void initView(View view) {

        view.findViewById(R.id.root_head_back).setOnClickListener(this);
        view. findViewById(R.id.date_chose).setOnClickListener(this);

        dataTv = (TextView) view.findViewById(R.id.data);
        c = Calendar.getInstance();
        Year = c.get(Calendar.YEAR);
        Month = c.get(Calendar.MONTH) + 1;
        dataTv.setText(Year + "年" + Month + "月");


        goodLinear = view.findViewById(R.id.my_collection_linear_good);
        linear = view.findViewById(R.id.my_collection_linear);
        goodText = (TextView) view.findViewById(R.id.my_collection_good);
        brandText = (TextView)view. findViewById(R.id.my_collection_brand);

        goodText.setOnClickListener(this);
        brandText.setOnClickListener(this);

    }

    // 定义一个重置所有选项的方法
    private void setLinearlayoutBackground() {
        goodText.setTextColor(Color
                .parseColor(getString(R.color.color999999)));
        brandText.setTextColor(Color
                .parseColor(getString(R.color.color999999)));

        goodLinear.setVisibility(View.INVISIBLE);
        linear.setVisibility(View.INVISIBLE);

    }


    // 隐藏所有的Fragment,避免fragment混乱
    private void hideFragments(FragmentTransaction bottomtransaction) {
        if (expendFragment != null) {
            bottomtransaction.hide(expendFragment);
        }
        if (incomeFragment != null) {
            bottomtransaction.hide(incomeFragment);
        }
    }


    // 定义一个选中一个item后的处理
    public void setChioceItem(int index) {
        // 重置选项+隐藏所有Fragment
        bottomtransaction = fragmentManager.beginTransaction();
        setLinearlayoutBackground();
        hideFragments(bottomtransaction);
        switch (index) {
            case 0:
                // 全部
                goodText.setTextColor(Color
                        .parseColor(getString(R.color.RoseGold)));
                goodLinear.setVisibility(View.VISIBLE);
//                if (expendFragment == null) {
//                    // 如果fg1为空，则创建一个并添加到界面上
//                    expendFragment = ExpenditureFragment.newInstance();
//                    bottomtransaction.add(R.id.content, expendFragment);
//                } else {
//                    // 如果MessageFragment不为空，则直接将它显示出来
//                    bottomtransaction.show(expendFragment);
//                }

                expendFragment = ExpenditureFragment.newInstance();
                bottomtransaction.replace(R.id.content, expendFragment);

                break;

            case 1:
                // 待付款
                brandText.setTextColor(Color
                        .parseColor(getString(R.color.RoseGold)));
                linear.setVisibility(View.VISIBLE);
                incomeFragment = IncomeFragment.newInstance();
                bottomtransaction.replace(R.id.content, incomeFragment);
                break;


        }

        bottomtransaction.commit();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.root_head_back:
                finish();
                break;
            case R.id.my_collection_good:
                type = 0;
                setChioceItem(type);
                break;
            case R.id.my_collection_brand:
                type = 1;
                setChioceItem(type);
                break;
            case R.id.date_chose:
                DatePickerDialog datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        dataTv.setText(String.format("%d年%d月", year, (monthOfYear + 1)));
                        Year = year;
                        Month = monthOfYear + 1;
                        setChioceItem(type);

                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
                datePicker.show();
                break;

        }

    }
}
