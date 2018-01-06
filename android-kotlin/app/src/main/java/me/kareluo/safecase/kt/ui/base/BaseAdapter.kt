package me.kareluo.safecase.kt.ui.base

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by felix on 2017/12/10 下午9:06.
 */

abstract class BaseAdapter<VH : RecyclerView.ViewHolder>(val context: Context?)
    : RecyclerView.Adapter<VH>() {

    private val layoutInflater: LayoutInflater by lazy {
        LayoutInflater.from(context)
    }

    fun <T : ViewDataBinding> inflater(layoutId: Int, parent: ViewGroup?): T
            = DataBindingUtil.inflate(layoutInflater, layoutId, parent, false)

}