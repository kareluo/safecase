package me.kareluo.safecase.kt

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_secret_edit.*
import me.kareluo.safecase.core.pojo.SecretsViewModel

/**
 * Created by felix on 2017/12/3 下午10:28.
 */
class SecretEditActivity : AppCompatActivity() {

    private var adapter: SecretEditAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secret_edit)
        adapter = SecretEditAdapter(this)

        rv_secrets.adapter = adapter
    }
}

class SecretEditAdapter(private val context: Context)
    : RecyclerView.Adapter<SecretEditViewHolder>() {

    private var secrets: SecretsViewModel? = null

    private val layoutInflater: LayoutInflater by lazy {
        LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SecretEditViewHolder {
        val binding = when (viewType) {
            SecretsViewModel.TYPE_HEAD -> {
                DataBindingUtil.inflate<ViewDataBinding>(layoutInflater,
                        R.layout.layout_edit_secret_head, parent, false)
            }
            else -> {
                DataBindingUtil.inflate<ViewDataBinding>(layoutInflater,
                        R.layout.layout_edit_secret_head, parent, false)
            }
        }
        return SecretEditViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SecretEditViewHolder?, position: Int) {
        holder?.setSecrets(secrets)
    }

    override fun getItemCount(): Int {
        if (secrets == null) {
            return 0
        }
        return secrets!!.itemCount
    }

    override fun getItemViewType(position: Int): Int {
        if (secrets != null) {
            secrets?.getViewType(position)
        }
        return 0
    }
}

class SecretEditViewHolder(private val binding: ViewDataBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun setSecrets(secrets: SecretsViewModel?) {
        binding.setVariable(BR.secrets, secrets)
    }

}