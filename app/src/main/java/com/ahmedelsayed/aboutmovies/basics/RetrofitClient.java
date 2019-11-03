package com.ahmedelsayed.aboutmovies.basics;

import com.ahmedelsayed.aboutmovies.models.MovieDetailsModel;
import com.ahmedelsayed.aboutmovies.models.MoviesModel;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitClient {

    @GET("movie/popular")
    Call<MoviesModel> getMovies(@Query("api_key") String apiKey,
                                @Query("language") String language,
                                @Query("page") int page);

    @GET("movie/{movie_id}")
    Call<MovieDetailsModel> getMDetails(@Path("movie_id") int movieId,
                                        @Query("api_key") String apiKey,
                                        @Query("language") String language);

}