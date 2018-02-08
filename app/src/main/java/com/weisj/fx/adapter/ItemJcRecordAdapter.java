package com.weisj.fx.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.activity.ListViewActivity;
import com.weisj.fx.bean.ActiBean;
import com.weisj.fx.bean.ShareActiBean;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.TextViewUtils;

public class ItemJcRecordAdapter<T> extends BaseAdapter implements View.OnClickListener {

    private List<T> objects;

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemJcRecordAdapter(Context context, List<T> objectses) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = objectses;
    }

    @Override
    public int getCount() {
        return objects != null ? objects.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return objects != null ? objects.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_jc_record, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((T) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(T object, ViewHolder holder) {
        //TODO implement
        if (object instanceof ShareActiBean.DataBean) {
            ShareActiBean.DataBean data = (ShareActiBean.DataBean) object;
            TextViewUtils.setText(holder.name, data.getActivityName());
            holder.jcNumber.setText(data.getGuessPersons() + "人");
            ImageLoaderUtils.getInstance().display(holder.image, data.getSharePic());
            holder.jcDetail.setOnClickListener(this);
            holder.jcDetail.setTag(object);
            if (data.getIsHaveAward() > 0) {
                holder.jcState.setSelected(true);
                holder.jcState.setText("中奖啦");
            } else {
                holder.jcState.setSelected(false);
                if (data.getActivityStatus() == 1) {
                    holder.jcState.setText("正在进行中");
                } else {
                    holder.jcState.setText("已结束");
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.jc_detail) {
            Intent intent = new Intent(context, ListViewActivity.class);
            ShareActiBean.DataBean data = (ShareActiBean.DataBean) v.getTag();
            intent.putExtra("listState", 1);
            intent.putExtra("activity_id", data.getGuessActivityId());
            context.startActivity(intent);
        }
    }

    protected class ViewHolder {
        private ImageView image;
        private TextView name;
        private TextView jcNumber;
        private TextView jcDetail;
        private TextView jcState;

        public ViewHolder(View view) {
            image = (ImageView) view.findViewById(R.id.image);
            name = (TextView) view.findViewById(R.id.name);
            jcNumber = (TextView) view.findViewById(R.id.jc_number);
            jcDetail = (TextView) view.findViewById(R.id.jc_detail);
            jcState = (TextView) view.findViewById(R.id.jc_state);
        }
    }
}
