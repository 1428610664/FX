package com.weisj.fx.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.weisj.fx.R;
import com.weisj.fx.utils.SystemConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/6 0006.
 */
public class AddPhotoAdapter extends RecyclerView.Adapter<AddPhotoAdapter.MyViewHolder> implements View.OnClickListener {
    private Context context;
    private AddListener addlistener;
    private int COUNT = 4;
    private List<Bitmap> list;
    private boolean ishint = false;
    private OnClickPhoto onClickPhoto;
    private Map<Bitmap, Bitmap> imageMaps;

    public void setOnClickPhoto(OnClickPhoto onClickPhoto) {
        this.onClickPhoto = onClickPhoto;
    }

    public void setAddlistener(AddListener addlistener) {
        this.addlistener = addlistener;
    }


    public AddPhotoAdapter(Context context, List<Bitmap> list, AddListener listener) {
        this.context = context;
        this.addlistener = listener;
        this.list = list;
    }

    public AddPhotoAdapter(Context context, List<Bitmap> list, AddListener listener, int count) {
        this.context = context;
        this.addlistener = listener;
        this.list = list;
        this.COUNT = count;
    }

    public AddPhotoAdapter(Context context, List<Bitmap> list, AddListener listener, int count, boolean isHint) {
        this.context = context;
        this.addlistener = listener;
        this.list = list;
        this.COUNT = count;
        this.ishint = isHint;
    }

    public AddPhotoAdapter(Context context, List<Bitmap> list, AddListener listener, int count, boolean isHint, OnClickPhoto onClickPhoto) {
        this.context = context;
        this.addlistener = listener;
        this.list = list;
        this.COUNT = count;
        this.ishint = isHint;
        this.onClickPhoto = onClickPhoto;
    }

    public AddPhotoAdapter(Context context, List<Bitmap> list, AddListener listener, int count, boolean isHint, OnClickPhoto onClickPhoto, Map<Bitmap, Bitmap> imageMaps) {
        this.context = context;
        this.addlistener = listener;
        this.list = list;
        this.COUNT = count;
        this.ishint = isHint;
        this.onClickPhoto = onClickPhoto;
        this.imageMaps = imageMaps;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_photo_submit, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.image.setVisibility(View.VISIBLE);
        if (position == list.size() && holder.getLayoutPosition() != COUNT) {
            holder.image.setSelected(true);
            holder.delimage.setVisibility(View.GONE);
            holder.image.setImageResource(R.mipmap.bg_become_shop_add_selected);
        } else {
            if (list.size() > holder.getLayoutPosition()) {
                holder.image.setImageBitmap(list.get(position));
                holder.delimage.setVisibility(View.VISIBLE);
                if (imageMaps != null) {
                    if (imageMaps.containsKey(list.get(position)) && imageMaps.get(list.get(position)) == null) {
                        holder.overrideView.setVisibility(View.VISIBLE);
                    } else {
                        holder.overrideView.setVisibility(View.GONE);
                    }
                }
            } else {
                holder.image.setImageResource(R.mipmap.bg_become_shop_add_normal);
                holder.delimage.setVisibility(View.GONE);
                if (ishint) {
                    holder.image.setVisibility(View.INVISIBLE);
                }
            }
            holder.image.setSelected(false);
        }
        holder.image.setOnClickListener(this);
        holder.image.setTag(holder);
        holder.delimage.setOnClickListener(this);
        holder.delimage.setTag(holder);
    }

    @Override
    public int getItemCount() {
        return COUNT;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_image:
                if (v.isSelected()) {
                    if (addlistener != null) {
                        addlistener.onclick(v);
                    }
                } else if (onClickPhoto != null) {
                    MyViewHolder holder = (MyViewHolder) v.getTag();
                    if (list.size() > holder.getLayoutPosition()) {
                        onClickPhoto.onClickPhoto(list.get(holder.getLayoutPosition()), holder.getLayoutPosition());
                    }
                }

                break;
            case R.id.del_image:
                int position = ((MyViewHolder) v.getTag()).getLayoutPosition();
                if (onClickPhoto != null) {
                    onClickPhoto.delete(position);
                }
                list.get(position).recycle();
                list.remove(position);
                this.notifyItemRemoved(position);
                break;
        }

    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        private int space;
        private RelativeLayout relative;
        private ImageView delimage;
        View overrideView;

        public MyViewHolder(View itemView) {
            super(itemView);
            space = SystemConfig.getScale(15);
            image = (ImageView) itemView.findViewById(R.id.add_image);
            relative = (RelativeLayout) itemView.findViewById(R.id.relative);
            delimage = (ImageView) itemView.findViewById(R.id.del_image);
            overrideView = itemView.findViewById(R.id.override_image);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) relative.getLayoutParams();
            if (params == null) {
                params = new RecyclerView.LayoutParams(SystemConfig.getScale(150) + space * 2, SystemConfig.getScale(170));
                relative.setLayoutParams(params);
            }
            params.height = SystemConfig.getScale(170);
            params.width = SystemConfig.getScale(150) + space * 2;
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            map.put(RelativeLayout.ALIGN_PARENT_BOTTOM,RelativeLayout.TRUE);
            map.put(RelativeLayout.CENTER_HORIZONTAL,RelativeLayout.TRUE);
            SystemConfig.dynamicSetRelayoutViewWidthAndHeight(image, 150, 150, 0, 0, 0, 0, map);
            SystemConfig.dynamicSetRelayoutViewWidthAndHeight(overrideView, 150, 150, 0, 0, 0, 0, map);
            SystemConfig.dynamicSetRelayoutViewWidthAndHeight(delimage, 60, 60, 0, 0, 0, 0);
        }
    }

    public interface AddListener {
        void onclick(View view);
    }

    public interface OnClickPhoto {
        void onClickPhoto(Bitmap bitmap, int index);

        void delete(int index);
    }
}
