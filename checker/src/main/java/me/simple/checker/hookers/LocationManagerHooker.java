package me.simple.checker.hookers;

import android.location.Location;
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
}
