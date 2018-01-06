package me.kareluo.safecase.kt.core

import android.support.v7.widget.RecyclerView

/**
 * Created by felix on 2018/1/5 下午5:27.
 */

interface ViewHolderCallback {

    fun onClick(holder: RecyclerView.ViewHolder)
}