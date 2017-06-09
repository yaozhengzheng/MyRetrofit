package com.example.myretrofit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        intent = new Intent();
        list = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        initData();
    }

    private void initData() {
        list.add("Video");
        list.add("SpringActionMenu");

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
                }
                startActivity(intent);
            }
        });
    }
}
