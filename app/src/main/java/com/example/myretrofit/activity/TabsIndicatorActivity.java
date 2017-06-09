package com.example.myretrofit.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.myretrofit.R;
import com.example.myretrofit.fragment.TextFragment;
import com.yinglan.alphatabs.AlphaTabsIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 姚政诤
 */
public class TabsIndicatorActivity extends AppCompatActivity {

    @Bind(R.id.mViewPager)
    ViewPager mViewPager;
    @Bind(R.id.alphaIndicator)
    AlphaTabsIndicator alphaIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs_indicator);
        ButterKnife.bind(this);

        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mainAdapter);
        mViewPager.addOnPageChangeListener(mainAdapter);

        alphaIndicator = (AlphaTabsIndicator) findViewById(R.id.alphaIndicator);
        alphaIndicator.setViewPager(mViewPager);

        alphaIndicator.getTabView(0).showNumber(6);
        alphaIndicator.getTabView(1).showNumber(888);
        alphaIndicator.getTabView(2).showNumber(88);
        alphaIndicator.getTabView(3).showPoint();
    }


    private class MainAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

        private List<Fragment> fragments = new ArrayList<>();
        private String[] titles = {"微信", "通讯录", "发现", "我"};

        public MainAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(TextFragment.newInstance(titles[0]));
            fragments.add(TextFragment.newInstance(titles[1]));
            fragments.add(TextFragment.newInstance(titles[2]));
            fragments.add(TextFragment.newInstance(titles[3]));
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (0 == position) {
                alphaIndicator.getTabView(0).showNumber(alphaIndicator.getTabView(0).getBadgeNumber() - 1);
            } else if (2 == position) {
                alphaIndicator.getCurrentItemView().removeShow();
            } else if (3 == position) {
                alphaIndicator.removeAllBadge();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
