package com.weisj.fx.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.weisj.fx.R;
import com.weisj.fx.adapter.AddPhotoAdapter;
import com.weisj.fx.base.BaseActivity;
import com.weisj.fx.presenter.BecomeShopPresenter;
import com.weisj.fx.utils.BitmapUtil;
import com.weisj.fx.utils.CameraUtil;
import com.weisj.fx.utils.CommenString;
import com.weisj.fx.utils.FileUtil;
import com.weisj.fx.utils.KeyboardUtil;
import com.weisj.fx.utils.SystemConfig;
import com.weisj.fx.view.CustomBottomPopWindow;
import com.weisj.fx.viewinterface.IBecomeShopView;

import java.io.File;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class BecomeShopActivity extends BaseActivity implements AddPhotoAdapter.AddListener, CustomBottomPopWindow.PopupListener, View.OnClickListener, IBecomeShopView {
    private List<Bitmap> bitmapList = new ArrayList<>();
    private CustomBottomPopWindow callTakePhotoOrLocalPhoto;
    private CameraUtil cameraUtil;
    private final int limit = 4;
    private EditText cityEdit, nameEdit, mainProductEdit, telEdit;
    private BecomeShopPresenter presenter;
    private long currentTime = 0;
    private RecyclerView recyclerView;

    @Override
    public View initView(Bundle savedInstanceState) {
        View view = mLayoutInflater.inflate(R.layout.activity_become_shop, null);
        initView(view);
        cameraUtil = new CameraUtil(this);
        return view;
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setAdapter(new AddPhotoAdapter(this, bitmapList, this));
        cityEdit = (EditText) view.findViewById(R.id.shop_city);
        cityEdit.setText(CommenString.selectCity);
        nameEdit = (EditText) view.findViewById(R.id.shop_name);
        telEdit = (EditText) view.findViewById(R.id.shop_tel);
        mainProductEdit = (EditText) view.findViewById(R.id.shop_main_product);
        view.findViewById(R.id.shop_submit).setOnClickListener(this);
        view.findViewById(R.id.city_linear).setOnClickListener(this);
    }


    @Override
    public String setTitleStr() {
        return "成为商家";
    }

    @Override
    public void getRefreshData() {

    }

    @Override
    public void onclick(View view) {
        if (callTakePhotoOrLocalPhoto == null) {
            callTakePhotoOrLocalPhoto = new CustomBottomPopWindow(this, this);
        }
        callTakePhotoOrLocalPhoto.show(this);
    }

    @Override
    public void onItemClick(View v, int position, int flag) {
        if (position == 0) {
            cameraUtil.callCamera();
        } else {
            cameraUtil.callLocalPhotos(getLimit());
        }
    }

    public int getLimit() {
        return limit - bitmapList.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.city_linear:
                cityEdit.setSelection(cityEdit.getText().length());
                KeyboardUtil.openKeyBoard(cityEdit, this);
                break;
            case R.id.shop_submit:
                if (presenter == null) {
                    presenter = new BecomeShopPresenter(this, this);
                }
                if (System.currentTimeMillis() - currentTime > 500) {
                    presenter.upload();
                } else {
                    currentTime = System.currentTimeMillis();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CommenString.LOCAL_PHOTO) {
                if (data != null && data.getExtras() != null) {
                    ArrayList<String> paths = data
                            .getStringArrayListExtra("paths");
                    for (int i = 0; i < paths.size(); i++) {
                        Bitmap bitmap = null;
                        try {
                            bitmap = BitmapUtil.getLoacalBitmap(paths.get(i));
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        bitmapList.add(bitmap);
                    }
                }
            }
            if (requestCode == CommenString.TAKE_PHOTO) {
                Bitmap bitmap = null;
                try {
                    bitmap = BitmapUtil.getLoacalBitmap();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (bitmap != null) {
                    bitmapList.add(bitmap);
                }
            }
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }


    @Override
    public String getCity() {
        return cityEdit.getText().toString();
    }

    @Override
    public String getTel() {
        return telEdit.getText().toString();
    }

    @Override
    public String getName() {
        return nameEdit.getText().toString();
    }

    @Override
    public String getMainProduct() {
        return mainProductEdit.getText().toString();
    }

    @Override
    public void becomeSuccess() {
        SystemConfig.showToast("提交成功");
        finish();
    }

    @Override
    public List<File> getImages() {
        List<File> list = new ArrayList<>();
        for (Bitmap bitmap : bitmapList) {
            File file = BitmapUtil.saveBitmapFile(bitmap, String.valueOf(System.currentTimeMillis()));
            list.add(file);
        }
        return list;
    }
}
