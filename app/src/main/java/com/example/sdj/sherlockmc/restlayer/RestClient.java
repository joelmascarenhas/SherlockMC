package com.example.sdj.sherlockmc.restlayer;

import com.example.sdj.sherlockmc.utils.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sdj on 4/16/17.
 *
 * Rest Client class giving Retrofit object and endpoint objects to make respective REST calls
 */

public class RestClient {
    public static Retrofit getRetroInstance(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
    }

    public static APIEndpoints endPointFetcher(){
        return getRetroInstance().create(APIEndpoints.class);
    }
}
