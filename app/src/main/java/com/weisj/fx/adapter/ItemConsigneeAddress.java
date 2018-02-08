package com.weisj.fx.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.activity.ConsigneeAddressActivity;
import com.weisj.fx.activity.EditAddressActivity;
import com.weisj.fx.bean.AdressBean;

/**
 * Created by Administrator on 2015/12/17 0017.
 */
public class ItemConsigneeAddress extends RecyclerView.Adapter<ItemConsigneeAddress.MyViewHolder> {
    private Activity context;
    private AdressBean adressBean;

    ImageView oldChoose;

    public ItemConsigneeAddress(Activity context, AdressBean adressBean) {
        this.context = context;
        this.adressBean = adressBean;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_consignee_address, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        AdressBean.DataEntity dataEntity = adressBean.getData().get(position);

        holder.consignee_name.setText(dataEntity.getRecipients());
        holder.consignee_phone.setText(dataEntity.getPhone());
        holder.consignee_address.setText(dataEntity.getProvince() + dataEntity.getCity() + dataEntity.getArea() + dataEntity.getAddress());

        if (dataEntity.getRecipient_state() > 0) {
            holder.consignee_choose.setSelected(true);
            oldChoose = holder.consignee_choose;
        }
        actionWork(holder, position);
    }

    private void actionWork(final MyViewHolder holder, final int position) {

        holder.consignee_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditAddressActivity.class);
                intent.putExtra("Title", "edit");
                intent.putExtra("Value", adressBean.getData().get(position));
                context.startActivity(intent);
            }
        });

        holder.consignee_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delectAddress(position);
            }
        });

        holder.consignee_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (oldChoose != null) {
                    oldChoose.setSelected(false);
                }
                holder.consignee_choose.setSelected(true);
                oldChoose = holder.consignee_choose;

                setdefaultconsignee(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return adressBean.getData().size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView consignee_name, consignee_phone, consignee_address;
        ImageView consignee_choose;
        Button consignee_edit, consignee_delete;

        public MyViewHolder(View itemView) {
            super(itemView);
            consignee_name = (TextView) itemView.findViewById(R.id.consignee_name);
            consignee_phone = (TextView) itemView.findViewById(R.id.consignee_phone);
            consignee_address = (TextView) itemView.findViewById(R.id.consignee_address);
            consignee_choose = (ImageView) itemView.findViewById(R.id.consignee_choose);
            consignee_edit = (Button) itemView.findViewById(R.id.consignee_edit);
            consignee_delete = (Button) itemView.findViewById(R.id.consignee_delete);
        }
    }

    private void setdefaultconsignee(final int position) {
        if (context instanceof ConsigneeAddressActivity) {
            ConsigneeAddressActivity consigneeAddressActivity = (ConsigneeAddressActivity) context;
            consigneeAddressActivity.getPresenter().setDefaultAddress(String.valueOf(adressBean.getData().get(position).getRecipient_id()));
        }
    }


    private void delectAddress(final int position) {
        if (context instanceof ConsigneeAddressActivity) {
            ConsigneeAddressActivity consigneeAddressActivity = (ConsigneeAddressActivity) context;
            consigneeAddressActivity.getPresenter().deleteAddress(String.valueOf(adressBean.getData().get(position).getRecipient_id()));
        }
    }

}
