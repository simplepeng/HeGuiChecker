package me.simple.checker.hookers;

import android.telephony.TelephonyManager;

import com.swift.sandhook.SandHook;
import com.swift.sandhook.annotation.HookClass;
import com.swift.sandhook.annotation.HookMethod;
import com.swift.sandhook.annotation.HookMethodBackup;
import com.swift.sandhook.annotation.MethodParams;

import java.lang.reflect.Method;

import me.simple.checker.CheckerHelper;

@HookClass(TelephonyManager.class)
public class TelephonyHooker {

    /**
     * DeviceId Hook **************************** start
     */
    @HookMethodBackup("getDeviceId")
    static Method getDeviceIdBackup;

    @HookMethod("getDeviceId")
    public static String getDeviceId(TelephonyManager origin) throws Throwable {

        CheckerHelper.showWarn("getDeviceId");

        Object result = SandHook.callOriginByBackup(getDeviceIdBackup, origin);
        return ((String) result);
    }

    //--------------

    @HookMethodBackup("getDeviceId")
    @MethodParams({int.class})
    static Method getDeviceIdWithIntBackup;

    @HookMethod("getDeviceId")
    @MethodParams({int.class})
    public static String getDeviceIdWithInt(TelephonyManager origin, int slotIndex) throws Throwable {

        CheckerHelper.showWarn("getDeviceId");

        Object result = SandHook.callOriginByBackup(getDeviceIdWithIntBackup, origin, slotIndex);
        return ((String) result);
    }

    /**
     * IMEI Hook **************************** start
     */
    @HookMethodBackup("getImei")
    static Method getImeiBackup;

    @HookMethod("getImei")
    public static String getImei(TelephonyManager origin) throws Throwable {

        CheckerHelper.showWarn("getImei");

        Object result = SandHook.callOriginByBackup(getImeiBackup, origin);
        return ((String) result);
    }

    //--------------

    @HookMethodBackup("getImei")
    @MethodParams({int.class})
    static Method getImeiWithIntBackup;

    @HookMethod("getImei")
    @MethodParams({int.class})
    public static String getImeiIdWithInt(TelephonyManager origin, int slotIndex) throws Throwable {

        CheckerHelper.showWarn("getImei");

        Object result = SandHook.callOriginByBackup(getImeiWithIntBackup, origin, slotIndex);
        return ((String) result);
    }

    /**
     * IMEI Hook **************************** start
     */
}
