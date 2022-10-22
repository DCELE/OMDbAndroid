package com.example.omdbandroid.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String API_KEY = "6901b7677b691dcb80ee096b90691596";

    private static Retrofit retrofitBuilder = null;

    public static Retrofit retrofitBuilder(){
        if (retrofitBuilder == null){
            retrofitBuilder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofitBuilder;
    }
}
