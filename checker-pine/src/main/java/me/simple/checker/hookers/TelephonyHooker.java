package me.simple.checker.hookers;

import android.annotation.SuppressLint;
import android.telephony.TelephonyManager;

import me.simple.checker.CheckerHelper;
import top.canyie.pine.Pine;
import top.canyie.pine.callback.MethodHook;

public class TelephonyHooker {

    public static void hook() throws NoSuchMethodException {
        hookGetDeviceId();
        hookGetDeviceIdWithInt();

        hookGetImei();
        hookGetImeiWithInt();

        hookGetSubscriberId();
        hookGetSubscriberIdWithInt();
    }

    private static void hookGetDeviceId() throws NoSuchMethodException {
        Pine.hook(TelephonyManager.class.getDeclaredMethod("getDeviceId"), new MethodHook() {
            @Override
            public void beforeCall(Pine.CallFrame callFrame) throws Throwable {
                super.beforeCall(callFrame);
                CheckerHelper.showWarn("getDeviceId");
            }
        });
    }

    private static void hookGetDeviceIdWithInt() throws NoSuchMethodException {
        Pine.hook(TelephonyManager.class.getDeclaredMethod("getDeviceId", int.class), new MethodHook() {
            @Override
            public void beforeCall(Pine.CallFrame callFrame) throws Throwable {
                super.beforeCall(callFrame);
                CheckerHelper.showWarn("getDeviceId");
            }
        });
    }

    private static void hookGetImei() throws NoSuchMethodException {
        Pine.hook(TelephonyManager.class.getDeclaredMethod("getImei"), new MethodHook() {
            @Override
            public void beforeCall(Pine.CallFrame callFrame) throws Throwable {
                super.beforeCall(callFrame);
                CheckerHelper.showWarn("getImei");
            }
        });
    }

    private static void hookGetImeiWithInt() throws NoSuchMethodException {
        Pine.hook(TelephonyManager.class.getDeclaredMethod("getImei", int.class), new MethodHook() {
            @Override
            public void beforeCall(Pine.CallFrame callFrame) throws Throwable {
                super.beforeCall(callFrame);
                CheckerHelper.showWarn("getImei");
            }
        });
    }

    private static void hookGetSubscriberId() throws NoSuchMethodException {
        Pine.hook(TelephonyManager.class.getDeclaredMethod("getSubscriberId"), new MethodHook() {
            @Override
            public void beforeCall(Pine.CallFrame callFrame) throws Throwable {
                super.beforeCall(callFrame);
                CheckerHelper.showWarn("getSubscriberId");
            }
        });
    }

    @SuppressLint("SoonBlockedPrivateApi")
    private static void hookGetSubscriberIdWithInt() throws NoSuchMethodException {
        Pine.hook(TelephonyManager.class.getDeclaredMethod("getSubscriberId", int.class), new MethodHook() {
            @Override
            public void beforeCall(Pine.CallFrame callFrame) throws Throwable {
                super.beforeCall(callFrame);
                CheckerHelper.showWarn("getSubscriberId");
            }
        });
    }
}
