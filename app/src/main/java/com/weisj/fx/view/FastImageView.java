package com.weisj.fx.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/3/7 0007.
 */
public class FastImageView extends ImageView {
    private FastListener listener;

    public void setListener(FastListener listener) {
        this.listener = listener;
    }

    public FastImageView(Context context) {
        super(context);
    }

    public FastImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    public interface FastListener{
        void fastSlide();
        void loadImage();
    }
}
