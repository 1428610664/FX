package com.weisj.fx.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.weisj.fx.R;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.view.choosearea.ArrayWheelAdapter;
import com.weisj.fx.view.choosearea.IDataCallback;
import com.weisj.fx.view.choosearea.WheelView;

/**
 * Created by Administrator on 2017-06-29.
 */

public class ChooseBankDialog extends Dialog implements View.OnClickListener {
    private Activity activity;
    private IDataCallback callback;
    private WheelView mViewBank;
    private Button btn_confirm;
    private static final String[] banks = {"中国工商银行", "中国农业银行", "中国银行", "中国建设银行", "交通银行", "中信银行", "中国光大银行",
            "华夏银行", "中国民生银行", "广发银行", "深圳发展银行", "招商银行", "兴业银行", "上海浦东发展银行",
            "恒丰银行", "浙商银行", "渤海银行", "中国邮政储蓄银行", "平安银行"};
    private ArrayWheelAdapter<String> arrayProviceWheelAdapter;

    public ChooseBankDialog(Activity activity, int themeResId, IDataCallback callback) {
        super(activity, themeResId);
        this.activity = activity;
        this.callback = callback;
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的地位
        window.setWindowAnimations(R.style.mystyle); // 添加动画
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwin_bank);
        setUpViews();
        setUpListener();
        setUpData();
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

    /**
     * 设置数据
     */
    private void setUpData() {
        // TODO Auto-generated method stub
        arrayProviceWheelAdapter = new ArrayWheelAdapter<String>(activity, banks
        );
//        arrayProviceWheelAdapter.setTextSize(24);
        // arrayWheelAdapter.setTextColor(context.getResources().getColor(R.color.province_line_border));
        mViewBank.setViewAdapter(arrayProviceWheelAdapter);
        // 设置可见条目数量
        mViewBank.setVisibleItems(7);
    }

    /**
     * 设置监听事件
     */
    private void setUpListener() {
        // TODO Auto-generated method stub
        btn_confirm.setOnClickListener(this);
    }

    /**
     * 设置布局控件
     */
    private void setUpViews() {
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        mViewBank = (WheelView) findViewById(R.id.id_bank);
        mViewBank.setWheelBackground(R.color.colorWhite);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) findViewById(R.id.placeWheel).getLayoutParams();
        layoutParams.width = SystemConfig.getScreenWidth();
    }

    @Override
    public void onClick(View view) {
        int item = mViewBank.getCurrentItem();
        callback.callback(banks[item]);
        dismiss();
    }

}
