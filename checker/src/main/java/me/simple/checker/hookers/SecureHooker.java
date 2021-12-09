package me.simple.checker.hookers;

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

import me.simple.checker.CheckerHelper;

@HookClass(Settings.Secure.class)
public class SecureHooker {

    @HookMethodBackup("getString")
    @MethodParams({ContentResolver.class, String.class})
    static Method getStringBackup;

    @HookMethod("getString")
    @MethodParams({ContentResolver.class, String.class})
    public static String getString(ContentResolver resolver, String name) throws Throwable {

        //AndroidId
        if (TextUtils.equals(Settings.Secure.ANDROID_ID, name)) {
            CheckerHelper.showWarn("AndroidId");
        }
        //
        else {
            CheckerHelper.showWarn(name);
        }

        Object result = SandHook.callOriginByBackup(getStringBackup, null, resolver, name);
        return ((String) result);
    }
}
