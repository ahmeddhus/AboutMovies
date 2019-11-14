package com.ahmedelsayed.aboutmovies.data.retrofit;

import com.ahmedelsayed.aboutmovies.models.CreditsModel;
import com.ahmedelsayed.aboutmovies.models.MovieDetailsModel;
import com.ahmedelsayed.aboutmovies.models.MoviesModel;
import com.ahmedelsayed.aboutmovies.models.PeopleModel;
import com.ahmedelsayed.aboutmovies.models.VideosModel;
import com.ahmedelsayed.aboutmovies.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitClient {

    @GET("movie/popular")
    Call<MoviesModel> getPopularMovies(@Query("api_key") String apiKey,
                                @Query("language") String language,
                                @Query("page") int page);

    @GET("movie/top_rated")
    Call<MoviesModel> getTopMovies(@Query("api_key") String apiKey,
                                @Query("language") String language,
                                @Query("page") int page);

    @GET("movie/now_playing")
    Call<MoviesModel> getNowMovies(@Query("api_key") String apiKey,
                                   @Query("language") String language,
                                   @Query("page") int page);

    @GET("movie/upcoming")
    Call<MoviesModel> getComingMovies(@Query("api_key") String apiKey,
                                   @Query("language") String language,
                                   @Query("page") int page);

    @GET("movie/{movie_id}?api_key="+ Constants.API_KEY)
    Call<MovieDetailsModel> getMDetails(@Path("movie_id") int movieId);

    @GET("movie/{movie_id}/videos")
    Call<VideosModel> getVideos(@Path("movie_id") int movieId,
                                @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/credits")
    Call<CreditsModel> getCredits(@Path("movie_id") int movieId,
                                  @Query("api_key") String apiKey);
    @GET("person/{person_id}")
    Call<PeopleModel> getPeople(@Path("person_id") int persoId,
                                @Query("api_key") String apiKey);
}
