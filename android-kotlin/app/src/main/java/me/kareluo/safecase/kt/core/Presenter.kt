package me.kareluo.safecase.kt.core

import io.reactivex.disposables.Disposable
import java.util.*

/**
 * Created by felix on 2017/12/10 下午7:39.
 */

open class Presenter<out V : Viewer>(val viewer: V) {

    private val disposables by lazy {
        LinkedList<Disposable>()
    }

    fun subscribe(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun destroy() {
        disposables.forEach {
            it.dispose()
        }
        disposables.clear()
    }
}