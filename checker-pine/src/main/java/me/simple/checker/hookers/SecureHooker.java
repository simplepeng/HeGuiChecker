package me.simple.checker.hookers;

import android.content.ContentResolver;
import android.provider.Settings;
import android.text.TextUtils;

import me.simple.checker.CheckerHelper;
import top.canyie.pine.Pine;
import top.canyie.pine.callback.MethodHook;

public class SecureHooker {

    public static void hook() throws NoSuchMethodException {
        hookAndroidId();
    }

    private static void hookAndroidId() throws NoSuchMethodException {
        Pine.hook(Settings.Secure.class.getDeclaredMethod("getString", ContentResolver.class, String.class), new MethodHook() {
            @Override
            public void beforeCall(Pine.CallFrame callFrame) throws Throwable {
                super.beforeCall(callFrame);
                try {
                    if (TextUtils.equals(Settings.Secure.ANDROID_ID, (String) callFrame.args[1])) {
                        CheckerHelper.showWarn("AndroidId");
                    }
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }
}
