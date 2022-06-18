package me.simple.checker;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;

import me.simple.checker.hookers.SecureHooker;
import top.canyie.pine.Pine;
import top.canyie.pine.PineConfig;
import top.canyie.pine.callback.MethodHook;

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
            PineConfig.debug = true;
            PineConfig.debuggable = true;

//            Pine.hook(TelephonyManager.class.getDeclaredMethod("getDeviceId"), new MethodHook() {
//                @Override
//                public void beforeCall(Pine.CallFrame callFrame) throws Throwable {
//                    super.beforeCall(callFrame);
//                    Log.d("TelephonyManager", "getDeviceId");
//                }
//
//                @Override
//                public void afterCall(Pine.CallFrame callFrame) throws Throwable {
//                    super.afterCall(callFrame);
//                    Log.d("TelephonyManager", "getDeviceId");
//                }
//            });

            SecureHooker.hook();
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
