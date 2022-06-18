package me.simple.checker.hookers;

import java.net.NetworkInterface;
import me.simple.checker.CheckerHelper;
import top.canyie.pine.Pine;
import top.canyie.pine.callback.MethodHook;

public class NetWorkHooker {

    public static void hook() throws NoSuchMethodException {
        hookGetHardwareAddress();
    }

    private static void hookGetHardwareAddress() throws NoSuchMethodException {
        Pine.hook(NetworkInterface.class.getDeclaredMethod("getHardwareAddress"), new MethodHook() {
            @Override
            public void beforeCall(Pine.CallFrame callFrame) throws Throwable {
                super.beforeCall(callFrame);
                CheckerHelper.showWarn("getHardwareAddress");
            }
        });
    }
}
