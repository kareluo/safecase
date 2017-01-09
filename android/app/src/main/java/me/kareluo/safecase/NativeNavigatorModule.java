package me.kareluo.safecase;

import android.app.Activity;
import android.content.Intent;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by felix on 16/8/11.
 */
public class NativeNavigatorModule extends ReactContextBaseJavaModule {

    public static ArrayBlockingQueue<String> mQueue = new ArrayBlockingQueue<>(1);

    public static final int REQ_FROM_JS = 0xfe;

    public NativeNavigatorModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "AppNavigatorModule";
    }

    @ReactMethod
    public void startActivity(String className, Callback callback) {
        Activity activity = getCurrentActivity();
        if (activity == null) {
            callback.invoke("Current Activity is null.");
            return;
        }
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            callback.invoke(e.getMessage());
        }

        if (clazz != null) {
            activity.startActivity(new Intent(activity, clazz));
            callback.invoke(null, true);
        }
    }

    @ReactMethod
    public void startActivityForResult(String className, Callback callback) {
        Activity activity = getCurrentActivity();
        if (activity == null) {
            callback.invoke("Current Activity is null.");
            return;
        }
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            callback.invoke(e.getMessage());
        }
        activity.startActivityForResult(new Intent(activity, clazz), REQ_FROM_JS);
        try {
            callback.invoke(null, mQueue.take());
        } catch (Exception e) {
            callback.invoke(e.getMessage());
        }
    }
}
