package com.weisj.fx.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.weisj.fx.R;
import com.weisj.fx.activity.WebViewActivity;
import com.weisj.fx.bean.HomeCouponbean;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.L;
import com.weisj.fx.utils.SystemConfig;

import java.util.List;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class ItemCouponHeadAdapter<T> extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<T> list;

    public ItemCouponHeadAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        L.log_i("position===" + position);
        if (convertView == null) {
            convertView = new ImageView(context);
            ((ImageView) convertView).setScaleType(ImageView.ScaleType.FIT_XY);
            SystemConfig.dynamicSetListViewWidthAndHeight(convertView, -1, 260);
        }
        ImageView imageView = (ImageView) convertView;
        HomeCouponbean.DataEntity.SingleCouponListEntity data = (HomeCouponbean.DataEntity.SingleCouponListEntity) getItem(position);
        ImageLoaderUtils.getInstance().display(imageView, data.getCouponPic());
        imageView.setOnClickListener(this);
        imageView.setTag(data);
        return convertView;
    }


    @Override
    public void onClick(View v) {
        HomeCouponbean.DataEntity.SingleCouponListEntity dataEntity = (HomeCouponbean.DataEntity.SingleCouponListEntity) v.getTag();
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("url", dataEntity.getHtmlAddress());
        intent.putExtra("web_title", dataEntity.getCouponTitle());
        intent.putExtra("imageUrl", dataEntity.getSharePic() != null ? dataEntity.getSharePic():dataEntity.getCouponPic());
        context.startActivity(intent);
    }
}
