package com.weisj.fx.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.adapter.ItemLogisticsContextAdapter;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.bean.LogisticsData;
import com.weisj.fx.presenter.LogisticsPresenter;
import com.weisj.fx.viewinterface.ILogisticsView;

/**
 * Created by Administrator on 2016/1/9 0009.
 * 查看物流界面
 */
public class CheckLogisticsActivity extends BaseActivity implements ILogisticsView {
    private RecyclerView recyclerView;
    private TextView logisticsFrom;
    private TextView logisticsState;
    private TextView logisticsNumber;
    private LogisticsPresenter presenter;


    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_check_logistics, null);
        initView(view);
        return view;
    }

    @Override
    public String setTitleStr() {
        return "物流查询";
    }

    @Override
    public void getRefreshData() {
        presenter.getData();
    }

    private void loadData(LogisticsData response) throws Exception {
        if (!"success".equals(response.getMessage())) {
            return;
        }
        logisticsState.setText(response.getResult().getStateMsg());
        if (response.getResult().getDetails() != null) {
            recyclerView.setAdapter(new ItemLogisticsContextAdapter(response.getResult().getDetails(), this));
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    private void initView(View view) {
        logisticsFrom = (TextView) view.findViewById(R.id.logistics_from);
        logisticsState = (TextView) view.findViewById(R.id.logistics_state);
        logisticsNumber = (TextView) view.findViewById(R.id.logistics_number);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        int orderState = getIntent().getIntExtra("orderType", 0);
        if (orderState == 2 || orderState == 3) {
            presenter = new LogisticsPresenter(this, this);
            presenter.getData();
        } else {
            switch (orderState) {
                case 0:// 0等待买家待付款,1买家已付款,2卖家已发货，待买家收货,3交易成功, 5交易关闭
                    logisticsState.setText("待付款");
                    break;
                case 1:
                    logisticsState.setText("待发货");
                    break;
                case 5:
                    logisticsState.setText("交易取消");
                    break;
            }
        }
    }

    @Override
    public void getData(LogisticsData data) {
        try {
            loadData(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void getDataFail() {

    }

    @Override
    public int getOrderId() {
        return getIntent().getIntExtra("orderId", 0);
    }

    @Override
    public void setSendCompany(String name, String nu) {
        logisticsFrom.setText("信息来源：" + name);
        logisticsNumber.setText("运单号:" + nu);
    }
}
