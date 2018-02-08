package com.weisj.fx.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.weisj.fx.R;
import com.weisj.fx.adapter.ItemJcPartRecordAdapter;
import com.weisj.fx.adapter.ItemJcRecordAdapter;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.bean.ActiBean;
import com.weisj.fx.bean.OneShareBean;
import com.weisj.fx.bean.ShareActiBean;
import com.weisj.fx.presenter.ListViewPresenter;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.view.abpullrefresh.AbPullToRefreshView;
import com.weisj.fx.viewinterface.IListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public class ListViewActivity extends BaseActivity implements AbPullToRefreshView.OnFooterLoadListener, AbPullToRefreshView.OnHeaderRefreshListener, IListView {
    private ListView listView;
    private AbPullToRefreshView refreshView;
    private int state;
    private List<ShareActiBean.DataBean> list = new ArrayList<>();
    private ListViewPresenter presenter;
    private ItemJcRecordAdapter jcRecordAdapter;
    private ItemJcPartRecordAdapter jcPartRecordAdapter;
    private List<OneShareBean.DataBean> oneList = new ArrayList<>();

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_list_view, null);
        state = getIntent().getIntExtra("listState", 0);
        presenter = new ListViewPresenter(this, this);
        initView(view);
        presenter.getData();
        return view;
    }


    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listview);
        refreshView = (AbPullToRefreshView) view.findViewById(R.id.refresh_view);
        refreshView.setOnFooterLoadListener(this);
        refreshView.setLoadMoreEnable(false);
        refreshView.setOnHeaderRefreshListener(this);
        if (state == 0) {
            jcRecordAdapter = new ItemJcRecordAdapter(this, list);
            listView.setAdapter(jcRecordAdapter);
            listView.setDividerHeight(SystemConfig.getScale(20));
        } else {
            jcPartRecordAdapter = new ItemJcPartRecordAdapter(this, oneList);
            listView.setAdapter(jcPartRecordAdapter);
            listView.setDividerHeight(0);
            refreshView.setLoadMoreEnable(true);
        }
    }

    @Override
    public String setTitleStr() {
        return state == 0 ? "竞猜记录" : "参与记录";
    }

    @Override
    public void getRefreshData() {
        presenter.getData();
    }

    @Override
    public void onFooterLoad(AbPullToRefreshView view) {
        presenter.getMoreData();
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        presenter.getData();
    }

    @Override
    public void getJCRecordData(ShareActiBean actiBean) {
        refreshView.onHeaderRefreshFinish();
        list.clear();
        list.addAll(actiBean.getData());
        jcRecordAdapter.notifyDataSetChanged();
    }

    @Override
    public void getJCPartData(OneShareBean oneShareBean) {
        refreshView.onHeaderRefreshFinish();
        oneList.clear();
        oneList.addAll(oneShareBean.getData());
        jcPartRecordAdapter.notifyDataSetChanged();
    }

    @Override
    public void getMoreJcPartData(OneShareBean oneShareBean) {
        oneList.addAll(oneShareBean.getData());
        jcPartRecordAdapter.notifyDataSetChanged();
        refreshView.onFooterLoadFinish();
    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public int getActivityId() {
        return getIntent().getIntExtra("activity_id", 0);
    }

    @Override
    public void getMoreFail() {
        refreshView.onFooterLoadFinish();
        Toast.makeText(this, "没有更多数据", Toast.LENGTH_SHORT).show();
    }
}
