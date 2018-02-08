package com.weisj.fx.view.choosearea;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;


import com.weisj.fx.R;
import com.weisj.fx.utils.SystemConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BBMJ on 2015/12/14.
 */
public class ChoosePop extends Dialog implements OnWheelChangedListener {
    private Activity activity;
    private List<WheelView> wheelViewList = new ArrayList<WheelView>();
    private List<String>[] data;
    private LinearLayout linearLayout;
    private List<String> oneList;
    private ChooseListener listener;
    private int flag;

    public ChoosePop(Activity activity, int flag, List<String> oneList, List<String>... data) {
        super(activity, R.style.mystyle);
        this.activity = activity;
        Window window = this.getWindow();
        this.setCanceledOnTouchOutside(true);
        window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的地位
        window.setWindowAnimations(R.style.mystyle); // 添加动画
        if (data != null) {
            this.data = data;
            if (this.data.length == 0) {
                this.data = null;
            }
        }
        this.oneList = oneList;
        this.flag = flag;
    }

    public void setListener(ChooseListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linearLayout = new LinearLayout(activity);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        setContentView(linearLayout);
        setUpViews();
        setUpData();
    }

    /**
     * 设置布局控件
     */
    private void setUpViews() {
        if (data != null) {
            for (int i = 0; i < 2; i++) {
                WheelView wheelView = new WheelView(activity);
                SystemConfig.dynamicSetWidthAndHeight(wheelView, 0.5, -2);
                wheelView.setWheelBackground(R.color.colorWhite);
                wheelView.addChangingListener(this);
                linearLayout.addView(wheelView);
                wheelViewList.add(wheelView);
            }
        } else {
            WheelView wheelView = new WheelView(activity);
            SystemConfig.dynamicSetWidthAndHeight(wheelView, -3, -2);
            wheelView.setWheelBackground(R.color.colorWhite);
            wheelView.addChangingListener(this);
            linearLayout.addView(wheelView);
            wheelViewList.add(wheelView);
        }
    }


    /**
     * 设置数据
     */
    private void setUpData() {
        ArrayWheelAdapter adapter = new ArrayWheelAdapter(activity, oneList, R.layout.item_choose_linkage, R.id.text);
        adapter.setTextColor(activity.getResources().getColor(R.color.colorBlack));
        wheelViewList.get(0).setViewAdapter(adapter);
        wheelViewList.get(0).setVisibleItems(5);
        wheelViewList.get(0).setCurrentItem(0);
        if (data != null) {
            ArrayWheelAdapter adapter2 = new ArrayWheelAdapter(activity, data[0], R.layout.item_choose_linkage, R.id.text);
            wheelViewList.get(1).setViewAdapter(adapter2);
            wheelViewList.get(1).setVisibleItems(5);
            wheelViewList.get(1).setCurrentItem(0);
            adapter2.setTextColor(activity.getResources().getColor(R.color.colorBlack));
        }
    }

    public void setVisibleItems(int count) {
        for (int i = 0; i < wheelViewList.size(); i++) {
            wheelViewList.get(i).setVisibleItems(count);
        }
    }

    public void showDialog() {
        this.show();
        // 设置弹窗占满横屏
        WindowManager windowManager = activity.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); // 设置宽度
        this.getWindow().setAttributes(lp);
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == wheelViewList.get(0) && data != null && data.length > 1) {
            int lastCurrent = wheelViewList.get(0).getCurrentItem();
            ArrayWheelAdapter adapter = new ArrayWheelAdapter(activity, data[lastCurrent], R.layout.item_choose_linkage, R.id.text);
            wheelViewList.get(1).setViewAdapter(adapter);
            wheelViewList.get(1).setVisibleItems(5);
            wheelViewList.get(1).setCurrentItem(0);
            adapter.setTextColor(activity.getResources().getColor(R.color.colorBlack));
        }
        int one = wheelViewList.get(0).getCurrentItem();
        String oneStr = oneList.get(one);
        if (data != null) {
            String twoStr = data[one].get(wheelViewList.get(1).getCurrentItem());
            if (listener != null) {
                listener.changeText(oneStr, twoStr, flag);
            }
        } else {
            if (listener != null) {
                listener.changeText(oneStr, null, flag);
            }
        }
    }

    public interface ChooseListener {
        void changeText(String one, String two, int flag);
    }

}
