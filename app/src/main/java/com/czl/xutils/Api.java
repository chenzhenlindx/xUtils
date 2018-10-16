package com.czl.xutils;

import com.czl.xutils.model.NetRsp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    @GET("api/today")
    Call<Today> get();

    @GET("api/data/Android/{pageSize}/{pageNum}")
    Call<NetRsp> getAndroids(@Path("pageSize")int pageSize, @Path("pageNum")int pageNum);
}
