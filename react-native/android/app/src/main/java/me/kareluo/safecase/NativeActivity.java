package me.kareluo.safecase;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/**
 * Created by felix on 16/8/11.
 */
public class NativeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mResultText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_native);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        mResultText = (EditText) findViewById(R.id.et_result);
        findViewById(R.id.btn_ok).setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok:
                setResult(RESULT_OK, new Intent().putExtra("result", mResultText.getText().toString()));
                finish();
                break;
        }
    }
}
