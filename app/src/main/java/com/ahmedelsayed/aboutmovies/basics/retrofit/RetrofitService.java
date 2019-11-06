package com.ahmedelsayed.aboutmovies.basics.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String API_KEY = "6c20bdc9fb3e86590c16b5f77d4754c7";

    public static Retrofit getRetro() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }
}

//https://api.themoviedb.org/3/discover/movie?api_key=6c20bdc9fb3e86590c16b5f77d4754c7&page=1