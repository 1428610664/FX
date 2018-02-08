package com.weisj.fx.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.bean.ConversionRateBean;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.TextViewUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ConversionRateAdapter<T> extends BaseAdapter {

    private List<T> objects = new ArrayList<T>();

    private Activity context;
    private LayoutInflater layoutInflater;

    public ConversionRateAdapter(Activity context, List<T> list) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = list;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public T getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_conversionrate, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.goodImage = (ImageView) convertView.findViewById(R.id.icon);
            viewHolder.goodName = (TextView) convertView.findViewById(R.id.title);
            viewHolder.goodFrom = (TextView) convertView.findViewById(R.id.from);
            viewHolder.goodPrice = (TextView) convertView.findViewById(R.id.price);
            viewHolder.goodRate = (TextView) convertView.findViewById(R.id.rate);

            convertView.setTag(viewHolder);
        }
        initializeViews((T) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(T object, ViewHolder holder) {
        //TODO implement
        ConversionRateBean.DataEntity.ShareGoodsInfoDomainListEntity data = (ConversionRateBean.DataEntity.ShareGoodsInfoDomainListEntity) object;
        TextViewUtils.setText(holder.goodName,data.getGoods_name());

        holder.goodName.setText(data.getGoods_name());

        holder.goodFrom.setText(data.getGoods_describe());

        holder.goodPrice.setText("￥" + data.getPay_money());

        if (data.getShare_record_count()>0){

            float num= (float)data.getGoods_number()/ data.getShare_record_count();;

            DecimalFormat df = new DecimalFormat("0.00");//格式化小数，.后跟几个零代表几位小数

            String s = df.format(num);
            holder.goodRate.setText(s+"%");
        }else {
            holder.goodRate.setText(0+"%");
        }



        ImageLoaderUtils.getInstance().display(holder.goodImage, data.getGoods_pic());
    }

    protected class ViewHolder {
        private ImageView goodImage;
        private TextView goodName,goodFrom,goodPrice,goodRate;
    }
}

