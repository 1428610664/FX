package com.weisj.fx.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.activity.UserInfoActivity;
import com.weisj.fx.utils.KeyboardUtil;
import com.weisj.fx.utils.SystemConfig;


/**
 * Created by Administrator on 2015/12/9 0009.
 */
public class InputPassDialog extends AlertDialog implements View.OnClickListener {
    private Context context;
    private EditText password;
    private InputListener listener;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //只用下面这一行弹出对话框时需要点击输入框才能弹出软键盘
            InputPassDialog.this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
//加上下面这一行弹出对话框时软键盘随之弹出
            InputPassDialog.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            KeyboardUtil.openKeyBoard(password, (UserInfoActivity) context);
        }
    };

    public InputPassDialog(Context context, InputListener listener) {
        super(context, R.style.dialog);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public void show() {
        super.show();
        if (password != null) {
            password.setText("");
        }
        handler.sendEmptyMessageDelayed(1, 300);
    }

    private void init() {
        setContentView(R.layout.dialog_input_pass);
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) (SystemConfig.getScreenWidth() * 0.72);
        findViewById(R.id.version_cancel).setOnClickListener(this);
        findViewById(R.id.version_confirm).setOnClickListener(this);
        password = (EditText) findViewById(R.id.password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.version_cancel:
                this.cancel();
                listener.cancel();
                break;
            case R.id.version_confirm:
                this.cancel();
                listener.confirm(password.getText().toString());
                break;
        }
        KeyboardUtil.closeKeyBoard((UserInfoActivity) context);
    }

    public interface InputListener {
        void cancel();

        void confirm(String pass);
    }


}
