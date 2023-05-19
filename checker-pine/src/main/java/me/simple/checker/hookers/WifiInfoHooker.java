package me.simple.checker.hookers;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import me.simple.checker.CheckerHelper;
import top.canyie.pine.Pine;
import top.canyie.pine.callback.MethodHook;

public class WifiInfoHooker {

    public static void hook() throws NoSuchMethodException {
        hookGetWifiInfo();
        hookGetMacAddress();
        hookGetSSID();
    }

    private static void hookGetWifiInfo() throws NoSuchMethodException {
        Pine.hook(WifiManager.class.getDeclaredMethod("getConnectionInfo"), new MethodHook() {
            @Override
            public void beforeCall(Pine.CallFrame callFrame) throws Throwable {
                super.beforeCall(callFrame);
                CheckerHelper.showWarn("getConnectionInfo");
            }
        });
    }

    private static void hookGetMacAddress() throws NoSuchMethodException {
        Pine.hook(WifiInfo.class.getDeclaredMethod("getMacAddress"), new MethodHook() {
            @Override
            public void beforeCall(Pine.CallFrame callFrame) throws Throwable {
                super.beforeCall(callFrame);
                CheckerHelper.showWarn("getMacAddress");
            }
        });
    }

    private static void hookGetSSID() throws NoSuchMethodException {
        Pine.hook(WifiInfo.class.getDeclaredMethod("getSSID"), new MethodHook() {
            @Override
            public void beforeCall(Pine.CallFrame callFrame) throws Throwable {
                super.beforeCall(callFrame);
                CheckerHelper.showWarn("getSSID");
            }
        });
    }
}
