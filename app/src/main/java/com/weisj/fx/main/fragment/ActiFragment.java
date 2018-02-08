package com.weisj.fx.main.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.weisj.fx.R;
import com.weisj.fx.activity.GoodDetailActivity;
import com.weisj.fx.activity.WebViewActivity;
import com.weisj.fx.adapter.ItemCouponHeadAdapter;
import com.weisj.fx.base.BaseFragment;
import com.weisj.fx.bean.ActiBean;
import com.weisj.fx.bean.CouponBean;
import com.weisj.fx.bean.HomeCouponbean;
import com.weisj.fx.presenter.CouponPresenter;
import com.weisj.fx.utils.CommenString;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.view.abpullrefresh.AbPullToRefreshView;
import com.weisj.fx.viewinterface.ICouponView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/12/6 0006.
 */

public class ActiFragment extends BaseFragment implements AbPullToRefreshView.OnHeaderRefreshListener, ICouponView {
    private ListView listView;
    private CouponPresenter presenter;
    private AbPullToRefreshView refreshView;
    private LinearLayout activityLinear;
    private View couponView;
    private ItemCouponHeadAdapter adapter;
    private List<HomeCouponbean.DataEntity.SingleCouponListEntity> list = new ArrayList<>();
    private View headView, cjView;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_acti, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        refreshView = (AbPullToRefreshView) view.findViewById(R.id.refresh_view);
        refreshView.setOnHeaderRefreshListener(this);
        refreshView.setLoadMoreEnable(false);
        listView = (ListView) view.findViewById(R.id.listview);
        headView = mInflater.inflate(R.layout.head_acti, null);
        activityLinear = (LinearLayout) headView.findViewById(R.id.linear);
        couponView = headView.findViewById(R.id.coupon_text);
        cjView = headView.findViewById(R.id.activity_cj);
        listView.addHeaderView(headView);
        listView.setEmptyView(mInflater.inflate(R.layout.error_view, null));
        presenter = new CouponPresenter(this, this);
        presenter.getData(CommenString.selectCity, true);
        this.adapter = new ItemCouponHeadAdapter(this.getContext(), list);
        listView.setAdapter(this.adapter);
    }

    @NonNull
    @Override
    public String setTitleStr() {
        return "活动";
    }

    @Override
    public void getRefreshData() {
        presenter.getData(CommenString.selectCity, true);
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        presenter.getData(CommenString.selectCity, false);
    }

    @Override
    public void getInitData(HomeCouponbean homeCouponbean) {
        list.clear();
        list.addAll(homeCouponbean.getData().getDistrictCouponList());
        if (this.adapter == null) {
            this.adapter = new ItemCouponHeadAdapter(this.getContext(), list);
            listView.setAdapter(this.adapter);
        } else {
            this.adapter.notifyDataSetChanged();
        }
        refreshView.onHeaderRefreshFinish();
        couponView.setVisibility(list.size() == 0 ? View.GONE : View.VISIBLE);
    }

    @Override
    public void getData(CouponBean couponList) {

    }

    @Override
    public void getInitData(CouponBean couponList) {

    }

    @Override
    public void getCouponFail() {
        couponView.setVisibility(View.GONE);
    }

    @Override
    public void getActiFail() {
        listView.removeHeaderView(headView);
    }

    @Override
    public void getActiData(ActiBean actiBean) {
        listView.removeHeaderView(headView);
        activityLinear.removeAllViews();
        if (actiBean.getData() != null) {
            listView.addHeaderView(headView);
            if (actiBean.getData().size() > 0) {
                cjView.setVisibility(View.VISIBLE);
            }
            for (int i = 0; i < actiBean.getData().size(); i++) {
                addActiImage(actiBean.getData().get(i));
            }
        }
    }

    private void addActiImage(ActiBean.DataBean data) {
        ImageView image = new ImageView(getContext());
        activityLinear.addView(image);
        SystemConfig.dynamicSetWidthAndHeight(image, -1, 450, 0, 20, 0, 0);
        image.setTag(data);
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageLoaderUtils.getInstance().display(image, data.getBannerPic(), new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                int width = loadedImage.getWidth();
                int height = loadedImage.getHeight();
                SystemConfig.dynamicSetWidthAndHeight(view, -1, 750 * height / width, 0, 20, 0, 0);
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActiBean.DataBean data = (ActiBean.DataBean) v.getTag();
                Intent intent = new Intent(getContext(), GoodDetailActivity.class);
                intent.putExtra("goodId", data.getGoodsId());
                intent.putExtra("activity_id", data.getGuessActivityId());
                intent.putExtra("good_state", 1);
                getActivity().startActivity(intent);
            }
        });
    }
}
