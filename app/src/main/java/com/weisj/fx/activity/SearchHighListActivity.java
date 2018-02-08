package com.weisj.fx.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.adapter.ItemSearchHighGoodAdapter;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.bean.GoodBean;
import com.weisj.fx.bean.HomeBean;
import com.weisj.fx.presenter.SearchHighListPresenter;
import com.weisj.fx.view.abpullrefresh.AbPullToRefreshView;
import com.weisj.fx.viewinterface.ISearchHighListView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017-07-10.
 */

public class SearchHighListActivity extends BaseActivity implements View.OnClickListener, AbPullToRefreshView.OnHeaderRefreshListener, AbPullToRefreshView.OnFooterLoadListener, ISearchHighListView {
    private TextView synthesizeText, priceText, discountText;
    private ImageView priceImageUp, priceImageDown, discountImageUp, discountImageDown;
    private int lastState = 0;
    private int upOrDown = 0;// 0降，1升
    private ListView listView;
    private AbPullToRefreshView refreshView;
    private SearchHighListPresenter presenter;
    private List<HomeBean.DataEntity.DistrictGoodsListEntity> list = new ArrayList<>();
    private ItemSearchHighGoodAdapter adapter;

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_search_high_list, null);
        initView(view);
        presenter = new SearchHighListPresenter(this, this);
        presenter.getInitData();
        return view;
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listview);
        refreshView = (AbPullToRefreshView) view.findViewById(R.id.refresh_view);
        refreshView.setOnFooterLoadListener(this);
        refreshView.setOnHeaderRefreshListener(this);
        view.findViewById(R.id.choose_synthesize).setOnClickListener(this);
        view.findViewById(R.id.choose_price).setOnClickListener(this);
        view.findViewById(R.id.choose_discount).setOnClickListener(this);
        listView.setEmptyView(View.inflate(this, R.layout.view_empty, null));

        synthesizeText = (TextView) view.findViewById(R.id.choose_synthesize);
        priceText = (TextView) view.findViewById(R.id.choose_price_text);
        discountText = (TextView) view.findViewById(R.id.choose_discount_text);

        priceImageUp = (ImageView) view.findViewById(R.id.choose_price_up);
        priceImageDown = (ImageView) view.findViewById(R.id.choose_price_down);
        discountImageUp = (ImageView) view.findViewById(R.id.choose_discount_up);
        discountImageDown = (ImageView) view.findViewById(R.id.choose_discount_down);

        synthesizeText.setSelected(true);
    }

    @Override
    public String setTitleStr() {
        return "高计提专区";
    }

    @Override
    public void getRefreshData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choose_discount:// 折扣
                selectNumber(3);
                break;
            case R.id.choose_price:// 价格
                selectNumber(1);
                break;
            case R.id.choose_synthesize:// 综合
                selectNumber(0);
                break;
        }
    }

    // 关闭上次选中
    private void closeLastSelect() {
        switch (lastState) {
            case 0:// 综合
                synthesizeText.setSelected(false);
                break;
            case 1:// 价格
                priceText.setSelected(false);
                priceImageUp.setSelected(false);
                priceImageDown.setSelected(false);
                break;
            case 3:// 折扣
                discountText.setSelected(false);
                discountImageUp.setSelected(false);
                discountImageDown.setSelected(false);
                break;
        }
    }

    // 选中
    private void selectNumber(int number) {
        if (lastState != number) {
            closeLastSelect();
        }
        openSelect(number);
        lastState = number;
        presenter.getInitData();
    }

    // 打开这次选中
    private void openSelect(int state) {
        switch (state) {
            case 0:// 综合
                synthesizeText.setSelected(true);
                break;
            case 1:// 价格
                if (!priceText.isSelected()) {
                    priceText.setSelected(true);
                    priceImageUp.setSelected(true);
                    priceImageDown.setSelected(false);
                    upOrDown = 1;
                } else {
                    priceImageUp.setSelected(!priceImageUp.isSelected());
                    priceImageDown.setSelected(!priceImageDown.isSelected());
                    upOrDown = priceImageUp.isSelected() ? 1 : 0;
                }
                break;
            case 3:// 折扣
                if (!discountText.isSelected()) {
                    discountText.setSelected(true);
                    discountImageUp.setSelected(false);
                    discountImageDown.setSelected(true);
                    upOrDown = 0;
                } else {
                    discountImageUp.setSelected(!discountImageUp.isSelected());
                    discountImageDown.setSelected(!discountImageDown.isSelected());
                    upOrDown = discountImageUp.isSelected() ? 1 : 0;
                }
                break;

        }
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
    public int getOrderField() {
        return lastState;
    }

    @Override
    public int getOrderType() {
        return upOrDown;
    }

    @Override
    public void getData(GoodBean goodBean) {
        list.addAll(goodBean.getData());
        adapter.notifyDataSetChanged();
        refreshView.onFooterLoadFinish();
    }

    @Override
    public void getInitData(GoodBean goodBean) {
        list.clear();
        list.addAll(goodBean.getData());
        if (adapter == null) {
            adapter = new ItemSearchHighGoodAdapter(this, list);
            listView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        refreshView.onHeaderRefreshFinish();
    }
}
