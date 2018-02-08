package com.weisj.fx.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.okhttp.Request;
import com.weisj.fx.R;
import com.weisj.fx.activity.ConversionrRateActivity;
import com.weisj.fx.activity.SystemNoticeActivity;
import com.weisj.fx.adapter.ItemOrderFragmentAdapter;
import com.weisj.fx.base.BaseFragment;
import com.weisj.fx.bean.BaseBean;
import com.weisj.fx.main.fragment.order.DistributionCommissionView;
import com.weisj.fx.main.fragment.order.DistributionRecordView;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.view.FiltratePopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 16/6/21.
 * 订单主页
 */
public class OrderFragment extends BaseFragment implements View.OnClickListener, ViewPager.OnPageChangeListener, FiltratePopupWindow.FiltrateListener {
    List<View> list = new ArrayList<>();
    private View view, leftBar, rightBar, topImage, filtrateBt;
    private ViewPager viewPager;
    private DistributionCommissionView commissionView;
    private DistributionRecordView recordView;
    private FiltratePopupWindow popupWindow;
    private BaseBean baseBean;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order, null);
        this.view = view;
        rootView.isHintHeadBar(true);
        leftBar = view.findViewById(R.id.order_left_bar);
        leftBar.setSelected(true);
        rightBar = view.findViewById(R.id.order_right_bar);
        topImage = view.findViewById(R.id.order_top_image);
        filtrateBt = view.findViewById(R.id.order_filtrate_bt);
        topImage.setSelected(true);
        topImage.setOnClickListener(this);
        rightBar.setOnClickListener(this);
        filtrateBt.setOnClickListener(this);
        leftBar.setOnClickListener(this);
        topImage.setVisibility(View.GONE);
        filtrateBt.setVisibility(View.VISIBLE);
        initView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        hid();
        commissionView.getdata();
        recordView.getdata();
    }

    private void initView() {
        commissionView = new DistributionCommissionView(mInflater);
        recordView = new DistributionRecordView(mInflater);
        list.add(commissionView.getRootView());

//        if (baseBean.getData().equals("0")) {
        list.add(recordView.getRootView());
//        } else {
//            rightBar.setVisibility(View.GONE);
//        }
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new ItemOrderFragmentAdapter(list));
        viewPager.addOnPageChangeListener(this);


        //

    }

    @Override
    public String setTitleStr() {
        return "订单";
    }

    @Override
    public void getRefreshData() {
        if (viewPager.getCurrentItem() == 0) {
            commissionView.getdata();
        } else {
            recordView.getdata();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_left_bar:
                if (viewPager != null)
                    viewPager.setCurrentItem(0);
                break;
            case R.id.order_right_bar:
                if (viewPager != null)
                    viewPager.setCurrentItem(1);
                break;
            case R.id.order_top_image: // 右上角图片点击
                if (viewPager != null)
                    if (viewPager.getCurrentItem() == 1) {
                        startActivity(new Intent(this.getContext(), SystemNoticeActivity.class));
                    } else {
                        startActivity(new Intent(this.getContext(), ConversionrRateActivity.class));
                    }
                break;
            case R.id.order_filtrate_bt:
                if (popupWindow == null) {
                    popupWindow = new FiltratePopupWindow(this.getContext(),this);
                }
                popupWindow.show(this.getActivity());
                break;
        }
    }

    private void changeBarState(int state) {
        if (state == 0) {
            topImage.setVisibility(View.GONE);
            filtrateBt.setVisibility(View.VISIBLE);
        } else {
            topImage.setVisibility(View.VISIBLE);
            filtrateBt.setVisibility(View.GONE);
        }
        leftBar.setSelected(state == 0);
        rightBar.setSelected(state == 1);
        topImage.setSelected(state == 0);
    }


    private void hid() {

        showLoading();
        OkHttpClientManager.postAsyn("http://shop.fx-sf.com/fxfront/interface/getstatus?appid=APPID", new OkHttpClientManager.ResultCallback<BaseBean>() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(BaseBean response) {
                showLoadFinish();
                baseBean = response;
                initView();
                commissionView.getdata();
                recordView.getdata();
            }
        });

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        changeBarState(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSure(String wxName, int filter_type) {
        if (commissionView != null){
            commissionView.setWxAndFilterType(filter_type,wxName);
        }
    }
}
