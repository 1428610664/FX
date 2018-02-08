package com.weisj.fx.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.weisj.fx.R;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.TextViewUtils;

import java.util.HashMap;
import java.util.Map;

public class KeyBoardGridView extends GridView implements AdapterView.OnItemClickListener {
    private boolean isHintPoint;
    private int Resource = R.drawable.keyboard_number_selector;

    public KeyBoardGridView(Context context) {
        super(context);
        init();
    }

    private NumberClickListener listener;


    public void setListener(NumberClickListener listener) {
        this.listener = listener;
    }

    private void init() {
        this.setVerticalSpacing(1);
        this.setHorizontalSpacing(1);
        this.setNumColumns(3);
        this.setBackgroundResource(R.color.keyboard_background);
        this.setAdapter(new KeyItemAdapter());
        this.setOnItemClickListener(this);
    }

    public void setHintPoint(boolean isHintPoint) {
        this.isHintPoint = isHintPoint;
        ((BaseAdapter) this.getAdapter()).notifyDataSetChanged();
    }

    public KeyBoardGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            return true; // 禁止GridView滑动
        }
        return super.dispatchTouchEvent(ev);
    }

    public void setNumberColor(int Resource) {
        this.Resource = Resource;
        ((BaseAdapter) getAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (listener != null) {
            if (position <= 8) {
                listener.clickNumber(String.valueOf(position + 1));
            } else if (position == 9) {
                if (!isHintPoint) {
                    listener.clickPoint();
                }
            } else if (position == 10) {
                listener.clickNumber("0");
            } else if (position == 11) {
                listener.deleteNumber();
            }
        }
    }

    public interface NumberClickListener {
        void clickNumber(String number);

        void deleteNumber();

        void clickPoint();
    }

    private class KeyItemAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 12;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (position != getCount() - 1) {
                TextView tv = new TextView(getContext());
                tv.setGravity(Gravity.CENTER);
                LayoutParams params = new LayoutParams(SystemConfig.getScreenWidth() / 3 - 1, SystemConfig.getScale(125));
                tv.setLayoutParams(params);
                TextViewUtils.setTextSize(tv, 50);
                tv.setTextColor(Color.BLACK);
                if (position <= 8) {
                    tv.setText(String.valueOf(position + 1));
                    tv.setBackgroundResource(Resource);
                } else {
                    if (position == 9) {
                        if (isHintPoint) {
                            tv.setText("");
                        } else {
                            tv.setText(".");
                        }
                        tv.setBackgroundResource(R.drawable.keyboard_other_selector);
                    } else if (position == 10) {
                        tv.setText("0");
                        tv.setBackgroundResource(Resource);
                    }
                }
                return tv;
            } else {
                RelativeLayout relativeLayout = new RelativeLayout(getContext());
                LayoutParams params = new LayoutParams(SystemConfig.getScreenWidth() / 3 - 1, SystemConfig.getScale(125));
                relativeLayout.setLayoutParams(params);
                ImageView imageView = new ImageView(getContext());
                Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                map.put(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                SystemConfig.dynamicSetRelayoutViewWidthAndHeight(imageView, 70, 44, 0, 0, 0, 0, map);
                relativeLayout.addView(imageView);
                imageView.setImageResource(R.drawable.wx_del_keyboard);
                relativeLayout.setBackgroundResource(R.drawable.keyboard_other_selector);
                return relativeLayout;
            }
        }
    }
}
