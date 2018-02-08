package com.weisj.fx.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.activity.GoodDetailActivity;
import com.weisj.fx.bean.GoodDetail;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.TextViewUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/8/1 0001.
 */
public class ItemGoodDetailRecommendAdapter<T> extends RecyclerView.Adapter<ItemGoodDetailRecommendAdapter.MyViewHolder> implements View.OnClickListener {
    private Context context;
    private List<T> list;

    public ItemGoodDetailRecommendAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_good_detail_recommend, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (list.get(0) instanceof GoodDetail.DataEntity.GoodsListEntity) {
            GoodDetail.DataEntity.GoodsListEntity goodsListEntity = (GoodDetail.DataEntity.GoodsListEntity) list.get(position);
            ImageLoaderUtils.getInstance().display(holder.image, goodsListEntity.getImg1());
            TextViewUtils.setText(holder.goodName, goodsListEntity.getGoodsName());
            TextViewUtils.setTextAndleftOther(holder.goodPrice, goodsListEntity.getPrice(),"￥");
            holder.view.setOnClickListener(this);
            holder.view.setTag(goodsListEntity);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        GoodDetail.DataEntity.GoodsListEntity goodsListEntity = (GoodDetail.DataEntity.GoodsListEntity) v.getTag();
        Intent intent = new Intent(context, GoodDetailActivity.class);
        intent.putExtra("goodId", goodsListEntity.getGoodsId());
        context.startActivity(intent);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView goodName;
        TextView goodPrice;
        View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            image = (ImageView) itemView.findViewById(R.id.image);
            goodName = (TextView) itemView.findViewById(R.id.good_name);
            goodPrice = (TextView) itemView.findViewById(R.id.good_price);
        }
    }
}
