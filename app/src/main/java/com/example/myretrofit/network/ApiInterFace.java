package com.example.myretrofit.network;

import com.example.myretrofit.bean.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created on 2017/6/5.
 * author ${yao}.
 */

public interface ApiInterFace {
    @GET("category/query")
    Call<Message> getInbox(@Query("key") String key);

}

