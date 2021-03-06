package com.example.myretrofit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myretrofit.MainActivity;
import com.example.myretrofit.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.lv_list)
    ListView listView;

    Intent intent;
    ArrayList<String> list;
    ArrayAdapter adapter;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();
        intent = new Intent();
        list = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        initData();
    }

    //算数题
    private void initView() {
        //6088 5174 1358 3826 2532

        int[][] num = new int[][]{
                {6, 0, 8, 7},
                {5, 1, 7, 3},
                {1, 3, 5, 8},
                {3, 8, 2, 5},
                {2, 5, 3, 1}
        };
        for (int i = 0; i < 10000; ++i) {
            // 取出个十百千位
            int g = i % 10;
            int s = (i / 10) % 10;
            int b = (i / 100) % 10;
            int q = (i / 1000) % 10;
            // 是否是最终答案标志位
            boolean isRight = true;
            // 筛选出只出现两次的，并且本次当前位置不对
            for (int j = 0; j < 5; j++) {
                int count = 0;
                for (int k = 0; k < 4; k++) {
                    if ((g == num[j][k] && k != 3)
                            || (s == num[j][k] && k != 2)
                            || (b == num[j][k] && k != 1)
                            || (q == num[j][k] && k != 0)) {
                        ++count;
                    }
                }
                // 如果不是出现两次的直接跳出
                if (count != 2) {
                    isRight = false;
                    break;
                }
            }
            if (isRight) {
                // 下面要判断所有位置都要是错误的
                for (int j = 0; j < 5; j++) {
                    if (g == num[j][3] || s == num[j][2] || b == num[j][1] || q == num[j][0]) {
                        isRight = false;
                        break;
                    }
                }
            }
            if (isRight) {
                // 到此才算是正确答案：8712
                System.out.println("----->" + i);
                Log.d("", "initView: ----->" + i);
            }
        }
    }


    private void initData() {

        list.add("Video传入地址有问题");
        list.add("SpringActionMenu");
        list.add("ChooseCity");
        list.add("TabsIndicator");
        list.add("okgo");
        list.add("image");
        list.add("Diy_view");
        list.add("Custom");
        list.add("Splash");
        list.add("WCDB数据库");
        list.add("收益曲线图");
        list.add("闪屏广告图");
        list.add("伸缩式标题栏");
        list.add("伸缩搜索框");
        list.add("应用宝下载");
        list.add("满减布局");
        list.add("权限申请页面");
        list.add("超级简便的申请权限");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        intent.setClass(HomeActivity.this, MainActivity.class);
                        break;
                    case 1:
                        intent.setClass(HomeActivity.this, ActionMenuActivity.class);
                        break;
                    case 2:
                        intent.setClass(HomeActivity.this, ChooseCityActivity.class);
                        break;
                    case 3:
                        intent.setClass(HomeActivity.this, TabsIndicatorActivity.class);
                        break;
                    case 4:
                        intent.setClass(HomeActivity.this, PracticeActivity.class);
                        break;
                    case 5:
                        intent.setClass(HomeActivity.this, ImageViewActivity.class);
                        break;
                    case 6:
                        intent.setClass(HomeActivity.this, DiyActivity.class);
                        break;
                    case 7:
                        intent.setClass(HomeActivity.this, CustomActivity.class);
                        break;
                    case 8:
                        intent.setClass(HomeActivity.this, SplashActivity.class);
                        break;
                    case 9:
                        intent.setClass(HomeActivity.this, WcdbActivity.class);
                        break;
                    case 10:
                        intent.setClass(HomeActivity.this, ChartActivity.class);
                        break;
                    case 11:
                        intent.setClass(HomeActivity.this, SplashAdvertisingActivity.class);
                        break;
                    case 12:
                        intent.setClass(HomeActivity.this, BarActivity.class);
                        break;
                    case 13:
                        intent.setClass(HomeActivity.this, SearchBarActivity.class);
                        break;
                    case 14:
                        intent.setClass(HomeActivity.this, DownloadActivity.class);
                        break;
                    case 15:
                        intent.setClass(HomeActivity.this, LayoutActivity.class);
                        break;
                    case 16:
                        intent.setClass(HomeActivity.this, PermissionActivity.class);
                        break;
                    case 17:
                        intent.setClass(HomeActivity.this, EasyPermissionActivity.class);
                        break;

                }
                startActivity(intent);
            }
        });
    }
}
