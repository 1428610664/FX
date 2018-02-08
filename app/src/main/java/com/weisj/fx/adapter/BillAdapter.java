package com.weisj.fx.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.weisj.fx.R;
import com.weisj.fx.bean.AccountBillBean;
import com.weisj.fx.utils.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BBMJ on 2016/1/18.
 */
public class BillAdapter extends BaseAdapter{


    Activity activity;

    List<AccountBillBean.DataEntity> list = new ArrayList<AccountBillBean.DataEntity>();


    public BillAdapter(Activity activity, List<AccountBillBean.DataEntity> list){
        this.activity =activity;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyViewHoler holer = null;

        if (convertView == null){
            convertView = LayoutInflater.from(activity).inflate(R.layout.item_bill, parent, false);//填充这个item布局
            holer = new MyViewHoler(convertView);
            convertView.setTag(holer);
        }else {
            holer = (MyViewHoler) convertView.getTag();
        }


        AccountBillBean.DataEntity dataEntity = list.get(position);

        holer.billTitle.setText(dataEntity.getType());
        holer.billTime.setText(dataEntity.getWithdraw_time());
        holer.billValue.setText("￥"+dataEntity.getMoney());


        holer.billId.setVisibility(View.VISIBLE);
        switch (dataEntity.getType()){

            case "红包":
                holer.billId.setVisibility(View.GONE);
                holer.bill_product.setVisibility(View.GONE);
                break;
            case "佣金":
                holer.billId.setText("订单号:" + dataEntity.getOrder_info_sn());
                holer.bill_product.setVisibility(View.VISIBLE);
                holer.billName.setText(dataEntity.getGoods_name());
                holer.billPrice.setText("￥"+dataEntity.getPrice());

                ImageLoaderUtils.getInstance().display(holer.billPic,dataEntity.getGoods_pic());
                break;
            case "充值":
                holer.billId.setText("交易号:" + dataEntity.getTrade_no());
                holer.bill_product.setVisibility(View.GONE);
                break;
            case "提现":
                holer.billId.setText(dataEntity.getStatus());
                holer.bill_product.setVisibility(View.GONE);
                break;
            case "签到":
                holer.billId.setVisibility(View.GONE);
                holer.bill_product.setVisibility(View.GONE);
                break;
            case "支付":
                holer.billId.setText("交易号:" + dataEntity.getTrade_no());
                holer.bill_product.setVisibility(View.GONE);
                break;

            case "订单退款":
                holer.billId.setText("订单号:" + dataEntity.getOrder_info_sn());
                holer.bill_product.setVisibility(View.VISIBLE);
                holer.billName.setText(dataEntity.getGoods_name());
                holer.billPrice.setText("￥"+dataEntity.getPrice());

                ImageLoaderUtils.getInstance().display(holer.billPic,dataEntity.getGoods_pic());
                break;

            case "一元购退款":
                holer.billId.setText("订单号:" + dataEntity.getPeriods_no());
                holer.bill_product.setVisibility(View.VISIBLE);
                holer.billName.setText(dataEntity.getYyg_spec_name());
                holer.billPrice.setText("￥"+dataEntity.getPrice());

                ImageLoaderUtils.getInstance().display(holer.billPic,dataEntity.getYyg_spec_pic());
                break;

        }


        return convertView;
    }


    static class MyViewHoler extends RecyclerView.ViewHolder {
        View view;

        TextView billTitle,billId,billTime,billValue,billName,billPrice;
        ImageView billPic;

        RelativeLayout bill_product;

        public MyViewHoler(View itemView) {
            super(itemView);
            view = itemView;


            billTitle = (TextView)itemView.findViewById(R.id.bill_title);
            billValue = (TextView)itemView.findViewById(R.id.bill_value);
            billTime = (TextView)itemView.findViewById(R.id.bill_time);


            billId = (TextView)itemView.findViewById(R.id.bill_id);
            billName = (TextView)itemView.findViewById(R.id.bill_name);
            billPrice = (TextView)itemView.findViewById(R.id.bill_price);
            billPic= (ImageView)itemView.findViewById(R.id.bill_pic);

            bill_product = (RelativeLayout)itemView.findViewById(R.id.bill_product);


        }
    }
}
