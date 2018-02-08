package com.weisj.fx.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weisj.fx.R;
import com.weisj.fx.utils.SystemConfig;


/**
 * Created by Administrator on 2016/1/20 0020.
 */
public class PasswordInput extends LinearLayout {
    private StringBuffer sb = new StringBuffer();
    private PassWord passWord;
    private InputFinishListener listener;

    public void setListener(InputFinishListener listener) {
        this.listener = listener;
    }

    public void addStr(String number) {
        if (sb.length() < 6) {
            sb.append(number);
            ((BaseAdapter) passWord.getAdapter()).notifyDataSetChanged();
        }
        if (listener != null && sb.length() == 6) {
            listener.inputFinish(sb.toString());
        }
    }

    public void delStr() {
        if (sb.length() > 0) {
            sb.delete(sb.length() - 1, sb.length());
            ((BaseAdapter) passWord.getAdapter()).notifyDataSetChanged();
        }
    }

    public String getPassword() {
        return sb.toString();
    }

    public PasswordInput(Context context) {
        super(context);
        init();
    }

    public PasswordInput(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOrientation(LinearLayout.VERTICAL);
        passWord = new PassWord(getContext());
        SystemConfig.dynamicSetWidthAndHeight(passWord, -1, -2, 0, 0, 30, 30);
        addView(passWord);
    }

    private class PassWord extends NoScrollGridView {
        public PassWord(Context context) {
            super(context);
            init();
        }

        private void init() {
            setNumColumns(6);
            setHorizontalSpacing(1);
            setBackgroundResource(R.drawable.input_password_background);
            setAdapter(new PasswordAdapter());
        }

        private class PasswordAdapter extends BaseAdapter {

            @Override
            public int getCount() {
                return 6;
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
                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                linearLayout.setGravity(Gravity.CENTER);
                if (position != 0) {
                    View view = new View(getContext());
                    SystemConfig.dynamicSetWidthAndHeight(view, 2, 95);
                    view.setBackgroundResource(R.color.password_background);
                    linearLayout.addView(view);
                }
                TextView tv = new TextView(getContext());
                LayoutParams params = new LayoutParams(-1, SystemConfig.getScale(97));
                tv.setLayoutParams(params);
                tv.setGravity(Gravity.CENTER);
                if (sb.length() > position) {
                    tv.setText("●");
                } else {
                    tv.setText("");
                }
                tv.setTextColor(Color.BLACK);
                tv.setTextSize(18);
                linearLayout.addView(tv);
                return linearLayout;
            }
        }
    }

    public interface InputFinishListener {
        void inputFinish(String pass);
    }
}
