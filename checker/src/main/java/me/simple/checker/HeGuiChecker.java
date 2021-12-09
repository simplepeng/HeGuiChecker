package me.simple.checker;

import android.content.Context;

import com.swift.sandhook.SandHook;
import com.swift.sandhook.SandHookConfig;

import me.simple.checker.hookers.AndroidIdHooker;

public class HeGuiChecker {

    static void install(Context context) {
        startHook();
    }

    private static void startHook() {
        try {
            SandHookConfig.DEBUG = CheckerConfig.DEBUG;
            SandHook.addHookClass(AndroidIdHooker.class);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
