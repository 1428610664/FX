package com.weisj.fx.view.chooseimage;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.weisj.fx.R;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.view.ImageBottomPopWindow;
import com.weisj.fx.view.RootView;
import com.weisj.fx.view.photocheck.ImagePagerActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ChooseImageActivity extends BaseActivity implements View.OnClickListener, ImageBottomPopWindow.PopupListener {
    public static final String SELETEIMAGE = "selete_image";
    public static final String BROADCAST_ACTION = "com.powerthink.jdx.view.choosenewimageactivity";
    private HashMap<String, List<String>> mGruopMap = new HashMap<String, List<String>>();
    private final static int SCAN_OK = 1;
    private RecyclerView recyclerView;
    private List<String> allPath = new ArrayList<String>();
    private ChooseImageAdapter adapter;
    private TextView buttonFinish;
    private List<String> selectList = new ArrayList<String>();
    private LinearLayout choosePhotoAlbum;
    private ImageBottomPopWindow imageBottomPop;
    private TextView imagePreView;
    private int height;
    private int currentItem = 0;
    private int limit = 9;
    private MyBroadcastReceiver mBroadcastReceiver;
    private TextView photoAlbumName;
    private int lastScrollY;

    public int getLimit() {
        return limit;
    }

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SCAN_OK:
                    // 关闭进度条
                    showLoadFinish();
                    adapter = new ChooseImageAdapter(ChooseImageActivity.this, allPath);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new GridLayoutManager(ChooseImageActivity.this, 3));
                    break;
            }
        }

    };


    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_choose_new_image, null);
        rootView.isHintHeadBar(true);
        limit = getIntent().getIntExtra("limit", 9);
        initView(view);
        getImages();
        mBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_ACTION);
        registerReceiver(mBroadcastReceiver, intentFilter);
        return view;
    }

    @Override
    public String setTitleStr() {
        return "";
    }

    @Override
    public void getRefreshData() {

    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setOnScrollListener(new mScrollListener());
        view.findViewById(R.id.back).setOnClickListener(this);
        buttonFinish = (TextView) view.findViewById(R.id.button_finish);
        buttonFinish.setOnClickListener(this);
        choosePhotoAlbum = (LinearLayout) view.findViewById(R.id.choose_photo_album);
        choosePhotoAlbum.setOnClickListener(this);
        imagePreView = (TextView) view.findViewById(R.id.choose_preview);
        imagePreView.setOnClickListener(this);
        photoAlbumName = (TextView)view.findViewById(R.id.choose_photo_text);
    }

    /**
     * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中
     */
    private void getImages() {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "暂无外部存储", Toast.LENGTH_SHORT).show();
            return;
        }

        showLoading();

        new Thread(new Runnable() {

            @Override
            public void run() {
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = ChooseImageActivity.this
                        .getContentResolver();

                // 只查询jpeg和png的图片
                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED);

                while (mCursor.moveToNext()) {
                    // 获取图片的路径
                    String path = "file://" + mCursor.getString(mCursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));

                    // 获取该图片的父路径名
                    String parentName = new File(path).getParentFile()
                            .getName();

                    // 根据父路径名将图片放入到mGruopMap中
                    if (!mGruopMap.containsKey(parentName)) {
                        List<String> chileList = new ArrayList<String>();
                        chileList.add(path);
                        mGruopMap.put(parentName, chileList);
                    } else {
                        mGruopMap.get(parentName).add(path);
                    }
                    allPath.add(path);
                }

                mCursor.close();

                // 通知Handler扫描图片完成
                mHandler.sendEmptyMessage(SCAN_OK);

            }
        }).start();

    }

    public List<String> getSelectList() {
        return selectList;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data.getExtras() != null) {
                selectList = data.getExtras().getStringArrayList(SELETEIMAGE);
                recyclerView.getAdapter().notifyDataSetChanged();
                convertData();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            // 完成
            case R.id.button_finish:
                if (v.isSelected()) {
                    Intent intent = new Intent();
                    intent.putStringArrayListExtra("paths", (ArrayList<String>) selectList);
                    ChooseImageActivity.this.setResult(RESULT_OK, intent);
                    finish();
                }
                break;
            case R.id.choose_photo_album:
                if (allPath.size() > 0) {
                    if (imageBottomPop == null) {
                        imageBottomPop = new ImageBottomPopWindow(this, this, mGruopMap, allPath.get(0), allPath.size());
                    }
                    imageBottomPop.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, height);
                }
                break;
            case R.id.choose_preview:
                if (selectList.size() > 0) {
                    Intent intent = new Intent(this, ImagePagerActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(ImagePagerActivity.EXTRA_IMAGE_URLS, (ArrayList) selectList);
                    bundle.putParcelableArrayList(ImagePagerActivity.EXTRA_IMAGE_SELETE, (ArrayList) selectList);
                    intent.putExtras(bundle);
                    this.startActivityForResult(intent, 0);
                }
                break;
        }
    }

    public boolean chooseImage(String position) {
        if (selectList.contains(position)) {
            selectList.remove(position);
        } else {
            if (selectList.size() < limit) {
                selectList.add(position);
            } else {
                Toast.makeText(ChooseImageActivity.this, "最多只能选择" + limit + "张图片", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        convertData();
        return true;
    }

    private void convertData() {
        if (selectList.size() == 0) {
            buttonFinish.setText("完成");
            buttonFinish.setSelected(false);
            imagePreView.setSelected(false);
            imagePreView.setText("预览");
        } else {
            buttonFinish.setText(String.format("完成(%d/%d)", selectList.size(), limit));
            buttonFinish.setSelected(true);
            imagePreView.setSelected(true);
            imagePreView.setText(String.format("预览(%d/%d)", selectList.size(), limit));
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        height = choosePhotoAlbum.getHeight();
    }

    @Override
    public void onItemClick(View v, int position, String flag) {
        if (currentItem != position) {
            currentItem = position;
            if (position == 0) {
                adapter.refreshData(allPath);
            } else {
                adapter.refreshData(mGruopMap.get(flag));
            }
            photoAlbumName.setText(flag);
        }
    }


    private class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ChooseImageActivity.this.setResult(RESULT_OK, intent);
            ChooseImageActivity.this.finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
        ImageLoaderUtils.getImageloader().clearMemoryCache();
    }

    private class mScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            switch (newState) {
                case RecyclerView.SCROLL_STATE_IDLE:
                    if (ImageLoader.getInstance().isInited()) {
                        ImageLoader.getInstance().resume();
                    }
                    break;
                case RecyclerView.SCROLL_STATE_DRAGGING:
                    if (ImageLoader.getInstance().isInited()) {
                        ImageLoader.getInstance().resume();
                    }
                    break;
                // 快速滑动
                case RecyclerView.SCROLL_STATE_SETTLING:
                    if (ImageLoader.getInstance().isInited()) {
                        ImageLoader.getInstance().pause();
                    }
                    break;
            }
        }

    }


}
