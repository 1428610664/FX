package com.weisj.fx.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;


import com.weisj.fx.view.chooseimage.ChooseImageActivity;

import java.io.File;


public class CameraUtil {
    private Activity mActivity;
    private Intent intent;
    private Fragment mFragment;
    private ContentResolver contentResolver;

    public CameraUtil(Activity activity) {
        mActivity = activity;
        contentResolver = mActivity.getContentResolver();
    }

    public CameraUtil(Fragment fragment) {
        mFragment = fragment;
        contentResolver = mFragment.getActivity().getContentResolver();
    }

    /**
     * 调用相机
     */
    public void callCamera() {
        boolean dirs = FileUtil.makeDirs(CommenString.tempDir);
        if (dirs) {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
                    CommenString.tempDir, CommenString.imageTakePhotoName)));

            if (mActivity != null) {
                mActivity.startActivityForResult(intent,
                        CommenString.TAKE_PHOTO);
            } else {
                mFragment.startActivityForResult(intent,
                        CommenString.TAKE_PHOTO);
            }
        }
    }

    /**
     * 调用本地图片浏览器
     */
    public void callLocalPhotos(int limit) {
        if (mActivity != null) {
            intent = new Intent(mActivity, ChooseImageActivity.class);
            intent.putExtra("limit", limit);
            mActivity.startActivityForResult(intent, CommenString.LOCAL_PHOTO);
        } else {
            intent = new Intent(mFragment.getActivity(),
                    ChooseImageActivity.class);
            mFragment.startActivityForResult(intent, CommenString.LOCAL_PHOTO);
        }
    }

    /**
     * 调用本地图库
     */
    public void callLocalPhoto() {
        intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        Intent wrapperIntent = Intent.createChooser(intent, null);
        if (mActivity != null) {
            mActivity.startActivityForResult(wrapperIntent,
                    CommenString.LOCAL_PHOTO);
        } else {
            mFragment.startActivityForResult(wrapperIntent,
                    CommenString.LOCAL_PHOTO);
        }
    }

    /**
     * 本地图库调用后裁剪
     *
     * @param uri
     */
    public void photoZoomFromMapStorage(Uri uri, CropperImageScale scale) {
        photoZoom(uri, scale);
    }

    /**
     * 照相过后裁剪
     */
    public void photoZoomFromTake(CropperImageScale scale) {
        photoZoom(getUri(CommenString.imagePath), scale);
    }

    /**
     * 照相过后裁剪
     */
    public void photoZoomFromMapStorage(String path, CropperImageScale scale) {
        if (path.contains("file://")) {
            path = path.substring(7);
        }
        photoZoom(getUri(path), scale);
    }

    private void photoZoom(Uri uri, CropperImageScale scale) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        switch (scale.getIndex()) {
            // 正方形
            case 1:
                // aspectX aspectY 是宽高的比例
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                // outputX outputY 是裁剪图片宽高
                intent.putExtra("outputX", 300);
                intent.putExtra("outputY", 300);
                break;
            // 长方形
            case 2:
                // aspectX aspectY 是宽高的比例
                intent.putExtra("aspectX", 75);
                intent.putExtra("aspectY", 28);
                // outputX outputY 是裁剪图片宽高
                intent.putExtra("outputX", 750);
                intent.putExtra("outputY", 280);
                break;
            default:
                break;
        }

        intent.putExtra("return-data", true);
        if (mActivity != null) {
            mActivity.startActivityForResult(intent, CommenString.PHOTO_RESULT);
        } else {
            mFragment.startActivityForResult(intent, CommenString.PHOTO_RESULT);
        }
    }

    private Uri getUri(String path) {
        try {
//            BitmapFactory.decodeFile(path);
//            Bitmap bitmap = BitmapUtil.getLoacalBitmap2(path);
//            if (bitmap != null) {
//                Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(
//                        contentResolver, bitmap, null, null));
//                if (uri == null) {
            if (!path.contains("file://")) {
                path = "file://" + path;
            }
            return Uri.parse(path);
//                }
//                return uri;
//        }

        } catch (
                Exception e
                )

        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
