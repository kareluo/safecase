package me.kareluo.safecase.kt

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_secret_edit.*
import me.kareluo.safecase.core.pojo.DatabaseHelper
import me.kareluo.safecase.core.pojo.Secrets
import me.kareluo.safecase.core.pojo.SecretsViewModel
import me.kareluo.safecase.core.pojo.dao.SecretsDao

/**
 * Created by felix on 2017/12/3 下午10:28.
 */
class SecretsEditActivity : BaseActivity<SecretsEditPresenter, SecretsEditViewer>(), SecretsEditViewer {
    private var adapter: SecretsEditAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secret_edit)
        adapter = SecretsEditAdapter(this, null)

        rv_secrets.adapter = adapter
    }

    override fun newPresenter(): SecretsEditPresenter? {
        return SecretsEditPresenter(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_secrets_edit, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.menu_save -> {
                onSave()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onSave() {
        presenter?.saveSecrets(adapter?.secrets)
    }

    override fun onSaveSuccess() {
        done()
        showMessage("保存成功")
        finish()
    }
}

class SecretsEditPresenter(viewer: SecretsEditViewer) : Presenter<SecretsEditViewer>(viewer) {

    fun saveSecrets(secrets: SecretsViewModel?) {
        viewer.loading()
        subscribe(Flowable.just(secrets)
                .observeOn(Schedulers.io())
                .map {

                    SystemClock.sleep(2000)

                    DatabaseHelper.get<SecretsDao, Secrets>(Secrets::class.java)
                            .createOrUpdate(secrets)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewer.onSaveSuccess()
                }))
    }
}

interface SecretsEditViewer : Viewer {

    fun onSaveSuccess()
}

class SecretsEditAdapter(private val context: Context)
    : RecyclerView.Adapter<SecretsEditViewHolder>() {

    var secrets: SecretsViewModel? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    constructor(context: Context, secrets: SecretsViewModel?) : this(context) {
        this.secrets = secrets ?: SecretsViewModel()
    }

    private val layoutInflater: LayoutInflater by lazy {
        LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SecretsEditViewHolder {
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
        return SecretsEditViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SecretsEditViewHolder?, position: Int) {
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

class SecretsEditViewHolder(private val binding: ViewDataBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun setSecrets(secrets: SecretsViewModel?) {
        binding.setVariable(BR.secrets, secrets)
    }

}