package com.ahmedelsayed.aboutmovies.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ahmedelsayed.aboutmovies.data.retrofit.RetrofitClient;
import com.ahmedelsayed.aboutmovies.data.retrofit.RetrofitService;
import com.ahmedelsayed.aboutmovies.models.MoviesModel;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesRepository {

    private RetrofitClient retrofitClient;
    private static MoviesRepository moviesRepository;

    public static MoviesRepository getInstance(){
        if (moviesRepository == null){
            moviesRepository = new MoviesRepository();
        }
        return moviesRepository;
    }

    private MoviesRepository() {
        retrofitClient = RetrofitService.getRetro().create(RetrofitClient.class);
    }

    public MutableLiveData<MoviesModel> getPopularMovies(String apiKey, String langyage, int page){
        MutableLiveData<MoviesModel> newData = new MutableLiveData<>();

        retrofitClient.getPopularMovies(apiKey, langyage, page).enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(@NotNull Call<MoviesModel> call, @NotNull Response<MoviesModel> response) {
                if (response.isSuccessful())
                    newData.setValue(response.body());
                Log.e("getMovies: ", response.message());
            }

            @Override
            public void onFailure(@NotNull Call<MoviesModel> call, @NotNull Throwable t) {
                newData.setValue(null);
                Log.e("getMoviesonFailure: ", t.getMessage());
            }
        });

        return newData;
    }

    public MutableLiveData<MoviesModel> getTopMovies(String apiKey, String langyage, int page){
        MutableLiveData<MoviesModel> newData = new MutableLiveData<>();

        retrofitClient.getTopMovies(apiKey, langyage, page).enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(@NotNull Call<MoviesModel> call, @NotNull Response<MoviesModel> response) {
                if (response.isSuccessful())
                    newData.setValue(response.body());
                Log.e("getMovies: ", response.message());
            }

            @Override
            public void onFailure(@NotNull Call<MoviesModel> call, @NotNull Throwable t) {
                newData.setValue(null);
                Log.e("getMoviesonFailure: ", t.getMessage());
            }
        });

        return newData;
    }

    public MutableLiveData<MoviesModel> getNowMovies(String apiKey, String langyage, int page){
        MutableLiveData<MoviesModel> newData = new MutableLiveData<>();

        retrofitClient.getNowMovies(apiKey, langyage, page).enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(@NotNull Call<MoviesModel> call, @NotNull Response<MoviesModel> response) {
                if (response.isSuccessful())
                    newData.setValue(response.body());
                Log.e("getMovies: ", response.message());
            }

            @Override
            public void onFailure(@NotNull Call<MoviesModel> call, @NotNull Throwable t) {
                newData.setValue(null);
                Log.e("getMoviesonFailure: ", t.getMessage());
            }
        });

        return newData;
    }

    public MutableLiveData<MoviesModel> getComingMovies(String apiKey, String langyage, int page){
        MutableLiveData<MoviesModel> newData = new MutableLiveData<>();

        retrofitClient.getComingMovies(apiKey, langyage, page).enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(@NotNull Call<MoviesModel> call, @NotNull Response<MoviesModel> response) {
                if (response.isSuccessful())
                    newData.setValue(response.body());
                Log.e("getMovies: ", response.message());
            }

            @Override
            public void onFailure(@NotNull Call<MoviesModel> call, @NotNull Throwable t) {
                newData.setValue(null);
                Log.e("getMoviesonFailure: ", t.getMessage());
            }
        });

        return newData;
    }
}
