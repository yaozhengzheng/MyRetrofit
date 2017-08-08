package com.example.myretrofit.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.myretrofit.R;
import com.example.myretrofit.bean.Model;
import com.example.myretrofit.view.Chart;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChartActivity extends AppCompatActivity {


    @Bind(R.id.chart)
    Chart chart;
    private List<Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        ButterKnife.bind(this);
        initData();

        //设置动画
        chart.setDrawPoints(false).setFillArea(true).setPlayAnim(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                chart.setDatas(list);
            }
        }, 1000);
    }

    private void initData() {
        list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 90; i++) {
            Model model = new Model();
            model.percent = -10 + random.nextFloat() * 21;//(-10,10)
            model.date = (int) (1 + Math.random() * 13) + "." + (int) (1 + Math.random() * 31);
            list.add(model);
        }
    }
}
