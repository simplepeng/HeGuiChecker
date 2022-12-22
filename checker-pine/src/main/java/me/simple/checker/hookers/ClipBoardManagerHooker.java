package me.simple.checker.hookers;

import android.content.ClipboardManager;
import android.location.LocationListener;
import android.location.LocationManager;

import me.simple.checker.CheckerHelper;
import top.canyie.pine.Pine;
import top.canyie.pine.callback.MethodHook;

public class ClipBoardManagerHooker {

    public static void hook() throws NoSuchMethodException {
        hookHasPrimaryClip();
        hookGetPrimaryClip();
    }

    private static void hookHasPrimaryClip() throws NoSuchMethodException {
        Pine.hook(ClipboardManager.class.getDeclaredMethod("hasPrimaryClip"), new MethodHook() {
            @Override
            public void beforeCall(Pine.CallFrame callFrame) throws Throwable {
                super.beforeCall(callFrame);
                CheckerHelper.showWarn("hasPrimaryClip");
            }
        });
    }

    private static void hookGetPrimaryClip() throws NoSuchMethodException {
        Pine.hook(ClipboardManager.class.getDeclaredMethod("getPrimaryClip"), new MethodHook() {
            @Override
            public void beforeCall(Pine.CallFrame callFrame) throws Throwable {
                super.beforeCall(callFrame);
                CheckerHelper.showWarn("getPrimaryClip");
            }
        });
    }
}
