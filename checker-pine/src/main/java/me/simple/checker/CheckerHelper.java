package me.simple.checker;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class CheckerHelper {

    static final String TAG = "HeGuiChecker";

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
        log(text);

        String builder = "Warning!!!" + " ---> " + text;
        toast(builder);
    }

    static void toast(String text) {
        if (!HeGuiChecker.SHOW_TOAST) return;
        Toast toast = Toast.makeText(appContext, text, Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    static void log(String msg) {
        if (!HeGuiChecker.SHOW_LOG) return;
        Log.e(TAG, "-------------------------------");
        Log.e(TAG, "非法使用了不合规的方法：");
        Log.e(TAG, msg);
        Log.e(TAG, "-------------------------------");
        Log.e(TAG, "调用栈输出：");
        Log.e(TAG, getMethodStack());
    }

    private static String getMethodStack() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        StringBuilder builder = new StringBuilder();
        for (StackTraceElement element : stackTraceElements) {
            String line = element.toString();

            String className = element.getClassName();
            boolean isChecker = !TextUtils.isEmpty(className) && className.startsWith("me.simple.checker");
            if (isChecker) continue;

            builder.append(line);
            builder.append("\n");
        }

        return builder.toString();

    }
}
