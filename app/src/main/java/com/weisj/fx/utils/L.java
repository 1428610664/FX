package com.weisj.fx.utils;

import android.util.Log;

/**
 * Created by zh on 16/6/21.
 */
public class L {
    private static String tag = "weishijie";
    private static boolean isdebug = true;

    public static void log_e(String str) {
        if (isdebug)
            Log.e(tag, str);
    }

    public static void log_i(String str) {
        if (isdebug)
            Log.i(tag, str);
    }
}
