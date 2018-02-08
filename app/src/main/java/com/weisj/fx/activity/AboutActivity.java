package com.weisj.fx.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.weisj.fx.MainActivity;
import com.weisj.fx.R;
import com.weisj.fx.utils.PersonMessagePreferencesUtils;
import com.weisj.fx.utils.PreferencesUtils;
import com.weisj.fx.view.bgabanner.BGABanner;

import java.util.Arrays;

public class AboutActivity extends Activity implements View.OnClickListener, BGABanner.OnItemClickListener {

    private Integer[] imageSrc = {R.mipmap.welcome};
    private Integer[] imageGuide = {R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3};
    private BGABanner bgaBanner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        bgaBanner = (BGABanner) findViewById(R.id.bagbanner_container);
        if (getIntent().getIntExtra("type", 0) == 1) {
            bgaBanner.setData(Arrays.asList(imageGuide), null);
            findViewById(R.id.back).setVisibility(View.GONE);
            bgaBanner.setOnItemClickListener(this);
        } else {
            bgaBanner.setData(Arrays.asList(imageSrc), null);
            findViewById(R.id.back).setOnClickListener(this);
        }
        bgaBanner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                ((ImageView) view).setImageResource((int) model);
            }
        });
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    @Override
    public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
        if (getIntent().getIntExtra("type", 0) == 1 && position == imageGuide.length - 1) {
            PreferencesUtils.putBoolean("initFinish", true);
            if (PersonMessagePreferencesUtils.getUid() != null) {
                startActivity(new Intent(AboutActivity.this, MainActivity.class));
            } else {
                startActivity(new Intent(AboutActivity.this, LoginActivity.class));
            }
            finish();
        }
    }
}
