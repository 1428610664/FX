package com.weisj.fx.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import com.weisj.fx.R;
import com.weisj.fx.utils.SystemConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/20 0020.
 */
public class KeyBoardView extends LinearLayout implements View.OnClickListener, KeyBoardGridView.NumberClickListener {
    private KeyBoardListener keyBoardListener;
    private KeyBoardGridView keyBoardGridView;
    private boolean isHintPoint;
    private int resource;

    public void setKeyBoardListener(KeyBoardListener keyBoardListener) {
        this.keyBoardListener = keyBoardListener;
    }

    public KeyBoardView(Context context) {
        super(context);
        init(false);
    }

    public KeyBoardView(Context context, boolean isHintTop) {
        super(context);
        init(isHintTop);
    }

    public KeyBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.KeyBoardView, 0, 0);
        boolean isHintTop = a.getBoolean(R.styleable.KeyBoardView_isHintTop, false);
        isHintPoint = a.getBoolean(R.styleable.KeyBoardView_isHintPoint, false);
        resource = a.getResourceId(R.styleable.KeyBoardView_numberColor, 0);
        init(isHintTop);
    }

    private void init(boolean isHintTop) {
        this.setOrientation(LinearLayout.VERTICAL);
        if (!isHintTop) {
            RelativeLayout topView = new RelativeLayout(getContext());
            SystemConfig.dynamicSetWidthAndHeight(topView, -1, 64);
            topView.setBackgroundResource(R.drawable.keyboard_number_selector);
            ImageView topIv = new ImageView(getContext());
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            map.put(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
            SystemConfig.dynamicSetRelayoutViewWidthAndHeight(topIv, 60, 28, 0, 0, 0, 0, map);
            topIv.setImageResource(R.drawable.wx_down_keyboard);
            topView.addView(topIv);
            this.addView(topView);
            topView.setOnClickListener(this);
        }
        View linearView = new View(getContext());
        LayoutParams params = new LayoutParams(-1, 1);
        linearView.setLayoutParams(params);
        linearView.setBackgroundResource(R.color.keyboard_background);
        this.addView(linearView);
        keyBoardGridView = new KeyBoardGridView(getContext());
        keyBoardGridView.setHintPoint(isHintPoint);
        SystemConfig.dynamicSetWidthAndHeight(keyBoardGridView, -1, 500);
        if (resource != 0){
            keyBoardGridView.setNumberColor(resource);
        }
        keyBoardGridView.setListener(this);
        this.addView(keyBoardGridView);
    }

    public void setNumberColor(int resource) {
        keyBoardGridView.setNumberColor(resource);
    }

    public void sethintPoint(boolean isHint) {
        keyBoardGridView.setHintPoint(isHint);
    }

    @Override
    public void onClick(View v) {
        if (keyBoardListener != null) {
            keyBoardListener.clickHint();
        }
    }

    @Override
    public void clickNumber(String number) {
        if (keyBoardListener != null) {
            keyBoardListener.clickNumber(number);
        }
    }

    @Override
    public void deleteNumber() {
        if (keyBoardListener != null) {
            keyBoardListener.deleteNumber();
        }
    }

    @Override
    public void clickPoint() {
        if (keyBoardListener != null) {
            keyBoardListener.clickPoint();
        }
    }

    public interface KeyBoardListener {
        void deleteNumber();

        void clickNumber(String number);

        void clickPoint();

        void clickHint();

    }

}
