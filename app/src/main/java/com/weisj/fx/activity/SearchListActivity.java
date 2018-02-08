package com.weisj.fx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.adapter.ItemHomeGoodAdapter;
import com.weisj.fx.adapter.ItemSearchRightAdapter;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.bean.GoodBean;
import com.weisj.fx.bean.HomeBean;
import com.weisj.fx.bean.Region;
import com.weisj.fx.bean.SearchBrand;
import com.weisj.fx.presenter.SearchListPresenter;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.Urls;
import com.weisj.fx.view.abpullrefresh.AbPullToRefreshView;
import com.weisj.fx.viewinterface.ISearchListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class SearchListActivity extends BaseActivity implements View.OnClickListener, ISearchListView, AbPullToRefreshView.OnFooterLoadListener, AbPullToRefreshView.OnHeaderRefreshListener, AdapterView.OnItemClickListener, ExpandableListView.OnChildClickListener {
    private TextView synthesizeText, saleText, priceText, discountText;
    private ImageView saleImageUp, saleImageDown, priceImageUp, priceImageDown, discountImageUp, discountImageDown;
    private int lastState = 0;
    private int upOrDown = 0;// 0降，1升
    private ListView listView;
    private SearchListPresenter presenter;
    private List<HomeBean.DataEntity.DistrictGoodsListEntity> list = new ArrayList<>();
    private ItemHomeGoodAdapter adapter;
    private AbPullToRefreshView refreshView;
    private DrawerLayout drawerLayout;
    private ExpandableListView rightList;
    private List<Region.DataEntity> regionList = new ArrayList<>();
    private List<SearchBrand.DataEntity> brandList = new ArrayList<>();
    private ItemSearchRightAdapter rightAdapter;
    private int brandId, directId;
    private int from;

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_search_list, null);
        initView(view);
        from = getIntent().getIntExtra("from", 0);
        if (getIntent().getIntExtra("couponId", 0) > 0) {
            presenter = new SearchListPresenter(this, this, Urls.getgoodsbycouponyorder,from);
        } else {
            presenter = new SearchListPresenter(this, this,from);
        }
        presenter.getInitData();
        presenter.getRegions();
        presenter.getbrandbydistrict(0);
        return view;
    }

    @Override
    public void onRootViewClick(View v) {
        switch (v.getId()) {
            case R.id.root_head_right_text:
                drawerLayout.openDrawer(Gravity.END);
                break;
            case R.id.root_head_back:
                if (drawerLayout.isDrawerOpen(Gravity.END)) {
                    drawerLayout.closeDrawer(Gravity.END);
                } else {
                    finish();
                }
                break;
        }
    }

    private void initView(View view) {
        setRightText("筛选", true);
        listView = (ListView) view.findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
        refreshView = (AbPullToRefreshView) view.findViewById(R.id.refresh_view);
        refreshView.setOnFooterLoadListener(this);
        refreshView.setOnHeaderRefreshListener(this);
        view.findViewById(R.id.choose_synthesize).setOnClickListener(this);
        view.findViewById(R.id.choose_price).setOnClickListener(this);
        view.findViewById(R.id.choose_sales).setOnClickListener(this);
        view.findViewById(R.id.choose_discount).setOnClickListener(this);
        rightList = (ExpandableListView) view.findViewById(R.id.expand_list_view);
        rightAdapter = new ItemSearchRightAdapter(this, brandList, regionList);
        rightList.setAdapter(rightAdapter);
        rightList.setOnChildClickListener(this);
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawerlayout);
        rightList.setGroupIndicator(null);
        listView.setEmptyView(View.inflate(this, R.layout.view_empty, null));

        DrawerLayout.LayoutParams layoutParams = new DrawerLayout.LayoutParams(SystemConfig.getScreenWidth(), -1);
        layoutParams.gravity = Gravity.RIGHT;
        view.findViewById(R.id.right).setLayoutParams(layoutParams);
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                rootView.setHeadTitle("筛选");
                rootView.getRightText().setVisibility(View.GONE);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                rootView.setHeadTitle("商品");
                rootView.getRightText().setVisibility(View.VISIBLE);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });

        synthesizeText = (TextView) view.findViewById(R.id.choose_synthesize);
        saleText = (TextView) view.findViewById(R.id.choose_sales_text);
        priceText = (TextView) view.findViewById(R.id.choose_price_text);
        discountText = (TextView) view.findViewById(R.id.choose_discount_text);

        saleImageUp = (ImageView) view.findViewById(R.id.choose_sales_up);
        saleImageDown = (ImageView) view.findViewById(R.id.choose_sales_down);
        priceImageUp = (ImageView) view.findViewById(R.id.choose_price_up);
        priceImageDown = (ImageView) view.findViewById(R.id.choose_price_down);
        discountImageUp = (ImageView) view.findViewById(R.id.choose_discount_up);
        discountImageDown = (ImageView) view.findViewById(R.id.choose_discount_down);

        synthesizeText.setSelected(true);
    }

    @Override
    public String setTitleStr() {
        return "商品";
    }

    @Override
    public void getRefreshData() {
        presenter.getInitData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choose_discount:// 折扣
                selectNumber(3);
                break;
            case R.id.choose_sales:// 特卖
                selectNumber(2);
                break;
            case R.id.choose_price:// 价格
                selectNumber(1);
                break;
            case R.id.choose_synthesize:// 综合
                selectNumber(0);
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
            case 2:// 特卖
                if (!saleText.isSelected()) {
                    saleText.setSelected(true);
                    saleImageUp.setSelected(false);
                    saleImageDown.setSelected(true);
                    upOrDown = 0;
                } else {
                    saleImageUp.setSelected(!saleImageUp.isSelected());
                    saleImageDown.setSelected(!saleImageDown.isSelected());
                    upOrDown = saleImageUp.isSelected() ? 1 : 0;
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
            case 2:// 特卖
                saleText.setSelected(false);
                saleImageUp.setSelected(false);
                saleImageDown.setSelected(false);
                break;
            case 3:// 折扣
                discountText.setSelected(false);
                discountImageUp.setSelected(false);
                discountImageDown.setSelected(false);
                break;
        }
    }

    @Override
    public int getCategoryId() {
        return getIntent().getIntExtra("categoryId", 0);
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
    public int getBrandId() {
        return brandId;
    }

    @Override
    public int getDirectId() {
        return directId;
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
            adapter = new ItemHomeGoodAdapter(this, list);
            listView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        refreshView.onHeaderRefreshFinish();
    }

    @Override
    public void getRegions(Region region) {
        if (region.getCode().equals("1")) {
            regionList.clear();
            regionList.addAll(region.getData());
            rightAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getBrandsByRegion(SearchBrand searchBrand) {
        if (searchBrand.getCode().equals("1")) {
            brandList.clear();
            brandList.addAll(searchBrand.getData());
            rightAdapter.notifyDataSetChanged();
            rightList.expandGroup(1);
        }
    }

    @Override
    public String getGoodName() {
        return getIntent().getStringExtra("goodName");
    }

    @Override
    public void onFooterLoad(AbPullToRefreshView view) {
        presenter.getData();
    }

    @Override
    public void onHeaderRefresh(AbPullToRefreshView view) {
        presenter.getInitData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HomeBean.DataEntity.DistrictGoodsListEntity data = (HomeBean.DataEntity.DistrictGoodsListEntity) adapter.getItem(position);
        Intent intent = new Intent(this, GoodDetailActivity.class);
        intent.putExtra("goodId", data.getGoodsId());
        startActivity(intent);
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        if (groupPosition == 0) {
            Region.DataEntity dataEntity = regionList.get(childPosition);
            presenter.getbrandbydistrict(dataEntity.getDistrictId());
            rightAdapter.setSelectNumber(childPosition);
            rightAdapter.notifyDataSetChanged();
            directId = dataEntity.getDistrictId();
            rightList.collapseGroup(0);
        } else {
            SearchBrand.DataEntity searchBrand = brandList.get(childPosition);
            brandId = searchBrand.getBrandId();
            presenter.getInitData();
            drawerLayout.closeDrawer(Gravity.END);
        }
        return true;
    }
}
