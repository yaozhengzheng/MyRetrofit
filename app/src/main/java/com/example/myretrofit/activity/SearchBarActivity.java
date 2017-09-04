package com.example.myretrofit.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myretrofit.R;
import com.example.myretrofit.view.SearchBarView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchBarActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.search_bar)
    SearchBarView searchBar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.app_bar)
    AppBarLayout appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
//            actionBar.hide();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        appBar.addOnOffsetChangedListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setVerticalScrollBarEnabled(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(new MyAdapter());
    }

    @OnClick(R.id.search_bar)
    public void onViewClicked() {
        Toast.makeText(this, "搜索！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        float alpha = Math.abs((float) verticalOffset / appBarLayout.getTotalScrollRange());
        toolbar.setAlpha(alpha);
        if (alpha >= 1) {
            searchBar.startOpen();
        } else {
            searchBar.startClose();
        }
    }


    private static class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private static final int TYPE_HEADER = 1;
        private static final int TYPE_NORMAL = 1 << 2;

        @Override
        public int getItemViewType(int position) {
            return TYPE_NORMAL;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView;
            if (viewType == TYPE_HEADER) {
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
                return new HeaderViewHolder(itemView);
            } else if (viewType == TYPE_NORMAL) {
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_normal, parent, false);
                return new NormalViewHolder(itemView);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 20;
        }

        class HeaderViewHolder extends RecyclerView.ViewHolder {

            HeaderViewHolder(View itemView) {
                super(itemView);
            }

        }

        class NormalViewHolder extends RecyclerView.ViewHolder {

            NormalViewHolder(View itemView) {
                super(itemView);
            }

        }

    }

}
