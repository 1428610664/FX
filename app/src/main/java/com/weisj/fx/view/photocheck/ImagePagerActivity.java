package com.weisj.fx.view.photocheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.weisj.fx.R;
import com.weisj.fx.view.chooseimage.ChooseImageActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * <p/>
 * 版 权 ： 百变美金 版权所有 (c) 2015
 * <p/>
 * 作 者 : 周章成
 * <p/>
 * 版 本 ： 3.7
 * <p/>
 * 创建日期 ： 2015-7-28 下午3:16:25
 * <p/>
 * 描 述 ： 图片放大缩小功能
 * <p/>
 * 修订历史 ：
 * <p/>
 * ============================================================
 **/
public class ImagePagerActivity extends FragmentActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    public static final String EXTRA_IMAGE_INDEX = "image_index";
    public static final String EXTRA_IMAGE_URLS = "image_urls";
    public static final String EXTRA_IMAGE_SELETE = "image_selete";
    public static final String EXTRA_IMAGE_LIMIT = "image_limit";


    private HackyViewPager mPager;
    private int pagerPosition;
    private ArrayList urls;

    private LinearLayout titleLinear;
    private RelativeLayout bottomRelative;
    private int titleHeight;
    private int bottomHeight;
    private boolean b = true;
    private TextView buttonFinish;
    private TextView imageNumber;
    private LinearLayout chooseImage;
    private ImageView image;
    private List<String> selectList = new ArrayList<String>();
    private int limit = 9;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        setContentView(R.layout.image_detail_pager);
        pagerPosition = getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);
        limit = getIntent().getIntExtra(EXTRA_IMAGE_LIMIT, 9);
        if (getIntent().getExtras() != null) {
            urls = getIntent().getExtras().getParcelableArrayList(EXTRA_IMAGE_URLS);
            ArrayList arrayList = getIntent().getExtras().getParcelableArrayList(EXTRA_IMAGE_SELETE);
            if (arrayList != null) {
                convert(arrayList);
            }
        }
        titleLinear = (LinearLayout) findViewById(R.id.image_title_linear);
        bottomRelative = (RelativeLayout) findViewById(R.id.image_bottom_relative);
        chooseImage = (LinearLayout) findViewById(R.id.choose_image);
        chooseImage.setOnClickListener(this);
        image = (ImageView) findViewById(R.id.image_checkbox);

        findViewById(R.id.back).setOnClickListener(this);
        buttonFinish = (TextView) findViewById(R.id.button_finish);
        buttonFinish.setSelected(true);
        buttonFinish.setOnClickListener(this);
        if (selectList.size() == 0) {
            buttonFinish.setText("完成");
        } else {
            buttonFinish.setText(String.format("完成(%d/%d)", selectList.size(), limit));
        }
        imageNumber = (TextView) findViewById(R.id.image_number);
        mPager = (HackyViewPager) findViewById(R.id.pager);
        ImagePagerAdapter mAdapter = new ImagePagerAdapter(
                getSupportFragmentManager(), urls);
        mPager.setAdapter(mAdapter);
        mPager.setOnPageChangeListener(this);
        if (selectList.contains(urls.get(pagerPosition))) {
            image.setSelected(true);
        } else {
            image.setSelected(false);
        }
        mPager.setCurrentItem(pagerPosition);
        imageNumber.setText(((mPager.getCurrentItem() + 1) + "/" + urls.size()));
    }

    private void convert(ArrayList<String> list) {
        selectList = list;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                clickFinish();
                finish();
                break;
            // 完成
            case R.id.button_finish:
                Intent intent = new Intent();
                if (selectList.size() == 0) {
                    selectList.add((String) urls.get(mPager.getCurrentItem()));
                }
                intent.putStringArrayListExtra("paths", (ArrayList<String>) selectList);
                intent.setAction(ChooseImageActivity.BROADCAST_ACTION);
                this.sendBroadcast(intent);
                finish();
                break;
            case R.id.choose_image:
                if (image.isSelected()) {
                    selectList.remove(urls.get(mPager.getCurrentItem()));
                } else {
                    if (selectList.size() >= limit) {
                        Toast.makeText(ImagePagerActivity.this, "最多只能选择" + limit + "张图片", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    selectList.add((String) urls.get(mPager.getCurrentItem()));
                }
                image.setSelected(!image.isSelected());
                if (selectList.size() == 0) {
                    buttonFinish.setText("完成");
                } else {
                    buttonFinish.setText(String.format("完成(%d/%d)", selectList.size(), limit));
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        clickFinish();
        super.onBackPressed();
    }

    private void clickFinish() {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(ChooseImageActivity.SELETEIMAGE, (ArrayList<String>) selectList);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (selectList.contains(urls.get(position))) {
            image.setSelected(true);
        } else {
            image.setSelected(false);
        }
        imageNumber.setText((++position) + "/" + urls.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void showTitle() {
        AnimatorSet set = new AnimatorSet();
        // true展示title，默认展示
        if (!set.isRunning()) {
            if (b) {
                set.playTogether(
                        ObjectAnimator.ofFloat(titleLinear, "translationY", 0, -titleHeight),
                        ObjectAnimator.ofFloat(bottomRelative, "translationY", 0, bottomHeight)
                );
                set.setDuration(400).start();
            } else {
                set.playTogether(
                        ObjectAnimator.ofFloat(titleLinear, "translationY", -titleHeight, 0),
                        ObjectAnimator.ofFloat(bottomRelative, "translationY", bottomHeight, 0)
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
        bottomHeight = bottomRelative.getHeight();
    }
}