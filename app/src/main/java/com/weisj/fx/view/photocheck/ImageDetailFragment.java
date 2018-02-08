package com.weisj.fx.view.photocheck;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.weisj.fx.R;
import com.weisj.fx.utils.ImageLoaderUtils;
import com.weisj.fx.utils.SanXingImageUtils;

public class ImageDetailFragment extends Fragment {
    private String mImageUrl;
    private ImageView mImageView;
    private ProgressBar progressBar;
    private PhotoViewAttacher mAttacher;
    private View v;
    private ImageSize imageSize = new ImageSize(500, 500);

    public static ImageDetailFragment newInstance(String imageUrl) {
        final ImageDetailFragment f = new ImageDetailFragment();

        final Bundle args = new Bundle();
        args.putString("url", imageUrl);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageUrl = getArguments() != null ? getArguments().getString("url") : null;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (v == null) {
            v = inflater.inflate(R.layout.image_detail_fragment, container, false);
            mImageView = (ImageView) v.findViewById(R.id.image);
            mAttacher = new PhotoViewAttacher(mImageView);
            if (getActivity() instanceof ImagePagerActivity) {
                mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {

                    @Override
                    public void onPhotoTap(View arg0, float arg1, float arg2) {
                        ((ImagePagerActivity) getActivity()).showTitle();
                    }
                });
            } else if (getActivity() instanceof WebImageCheckActivity) {
                mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {

                    @Override
                    public void onPhotoTap(View arg0, float arg1, float arg2) {
                        ((WebImageCheckActivity) getActivity()).showTitle();
                    }
                });
            }
            progressBar = (ProgressBar) v.findViewById(R.id.loading);
        }
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageLoaderUtils.getInstance().display(mImageUrl, imageSize, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                try {
                    String message = null;
                    switch (failReason.getType()) {
                        case IO_ERROR:
                            message = "下载错误";
                            break;
                        case DECODING_ERROR:
                            message = "图片无法显示";
                            break;
                        case NETWORK_DENIED:
                            message = "网络有问题，无法下载";
                            break;
                        case OUT_OF_MEMORY:
                            message = "图片太大无法显示";
                            break;
                        case UNKNOWN:
                            message = "未知的错误";
                            break;
                    }
                    try {
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    progressBar.setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                try {
                    if (loadedImage != null && !loadedImage.isRecycled()) {
                        progressBar.setVisibility(View.GONE);
                        if (imageUri.contains("file://")) {
                            imageUri = imageUri.substring(7);
                        }
                        Bitmap bitmap = SanXingImageUtils.toturn(loadedImage, imageUri);
                        mImageView.setImageBitmap(bitmap);
                        mAttacher.update();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAttacher.setOnPhotoTapListener(null);
        recycleImageView(mImageView);
        mImageView = null;
        mAttacher = null;
    }

    /**
     * 回收ImageView占用的图像内存;
     *
     * @param view
     */
    public static void recycleImageView(View view) {
        if (view == null) return;
        if (view instanceof ImageView) {
            Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable != null && drawable instanceof BitmapDrawable) {
                Bitmap bmp = ((BitmapDrawable) drawable).getBitmap();
                if (bmp != null && !bmp.isRecycled()) {
                    ((ImageView) view).setImageBitmap(null);
                    bmp.recycle();
                }
            }
        }
    }

}
