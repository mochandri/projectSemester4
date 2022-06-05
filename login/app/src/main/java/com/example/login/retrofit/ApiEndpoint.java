package com.example.login.retrofit;

import com.example.login.MainModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {
    @GET("barang")
    Call<MainModel> getData();
}
