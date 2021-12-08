package me.simple.checker

import android.content.Context
import android.util.Log
import com.swift.sandhook.SandHook
import com.swift.sandhook.SandHookConfig

object Helper {

    fun startHook(context: Context) {
//        DexposedBridge.findAndHookMethod(
//            Settings.Secure::class.java,
//            "getString",
//            ContentResolver::class.java,
//            String::class.java,
//            AndroidIdHook()
//        )

//        DexposedBridge.findAndHookMethod(
//            Settings.Secure::class.java,
//            "getStringForUser",
//            ContentResolver::class.java,
//            String::class.java,
//            Int::class.java,
//            AndroidIdHook()
//        )

//        DexposedBridge.findAndHookMethod(
//            View::class.java,
//            "setBackgroundDrawable",
//            Drawable::class.java,
//            AndroidIdHook()
//        )
//        SandHookConfig.DEBUG = true
//
//        SandHook.disableVMInline();
//        SandHook.tryDisableProfile(context.packageName);
//        SandHook.disableDex2oatInline(false);
//        SandHook.passApiCheck();

        SandHook.addHookClass(
            ActivityHooker::class.java,
            AndroidIdHooker::class.java
        )


    }

    fun debugLog(msg: String) {
        Log.d("HeGuiChecker", msg)
    }
}