package com.weisj.fx.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.weisj.fx.R;
import com.weisj.fx.adapter.ItemFriendRecordAdapter;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.bean.FriendRecordBean;
import com.weisj.fx.presenter.FriendRecordPresenter;
import com.weisj.fx.view.abpullrefresh.AbPullToRefreshView;
import com.weisj.fx.viewinterface.IFriendRecordView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/5 0005.
 */
public class FriendRecordActivity extends BaseActivity implements IFriendRecordView, AbPullToRefreshView.OnHeaderRefreshListener {
    private ListView listView;
    private AbPullToRefreshView refreshView;
    private FriendRecordPresenter presenter;

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_friend_record, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listview);
        refreshView = (AbPullToRefreshView) view.findViewById(R.id.refresh_view);
        refreshView.setLoadMoreEnable(false);
        refreshView.setOnHeaderRefreshListener(this);
        presenter = new FriendRecordPresenter(this, this);
        presenter.getData();
    }

    @Override
    public String setTitleStr() {
        return "浏览记录";
    }

    @Override
    public void getRefreshData() {
        presenter.getData();
    }

    @Override
    public void getData(FriendRecordBean data) {
        listView.setAdapter(new ItemFriendRecordAdapter(this, data.getData()));
        refreshView.onHeaderRefreshFinish();
    }

    @Override
    public int getGoodId() {
        return getIntent().getIntExtra("goodId", 0);
    }

    @Override
    public int getCouponId() {
        return getIntent().getIntExtra("couponId", 0);
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        presenter.getData();
    }
}
