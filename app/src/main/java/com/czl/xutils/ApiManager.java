package com.czl.xutils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {
    private final static Api mApi;

    static {
        Retrofit retrofit = new Retrofit.Builder()
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                //设置网络请求的Url地址
                .baseUrl("https://gank.io/")
                .build();
        // 创建网络请求接口的实例
        mApi = retrofit.create(Api.class);
    }

    public static LiveData<Today> getToday() {
        MutableLiveData<Today> todayLiveData = new MutableLiveData<>();
        mApi.get().enqueue(new Callback<Today>() {
            @Override
            public void onResponse(Call<Today> call, Response<Today> response) {
                todayLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Today> call, Throwable t) {

            }
        });
        return todayLiveData;
    }
}
