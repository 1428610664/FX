package com.weisj.fx.view;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.weisj.fx.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/7 0007.
 */
public class CustomBottomPopWindow extends BottomPushPopupWindow implements View.OnClickListener {
    private List<String> list;
    private PopupListener listener;
    private LinearLayout linearLayout;
    private int flag = 0;

    public CustomBottomPopWindow(Context context, List<String> list, PopupListener listener) {
        super(context);
        this.list = list;
        this.listener = listener;
        loadItem();
    }

    public CustomBottomPopWindow(Context context, List<String> list, PopupListener listener, int flag) {
        super(context);
        this.list = list;
        this.listener = listener;
        loadItem();
        this.flag = flag;
    }
    public CustomBottomPopWindow(Context context, PopupListener listener) {
        super(context);
        this.listener = listener;
        list = new ArrayList<String>();
        list.add("拍摄新照片");
        list.add("从照片库获取");
        loadItem();
    }
    public CustomBottomPopWindow(Context context, PopupListener listener, int flag) {
        super(context);
        this.listener = listener;
        list = new ArrayList<>();
        list.add("拍摄新照片");
        list.add("从照片库获取");
        loadItem();
        this.flag = flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    protected View generateCustomView() {
        View root = View.inflate(context, R.layout.popup_bottom_base, null);
        linearLayout = (LinearLayout) root.findViewById(R.id.base_lieaner);
        root.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return root;
    }

    private void loadItem() {
        for (int i = 0; i < list.size(); i++) {
            TextView tv = (TextView) View.inflate(context, R.layout.item_popup_bottom_textview, null);
            tv.setText(list.get(i));
            tv.setTag(i);
            tv.setOnClickListener(this);
            linearLayout.addView(tv);
        }
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onItemClick(v, (Integer) v.getTag(),flag);
        }
        dismiss();
    }

    public interface PopupListener {
        void onItemClick(View v, int position, int flag);
    }
}
