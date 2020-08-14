package com.aliya.compat.sample;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("主界面");

        findViewById(R.id.tv_toast).setOnClickListener(this);
        findViewById(R.id.tv_finalize).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_toast:
                startActivity(new Intent(this, ToastSampleActivity.class));
                break;
            case R.id.tv_finalize:
                startActivity(new Intent(this, FinalizeSampleActivity.class));
                break;
        }
    }
}
