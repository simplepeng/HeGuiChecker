package me.simple.checker;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class CheckerHelper {

    public static Context appContext;

    public static Context getAppContext(Context context) {

        if (context == null) {
            try {
                Class<?> activityThread = Class.forName("android.app.ActivityThread");
                Object thread = activityThread.getMethod("currentActivityThread").invoke(null);
                Application app = ((Application) activityThread.getMethod("getApplication").invoke(thread));
                context = app;
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        return context.getApplicationContext();
    }

    public static void showWarn(String text) {
        toast("非法调用了" + text);
        log(text);
    }

    static void toast(String text) {
        Toast toast = Toast.makeText(appContext, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    static void log(String msg) {
        Log.e("HeGuiChecker", msg);
    }
}