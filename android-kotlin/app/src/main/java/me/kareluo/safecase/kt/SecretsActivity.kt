package me.kareluo.safecase.kt

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import me.kareluo.safecase.core.pojo.SecretsViewModel
import me.kareluo.safecase.kt.databinding.ActivitySecretBinding

/**
 * Created by felix on 2017/12/3 下午10:14.
 */

const val EXTRA_SECRETS_ID = "SECRETS_ID"

class SecretsActivity : AppCompatActivity() {

    private var secretsId: String? = null

    private var secrets: SecretsViewModel? = null

    private var binding: ActivitySecretBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        secretsId = intent.getStringExtra(EXTRA_SECRETS_ID)
        if (secretsId != null) {
            // query from db
        }

        // TODO
        if (secrets == null) {
            secrets = SecretsViewModel()

        }

        secrets?.name = "你好是"


        binding = DataBindingUtil.setContentView(this, R.layout.activity_secret)

        if (secrets != null) {
            binding?.rvSecrets?.adapter = SecretsAdapter(this, secrets!!)
        }
    }
}

class SecretsAdapter(private val context: Context, private val secrets: SecretsViewModel)
    : RecyclerView.Adapter<SecretsViewHolder>() {

    private val layoutInflater: LayoutInflater by lazy {
        LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SecretsViewHolder {
        val binding = when (viewType) {
            SecretsViewModel.TYPE_HEAD -> {
                DataBindingUtil.inflate<ViewDataBinding>(layoutInflater,
                        R.layout.layout_secrets_head, parent, false)
            }
            else -> throw IllegalStateException("ViewType: $viewType is not support.")
        }
        return SecretsViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return secrets.getViewType(position)
    }

    override fun getItemCount(): Int {
        return secrets.itemCount
    }

    override fun onBindViewHolder(holder: SecretsViewHolder?, position: Int) {
        holder?.secrets = secrets
    }
}

class SecretsViewHolder(private val binding: ViewDataBinding)
    : RecyclerView.ViewHolder(binding.root) {

    var secrets: SecretsViewModel? = null
        set(value) {
            field = value
            binding.setVariable(BR.secrets, value)
        }
}