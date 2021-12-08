package me.simple.checker

import android.content.Context

object HeGuiChecker {

    @Synchronized
    fun install(context: Context) {
        Helper.startHook(context)
    }
}