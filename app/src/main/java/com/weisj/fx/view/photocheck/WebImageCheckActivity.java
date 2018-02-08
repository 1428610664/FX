package com.weisj.fx.view.photocheck;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.weisj.fx.R;
import com.weisj.fx.utils.CommenString;

import java.util.List;

/**
 * Created by Administrator on 2016/1/27 0027.
 */
public class WebImageCheckActivity extends FragmentActivity implements View.OnClickListener {
    private HackyViewPager viewpager;
    private List<String> list;
    private LinearLayout titleLinear;
    private TextView text;
    private int titleHeight;
    private int bottomHeight;
    private boolean b = true;


    int picPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webimage_check);
        list = getIntent().getStringArrayListExtra(CommenString.url);

        picPosition = getIntent().getIntExtra("image_index", 0);
        initView();
    }

    private void initView() {
        findViewById(R.id.back).setOnClickListener(this);
        viewpager = (HackyViewPager) findViewById(R.id.pager);
        viewpager.setAdapter(new ImagePagerAdapter(getSupportFragmentManager(), list));
        titleLinear = (LinearLayout) findViewById(R.id.title_linear);
        text = (TextView) findViewById(R.id.text_des);
        if (getIntent().getStringExtra("remark") != null && !getIntent().getStringExtra("remark").trim().equals("")) {
            text.setText(getIntent().getStringExtra("remark"));
        } else {
            text.setVisibility(View.GONE);
        }

        viewpager.setCurrentItem(picPosition);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    private class ImagePagerAdapter extends FragmentStatePagerAdapter {

        public List<String> fileList;

        public ImagePagerAdapter(FragmentManager fm, List<String> fileList) {
            super(fm);
            this.fileList = fileList;
        }

        @Override
        public int getCount() {
            return fileList == null ? 0 : fileList.size();
        }

        @Override
        public Fragment getItem(int position) {
            String url = fileList.get(position);
            return ImageDetailFragment.newInstance(url);


        }
    }


    public void showTitle() {
        AnimatorSet set = new AnimatorSet();
        // true展示title，默认展示
        if (!set.isRunning()) {
            if (b) {
                set.playTogether(
                        ObjectAnimator.ofFloat(titleLinear, "translationY", 0, -titleHeight),
                        ObjectAnimator.ofFloat(text, "translationY", 0, bottomHeight)
                );
                set.setDuration(400).start();
            } else {
                set.playTogether(
                        ObjectAnimator.ofFloat(titleLinear, "translationY", -titleHeight, 0),
                        ObjectAnimator.ofFloat(text, "translationY", bottomHeight, 0)
                );
                set.setDuration(400).start();
            }
            b = !b;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        titleHeight = titleLinear.getHeight();
        bottomHeight = text.getHeight();
    }

}
