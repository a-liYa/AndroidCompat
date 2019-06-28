package com.aliya.compat.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.aliya.compat.CrashCompat;

/**
 * Object#finalize() - java.util.concurrent.TimeoutException 异常 演示
 *
 * @author a_liYa
 * @date 2019-06-12 15:41.
 */
public class FinalizeSampleActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize_sample);
        setTitle("finalize演示");

        findViewById(R.id.tv_mock_exception).setOnClickListener(this);
        findViewById(R.id.tv_fix_exception).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_mock_exception:
                if (getApplication() instanceof App) {
                    App app = (App) getApplication();
                    // 恢复默认
                    Thread.setDefaultUncaughtExceptionHandler(app.mDefaultUncaughtHandler);
                    new FinalizeObject();
                    getWindow().getDecorView().post(new Runnable() {
                        @Override
                        public void run() {
                            System.gc();
                        }
                    });
                    Toast.makeText(this, "需等系统GC才触发异常\n请耐心等待", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_fix_exception:
                CrashCompat.fixBug();
                break;
        }
    }

    static class FinalizeObject extends Object {
        @Override
        protected void finalize() throws Throwable {
            Log.e("TAG", "finalize: before " + "Thread name: " + Thread.currentThread().getName());
            Thread.sleep(100 * 1000);
            Log.e("TAG", "finalize: after " + "Thread name: " + Thread.currentThread().getName());
            super.finalize();
        }
    }
}
