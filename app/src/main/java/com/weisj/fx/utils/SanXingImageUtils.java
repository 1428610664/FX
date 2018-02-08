package com.weisj.fx.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/7 0007.
 */
public class SanXingImageUtils {
    /*读取照片exif信息中的旋转角度
    *
    @param
    path 照片路径
    *
    @return角度
    */

    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    public static Bitmap toturn(Bitmap img, String path) {
        try {
            Matrix matrix = new Matrix();
            matrix.postRotate(+readPictureDegree(path)); /*翻转90度*/
            int width = img.getWidth();
            int height = img.getHeight();
            img = Bitmap.createBitmap(img, 0, 0, width, height, matrix, true);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return img;
    }

}
