package com.weisj.fx.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;

import com.weisj.fx.R;
import com.weisj.fx.adapter.ItemGoodPointAdapter;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.bean.GoodPoint;
import com.weisj.fx.presenter.GoodPointPresenter;
import com.weisj.fx.view.abpullrefresh.AbPullToRefreshView;
import com.weisj.fx.viewinterface.IGoodPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/2 0002.
 */

public class GoodPointActivity extends BaseActivity implements IGoodPoint, AbPullToRefreshView.OnFooterLoadListener, AbPullToRefreshView.OnHeaderRefreshListener {
    private ListView listView;
    private List<GoodPoint.DataBean> list = new ArrayList<>();
    private ItemGoodPointAdapter goodPointAdapter;
    private GoodPointPresenter pointPresenter;
    private AbPullToRefreshView refreshView;
    private int index = 1;
    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_good_point,null);
        initView(view);
        pointPresenter = new GoodPointPresenter(this,this);
        pointPresenter.getInitData();
        return view;
    }


    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listview);
        refreshView = (AbPullToRefreshView) view.findViewById(R.id.refresh_view);
        refreshView.setOnFooterLoadListener(this);
        refreshView.setOnHeaderRefreshListener(this);
    }

    @Override
    public String setTitleStr() {
        return "商品评价";
    }

    @Override
    public void getRefreshData() {

    }

    @Override
    public int getGoodId() {
        return getIntent().getIntExtra("good_id",0);
    }

    @Override
    public void getInitData(GoodPoint goodPoint) {
        list.clear();
        list.addAll(goodPoint.getData());
        if (goodPointAdapter == null){
            goodPointAdapter = new ItemGoodPointAdapter(this,list);
            listView.setAdapter(goodPointAdapter);
        }
        goodPointAdapter.notifyDataSetChanged();
        refreshView.onHeaderRefreshFinish();
    }

    @Override
    public void getData(GoodPoint goodPoint) {
        list.addAll(goodPoint.getData());
        goodPointAdapter.notifyDataSetChanged();
        refreshView.onFooterLoadFinish();
    }

    @Override
    public void getFail() {
        refreshView.onFooterLoadFinish();
        refreshView.onHeaderRefreshFinish();
    }

    @Override
    public void onFooterLoad(AbPullToRefreshView view) {
        index++;
        pointPresenter.getData(index);
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        index = 1;
        pointPresenter.getData(index);
    }
}
