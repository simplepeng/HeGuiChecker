package me.simple.checker.hookers;

import android.net.wifi.WifiInfo;

import com.swift.sandhook.SandHook;
import com.swift.sandhook.annotation.HookClass;
import com.swift.sandhook.annotation.HookMethod;
import com.swift.sandhook.annotation.HookMethodBackup;

import java.lang.reflect.Method;

import me.simple.checker.CheckerHelper;

@HookClass(WifiInfo.class)
public class WifiInfoHooker {

    private static final String GET_MAC_ADDRESS = "getMacAddress";

    @HookMethodBackup(GET_MAC_ADDRESS)
    static Method getMacAddressBackup;

    @HookMethod(GET_MAC_ADDRESS)
    public static String getMacAddress(WifiInfo info) throws Throwable {

        CheckerHelper.showWarn(GET_MAC_ADDRESS);

        Object result = SandHook.callOriginByBackup(getMacAddressBackup, info);
        return ((String) result);
    }
}
