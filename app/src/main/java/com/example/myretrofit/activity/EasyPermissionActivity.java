package com.example.myretrofit.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myretrofit.R;
import com.example.myretrofit.utils.PermissionUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EasyPermissionActivity extends AppCompatActivity {

    @Bind(R.id.btn_one)
    Button btnOne;
    @Bind(R.id.btn_more)
    Button btnMore;


    // 相机权限、多个权限
    private final String PERMISSION_WRITE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private final int REQUEST_CODE_WRITE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_permission);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_one, R.id.btn_more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_one:
                requestPermission();
                break;
            case R.id.btn_more:
                break;
        }
    }

    // 普通申请一个权限
    private void requestPermission() {
        PermissionUtils.checkAndRequestPermission(this, PERMISSION_WRITE, REQUEST_CODE_WRITE,
                new PermissionUtils.PermissionRequestSuccessCallBack() {
                    @Override
                    public void onHasPermission() {
                        // 权限已被授予
                        Toast.makeText(EasyPermissionActivity.this, "来咬我呀！", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
