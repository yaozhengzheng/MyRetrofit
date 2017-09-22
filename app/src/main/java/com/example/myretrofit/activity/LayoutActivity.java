package com.example.myretrofit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myretrofit.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LayoutActivity extends AppCompatActivity {

    @Bind(R.id.tv_add)
    TextView tvAdd;
    @Bind(R.id.lv_show)
    ListView listView;

    ArrayList<String> list;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

    @OnClick(R.id.tv_add)
    public void onViewClicked() {
        list.add("hahahaha");
        adapter.notifyDataSetChanged();
    }
}
