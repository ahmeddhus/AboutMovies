package com.ahmedelsayed.aboutmovies;

import com.ahmedelsayed.aboutmovies.models.MainMoviesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitClient {

    @GET("movie/popular")
    Call<MainMoviesModel> getMovies( @Query("api_key") String apiKey,
                                           @Query("language") String language,
                                           @Query("page") int page);

}