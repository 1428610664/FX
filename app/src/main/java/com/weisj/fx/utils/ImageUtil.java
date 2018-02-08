package com.weisj.fx.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Environment;
import android.view.View;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ImageUtil {
    private int imageWidth = 320;
    private ImageFinishListener listener;
    private int imageCount;
    private Map<Integer, Bitmap> bitmapList = new HashMap<>();
    private String url;

    public ImageUtil(ImageFinishListener listener) {
        this.listener = listener;
    }

    public native String compressionImage(Bitmap bitmap, int w, int h, int quality, byte[] fileNmae, boolean optimize);

    static {
        System.loadLibrary("jpeg");
        System.loadLibrary("ImageCompre");
    }

    public void getShareImage(List<String> imageBitmap, String url) {
        this.url = url;
        bitmapList.clear();
        imageCount = imageBitmap.size();
        for (int i = 0; i < imageCount; i++) {
            downImage(imageBitmap.get(i),i);
        }
    }

    private void createImage() {
//        imageWidth = bitmapList.get(0).getWidth();
        Bitmap bitmap = Bitmap.createBitmap(imageWidth, imageWidth * (imageCount + 1), Bitmap.Config.ARGB_4444);
        // 新建一个画布
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        for (int i = 0; i < imageCount; i++) {
            canvas.drawBitmap(bitmapList.get(i), null, new Rect(0, imageWidth * i, imageWidth, imageWidth * (i + 1)), paint);
        }
        canvas.drawBitmap(QRCodeUtil.createQRImage(url, imageWidth, imageWidth, null), null, new Rect(0, imageWidth * imageCount, imageWidth, imageWidth * (imageCount + 1)), paint);
        canvas.save();
        canvas.restore();
        File picFileDir = new File(Environment.getExternalStorageDirectory()
                + File.separator + "FX");// 将要保存图片的路径
        if (!picFileDir.exists()) {
            picFileDir.mkdir();// 如果路径不存在就先创建路径
        }
        File picFile = new File(picFileDir, "temp.jpg");
        String str = compressionImage(bitmap, bitmap.getWidth(), bitmap.getHeight(), 80, picFile.getAbsolutePath().getBytes(), true);
        if (listener != null) {
            listener.finishImage(picFile.getPath());
        }
    }


    private void downImage(String imageUrl,final int i) {
        ImageLoaderUtils.getInstance().display(imageUrl, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                if (listener != null) {
                    listener.fail();
                }
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                bitmapList.put(i,loadedImage);
                if (bitmapList.size() == imageCount) {
                    createImage();
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                if (listener != null) {
                    listener.fail();
                }
            }
        });
    }

    public interface ImageFinishListener {
        void finishImage(String fileAddr);

        void fail();
    }
}
