package com.weisj.fx.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.weisj.fx.R;
import com.weisj.fx.utils.SystemConfig;

/**
 * Created by Administrator on 2016/7/4 0004.
 */
public class MyRatingBar extends LinearLayout {
    private int count = 5;

    public MyRatingBar(Context context) {
        super(context);
        initView();
    }

    public MyRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.MyRatingBar, 0, 0);
        int indexCount = a.getIndexCount();
        if (indexCount > 0) {
            count = a.getInt(R.attr.number, 5);
        }
        initView();
    }


    private void initView() {
        setOrientation(LinearLayout.HORIZONTAL);
        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.drawable.good_bar_image);
            imageView.setSelected(true);
            SystemConfig.dynamicSetWidthAndHeight(imageView, -2, -1, 0, 0, 0, 5);
            addView(imageView);
        }
    }

    public void setPoint(int point) {
        for (int i = 0; i < count; i++) {
            getChildAt(i).setSelected(i < point);
        }
    }
}
