package me.simple.checker.hookers;

import android.location.LocationListener;
import android.location.LocationManager;

import me.simple.checker.CheckerHelper;
import top.canyie.pine.Pine;
import top.canyie.pine.callback.MethodHook;

public class LocationHooker {

    public static void hook() throws NoSuchMethodException {
        hookGetLastKnownLocation();
        hookRequestLocationUpdates();
    }

    private static void hookGetLastKnownLocation() throws NoSuchMethodException {
        Pine.hook(LocationManager.class.getDeclaredMethod("getLastKnownLocation", String.class), new MethodHook() {
            @Override
            public void beforeCall(Pine.CallFrame callFrame) throws Throwable {
                super.beforeCall(callFrame);
                CheckerHelper.showWarn("getLastKnownLocation");
            }
        });
    }

    private static void hookRequestLocationUpdates() throws NoSuchMethodException {
        Pine.hook(LocationManager.class.getDeclaredMethod("requestLocationUpdates",
                String.class, long.class, float.class, LocationListener.class), new MethodHook() {
            @Override
            public void beforeCall(Pine.CallFrame callFrame) throws Throwable {
                super.beforeCall(callFrame);
                CheckerHelper.showWarn("requestLocationUpdates");
            }
        });
    }
}
