package com.weisj.fx.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.bean.Region;
import com.weisj.fx.bean.SearchBrand;

import java.util.List;

/**
 * Created by Administrator on 2016/9/14 0014.
 */
public class ItemSearchRightAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<Region.DataEntity> regionList;
    private List<SearchBrand.DataEntity> brandList;
    private int selectNumber;

    public void setSelectNumber(int selectNumber) {
        this.selectNumber = selectNumber;
    }

    public ItemSearchRightAdapter(Context context, List<SearchBrand.DataEntity> brandList, List<Region.DataEntity> regionList) {
        this.brandList = brandList;
        this.context = context;
        this.regionList = regionList;
    }

    @Override
    public int getGroupCount() {
        return 2;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (groupPosition == 0) {
            return regionList.size();
        } else {
            return brandList.size();
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (groupPosition == 0) {
            return regionList.get(childPosition).getDistrictName();
        } else {
            return brandList.get(childPosition).getBrandName();
        }
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.item_right_title, null);
        ((TextView) view.findViewById(R.id.title)).setText(groupPosition == 0 ? "区域" : "品牌");
        ((ImageView) view.findViewById(R.id.icon)).setImageResource(isExpanded ? R.mipmap.icon_up : R.mipmap.icon_down);
        view.findViewById(R.id.direct_name).setVisibility(groupPosition == 0 ? View.VISIBLE : View.GONE);
        if (regionList.size() > 0) {
            ((TextView) view.findViewById(R.id.direct_name)).setText(regionList.get(selectNumber).getDistrictName());
        } else {
            ((TextView) view.findViewById(R.id.direct_name)).setText("所有区域");
        }
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.item_right_child, null);
        ((TextView) view.findViewById(R.id.text)).setText((String) getChild(groupPosition, childPosition));
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
