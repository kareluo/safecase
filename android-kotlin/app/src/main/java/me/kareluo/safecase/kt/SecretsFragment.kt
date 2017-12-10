package me.kareluo.safecase.kt

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.kareluo.safecase.core.pojo.DatabaseHelper
import me.kareluo.safecase.core.pojo.Secrets
import me.kareluo.safecase.core.pojo.dao.SecretsDao
import me.kareluo.safecase.kt.databinding.FragmentSecretsBinding
import me.kareluo.safecase.kt.databinding.LayoutSecretsItemBinding

/**
 * Created by felix on 2017/12/10 下午8:53.
 */

class SecretsFragment : Fragment() {

    private var adapter: SecretsItemAdapter? = null

    private var binding: FragmentSecretsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate<FragmentSecretsBinding>(inflater,
                R.layout.fragment_secrets, container, false)

        return binding!!.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val secrets = SecretsPresenter().secrets()
        adapter = SecretsItemAdapter(context, secrets)
        binding?.rvSecrets?.adapter = adapter
    }

}

class SecretsPresenter {


    fun secrets(): List<Secrets> {
        val dao = DatabaseHelper.get<SecretsDao, Secrets>(Secrets::class.java)
        return dao.queryForAll()
    }
}

class SecretsItemAdapter(context: Context, val secrets: List<Secrets>)
    : BaseAdapter<SecretsItemViewHolder>(context) {

    override fun onBindViewHolder(holder: SecretsItemViewHolder?, position: Int) {
        holder?.secrets = secrets[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SecretsItemViewHolder {
        return SecretsItemViewHolder(inflater(R.layout.layout_secrets_item, parent))
    }

    override fun getItemCount(): Int {
        return secrets.size
    }
}

class SecretsItemViewHolder(val binding: LayoutSecretsItemBinding)
    : RecyclerView.ViewHolder(binding.root) {

    var secrets: Secrets? = null
        set(value) {
            field = value
            binding.setVariable(BR.secrets, value)
        }
}