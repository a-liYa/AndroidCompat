package com.aliya.compat.sample;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aliya.compat.ToastContext;

/**
 * Toast WindowManager$BadTokenException 异常演示
 *
 * @author a_liYa
 * @date 2019-06-08 12:11.
 */
public class ToastSampleActivity extends AppCompatActivity implements View.OnClickListener {

    TextView mTvTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_sample);
        setTitle("Toast演示");
        mTvTip = findViewById(R.id.tv_tip);

        // 最好使用原生模拟器演示
        mTvTip.setText("Toast异常需要系统在 Android 26 以下\n" + "当前 Api = " + Build.VERSION.SDK_INT);

        findViewById(R.id.tv_normal).setOnClickListener(this);
        findViewById(R.id.tv_exception).setOnClickListener(this);
        findViewById(R.id.tv_fix_exception).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_normal:
                Toast.makeText(this, "我是正常Toast", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_exception:
                Toast.makeText(this, "我是异常Toast", Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                break;
            case R.id.tv_fix_exception:
                Toast.makeText(ToastContext.compatContext(this), "我是异常修复Toast", Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                break;
        }
    }
}
