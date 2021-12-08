package demo.simple.checker

import android.app.Application
import me.simple.checker.HeGuiChecker

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        HeGuiChecker.install(this)
    }
}