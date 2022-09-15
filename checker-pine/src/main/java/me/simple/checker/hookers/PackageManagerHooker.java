package me.simple.checker.hookers;

import android.content.pm.PackageManager;
import android.location.LocationManager;

import java.lang.reflect.Member;

import me.simple.checker.CheckerHelper;
import top.canyie.pine.Pine;
import top.canyie.pine.callback.MethodHook;

//android.app.ApplicationPackageManager
public class PackageManagerHooker {

    public static void hook() throws NoSuchMethodException {
        hookGetInstalledPackages();
        hookGetInstalledApplications();
    }

    private static void hookGetInstalledPackages() throws NoSuchMethodException {
        try {
            Class clazz = Class.forName("android.app.ApplicationPackageManager");
            Pine.hook(clazz.getMethod("getInstalledPackages", int.class), new MethodHook() {
                @Override
                public void beforeCall(Pine.CallFrame callFrame) throws Throwable {
                    super.beforeCall(callFrame);
                    CheckerHelper.showWarn("getInstalledPackages");
                }
            });
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void hookGetInstalledApplications() throws NoSuchMethodException {
        try {
            Class clazz = Class.forName("android.app.ApplicationPackageManager");
            Pine.hook(clazz.getMethod("getInstalledApplications", int.class), new MethodHook() {
                @Override
                public void beforeCall(Pine.CallFrame callFrame) throws Throwable {
                    super.beforeCall(callFrame);
                    CheckerHelper.showWarn("getInstalledApplications");
                }
            });
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
