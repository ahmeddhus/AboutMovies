package com.ahmedelsayed.aboutmovies.basics;

import com.ahmedelsayed.aboutmovies.models.MoviesModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitClient {

    @GET("movie/popular")
    Call<MoviesModel> getMovies(@Query("api_key") String apiKey,
                                @Query("language") String language,
                                @Query("page") int page);

}