package com.weisj.fx.activity;

import android.os.Bundle;
import android.view.View;

import com.weisj.fx.R;
import com.weisj.fx.adapter.ItemMyCollectionAdapter;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.view.swipelistview.SwipeListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class MyCollectionActivity extends BaseActivity {
    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_my_collection, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        SwipeListView swipeListView = (SwipeListView) view.findViewById(R.id.swipe_listview);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        swipeListView.setAdapter(new ItemMyCollectionAdapter(this, list));
    }

    @Override
    public String setTitleStr() {
        return "我的收藏";
    }

    @Override
    public void getRefreshData() {

    }
}
