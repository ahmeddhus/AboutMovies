package com.ahmedelsayed.aboutmovies.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ahmedelsayed.aboutmovies.basics.RetrofitClient;
import com.ahmedelsayed.aboutmovies.basics.RetrofitService;
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

    public MutableLiveData<MoviesModel> getMovies(String apiKey, String langyage, int page){
        MutableLiveData<MoviesModel> newData = new MutableLiveData<>();

        retrofitClient.getMovies(apiKey, langyage, page).enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(@NotNull Call<MoviesModel> call, @NotNull Response<MoviesModel> response) {
                if (response.isSuccessful())
                    newData.setValue(response.body());
                Log.e("getMoviesonFailure: ", response.message());
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
