package com.weisj.fx.view;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.weisj.fx.R;
import com.weisj.fx.view.choosearea.IDataCallback;

/**
 * Created by Administrator on 2016/1/7 0007.
 */
public class KeyboardPopWindow extends BottomPushPopupWindow implements View.OnClickListener, KeyBoardView.KeyBoardListener, PasswordInput.InputFinishListener {
    private PasswordInput passwordInput;
    private IDataCallback callback;


    public KeyboardPopWindow(Context context) {
        super(context);
    }


    public KeyboardPopWindow(Context context, IDataCallback callback) {
        super(context);

        this.callback = callback;

    }

    @Override
    protected View generateCustomView() {
        View view = View.inflate(context, R.layout.pop_input_password, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        view.findViewById(R.id.close_pop).setOnClickListener(this);
        view.findViewById(R.id.forgetPass).setOnClickListener(this);
        ((KeyBoardView) view.findViewById(R.id.input_pass)).setKeyBoardListener(this);
        passwordInput = (PasswordInput) view.findViewById(R.id.password);
        passwordInput.setListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close_pop:
                dismiss();
                break;
            case R.id.forgetPass:
                Toast.makeText(context, passwordInput.getPassword(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void deleteNumber() {
        passwordInput.delStr();
    }

    @Override
    public void clickNumber(String number) {
        passwordInput.addStr(number);
    }

    @Override
    public void clickPoint() {

    }

    @Override
    public void clickHint() {

    }

    @Override
    public void inputFinish(String pass) {
//        Toast.makeText(context, pass, Toast.LENGTH_SHORT).show();
        callback.callback(pass);
    }
}
