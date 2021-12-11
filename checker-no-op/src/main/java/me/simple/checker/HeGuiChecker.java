package me.simple.checker;

import android.content.Context;

public class HeGuiChecker {

    public static volatile boolean DEBUG = true;

    public static volatile boolean SHOW_TOAST = true;

    public static volatile boolean SHOW_LOG = true;

    public static volatile boolean isAllow = false;

    static void install(Context context) {
        
    }

    private static void startHook() {

    }

    /**
     * 是否同意了隐私政策
     */
    public static synchronized void allow(boolean allow) {
        isAllow = !isAllow;
    }
}
