package com.anup.myapplication.api;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiManager {
    private static final ApiManager ourInstance = new ApiManager();


    public static ApiManager getInstance() {
        return ourInstance;
    }

    public static  String Cookie = "";

    private ApiManager() {
    }


    public ApiClient getApiClient(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        return retrofit.create(ApiClient.class);
    }
}