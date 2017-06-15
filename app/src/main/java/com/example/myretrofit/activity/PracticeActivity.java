package com.example.myretrofit.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.myretrofit.R;
import com.example.myretrofit.fragment.NewsTabFragment;
import com.example.myretrofit.utils.GlideImageLoader;
import com.lzy.ninegrid.NineGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PracticeActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tab)
    TabLayout tab;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        ButterKnife.bind(this);
        toolbar.setTitle("强大的缓存");
        setSupportActionBar(toolbar);

        NineGridView.setImageLoader(new GlideImageLoader());

        ArrayList<NewsTabFragment> fragments = new ArrayList<>();
        NewsTabFragment fragment1 = NewsTabFragment.newInstance();
        fragment1.setTitle("Android");
        fragments.add(fragment1);
        NewsTabFragment fragment2 = NewsTabFragment.newInstance();
        fragment2.setTitle("iOS");
        fragments.add(fragment2);
        NewsTabFragment fragment3 = NewsTabFragment.newInstance();
        fragment3.setTitle("前端");
        fragments.add(fragment3);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(fragments.size());
        tab.setupWithViewPager(viewPager);
    }

    @OnClick(R.id.fab)
    public void onViewClicked() {
        Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show();
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private List<NewsTabFragment> fragments;

        public MyPagerAdapter(FragmentManager fm, List<NewsTabFragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments.get(position).getTitle();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
