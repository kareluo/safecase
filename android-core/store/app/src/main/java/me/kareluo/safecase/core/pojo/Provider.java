package me.kareluo.safecase.core.pojo;

import android.content.Context;

/**
 * Created by felix on 2017/12/3 下午9:42.
 */

public class Provider {

    private static Context mApplicationContext;

    public static void init(Context context) {
        mApplicationContext = context.getApplicationContext();
    }

    public static Context getApplicationContext() {
        return mApplicationContext;
    }
}
