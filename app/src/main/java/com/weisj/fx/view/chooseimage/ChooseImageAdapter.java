package com.weisj.fx.view.chooseimage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.weisj.fx.R;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.SanXingImageUtils;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.view.photocheck.ImagePagerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/8 0008.
 */
public class ChooseImageAdapter extends RecyclerView.Adapter<ChooseImageAdapter.MyViewHolder> implements View.OnClickListener {
    private Activity context;
    private List<String> pathList = new ArrayList<String>();
    private ImageSize imageSize = new ImageSize(100, 100);

    public ChooseImageAdapter(Activity context, List<String> pathList) {
        this.context = context;
        this.pathList.addAll(pathList);
    }

    public void refreshData(List<String> pathList) {
        this.pathList.clear();
        this.pathList.addAll(pathList);
        this.notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_image_show, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        ImageLoaderUtils.getInstance().display(holder.image,pathList.get(holder.getLayoutPosition()), new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                holder.image.setImageResource(R.mipmap.load_default);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                holder.image.setImageResource(R.mipmap.load_default);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if (imageUri.contains("file://")) {
                    imageUri = imageUri.substring(7);
                }
                Bitmap bitmap = SanXingImageUtils.toturn(loadedImage, imageUri);
                holder.image.setImageBitmap(bitmap);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                holder.image.setImageResource(R.mipmap.load_default);
            }
        });
        holder.imageSelect.setOnClickListener(this);
        holder.imageSelect.setTag(holder.imageBack);
        holder.imageBack.setTag(holder);
        holder.view.setOnClickListener(this);
        holder.view.setTag(holder);
        if (holder.imageSelect.isSelected()) {
            holder.imageBack.setVisibility(View.VISIBLE);
        } else {
            holder.imageBack.setVisibility(View.GONE);
        }
        if (((ChooseImageActivity) context).getSelectList().contains(pathList.get(holder.getLayoutPosition()))) {
            holder.imageBack.setVisibility(View.VISIBLE);
            holder.imageSelect.setSelected(true);
        } else {
            holder.imageBack.setVisibility(View.GONE);
            holder.imageSelect.setSelected(false);
        }
    }

    @Override
    public int getItemCount() {
        return pathList.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_checkbox:
                View backView = (View) v.getTag();
                boolean b = ((ChooseImageActivity) context).chooseImage(pathList.get(((MyViewHolder) backView.getTag()).getLayoutPosition()));
                if (b) {
                    if (v.isSelected()) {
                        backView.setVisibility(View.GONE);
                    } else {
                        backView.setVisibility(View.VISIBLE);
                    }
                    v.setSelected(!v.isSelected());
                }
                break;
            default:
                Intent intent = new Intent(context, ImagePagerActivity.class);
                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, ((MyViewHolder) v.getTag()).getLayoutPosition());
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(ImagePagerActivity.EXTRA_IMAGE_URLS, (ArrayList) pathList);
                intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_LIMIT, ((ChooseImageActivity) context).getLimit());
                bundle.putParcelableArrayList(ImagePagerActivity.EXTRA_IMAGE_SELETE, (ArrayList) ((ChooseImageActivity) context).getSelectList());
                intent.putExtras(bundle);
                context.startActivityForResult(intent, 0);
                break;
        }
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout baseView;
        ImageView image;
        View imageBack;
        ImageView imageSelect;
        View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            baseView = (RelativeLayout) itemView.findViewById(R.id.image_show_base);
            image = (ImageView) itemView.findViewById(R.id.image);
            imageBack = itemView.findViewById(R.id.image_select_background);
            imageSelect = (ImageView) itemView.findViewById(R.id.image_checkbox);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) baseView.getLayoutParams();
            params.width = SystemConfig.getScreenWidth() / 3;
            params.height = SystemConfig.getScreenWidth() / 3;
            params.topMargin = SystemConfig.getScale(6);
            params.rightMargin = SystemConfig.getScale(3);
            params.leftMargin = SystemConfig.getScale(3);
        }
    }


}
