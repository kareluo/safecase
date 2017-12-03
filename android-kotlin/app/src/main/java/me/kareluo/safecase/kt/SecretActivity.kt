package me.kareluo.safecase.kt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

/**
 * Created by felix on 2017/12/3 下午10:14.
 */

class SecretActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secret)

    }

}

class SecretAdapter : RecyclerView.Adapter<SecretViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SecretViewHolder {
        TODO("not implemented")

    }

    override fun getItemCount(): Int {

        TODO("not implemented")
    }

    override fun onBindViewHolder(holder: SecretViewHolder?, position: Int) {

    }
}

class SecretViewHolder(view: View) : RecyclerView.ViewHolder(view) {

}