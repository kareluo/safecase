package me.kareluo.safecase.kt

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Toast

/**
 * Created by felix on 2017/12/10 下午7:37.
 */

@SuppressLint("Registered")
open class BaseActivity<out P : Presenter<V>, out V : Viewer> : AppCompatActivity(), Viewer {

    protected val presenter: P? by lazy {
        newPresenter()
    }

    private val dialog: Dialog by lazy {
        LoadingDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    protected open fun newPresenter(): P? {
        return null
    }

    override fun loading() {
        dialog.show()
    }

    override fun done() {
        dialog.dismiss()
    }

    override fun showMessage(resId: Int) {
        showMessage(getString(resId))
    }

    override fun showMessage(message: String?) {
        if (!TextUtils.isEmpty(message)) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }
}