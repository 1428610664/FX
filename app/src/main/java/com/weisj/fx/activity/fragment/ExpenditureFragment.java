package com.weisj.fx.activity.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.weisj.fx.R;
import com.weisj.fx.activity.BillActivity;
import com.weisj.fx.adapter.BillAdapter;
import com.weisj.fx.bean.AccountBillBean;
import com.weisj.fx.utils.OkHttpClientManager;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.Urls;
import com.weisj.fx.view.MyListView;
import com.weisj.fx.view.RootView;
import com.weisj.fx.view.abpullrefresh.AbPullToRefreshView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BBMJ on 2016/1/27.
 */
public class ExpenditureFragment extends Fragment implements AbPullToRefreshView.OnFooterLoadListener, AbPullToRefreshView.OnHeaderRefreshListener {


    private View baseView;
    MyListView listView;
    AbPullToRefreshView abPullToRefreshView;
    BillAdapter adapter;
    private RootView rootView;

    List<AccountBillBean.DataEntity> dataEntities = new ArrayList<AccountBillBean.DataEntity>();

    int page = 1;

    public static Fragment newInstance() {
        ExpenditureFragment f = new ExpenditureFragment();
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_order_2, null);

        abPullToRefreshView = (AbPullToRefreshView) baseView.findViewById(R.id.pull_refresh_view);
        abPullToRefreshView.setOnFooterLoadListener(this);
        abPullToRefreshView.setOnHeaderRefreshListener(this);
        listView = (MyListView) baseView.findViewById(R.id.list);
        rootView = new RootView(getActivity());
        rootView.addContentView(baseView);
        rootView.isHintHeadBar(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Getaccountbill();
    }

    /**
     * 获取数据
     */
    private void Getaccountbill() {

        if (page == 1) {
            rootView.changeRootViewState(RootView.ViewState.LOADING);
        }

        Map<String, String> params = new HashMap<>();
        params.put("member_id", PersonMessagePreferencesUtils.getUid());
        params.put("year", String.valueOf(BillActivity.Year));
        params.put("month", String.valueOf(BillActivity.Month));
        params.put("type", "0");
        params.put("page", String.valueOf(page));
        params.put("page_num", "10");


        OkHttpClientManager.postAsyn(Urls.getaccountbill, params, new OkHttpClientManager.ResultCallback<AccountBillBean>() {
            @Override
            public void onError(Request request, Exception e) {
                try {
                    rootView.changeRootViewState(RootView.ViewState.NONETWORK);
                } catch (Exception e1) {

                }
            }

            @Override
            public void onResponse(AccountBillBean response) {
                rootView.changeRootViewState(RootView.ViewState.SUCCESS);
                try {
                    if (response.getCode().equals("1") && response != null) {
                        if (page == 1) {
                            abPullToRefreshView.setLoadMoreEnable(true);
                            dataEntities = response.getData();
                            adapter = new BillAdapter(getActivity(), dataEntities);
                            listView.setAdapter(adapter);
                        } else {
                            dataEntities.addAll(response.getData());
                            adapter.notifyDataSetChanged();
                        }
                    } else if (page > 1) {
                        abPullToRefreshView.setLoadMoreEnable(false);
                        Toast.makeText(getActivity(), "数据加载完毕", Toast.LENGTH_SHORT).show();
                    } else {
                        rootView.changeRootViewState(RootView.ViewState.NODATA);
                    }
                } catch (Exception e) {

                }

            }
        });
    }


    @Override
    public void onFooterLoad(AbPullToRefreshView view) {
        abPullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {

                page++;
                Getaccountbill();
                abPullToRefreshView.onFooterLoadFinish();

            }
        }, 1000);
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        abPullToRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {

                page = 1;
                Getaccountbill();
                abPullToRefreshView.onHeaderRefreshFinish();

            }
        }, 1000);
    }


}
