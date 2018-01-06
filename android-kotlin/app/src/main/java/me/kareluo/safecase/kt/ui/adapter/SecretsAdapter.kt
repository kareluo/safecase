package me.kareluo.safecase.kt.ui.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import me.kareluo.safecase.core.pojo.Field
import me.kareluo.safecase.core.pojo.Secret
import me.kareluo.safecase.core.pojo.SecretsViewModel
import me.kareluo.safecase.kt.BR
import me.kareluo.safecase.kt.R
import me.kareluo.safecase.kt.core.VIEW_TYPE_INVALIDATE

/**
 * Created by felix on 2018/1/6 下午2:20.
 */

class SecretsAdapter(
        private val context: Context,
        secrets: SecretsViewModel? = null)
    : RecyclerView.Adapter<SecretsViewHolder>() {

    var secrets: SecretsViewModel? = secrets
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private val layoutInflater by lazy {
        LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SecretsViewHolder {
        val layoutId = when (viewType) {
            SecretsViewModel.TYPE_HEAD -> R.layout.layout_secrets_head
            SecretsViewModel.TYPE_FIELD -> R.layout.layout_secret_field
            SecretsViewModel.TYPE_SECRET -> R.layout.layout_secret
            else -> throw IllegalStateException("ViewType: $viewType is not support.")
        }
        return SecretsViewHolder(DataBindingUtil
                .inflate<ViewDataBinding>(layoutInflater, layoutId, parent, false))
    }

    override fun getItemViewType(position: Int): Int {
        return secrets?.getViewType(position) ?: VIEW_TYPE_INVALIDATE
    }

    override fun getItemCount(): Int {
        return secrets?.itemCount ?: 0
    }

    override fun onBindViewHolder(holder: SecretsViewHolder?, position: Int) {
        when (getItemViewType(position)) {
            SecretsViewModel.TYPE_HEAD -> holder?.secrets = secrets
            SecretsViewModel.TYPE_FIELD -> holder?.field = secrets?.getField(position)
            SecretsViewModel.TYPE_SECRET -> holder?.secret = secrets?.getSecret(position)
            else -> throw IllegalStateException("ViewType: $this is not support.")
        }
    }
}

class SecretsViewHolder(private val binding: ViewDataBinding)
    : RecyclerView.ViewHolder(binding.root) {

    var field: Field? = null
        set(value) {
            binding.setVariable(BR.field, value)
        }

    var secret: Secret? = null
        set(value) {
            binding.setVariable(BR.secret, value)
        }

    var secrets: SecretsViewModel? = null
        set(value) {
            binding.setVariable(BR.secrets, value)
        }
}