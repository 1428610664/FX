package com.weisj.fx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.weisj.fx.R;
import com.weisj.fx.adapter.ItemNoticeAdapter;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.bean.NoticeBean;
import com.weisj.fx.presenter.NoticePresenter;
import com.weisj.fx.view.abpullrefresh.AbPullToRefreshView;
import com.weisj.fx.viewinterface.INoticeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/30 0030.
 */
public class SystemNoticeActivity extends BaseActivity implements INoticeView, AbPullToRefreshView.OnHeaderRefreshListener, AbPullToRefreshView.OnFooterLoadListener, AdapterView.OnItemClickListener {
    private List<NoticeBean.DataEntity> list = new ArrayList<>();
    private AbPullToRefreshView refreshView;
    private ListView listView;
    private ItemNoticeAdapter adapter;
    private NoticePresenter presenter;

    private void initView(View view) {
        refreshView = (AbPullToRefreshView) view.findViewById(R.id.refresh_view);
        listView = (ListView) view.findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
        presenter = new NoticePresenter(this, this);
        presenter.getInitData();
        refreshView.setOnFooterLoadListener(this);
        refreshView.setOnHeaderRefreshListener(this);
    }


    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_notice, null);
        initView(view);
        return view;
    }

    @Override
    public String setTitleStr() {
        return "公告";
    }

    @Override
    public void getRefreshData() {
        presenter.getInitData();
    }

    @Override
    public void getData(NoticeBean noticeBean) {
        list.addAll(noticeBean.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getInitData(NoticeBean noticeBean) {
        list.clear();
        list.addAll(noticeBean.getData());
        if (adapter == null) {
            adapter = new ItemNoticeAdapter(this, list);
            listView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        refreshView.onHeaderRefreshFinish();
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        presenter.getInitData();
    }

    @Override
    public void onFooterLoad(AbPullToRefreshView view) {
        presenter.getData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NoticeBean.DataEntity dataEntity = list.get(position);
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("web_title", dataEntity.getTitle());
        intent.putExtra("url", dataEntity.getWebsibe());

        Toast.makeText(this, dataEntity.getWebsibe(), Toast.LENGTH_SHORT).show();
        this.startActivity(intent);
    }
}
