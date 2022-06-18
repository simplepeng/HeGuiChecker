package me.simple.checker;

import android.content.Context;

public class HeGuiChecker {

    public static volatile boolean DEBUG = true;

    public static volatile boolean SHOW_TOAST = true;

    public static volatile boolean SHOW_LOG = true;

    public static volatile boolean isAllow = false;

    static void install(Context context) {
        CheckerHelper.appContext = context.getApplicationContext();
        startHook();
    }

    private static void startHook() {
        try {
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否同意了隐私政策
     */
    public static synchronized void allow(boolean allow) {
        if (allow) {
            SHOW_LOG = false;
            SHOW_TOAST = false;
        } else {
            SHOW_LOG = true;
            SHOW_TOAST = true;
        }
        isAllow = !isAllow;
    }
}
