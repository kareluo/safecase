package me.kareluo.safecase.kt.ui

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_home.*
import me.kareluo.safecase.core.pojo.DatabaseHelper
import me.kareluo.safecase.core.pojo.Secrets
import me.kareluo.safecase.core.pojo.dao.SecretsDao
import me.kareluo.safecase.kt.R
import me.kareluo.safecase.kt.core.Presenter
import me.kareluo.safecase.kt.core.REQ_SECRETS
import me.kareluo.safecase.kt.core.REQ_SECRETS_ADD
import me.kareluo.safecase.kt.core.Viewer
import me.kareluo.safecase.kt.databinding.ActivityHomeBinding
import me.kareluo.safecase.kt.ui.adapter.SecretsCallback
import me.kareluo.safecase.kt.ui.adapter.SecretsItemAdapter
import me.kareluo.safecase.kt.ui.base.BaseActivity

/**
 * Created by felix on 2018/1/5 下午6:06.
 */

class HomeActivity : BaseActivity<HomePresenter, HomeViewer>(), HomeViewer, SecretsCallback {

    private var mAdapter: SecretsItemAdapter? = null

    private var mBinding: ActivityHomeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        mAdapter = SecretsItemAdapter(this, null, this)
        rv_secrets.adapter = mAdapter

        fab_add.setOnClickListener {
            onAddClick()
        }

        presenter?.fetchSecrets()
    }

    override fun onSecretsClick(secrets: Secrets?) {
        startActivityForResult(newSecretsIntent(this, secrets?.uid), REQ_SECRETS)
    }

    private fun onAddClick() {
        startActivityForResult(newSecretsEditIntent(this), REQ_SECRETS_ADD)
    }

    override fun newPresenter() = HomePresenter(this)

    override fun onSecrets(secrets: List<Secrets>?) {
        mAdapter?.secrets = secrets
    }

    private fun onReload() {
        presenter?.fetchSecrets()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQ_SECRETS -> {
                if (resultCode == Activity.RESULT_OK) {
                    // TODO
                }
            }
            REQ_SECRETS_ADD -> {
                if (resultCode == Activity.RESULT_OK) {
                    onReload()
                }
            }
        }
    }
}

class HomePresenter(viewer: HomeViewer) : Presenter<HomeViewer>(viewer) {

    fun fetchSecrets() {
        subscribe(Observable
                .create<List<Secrets>?> {
                    it.onNext(
                            DatabaseHelper.get<SecretsDao, Secrets>(Secrets::class.java)
                                    .queryForAll()
                    )
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewer.onSecrets(it)
                }))
    }
}

interface HomeViewer : Viewer {

    fun onSecrets(secrets: List<Secrets>?)
}

