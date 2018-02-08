package com.weisj.fx;

import android.app.Application;
import android.content.Context;

import com.umeng.socialize.PlatformConfig;

/**
 * Created by Administrator on 2016/6/27 0027.
 */
public class MyApplication extends Application {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();

        PlatformConfig.setQQZone("1105562634", "noktgEbDLLqsQuUh");
        PlatformConfig.setWeixin("wx005ab789136494c0", "ad9d74847446a17aaee4d9c805c3803f");
        PlatformConfig.setSinaWeibo("476430127", "d49c3c2ba7db9d5a97d5ede968a6be88");
    }
}
