package com.weisj.fx.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.weisj.fx.BuildConfig;
import com.weisj.fx.R;
import com.weisj.fx.utils.KeyboardUtil;
import com.weisj.fx.utils.SystemConfig;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class FiltratePopupWindow extends PopupWindow implements View.OnClickListener {
    private Context mContext;
    private WindowManager wm;
    private View maskView;
    private View allText, allImage, yesterdayText, yesterdayImage, weekText, weekImage, monthText, monthImage;
    private EditText wxName;
    private FiltrateListener listener;
    private int state = 0;

    public FiltratePopupWindow(Context context, FiltrateListener listener) {
        super(context);
        this.mContext = context;
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        setContentView(generateCustomView());
        setWidth(SystemConfig.getScreenWidth() / 2);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setOutsideTouchable(true);
        setFocusable(true);
        setBackgroundDrawable(context.getResources().getDrawable(android.R.color.transparent));
        setAnimationStyle(R.style.mystyle2);
        this.listener = listener;
    }

    private View generateCustomView() {
        View view = View.inflate(mContext, R.layout.pop_filtrate_order, null);
        view.findViewById(R.id.filtrate_sure).setOnClickListener(this);
        view.findViewById(R.id.filtrate_all_linear).setOnClickListener(this);
        view.findViewById(R.id.filtrate_yesterday_linear).setOnClickListener(this);
        view.findViewById(R.id.filtrate_week_linear).setOnClickListener(this);
        view.findViewById(R.id.filtrate_month_linear).setOnClickListener(this);
        allText = view.findViewById(R.id.filtrate_all_text);
        allImage = view.findViewById(R.id.filtrate_all_image);
        yesterdayText = view.findViewById(R.id.filtrate_yesterday_text);
        yesterdayImage = view.findViewById(R.id.filtrate_yesterday_image);
        weekText = view.findViewById(R.id.filtrate_week_text);
        weekImage = view.findViewById(R.id.filtrate_week_image);
        monthText = view.findViewById(R.id.filtrate_month_text);
        monthImage = view.findViewById(R.id.filtrate_month_image);
        wxName = (EditText) view.findViewById(R.id.filtrate_wx);
        allText.setSelected(true);
        allImage.setSelected(true);
        return view;
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        addMaskView(parent.getWindowToken());
        super.showAtLocation(parent, gravity, x, y);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        addMaskView(anchor.getWindowToken());
        super.showAsDropDown(anchor, xoff, yoff);
    }

    @Override
    public void dismiss() {
        removeMaskView();
        super.dismiss();
    }


    public void show(Activity activity) {
        showAtLocation(activity.getWindow().getDecorView(), Gravity.RIGHT, 0, 0);
    }

    private void addMaskView(IBinder token) {
        WindowManager.LayoutParams p = new WindowManager.LayoutParams();
        p.width = WindowManager.LayoutParams.MATCH_PARENT;
        p.height = WindowManager.LayoutParams.MATCH_PARENT;
        p.format = PixelFormat.TRANSLUCENT;
        p.type = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL;
        p.token = token;
        p.windowAnimations = android.R.style.Animation_Toast;
        maskView = new View(mContext);
        maskView.setBackgroundColor(0x7f000000);
        if (BuildConfig.VERSION_CODE >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            maskView.setFitsSystemWindows(false);
        }
        // 华为手机在home建进入后台后，在进入应用，蒙层出现在popupWindow上层，导致界面卡死，
        // 这里新增加按bug返回。
        // initType方法已经解决该问题，但是还是留着这个按back返回功能，防止其他手机出现华为手机类似问题。
        maskView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    removeMaskView();
                    return true;
                }
                return false;
            }
        });
        wm.addView(maskView, p);
    }

    private void removeMaskView() {
        if (maskView != null) {
            wm.removeViewImmediate(maskView);
            maskView = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.filtrate_sure:
                dismiss();
                KeyboardUtil.closeKeyBoard((Activity) mContext);
                if (listener != null) {
                    listener.onSure(wxName.getText().toString(),state);
                }
                break;
            case R.id.filtrate_all_linear:
                changeState(0);
                break;
            case R.id.filtrate_yesterday_linear:
                changeState(1);
                break;
            case R.id.filtrate_week_linear:
                changeState(2);
                break;
            case R.id.filtrate_month_linear:
                changeState(3);
                break;
        }
    }

    private void changeState(int i) {
        state = i;
        allText.setSelected(i == 0);
        allImage.setSelected(i == 0);
        yesterdayText.setSelected(i == 1);
        yesterdayImage.setSelected(i == 1);
        weekText.setSelected(i == 2);
        weekImage.setSelected(i == 2);
        monthText.setSelected(i == 3);
        monthImage.setSelected(i == 3);
    }

    public interface FiltrateListener {
        void onSure(String wxName, int filter_type);
    }
}
