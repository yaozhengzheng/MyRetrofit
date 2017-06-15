package com.example.myretrofit.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.myretrofit.R;
import com.example.myretrofit.adapter.NewsAdapter;
import com.example.myretrofit.bean.GankModel;
import com.example.myretrofit.bean.GankResponse;
import com.example.myretrofit.bean.NewsCallback;
import com.example.myretrofit.utils.Urls;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created on 2017/6/14.
 * author ${yao}.
 */

public class NewsTabFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private static final int PAGE_SIZE = 20;
    private String fragmentTitle;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    private Context context;
    private int currentPage = 2;
    private NewsAdapter newsAdapter;
    private String url;
    private boolean isInitCache = false;
    protected LayoutInflater inflater;

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_refresh, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    public static NewsTabFragment newInstance() {
        return new NewsTabFragment();
    }

    public void initData() {
        url = Urls.URL_GANK_BASE + fragmentTitle + "/" + PAGE_SIZE + "/";
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        newsAdapter = new NewsAdapter(null);
        newsAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        newsAdapter.isFirstOnly(false);
        recyclerView.setAdapter(newsAdapter);

        refreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        refreshLayout.setOnRefreshListener(this);
        newsAdapter.setOnLoadMoreListener(this);

        //开启loading,获取数据
        setRefreshing(true);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        OkGo.<GankResponse<List<GankModel>>>get(url + "1")//
                .cacheKey("TabFragment_" + fragmentTitle)       //由于该fragment会被复用,必须保证key唯一,否则数据会发生覆盖
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)  //缓存模式先使用缓存,然后使用网络数据
                .execute(new NewsCallback<GankResponse<List<GankModel>>>() {
                    @Override
                    public void onSuccess(Response<GankResponse<List<GankModel>>> response) {
                        List<GankModel> results = response.body().results;
                        if (results != null) {
                            currentPage = 2;
                            newsAdapter.setNewData(results);
                        }
                    }

                    @Override
                    public void onCacheSuccess(Response<GankResponse<List<GankModel>>> response) {
                        //一般来说,只需呀第一次初始化界面的时候需要使用缓存刷新界面,以后不需要,所以用一个变量标识
                        if (!isInitCache) {
                            //一般来说,缓存回调成功和网络回调成功做的事情是一样的,所以这里直接回调onSuccess
                            onSuccess(response);
                            isInitCache = true;
                        }
                    }

                    @Override
                    public void onError(Response<GankResponse<List<GankModel>>> response) {
                        //网络请求失败的回调,一般会弹个Toast
                        Toast.makeText(context, response.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFinish() {
                        //可能需要移除之前添加的布局
                        newsAdapter.removeAllFooterView();
                        //最后调用结束刷新的方法
                        setRefreshing(false);
                    }
                });
    }

    @Override
    public void onLoadMoreRequested() {
        OkGo.<GankResponse<List<GankModel>>>get(url + currentPage)//
                .cacheMode(CacheMode.NO_CACHE)       //上拉不需要缓存
                .execute(new NewsCallback<GankResponse<List<GankModel>>>() {
                    @Override
                    public void onSuccess(Response<GankResponse<List<GankModel>>> response) {
                        List<GankModel> results = response.body().results;
                        if (results != null && results.size() > 0) {
                            currentPage++;
                            newsAdapter.addData(results);
                        } else {
                            //显示没有更多数据
                            newsAdapter.loadComplete();
                            View noDataView = inflater.inflate(R.layout.item_no_data, (ViewGroup) recyclerView.getParent(), false);
                            newsAdapter.addFooterView(noDataView);
                        }
                    }

                    @Override
                    public void onError(Response<GankResponse<List<GankModel>>> response) {
                        //显示数据加载失败,点击重试
                        newsAdapter.showLoadMoreFailedView();
                        //网络请求失败的回调,一般会弹个Toast
                        Toast.makeText(context, response.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void setRefreshing(final boolean refreshing) {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(refreshing);
            }
        });
    }

    public String getTitle() {
        return TextUtils.isEmpty(fragmentTitle) ? "" : fragmentTitle;
    }

    public void setTitle(String title) {
        fragmentTitle = title;
    }
}
