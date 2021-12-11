package me.simple.checker.hookers;

import com.swift.sandhook.SandHook;
import com.swift.sandhook.annotation.HookClass;
import com.swift.sandhook.annotation.HookMethod;
import com.swift.sandhook.annotation.HookMethodBackup;

import java.lang.reflect.Method;
import java.net.NetworkInterface;

import me.simple.checker.CheckerHelper;

@HookClass(NetworkInterface.class)
public class NetworkHooker {

    private static final String GET_HARDWARE_ADDRESS = "getHardwareAddress";

    @HookMethodBackup(GET_HARDWARE_ADDRESS)
    static Method getHardwareAddressBackup;

    @HookMethod(GET_HARDWARE_ADDRESS)
    public static byte[] getHardwareAddress(NetworkInterface origin) throws Throwable {

        CheckerHelper.showWarn(GET_HARDWARE_ADDRESS);

        Object result = SandHook.callOriginByBackup(getHardwareAddressBackup, origin);
        return ((byte[]) result);
    }
}
