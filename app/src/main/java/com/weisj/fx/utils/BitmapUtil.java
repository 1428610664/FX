package com.weisj.fx.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;

public class BitmapUtil {
    public static String SDPATH = Environment.getExternalStorageDirectory()
            + "/formats/";
    public static String SDPATH1 = Environment.getExternalStorageDirectory()
            + "/myimages/";

    /**
     * 通过地址获取bitmap
     *
     * @param url
     * @return
     */
    public static Bitmap getLoacalBitmap(String path) {
        return getLoacalBitmap2(path);
    }

    /**
     * 通过默认相机地址获取bitmap
     *
     * @param url
     * @return
     */
    public static Bitmap getLoacalBitmap() {
        return getLoacalBitmap2(CommenString.imagePath);
    }

    public static Bitmap getLoacalBitmap2(String path) {
        try {
            if (path.contains("file://")) {
                path = path.substring(7);
            }
            BufferedInputStream in = new BufferedInputStream(
                    new FileInputStream(new File(path)));

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            // Bitmap btBitmap=BitmapFactory.decodeFile(path);
            // System.out.println("原尺寸高度："+btBitmap.getHeight());
            // System.out.println("原尺寸宽度："+btBitmap.getWidth());
            BitmapFactory.decodeStream(in, null, options);
            in.close();
            int i = 0;
            Bitmap bitmap = null;
            while (true) {
                if ((options.outWidth >> i <= 600)
                        && (options.outHeight >> i <= 600)) {
                    in = new BufferedInputStream(new FileInputStream(new File(
                            path)));
                    options.inSampleSize = (int) Math.pow(2.0D, i);
                    options.inJustDecodeBounds = false;
                    bitmap = BitmapFactory.decodeStream(in, null, options);
                    break;
                }
                i += 1;
            }
            return bitmap;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap getLoacalBitmap3(String path) {
        try {
            if (path.contains("file://")) {
                path = path.substring(7);
            }
            BufferedInputStream in = new BufferedInputStream(
                    new FileInputStream(new File(path)));

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            // Bitmap btBitmap=BitmapFactory.decodeFile(path);
            // System.out.println("原尺寸高度："+btBitmap.getHeight());
            // System.out.println("原尺寸宽度："+btBitmap.getWidth());
            BitmapFactory.decodeStream(in, null, options);
            in.close();
            int i = 0;
            Bitmap bitmap = null;
            while (true) {
                if ((options.outWidth >> i <= 400)
                        || (options.outHeight >> i <= 400)) {
                    in = new BufferedInputStream(new FileInputStream(new File(
                            path)));
                    options.inSampleSize = (int) Math.pow(2.0D, i);
                    options.inJustDecodeBounds = false;
                    bitmap = BitmapFactory.decodeStream(in, null, options);
                    break;
                }
                i += 1;
            }
            return ImageCrop(bitmap);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap getLoacalBitmap4(String path) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
            Bitmap bitmap=BitmapFactory.decodeStream(fis);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 按正方形裁切图片
     */
    public static Bitmap ImageCrop(Bitmap bitmap) {
        int w = bitmap.getWidth(); // 得到图片的宽，高
        int h = bitmap.getHeight();
        int wh = w > h ? h : w;// 裁切后所取的正方形区域边长

        int retX = w > h ? (w - h) / 2 : 0;//基于原图，取正方形左上角x坐标
        int retY = w > h ? 0 : (h - w) / 2;
        //下面这句是关键
        return Bitmap.createBitmap(bitmap, retX, retY, wh, wh, null, false);
    }

    /**
     * 得到一个有水印的文字的原图
     *
     * @param str
     * @param bitmap
     * @return
     */
    public static Bitmap getStrBitmap(String str, Bitmap bitmap) {
        int leftPad = 10;
        int topPad = 8;
        Bitmap imgTemp = null;
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(14.0F);
        textPaint.setColor(Color.WHITE);
        textPaint.setFilterBitmap(true);
        float length = textPaint.measureText(str);
        String str1 = null;
        if (length > (bitmap.getWidth() - leftPad * 2)) {
            str1 = TextUtils.ellipsize(str, textPaint, bitmap.getWidth() * 2 - leftPad * 4, TextUtils.TruncateAt.END).toString();
            imgTemp = Bitmap.createBitmap(bitmap.getWidth(), 50, Bitmap.Config.ARGB_4444);
        } else {
            str1 = str;
            imgTemp = Bitmap.createBitmap(bitmap.getWidth(), 30, Bitmap.Config.ARGB_4444);
        }
        Canvas canvas = new Canvas(imgTemp);
        Paint paint = new Paint(); // 建立画笔
        paint.setDither(true);
        paint.setFilterBitmap(true);
        paint.setColor(Color.parseColor("#000000"));
        paint.setAlpha(120);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), imgTemp.getHeight());
        canvas.drawRect(rect, paint);
        StaticLayout sl = new StaticLayout(str1, textPaint, bitmap.getWidth() - leftPad * 2, Layout.Alignment.ALIGN_CENTER, 1.3f, 0.0f, false);
        canvas.translate(leftPad, topPad);
        sl.draw(canvas);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        return createBitmap(bitmap, imgTemp);
    }

    /**
     * 使用完水印就回收掉
     *
     * @param src
     * @param watermark
     * @return
     */
    public static Bitmap createBitmap(Bitmap src, Bitmap watermark) {
        String tag = "createBitmap";
        // Log.d( tag, "create a new bitmap" );
        if (src == null) {
            return null;
        }
        int w = src.getWidth();
        int h = src.getHeight();
        int ww = watermark.getWidth();
        int wh = watermark.getHeight();
        //create the new blank bitmap
        Bitmap newb = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_4444);
        //创建一个新的和SRC长度宽度一样的位图
        Canvas cv = new Canvas(newb);
        //draw src into
        cv.drawBitmap(src, 0, 0, null);//在 0，0坐标开始画入src
        //draw watermark into
        cv.drawBitmap(watermark, 0, h - wh, null);//在src的右下角画入水印
        //save all clip
        cv.save(Canvas.ALL_SAVE_FLAG);//保存
        //store
        cv.restore();//存储
        watermark.recycle();
        return newb;
    }


    /**
     * bitmap转换成文件
     *
     * @param bitmap
     * @return
     */
    public static File saveBitmapFile(Bitmap bitmap) {
        File picFileDir = new File(Environment.getExternalStorageDirectory()
                + File.separator + "FX");// 将要保存图片的路径

        if (!picFileDir.exists()) {
            picFileDir.mkdir();// 如果路径不存在就先创建路径
        }
        File picFile = new File(picFileDir, "temp.jpg");
        try {

            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(picFile));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return picFile;
    }

    /**
     * bitmap转换成文件
     *
     * @param bitmap
     * @return
     */
    public static File saveBitmapFile(Bitmap bitmap, String name) {
        File picFileDir = new File(Environment.getExternalStorageDirectory()
                + File.separator + "FX");// 将要保存图片的路径




        if (!picFileDir.exists()) {
            picFileDir.mkdir();// 如果路径不存在就先创建路径
        }
        File picFile = new File(picFileDir, name + ".jpg");
        try {

            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(picFile));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return picFile;
    }

    public static boolean isFileExist(String fileName) {
        File file = new File(SDPATH + fileName);
        file.isFile();
        return file.exists();
    }

    /**
     * @param x              图像的宽度
     * @param y              图像的高度
     * @param image          源图片
     * @param outerRadiusRat 圆角的大小
     * @return 圆角图片
     */
    public static Bitmap createFramedPhoto(int x, int y, Bitmap image, float outerRadiusRat) {
        // 根据源文件新建一个darwable对象
        Drawable imageDrawable = new BitmapDrawable(image);

        // 新建一个新的输出图片
        Bitmap output = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        // 新建一个矩形
        RectF outerRect = new RectF(0, 0, x, y);

        // 产生一个红色的圆角矩形
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        canvas.drawRoundRect(outerRect, outerRadiusRat, outerRadiusRat, paint);

        // 将源图片绘制到这个圆角矩形上
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        imageDrawable.setBounds(0, 0, x, y);
        canvas.saveLayer(outerRect, paint, Canvas.ALL_SAVE_FLAG);
        imageDrawable.draw(canvas);
        canvas.restore();

        return output;
    }

    public static File createSDDir(String dirName) {
        try {
            File dir = new File(SDPATH + dirName);
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                dir.mkdirs();
//			System.out.println("createSDDir:" + dir.getAbsolutePath());
//			System.out.println("createSDDir:" + dir.mkdir());
            }
            return dir;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteDir(String path) {
        File dir = new File(path);
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;

        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDir(path); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }


}
