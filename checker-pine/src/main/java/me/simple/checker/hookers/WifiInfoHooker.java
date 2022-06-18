package me.simple.checker.hookers;

import android.net.wifi.WifiInfo;

import me.simple.checker.CheckerHelper;
import top.canyie.pine.Pine;
import top.canyie.pine.callback.MethodHook;

public class WifiInfoHooker {

    public static void hook() throws NoSuchMethodException {
        hookGetMacAddress();
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
}
