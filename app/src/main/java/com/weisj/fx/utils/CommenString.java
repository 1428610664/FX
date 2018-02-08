package com.weisj.fx.utils;

import android.os.Environment;

import java.io.File;

public class CommenString {
    /**
     * 缓存目录
     */
    public static final String tempDir = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + File.separator + "fx";
    /**
     * 照相请求号：100
     */
    public static final int TAKE_PHOTO = 100;
    /**
     * 本地照片请求号：101
     */
    public static final int LOCAL_PHOTO = 101;
    /**
     * 剪切照片请求号：102
     */
    public static final int PHOTO_RESULT = 102;
    /**
     * 照片名称
     */
    public static final String imageTakePhotoName = "temp.jpg";
    /**
     * 图片路径
     */
    public static final String imagePath = tempDir + File.separator + imageTakePhotoName;


    public static String location;
    public static String address;

    public static double Latitude;
    public static double Longitude;

    public static String url = "url";

    public static String city = "深圳";

    public static String selectCity = "深圳";

    public static boolean locationState = false;
}
