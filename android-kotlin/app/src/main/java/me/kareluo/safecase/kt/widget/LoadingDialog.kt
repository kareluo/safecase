package me.kareluo.safecase.kt.widget

import android.app.Dialog
import android.content.Context
import me.kareluo.safecase.kt.R

/**
 * Created by felix on 2017/12/10 下午11:16.
 */

class LoadingDialog(context: Context) : Dialog(context) {

    init {
        setContentView(R.layout.dialog_loading)
    }


}