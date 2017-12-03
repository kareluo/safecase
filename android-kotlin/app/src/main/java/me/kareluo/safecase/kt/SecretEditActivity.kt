package me.kareluo.safecase.kt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import me.kareluo.safecase.core.pojo.SecretsViewModel

/**
 * Created by felix on 2017/12/3 下午10:28.
 */
class SecretEditActivity : AppCompatActivity() {

    var mSecretsViewModel: SecretsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secret_edit)
        
    }
}

class SecretEditAdapter(vm: SecretsViewModel?) : RecyclerView.Adapter<SecretEditViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SecretEditViewHolder {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: SecretEditViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {


        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

class SecretEditViewHolder(view: View) : RecyclerView.ViewHolder(view) {


}