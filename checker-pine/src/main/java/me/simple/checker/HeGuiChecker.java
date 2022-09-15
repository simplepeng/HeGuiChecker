package me.simple.checker;

import android.content.Context;

import me.simple.checker.hookers.LocationHooker;
import me.simple.checker.hookers.NetWorkHooker;
import me.simple.checker.hookers.PackageManagerHooker;
import me.simple.checker.hookers.SecureHooker;
import me.simple.checker.hookers.TelephonyHooker;
import me.simple.checker.hookers.WifiInfoHooker;
import top.canyie.pine.PineConfig;

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
            PineConfig.debug = DEBUG;
            PineConfig.debuggable = DEBUG;

            SecureHooker.hook();
            TelephonyHooker.hook();
            NetWorkHooker.hook();
            TelephonyHooker.hook();
            WifiInfoHooker.hook();
            LocationHooker.hook();
            PackageManagerHooker.hook();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 是否同意了隐私政策
     */
    public static synchronized void allow(boolean allow) {
        SHOW_LOG = !allow;
        SHOW_TOAST = !allow;
        isAllow = allow;
    }
}
