package com.weisj.fx.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.weisj.fx.R;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.utils.Urls;

/**
 * Created by Administrator on 2017-05-11.
 */

public class QuestionActivity extends Activity {
    private LinearLayout rootLinear;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        rootLinear = (LinearLayout) findViewById(R.id.root_linear);
        webView = (WebView) findViewById(R.id.webView);
        ViewGroup.LayoutParams layoutParams = rootLinear.getLayoutParams();
        layoutParams.height = SystemConfig.getScreenHeight() * 2 / 3;
        layoutParams.width = SystemConfig.getScreenWidth() * 2 / 3;
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(Urls.IP + "/Shop/User/rule.html");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        webView.stopLoading();
    }
}
