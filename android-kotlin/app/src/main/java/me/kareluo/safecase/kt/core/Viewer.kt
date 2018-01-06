package me.kareluo.safecase.kt.core

/**
 * Created by felix on 2017/12/10 下午10:30.
 */

interface Viewer {

    fun loading()

    fun done()

    fun showMessage(resId: Int)

    fun showMessage(message: String?)
}