package com.example.login;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndpoint {
    @GET("barang.php")
    Call<MainModel> getData();
}
