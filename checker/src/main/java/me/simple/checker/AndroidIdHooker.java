package me.simple.checker;

import android.content.ContentResolver;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import com.swift.sandhook.SandHook;
import com.swift.sandhook.annotation.HookClass;
import com.swift.sandhook.annotation.HookMethod;
import com.swift.sandhook.annotation.HookMethodBackup;
import com.swift.sandhook.annotation.MethodParams;
import com.swift.sandhook.annotation.Param;

import java.lang.reflect.Method;

@HookClass(Settings.Secure.class)
public class AndroidIdHooker {

    @HookMethodBackup("getString")
    @MethodParams({ContentResolver.class, String.class})
    static Method getStringBackup;

    @HookMethod("getString")
    public static String getString(
            @Param("android.content.ContentResolver") Object resolver,
            @Param("java.lang.String") Object name
    ) throws Throwable {
        String castName = ((String) name);
        if (TextUtils.equals(Settings.Secure.ANDROID_ID, castName)) {
            Log.e("AndroidIdHooker", "hooked AndroidIdHooker success ");
            Object result = SandHook.callOriginByBackup(getStringBackup, null, resolver, name);
            String androidId = ((String) result);
            Log.e("AndroidIdHooker", "androidId == " + androidId);
            return androidId;
        }
        return "null-error";
    }
}
