package me.kareluo.safecase.kt

import android.app.Dialog
import android.content.Context

/**
 * Created by felix on 2017/12/10 下午11:16.
 */

class LoadingDialog(context: Context) : Dialog(context) {

    init {
        setContentView(R.layout.dialog_loading)
    }

}