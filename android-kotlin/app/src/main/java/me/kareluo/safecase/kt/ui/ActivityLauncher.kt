package me.kareluo.safecase.kt.ui

import android.content.Context
import android.content.Intent
import me.kareluo.safecase.kt.core.EXTRA_SECRETS_ID

/**
 * Created by felix on 2018/1/5 下午5:41.
 */

fun newSecretsIntent(context: Context, secretsId: String? = null)
        = Intent(context, SecretsActivity::class.java)
        .putExtra(EXTRA_SECRETS_ID, secretsId)!!

fun toSecretsActivity(context: Context, secretsId: String? = null) {
    context.startActivity(newSecretsIntent(context, secretsId))
}

fun toSecretsEditActivity(context: Context, secretsId: String? = null) {
    context.startActivity(newSecretsIntent(context, secretsId))
}

fun newSecretsEditIntent(context: Context, secretsId: String? = null): Intent
        = Intent(context, SecretsEditActivity::class.java)
        .putExtra(EXTRA_SECRETS_ID, secretsId)!!