package me.kareluo.safecase.kt.ui.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import me.kareluo.safecase.core.pojo.Field
import me.kareluo.safecase.core.pojo.Secret
import me.kareluo.safecase.core.pojo.SecretsEditViewModel
import me.kareluo.safecase.core.pojo.SecretsViewModel
import me.kareluo.safecase.kt.BR
import me.kareluo.safecase.kt.R
import me.kareluo.safecase.kt.core.ViewHolderCallback

/**
 * Created by felix on 2018/1/5 下午6:35.
 */

class SecretsEditAdapter(
        private val context: Context,
        secrets: SecretsEditViewModel? = null,
        private val callback: SecretsEditCallback? = null)
    : RecyclerView.Adapter<SecretsEditViewHolder>(), SecretsEditViewHolderCallback {
    private val layoutInflater: LayoutInflater by lazy {
        LayoutInflater.from(context)
    }

    var secrets: SecretsEditViewModel? = secrets ?: SecretsEditViewModel()
        set (value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SecretsEditViewHolder {
        val layoutId: Int = when (viewType) {
            SecretsEditViewModel.TYPE_HEAD -> R.layout.layout_edit_secret_head
            SecretsEditViewModel.TYPE_FIELD -> R.layout.layout_edit_secret_field
            SecretsEditViewModel.TYPE_FIELD_MORE -> R.layout.layout_edit_field_more
            SecretsEditViewModel.TYPE_SECRET -> R.layout.layout_edit_secret
            SecretsEditViewModel.TYPE_SECRET_MORE -> R.layout.layout_edit_field_more
            else -> throw IllegalArgumentException("Unknown view type: $viewType.")
        }
        return SecretsEditViewHolder(DataBindingUtil.inflate<ViewDataBinding>(
                layoutInflater, layoutId, parent, false), this)
    }

    override fun onBindViewHolder(holder: SecretsEditViewHolder?, position: Int) {
        when (getItemViewType(position)) {
            SecretsEditViewModel.TYPE_HEAD -> {
                holder?.secrets(secrets)
            }
            SecretsEditViewModel.TYPE_FIELD -> {
                holder?.field(secrets?.getField(position))
            }
            SecretsEditViewModel.TYPE_SECRET -> {
                holder?.secret(secrets?.getSecret(position))
            }
            SecretsEditViewModel.TYPE_FIELD_MORE -> {
                // TODO
            }
            SecretsEditViewModel.TYPE_SECRET_MORE -> {
                // TODO
            }
            else -> throw IllegalArgumentException("Unknown view type: &viewType.")
        }
    }

    override fun onClick(holder: RecyclerView.ViewHolder) {
        if (holder.adapterPosition in 0..(itemCount - 1)) {
            callback?.onItemClick(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return secrets?.itemCount ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return secrets?.getViewType(position) ?: -1
    }
}

class SecretsEditViewHolder(
        private val binding: ViewDataBinding,
        private val callback: SecretsEditViewHolderCallback? = null)
    : RecyclerView.ViewHolder(binding.root) {

    init {
        itemView.setOnClickListener {
            callback?.onClick(this)
        }
    }

    fun secrets(secrets: SecretsViewModel?) {
        binding.setVariable(BR.secrets, secrets)
    }

    fun field(field: Field?) {
        binding.setVariable(BR.field, field)
    }

    fun secret(secret: Secret?) {
        binding.setVariable(BR.secret, secret)
    }
}

interface SecretsEditViewHolderCallback : ViewHolderCallback {

}

interface SecretsEditCallback {

    fun onItemClick(position: Int)
}