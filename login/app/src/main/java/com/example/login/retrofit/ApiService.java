package com.example.login.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private static  String BASE_URL ="http://192.168.117.44/projectSemester4/ci3/api/";
    private  static Retrofit retrofit = null;
    public static ApiEndpoint endpoint (){
        retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiEndpoint.class);
    }
}
