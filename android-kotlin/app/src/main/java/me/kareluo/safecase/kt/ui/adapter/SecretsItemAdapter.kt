package me.kareluo.safecase.kt.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import me.kareluo.safecase.core.pojo.Secrets
import me.kareluo.safecase.kt.BR
import me.kareluo.safecase.kt.R
import me.kareluo.safecase.kt.core.ViewHolderCallback
import me.kareluo.safecase.kt.databinding.LayoutSecretsItemBinding
import me.kareluo.safecase.kt.ui.base.BaseAdapter

/**
 * Created by felix on 2018/1/5 下午6:15.
 */

class SecretsItemAdapter(
        context: Context?,
        secrets: List<Secrets>? = null,
        private val callback: SecretsCallback? = null)
    : BaseAdapter<SecretsItemViewHolder>(context), ViewHolderCallback {

    var secrets: List<Secrets>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: SecretsItemViewHolder?, position: Int) {
        holder?.secrets = secrets?.get(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SecretsItemViewHolder {
        return SecretsItemViewHolder(inflater(R.layout.layout_secrets_item, parent), this)
    }

    override fun getItemCount(): Int {
        return secrets?.size ?: 0
    }

    private fun getItem(position: Int): Secrets? {
        return if (position in 0..(itemCount - 1)) secrets?.get(position) else null
    }

    override fun onClick(holder: RecyclerView.ViewHolder) {
        callback?.onSecretsClick(getItem(holder.adapterPosition))
    }
}

class SecretsItemViewHolder(
        private val binding: LayoutSecretsItemBinding,
        private val callback: ViewHolderCallback?)
    : RecyclerView.ViewHolder(binding.root) {

    var secrets: Secrets? = null
        set(value) {
            field = value
            binding.setVariable(BR.secrets, value)
        }

    init {
        binding.root.setOnClickListener {
            callback?.onClick(this)
        }
    }
}

interface SecretsCallback {

    fun onSecretsClick(secrets: Secrets?)
}