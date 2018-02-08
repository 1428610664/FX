package com.weisj.fx.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.weisj.fx.R;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.SystemConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2016/1/7 0007.
 * 相册弹窗
 */
public class ImageBottomPopWindow extends BottomPushPopupWindow implements AdapterView.OnItemClickListener {
    private HashMap<String, List<String>> list;
    private PopupListener listener;
    private ListView listView;
    private int seleteNumber = 0;
    private Set<String> set;
    private String oneUrl;
    private int allSize;

    public ImageBottomPopWindow(Context context, PopupListener listener, HashMap<String, List<String>> list, String oneUrl, int allSize) {
        super(context);
        this.listener = listener;
        this.list = list;
        listView.setAdapter(new MyAdapter());
        set = list.keySet();
        this.oneUrl = oneUrl;
        this.allSize = allSize;
    }

    @Override
    protected View generateCustomView() {
        View root = View.inflate(context, R.layout.item_choose_pop, null);
        listView = (ListView) root.findViewById(R.id.listview);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, SystemConfig.getScreenHeight() / 4 * 3);
        params.leftMargin = SystemConfig.getScale(20);
        params.bottomMargin = SystemConfig.getScale(20);
        params.rightMargin = SystemConfig.getScale(20);
        listView.setLayoutParams(params);
        listView.setOnItemClickListener(this);
        return root;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            listener.onItemClick(view, position, "所有图片");
        } else {
            String key = (set.toArray()[position - 1]).toString();
            listener.onItemClick(view, position, key);
        }
        seleteNumber = position;
        ((BaseAdapter) parent.getAdapter()).notifyDataSetChanged();
        dismiss();
    }

    public interface PopupListener {
        void onItemClick(View v, int position, String flag);
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size() + 1;
        }

        @Override
        public Object getItem(int position) {
            if (position == 0) {
                return 0;
            }
            return list.get(position - 1);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_pop_choose_item, null);
                viewHolder = new ViewHolder();
                viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
                viewHolder.photoAlbumSelete = (ImageView) convertView.findViewById(R.id.selete);
                viewHolder.imageName = (TextView) convertView.findViewById(R.id.image_name);
                viewHolder.imageNumber = (TextView) convertView.findViewById(R.id.image_number);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            if (seleteNumber == position) {
                viewHolder.photoAlbumSelete.setVisibility(View.VISIBLE);
            } else {
                viewHolder.photoAlbumSelete.setVisibility(View.GONE);
            }

            if (position == 0) {
                viewHolder.imageName.setText("所有图片");
                ImageLoaderUtils.getInstance().display(viewHolder.image, oneUrl);
                viewHolder.imageNumber.setText(allSize+"张");
            } else {
                String key = (set.toArray()[position - 1]).toString();
                viewHolder.imageName.setText(key);
                ImageLoaderUtils.getInstance().display(viewHolder.image, list.get(key).get(0));
                viewHolder.imageNumber.setText(list.get(key).size()+"张");
            }
            return convertView;
        }
    }

    class ViewHolder {
        TextView imageNumber;
        TextView imageName;
        ImageView image;
        ImageView photoAlbumSelete;
    }
}
