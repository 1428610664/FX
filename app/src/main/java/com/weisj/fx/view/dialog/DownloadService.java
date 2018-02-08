package com.weisj.fx.view.dialog;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;
import android.widget.Toast;


import com.weisj.fx.R;
import com.weisj.fx.utils.SystemConfig;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/2/25 0025.
 */
public class DownloadService extends Service {
    private Notification.Builder builder;
    private Notification notification;
    private int lastSize;
    private DownloadFileAsync async;
    private String fileURL;
    private File downLoadFile;
    private boolean isFinish;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        fileURL = intent.getStringExtra("downLoadUrl");
        notification();
        return super.onStartCommand(intent, flags, startId);
    }


    private void notification() {
//        Intent intent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 35, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder = new Notification.Builder(this);
        builder.setLights(-16711936, 300, 1000)
                .setSmallIcon(R.mipmap.small_icon)
                .setTicker("下载提示");
        RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.notification_custom);
        builder.setContent(remoteViews);
//        builder.setContentIntent(pendingIntent);
        builder.setWhen(System.currentTimeMillis());
        builder.setAutoCancel(true);
        notification = builder.build();
        ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(35, notification);
        async = new DownloadFileAsync();
        async.execute();
    }

    /**
     * 安装
     * <p/>
     * 接收外部传进来的context
     */
    private void install() {
        // 核心是下面几句代码
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(downLoadFile),
                "application/vnd.android.package-archive");
        getApplicationContext().startActivity(intent);
    }

    class DownloadFileAsync extends AsyncTask<String, Integer, String> {
        File rootDir = Environment.getExternalStorageDirectory();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(String... aurl) {
            try {
                downLoadFile = new File(rootDir + "/fx/", "new_wsj.apk");
                saveToFile(fileURL, downLoadFile.getPath());
            } catch (Exception e) {
            }
            return null;
        }

        protected void onProgressUpdate(Integer... progress) {
            notification.contentView.setProgressBar(R.id.pd, 100, progress[0], false);
            notification.contentView.setTextViewText(R.id.progress_text, progress[0] + "%");
            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(35, notification);
        }

        @Override
        protected void onPostExecute(String unused) {
            try {
                ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).cancel(35);
                if (isFinish) {
                    install();
                } else {
                    Toast.makeText(getApplicationContext(), "下载失败", Toast.LENGTH_SHORT).show();
                }
                DownloadService.this.stopSelf();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将HTTP资源另存为文件
     *
     * @param destUrl  String
     * @param fileName String
     * @throws Exception
     */
    public void saveToFile(String destUrl, String fileName) throws IOException {
        FileOutputStream fos = null;
        BufferedInputStream bis = null;
        HttpURLConnection httpUrl = null;
        URL url = null;
        byte[] buf = new byte[8096];
        int size = 0;
        int initSize = 0;
        //建立链接
        url = new URL(destUrl);
        httpUrl = (HttpURLConnection) url.openConnection();
        //连接指定的资源
        httpUrl.connect();
        int fileSize = httpUrl.getContentLength();
        //获取网络输入流
        bis = new BufferedInputStream(httpUrl.getInputStream());
        //建立文件
        File file = new File(fileName);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        }
        fos = new FileOutputStream(fileName);
        //保存文件
        while ((size = bis.read(buf)) != -1) {
            initSize += size;
            fos.write(buf, 0, size);
            BigDecimal b = new BigDecimal(initSize / (fileSize * 1.00));
            double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            int alreadlySize = (int) (f1 * 100);
            if (alreadlySize != lastSize) {
                async.onProgressUpdate(alreadlySize);
                lastSize = alreadlySize;
            }
        }
        isFinish = true;
        fos.close();
        bis.close();
        httpUrl.disconnect();
    }


}
