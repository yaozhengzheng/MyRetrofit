package com.example.myretrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.myretrofit.R;
import com.example.myretrofit.bean.AdvertisingImgModel;
import com.example.myretrofit.view.SplashDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 姚政诤
 *         广告显示页面
 */
public class SplashAdvertisingActivity extends AppCompatActivity {

    SplashDialog splashDialog;

    //图片地址
    private String url = "http://img0.ph.126.net/Ol1MdFsK-wBADSwY-pSnuQ==/3166593487997081622.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_advertising);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_show)
    public void onViewClicked() {
        AdvertisingImgModel advertisingImgModel = new AdvertisingImgModel(1, url);
        splashDialog = new SplashDialog(SplashAdvertisingActivity.this, advertisingImgModel);
        splashDialog.setOnSplashDetailClickListener(new SplashDialog.OnSplashDetailClickListener() {
            @Override
            public void onSplashDetailClick(AdvertisingImgModel advertisingImgModel) {
                Toast.makeText(SplashAdvertisingActivity.this, "跳转到广告的详情页", Toast.LENGTH_SHORT).show();
            }
        });
        splashDialog.show();
    }
}
