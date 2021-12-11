package me.simple.checker;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.swift.sandhook.SandHook;
import com.swift.sandhook.SandHookConfig;

import me.simple.checker.hookers.LocationManagerHooker;
import me.simple.checker.hookers.NetworkHooker;
import me.simple.checker.hookers.SecureHooker;
import me.simple.checker.hookers.TelephonyHooker;
import me.simple.checker.hookers.WifiInfoHooker;

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
            int systemVersion = Build.VERSION.SDK_INT;
            if (systemVersion < Build.VERSION_CODES.LOLLIPOP || systemVersion > Build.VERSION_CODES.R) {
                Log.d(CheckerHelper.TAG, "不支持的系统版本 - " + systemVersion);
                return;
            }

            SandHookConfig.DEBUG = HeGuiChecker.DEBUG;
            SandHook.addHookClass(
                    SecureHooker.class,
                    TelephonyHooker.class,
                    WifiInfoHooker.class,
                    NetworkHooker.class,
                    LocationManagerHooker.class
            );
        } catch (Throwable e) {
//            e.printStackTrace();
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
