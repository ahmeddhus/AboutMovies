package com.ahmedelsayed.aboutmovies.basics.retrofit;

import com.ahmedelsayed.aboutmovies.models.CreditsModel;
import com.ahmedelsayed.aboutmovies.models.MovieDetailsModel;
import com.ahmedelsayed.aboutmovies.models.MoviesModel;
import com.ahmedelsayed.aboutmovies.models.PeopleModel;
import com.ahmedelsayed.aboutmovies.models.VideosModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitClient {

    @GET("movie/popular")
    Call<MoviesModel> getMovies(@Query("api_key") String apiKey,
                                @Query("language") String language,
                                @Query("page") int page);

    @GET("movie/{movie_id}?api_key=6c20bdc9fb3e86590c16b5f77d4754c7")
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
//475557
//73421