package com.weisj.fx.view;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weisj.fx.R;


public class AlertDialog {
    Context context;
    android.app.AlertDialog ad;
    TextView titleView;
    
    
    
   public TextView messageView;
    LinearLayout buttonLayout;
    
    Button cancel,sure;
    
    EditText editText;
    View linearView;
    
    
    public AlertDialog(Context context) {
        // TODO Auto-generated constructor stub
        this.context=context;
        ad=new android.app.AlertDialog.Builder(context).create();
        ad.setCanceledOnTouchOutside(false);
        ad.setCancelable(false);
        ad.show();
        //关键在下面的两行,使用window.setContentView,替换整个对话框窗口的布局
        Window window = ad.getWindow();
        ad.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        window.setContentView(R.layout.prompt_dialog);
        titleView=(TextView)window.findViewById(R.id.title);
        messageView=(TextView)window.findViewById(R.id.message);
        buttonLayout=(LinearLayout)window.findViewById(R.id.buttonLayout);
        linearView = window.findViewById(R.id.view);
        editText =(EditText)window.findViewById(R.id.password);
        
        cancel = (Button)window.findViewById(R.id.cancel);
        sure = (Button)window.findViewById(R.id.ok);
        
        
    }
    public void setTitle(int resId)
    {
        titleView.setText(resId);
    }
    public void setTitle(String title) {
        titleView.setText(title);
    }
    public void setMessage(int resId) {
        messageView.setText(resId);
    }
    public void setMessage(String message)
    {
        messageView.setText(message);
    }
    
    
    public String getEditText(){
    	return editText.getText().toString().trim();
    }
    
    public void setCancelVisible(boolean is){
    	
    	if(is){
    		cancel.setVisibility(View.GONE);
            linearView.setVisibility(View.GONE);
    	}
    }
    
    
    public void setEditVisible(boolean is){
    	
    	if(is){
    		editText.setVisibility(View.VISIBLE);
    	}
    }
    
    
    
    /**
     * 设置按钮
     * @param text
     * @param listener
     */
    public void setPositiveButton(String text,final View.OnClickListener listener)
    {
    	sure.setText(text);
    	sure.setOnClickListener(listener);
    }  /**
//     * 设置按钮
//     * @param text
//     * @param listener
//     */
    public void setNegativeButton(String text,final View.OnClickListener listener)
    {
    	cancel.setText(text);
    	cancel.setOnClickListener(listener);
        }
    /**
     * 关闭对话框
     */
    public void dismiss() {
        ad.dismiss();
	}
    
    
    /**
     * 关闭对话框
     */
    public void show() {
        ad.show();
	}
    
}