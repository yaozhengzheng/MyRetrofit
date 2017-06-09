package com.example.myretrofit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.myretrofit.R;
import com.zaaach.citypicker.CityPickerActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 城市选择
 * @author 姚政诤
 */
public class ChooseCityActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PICK_CITY = 233;

    @Bind(R.id.btn_select)
    Button btnSelect;
    @Bind(R.id.tv_result)
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_select)
    public void onViewClicked() {
        startActivityForResult(new Intent(ChooseCityActivity.this, com.example.myretrofit.activity.CityPickerActivity.class),
                REQUEST_CODE_PICK_CITY);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK){
            if (data != null){
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                tvResult.setText("当前选择：" + city);
            }
        }
    }
}
