package com.weisj.fx.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.activity.GoodDetailActivity;
import com.weisj.fx.activity.SearchListActivity;
import com.weisj.fx.adapter.ItemCategoryGoodAdapter;
import com.weisj.fx.adapter.ItemCategoryTextAdapter;
import com.weisj.fx.base.BaseFragment;
import com.weisj.fx.bean.CategoryBean;
import com.weisj.fx.presenter.CategoryPresenter;
import com.weisj.fx.utils.L;
import com.weisj.fx.viewinterface.ICategoryView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zh on 16/6/21.
 * 品类首页
 */
public class CategoryFragment extends BaseFragment implements AdapterView.OnItemClickListener, ICategoryView {
    private ListView listView;
    private GridView gridView;
    private TextView failView;
    private CategoryPresenter presenter;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, null);
        presenter = new CategoryPresenter(this, this);
        initView(view);
        presenter.getTitleList();
        return view;
    }

    private void initView(View view) {
        listView = (ListView) view.findViewById(R.id.listview);
        gridView = (GridView) view.findViewById(R.id.grid_view);
        failView = (TextView) view.findViewById(R.id.load_fail);
    }

    @Override
    public String setTitleStr() {
        return "分类";
    }

    @Override
    public void getRefreshData() {
        presenter.getTitleList();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getAdapter() instanceof ItemCategoryTextAdapter) {
            ItemCategoryTextAdapter itemCategoryTextAdapter = (ItemCategoryTextAdapter) parent.getAdapter();
            CategoryBean.DataEntity dataEntity = (CategoryBean.DataEntity) itemCategoryTextAdapter.getItem(position);
            itemCategoryTextAdapter.setSelectId(dataEntity.getCategoryId());
            presenter.getContentList(dataEntity.getCategoryId());
            itemCategoryTextAdapter.notifyDataSetChanged();
        } else if (parent.getAdapter() instanceof ItemCategoryGoodAdapter) {
            ItemCategoryGoodAdapter itemCategoryGoodAdapter = (ItemCategoryGoodAdapter) parent.getAdapter();
            CategoryBean.DataEntity dataEntity = (CategoryBean.DataEntity) itemCategoryGoodAdapter.getItem(position);
            Intent intent = new Intent(this.getContext(), SearchListActivity.class);
            intent.putExtra("categoryId", dataEntity.getCategoryId());
            startActivity(intent);
        }
    }

    @Override
    public void getTitle(CategoryBean categoryTitleBean) {
        listView.setAdapter(new ItemCategoryTextAdapter(this.getContext(), categoryTitleBean.getData()));
        listView.setOnItemClickListener(this);
        if (categoryTitleBean.getData() != null && categoryTitleBean.getData().size() > 0) {
            presenter.getContentList(categoryTitleBean.getData().get(0).getCategoryId());
        }
    }

    @Override
    public void getContent(CategoryBean categoryContentBean) {
        failView.setVisibility(View.GONE);
        gridView.setAdapter(new ItemCategoryGoodAdapter(this.getContext(), categoryContentBean.getData()));
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void cateFail() {
        failView.setVisibility(View.VISIBLE);
    }
}
