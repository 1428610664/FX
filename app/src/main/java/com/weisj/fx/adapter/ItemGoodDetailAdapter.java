package com.weisj.fx.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.weisj.fx.utils.CommenString;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.view.photocheck.WebImageCheckActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class ItemGoodDetailAdapter<T> extends BaseAdapter {
    private List<T> list;
    private Context context;
    private Map<String, View> map;

    public ItemGoodDetailAdapter(Context context, List<T> list) {
        this.context = context;
        this.list = list;
        map = new HashMap<>();
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
        if (list.get(0) instanceof String) {
            if (map.containsKey(list.get(position))) {
                return map.get(list.get(position));
            } else {
                ImageView imageView = new ImageView(context);
                ImageLoaderUtils.getInstance().display(imageView, (String) list.get(position), new SimpleImageLoadingListener() {
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        if (!map.containsKey(imageUri)) {
                            SystemConfig.dynamicSetListViewWidthAndHeight(view, -1, 750.0 * loadedImage.getHeight() / loadedImage.getWidth());
                            map.put(imageUri, view);
                        }
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        map.put(imageUri, view);
                    }

                });
                return imageView;
            }
        } else {
            return null;
        }
    }


}
