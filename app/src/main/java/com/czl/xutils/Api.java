package com.czl.xutils;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("api/today")
    Call<Today> get();
}
