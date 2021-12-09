package me.simple.checker;

import android.content.Context;

import com.swift.sandhook.SandHook;
import com.swift.sandhook.SandHookConfig;

import me.simple.checker.hookers.SecureHooker;
import me.simple.checker.hookers.TelephonyHooker;
import me.simple.checker.hookers.WifiInfoHooker;

public class HeGuiChecker {

    static void install(Context context) {
        CheckerHelper.appContext = context.getApplicationContext();
        startHook();
    }

    private static void startHook() {
        try {
            SandHookConfig.DEBUG = CheckerConfig.DEBUG;
            SandHook.addHookClass(
                    SecureHooker.class,
                    TelephonyHooker.class,
                    WifiInfoHooker.class
            );
        } catch (Throwable e) {
//            e.printStackTrace();
        }
    }
}
