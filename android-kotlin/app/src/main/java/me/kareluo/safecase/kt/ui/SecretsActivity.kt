package me.kareluo.safecase.kt.ui

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_secrets.*
import me.kareluo.safecase.core.pojo.Secrets
import me.kareluo.safecase.core.pojo.SecretsManager
import me.kareluo.safecase.core.pojo.SecretsViewModel
import me.kareluo.safecase.kt.R
import me.kareluo.safecase.kt.core.EXTRA_SECRETS_ID
import me.kareluo.safecase.kt.core.Presenter
import me.kareluo.safecase.kt.core.REQ_SECRETS_EDIT
import me.kareluo.safecase.kt.core.Viewer
import me.kareluo.safecase.kt.databinding.ActivitySecretsBinding
import me.kareluo.safecase.kt.ui.adapter.SecretsAdapter
import me.kareluo.safecase.kt.ui.base.BaseActivity

/**
 * Created by felix on 2017/12/3 下午10:14.
 */

class SecretsActivity : BaseActivity<SecretsPresenter, SecretsViewer>(), SecretsViewer {

    private var mSecretsId: String? = null

    private var mAdapter: SecretsAdapter? = null

    private var mBinding: ActivitySecretsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_secrets)
        mAdapter = SecretsAdapter(this)
        rv_secrets.adapter = mAdapter

        mSecretsId = intent.getStringExtra(EXTRA_SECRETS_ID)
        if (!TextUtils.isEmpty(mSecretsId)) {
            presenter?.fetchSecrets(mSecretsId!!)
        } else Toast.makeText(this, "不存在", Toast.LENGTH_SHORT).show()
    }

    override fun onSecrets(secrets: SecretsViewModel) {
        mAdapter?.secrets = secrets
    }

    override fun newPresenter() = SecretsPresenter(this)

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_edit, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.menu_edit -> {
                onEdit()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onEdit() {
        startActivityForResult(newSecretsEditIntent(this,
                mAdapter?.secrets?.uid), REQ_SECRETS_EDIT)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQ_SECRETS_EDIT -> {
                if (resultCode == Activity.RESULT_OK) {
                    if (mSecretsId != null) {
                        presenter?.fetchSecrets(mSecretsId!!)
                    }
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }
}

class SecretsPresenter(viewer: SecretsViewer) : Presenter<SecretsViewer>(viewer) {

    fun fetchSecrets(uid: String) {
        subscribe(Observable.just(uid)
                .observeOn(Schedulers.io())
                .map {
                    val secrets = SecretsManager.getSecrets(it)
                    if (secrets != null) {
                        SecretsViewModel(secrets)
                    } else SecretsViewModel(Secrets.create())
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewer.onSecrets(it)
                })
        )
    }
}

interface SecretsViewer : Viewer {

    fun onSecrets(secrets: SecretsViewModel)
}
