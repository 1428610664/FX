package com.weisj.fx.utils;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.weisj.fx.R;

/**
 * ============================================================
 * <p/>
 * 版 权 ： 百变美金 版权所有 (c) 2015
 * <p/>
 * 作 者 : 周章成
 * <p/>
 * 版 本 ： 3.7
 * <p/>
 * 创建日期 ： 2015-7-28 下午2:19:24
 * <p/>
 * 描 述 ： imageLoader
 * <p/>
 * 修订历史 ：
 * <p/>
 * ============================================================
 **/
public class ImageLoaderUtils {

    private static ImageLoader imageloader = ImageLoader.getInstance();
    private static DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.load_default)
            .showImageForEmptyUri(R.mipmap.load_default)
            .showImageOnFail(R.mipmap.load_default).cacheOnDisk(true)
            .cacheInMemory(true).imageScaleType(ImageScaleType.NONE)
            .bitmapConfig(Bitmap.Config.RGB_565).build();
    private static DisplayImageOptions optionsOriginal = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.mipmap.load_default)
            .showImageForEmptyUri(R.mipmap.load_default)
            .showImageOnFail(R.mipmap.load_default).cacheOnDisk(true)
            .cacheInMemory(true).imageScaleType(ImageScaleType.NONE)
            .bitmapConfig(Bitmap.Config.RGB_565).build();
    private static ImageLoaderUtils imageloaderUtil;

    public static ImageLoaderUtils getInstance() {
        if (imageloaderUtil == null) {
            imageloaderUtil = new ImageLoaderUtils();
            initImageLoader(SystemConfig.getContext());
        }
        return imageloaderUtil;
    }

    public static ImageLoader getImageloader() {
        if (imageloaderUtil == null) {
            getInstance();
        }
        return imageloader;
    }

    private ImageLoaderUtils() {
    }

    private String getUrl(String url){
       return  url.split(",")[0];
    }

    /**
     * 加载图片，使用默认的 缓冲图片
     *
     * @param container
     * @param imageUrl
     */
    public void display(ImageView container, String imageUrl) {
        try {
            if (imageUrl != null && (imageUrl.contains("http://")
                    || imageUrl.contains("assets://") ||  imageUrl.contains("https://")
                    || imageUrl.contains("file://"))) {

                imageloader.displayImage(getUrl(imageUrl), container, options);
            } else {
                if (imageUrl != null) {
                    if (!imageUrl.startsWith("/")) {
                        imageUrl = "/" + imageUrl;
                    }
                    imageloader.displayImage(Urls.IP + imageUrl, container, options);
                } else {
                    container.setImageResource(R.mipmap.load_default);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void display(String imageUrl, ImageLoadingListener listen) {

        try {
            if (imageUrl != null && (imageUrl.contains("http://")
                    || imageUrl.contains("assets://")||  imageUrl.contains("https://")
                    || imageUrl.contains("file://"))) {
                imageloader.loadImage(getUrl(imageUrl), listen);
            } else {
                if (imageUrl != null) {
                    if (!imageUrl.startsWith("/")) {
                        imageUrl = "/" + imageUrl;
                    }
                    imageloader.loadImage(Urls.IP + imageUrl, listen);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void display(String imageUrl, ImageSize mimageSize,
                        ImageLoadingListener listen) {

        try {
            if (imageUrl != null && (imageUrl.contains("http://")
                    || imageUrl.contains("assets://")||  imageUrl.contains("https://")
                    || imageUrl.contains("file://"))) {
                imageloader.loadImage(getUrl(imageUrl), mimageSize, listen);
            } else {
                if (!imageUrl.startsWith("/")) {
                    imageUrl = "/" + imageUrl;
                }
                imageloader.loadImage(Urls.IP + imageUrl, mimageSize, listen);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 加载图片，使用默认的 缓冲图片 ，加监听事件
     *
     * @param container
     * @param imageUrl
     */
    public void display(ImageView container, String imageUrl,
                        ImageLoadingListener listen) {
        try {
            if (imageUrl != null && (imageUrl.contains("http://")
                    || imageUrl.contains("assets://")||  imageUrl.contains("https://")
                    || imageUrl.contains("file://"))) {
                imageloader.displayImage(getUrl(imageUrl), container, options, listen);
            } else {
                if (!imageUrl.startsWith("/")) {
                    imageUrl = "/" + imageUrl;
                }
                imageloader.displayImage(Urls.IP + imageUrl, container, options, listen);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 加载图片，使用默认的 缓冲图片 ，加监听事件
     *
     * @param container
     * @param imageUrl
     */
    public void displayOriginal(ImageView container, String imageUrl,
                                ImageLoadingListener listen) {
        try {
            imageloader.displayImage(imageUrl, container, optionsOriginal, listen);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 加载图片，使用自定义的 缓冲图片
     *
     * @param container
     * @param imageUrl
     * @param image     缓冲图片地址
     */
    public void display(ImageView container, String imageUrl, int image) {
        try {
            DisplayImageOptions options2 = new DisplayImageOptions.Builder()
                    .showImageOnLoading(image).showImageOnLoading(image)
                    .showImageForEmptyUri(image).showImageOnFail(image)
                    .cacheOnDisk(true).cacheInMemory(true)
                    .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                    .bitmapConfig(Bitmap.Config.RGB_565).build();
            if (imageUrl != null && imageUrl.contains("http://")
                    || imageUrl.contains("assets://")||  imageUrl.contains("https://")
                    || imageUrl.contains("file://")) {
                imageloader.displayImage(getUrl(imageUrl), container, options2);
            } else {
                if (imageUrl != null) {
                    if (!imageUrl.startsWith("/")) {
                        imageUrl = "/" + imageUrl;
                    }
                    imageloader.displayImage(Urls.IP + imageUrl, container, options2);
                } else {
                    container.setImageResource(R.mipmap.icon);
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 加载图片，使用自定义的 缓冲图片
     *
     * @param container
     * @param imageUrl
     * @param image     缓冲图片地址
     */
    public void display(ImageView container, String imageUrl, int onLoadImage,
                        int onFailImage) {
        try {
            DisplayImageOptions options2 = new DisplayImageOptions.Builder()
                    .showImageOnLoading(onLoadImage)
                    .showImageOnLoading(onLoadImage)
                    .showImageForEmptyUri(onFailImage).showImageOnFail(onFailImage)
                    .cacheOnDisk(true).cacheInMemory(true)
                    .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                    .bitmapConfig(Bitmap.Config.RGB_565).build();
            imageloader.displayImage(imageUrl, container, options2);
            if (imageUrl != null && imageUrl.contains("http://")
                    || imageUrl.contains("assets://")||  imageUrl.contains("https://")
                    || imageUrl.contains("file://")) {
                imageloader.displayImage(getUrl(imageUrl), container, options2);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context,
                "fx/Cache");// 获取到缓存的目录地址
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .threadPoolSize(3)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCacheExtraOptions(480, 800)
                // .memoryCacheSize(30 * 1024 * 1024)
                .discCacheFileCount(100)
                // 缓存的文件数量
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .memoryCache(new WeakMemoryCache())
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
                .diskCacheSize(200 * 1024 * 1024) // 200 Mb
                .diskCache(new UnlimitedDiskCache(cacheDir))// 自定义缓存路径
                // .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(config);
    }

    public Bitmap loadImageSync(String string) {
        // TODO Auto-generated method stub
        return null;
    }

}
