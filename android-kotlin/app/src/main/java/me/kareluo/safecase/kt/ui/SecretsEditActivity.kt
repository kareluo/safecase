package me.kareluo.safecase.kt.ui

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_secrets_edit.*
import me.kareluo.safecase.core.pojo.Secrets
import me.kareluo.safecase.core.pojo.SecretsEditViewModel
import me.kareluo.safecase.core.pojo.SecretsManager
import me.kareluo.safecase.core.pojo.SecretsViewModel
import me.kareluo.safecase.kt.R
import me.kareluo.safecase.kt.core.EXTRA_SECRETS_ID
import me.kareluo.safecase.kt.core.EXTRA_UID
import me.kareluo.safecase.kt.core.Presenter
import me.kareluo.safecase.kt.core.Viewer
import me.kareluo.safecase.kt.databinding.ActivitySecretsBinding
import me.kareluo.safecase.kt.ui.adapter.SecretsEditAdapter
import me.kareluo.safecase.kt.ui.adapter.SecretsEditCallback
import me.kareluo.safecase.kt.ui.base.BaseActivity

/**
 * Created by felix on 2017/12/3 下午10:28.
 */
class SecretsEditActivity : BaseActivity<SecretsEditPresenter, SecretsEditViewer>(),
        SecretsEditViewer, SecretsEditCallback {

    private var mAdapter: SecretsEditAdapter? = null

    private var mBinding: ActivitySecretsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_secrets_edit)

        val uid = intent.getStringExtra(EXTRA_SECRETS_ID)
        var model: SecretsEditViewModel? = null
        if (!TextUtils.isEmpty(uid)) {
            presenter?.fetchSecrets(uid)
        } else {
            model = SecretsEditViewModel(Secrets.create())
        }

        mAdapter = SecretsEditAdapter(this, model, this)

        rv_secrets.adapter = mAdapter
    }

    override fun newPresenter() = SecretsEditPresenter(this)

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu)
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

    override fun onItemClick(position: Int) {
        when (mAdapter?.getItemViewType(position)) {
            SecretsEditViewModel.TYPE_FIELD_MORE -> {
                mAdapter?.secrets?.addFiled(position)
                mAdapter?.notifyItemInserted(position)
            }
            SecretsEditViewModel.TYPE_SECRET_MORE -> {
                mAdapter?.secrets?.addSecret(position)
                mAdapter?.notifyItemInserted(position)
            }
        }
    }

    private fun onSave() {
        presenter?.saveSecrets(mAdapter?.secrets)
    }

    override fun onSecrets(secrets: SecretsEditViewModel) {
        mAdapter?.secrets = secrets
    }

    override fun onSaveSuccess() {
        showMessage("保存成功")
        setResult(Activity.RESULT_OK)
        finish()
    }
}

class SecretsEditPresenter(viewer: SecretsEditViewer) : Presenter<SecretsEditViewer>(viewer) {

    fun fetchSecrets(uid: String) {
        subscribe(Observable.just(uid)
                .observeOn(Schedulers.io())
                .map<SecretsEditViewModel> {
                    val secrets = SecretsManager.getSecrets(it)
                    if (secrets != null) {
                        SecretsEditViewModel(secrets)
                    } else SecretsEditViewModel(Secrets.create())
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewer.onSecrets(it)
                }))
    }

    fun saveSecrets(secrets: SecretsViewModel?) {
        viewer.loading()
        subscribe(Flowable.just(secrets)
                .filter {
                    secrets != null
                }
                .observeOn(Schedulers.io())
                .map {
                    SecretsManager.setSecrets(secrets)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewer.done()
                    viewer.onSaveSuccess()
                }))
    }
}

interface SecretsEditViewer : Viewer {

    fun onSaveSuccess()

    fun onSecrets(secrets: SecretsEditViewModel)
}