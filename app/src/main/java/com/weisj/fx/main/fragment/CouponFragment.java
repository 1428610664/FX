package com.weisj.fx.main.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.adapter.ItemCouponAdapter;
import com.weisj.fx.adapter.ItemCouponCategoryAdapter;
import com.weisj.fx.adapter.ItemCouponHeadAdapter;
import com.weisj.fx.base.BaseFragment;
import com.weisj.fx.bean.ActiBean;
import com.weisj.fx.bean.CouponBean;
import com.weisj.fx.bean.HomeCouponbean;
import com.weisj.fx.presenter.CouponPresenter;
import com.weisj.fx.utils.CommenString;
import com.weisj.fx.utils.ViewStateUtil;
import com.weisj.fx.view.RootView;
import com.weisj.fx.view.abpullrefresh.AbPullToRefreshView;
import com.weisj.fx.viewinterface.ICouponView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by zh on 16/6/21.
 * 优惠券主页
 */
public class CouponFragment extends BaseFragment implements AbsListView.OnScrollListener, ICouponView, AbPullToRefreshView.OnHeaderRefreshListener, AbPullToRefreshView.OnFooterLoadListener {
    private LayoutInflater mInflater;
    private RelativeLayout couponBar;
    private RecyclerView recyclerView, recyclerView1;
    private ListView listView, headListView;
    private View stateView,coupon_no;
    private CouponPresenter presenter;
    private int dx1, dx2;
    private AbPullToRefreshView refreshView;
    private ItemCouponAdapter adapter;
    private List<HomeCouponbean.DataEntity.SingleCouponListEntity> list = new ArrayList<>();

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.mInflater = inflater;
        View view = inflater.inflate(R.layout.fragment_coupon, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        refreshView = (AbPullToRefreshView) view.findViewById(R.id.refresh_view);
        refreshView.setOnHeaderRefreshListener(this);
        refreshView.setOnFooterLoadListener(this);
        listView = (ListView) view.findViewById(R.id.listview);
        couponBar = (RelativeLayout) view.findViewById(R.id.coupon_bar_relative);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                dx1 += dx;
            }
        });
        couponBar.setVisibility(View.INVISIBLE);
        listView.setOnScrollListener(this);
        View headView = mInflater.inflate(R.layout.head_coupon, null);
        headListView = (ListView) headView.findViewById(R.id.listview);
        listView.addHeaderView(headView);
        presenter = new CouponPresenter(this, this);
        presenter.getData(CommenString.selectCity, true);
        coupon_no = view.findViewById(R.id.coupon_no);
    }

    public void getData(int state) {
        presenter.getInitData(state);
    }

    @Override
    public String setTitleStr() {
        return "活动";
    }

    @Override
    public void getRefreshData() {
        presenter.getData(CommenString.selectCity, true);
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem >= 1) {
            if (recyclerView1 != null) {
                recyclerView1.scrollBy(dx1 - dx2, 0);
            }
            couponBar.setVisibility(View.VISIBLE);
        } else {
            if (recyclerView1 != null) {
                recyclerView.scrollBy(dx2 - dx1, 0);
            }
            couponBar.setVisibility(View.GONE);
        }
    }


    @Override
    public void getInitData(HomeCouponbean homeCouponbean) {
        headListView.setAdapter(new ItemCouponHeadAdapter(this.getContext(), homeCouponbean.getData().getDistrictCouponList()));
        if (stateView == null) {
            stateView = mInflater.inflate(R.layout.include_coupon_choose_bar, null);
            recyclerView1 = (RecyclerView) stateView.findViewById(R.id.recyclerView);
            recyclerView1.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    dx2 += dx;
                }
            });
        }
        ItemCouponCategoryAdapter adapter = new ItemCouponCategoryAdapter(this.getContext(), homeCouponbean.getData().getCategoryList(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView1.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this.getContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView1.setLayoutManager(linearLayoutManager2);
        list.clear();
        list.addAll(homeCouponbean.getData().getSingleCouponList());
        if (this.adapter == null) {
            this.adapter = new ItemCouponAdapter(this.getContext(), list, stateView);
            listView.setAdapter(this.adapter);
        } else {
            this.adapter.notifyDataSetChanged();
        }
        refreshView.onHeaderRefreshFinish();
        if (list.size() == 0){
            coupon_no.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getData(CouponBean couponList) {
        if (couponList.getCode().equals("1") && couponList.getData() != null && couponList.getData().size() > 0) {
            list.addAll(couponList.getData());
            adapter.notifyDataSetChanged();
        }
        refreshView.onFooterLoadFinish();
    }

    @Override
    public void getInitData(CouponBean couponList) {
        list.clear();
        list.addAll(couponList.getData());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void getCouponFail() {

    }

    @Override
    public void getActiFail() {

    }

    @Override
    public void getActiData(ActiBean actiBean) {

    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        presenter.getData(CommenString.selectCity, false);
    }

    @Override
    public void onFooterLoad(AbPullToRefreshView view) {
        presenter.getData();
    }
}
