package me.kareluo.safecase.kt

import android.app.Application
import me.kareluo.safecase.core.pojo.Provider

/**
 * Created by felix on 2017/12/3 下午9:38.
 */

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Provider.init(applicationContext)


    }
}