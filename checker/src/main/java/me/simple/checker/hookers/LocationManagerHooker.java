package me.simple.checker.hookers;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import com.swift.sandhook.SandHook;
import com.swift.sandhook.annotation.HookClass;
import com.swift.sandhook.annotation.HookMethod;
import com.swift.sandhook.annotation.HookMethodBackup;
import com.swift.sandhook.annotation.MethodParams;

import java.lang.reflect.Method;

import me.simple.checker.CheckerHelper;

@HookClass(LocationManager.class)
public class LocationManagerHooker {

    private static final String GET_LAST_KNOWN_LOCATION = "getLastKnownLocation";

    @HookMethodBackup(GET_LAST_KNOWN_LOCATION)
    @MethodParams({String.class})
    static Method getLastKnownLocationBackup;

    @HookMethod(GET_LAST_KNOWN_LOCATION)
    @MethodParams({String.class})
    public static Location getLastKnownLocation(
            LocationManager origin,
            String provider) throws Throwable {

        CheckerHelper.showWarn(GET_LAST_KNOWN_LOCATION);

        Object result = SandHook.callOriginByBackup(getLastKnownLocationBackup, origin, provider);
        return ((Location) result);
    }

    /**
     *
     */

    private static final String REQUEST_LOCATION_UPDATES = "requestLocationUpdates";

    @HookMethodBackup(REQUEST_LOCATION_UPDATES)
    @MethodParams({String.class, long.class, float.class, LocationListener.class})
    static Method requestLocationUpdatesBackup;

    @HookMethod(REQUEST_LOCATION_UPDATES)
    @MethodParams({String.class, long.class, float.class, LocationListener.class})
    public static void requestLocationUpdates(
            LocationManager origin,
            String provider,
            long minTime,
            float minDistance,
            LocationListener listener) throws Throwable {

        CheckerHelper.showWarn(REQUEST_LOCATION_UPDATES);

        SandHook.callOriginByBackup(requestLocationUpdatesBackup, origin, provider, minTime, minDistance, listener);
    }
}
