package com.example.sdj.sherlockmc.restlayer;

import com.example.sdj.sherlockmc.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sdj on 4/16/17.
 *
 * Rest Client class giving Retrofit object and endpoint objects to make respective REST calls
 */

public class RestClient {
    public static Retrofit getRetroInstance(){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static APIEndpoints endPointFetcher(){
        return getRetroInstance().create(APIEndpoints.class);
    }
}
