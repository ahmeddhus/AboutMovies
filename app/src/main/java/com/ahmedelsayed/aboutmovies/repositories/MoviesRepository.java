package com.ahmedelsayed.aboutmovies.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ahmedelsayed.aboutmovies.RetrofitClient;
import com.ahmedelsayed.aboutmovies.RetrofitService;
import com.ahmedelsayed.aboutmovies.models.MainMoviesModel;

import java.util.List;

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

    public MoviesRepository() {
        retrofitClient = RetrofitService.getRetro().create(RetrofitClient.class);
    }

    public MutableLiveData<MainMoviesModel> getMovies(String apiKey, String langyage, int page){
        MutableLiveData<MainMoviesModel> newData = new MutableLiveData<>();

        retrofitClient.getMovies(apiKey, langyage, page).enqueue(new Callback<MainMoviesModel>() {
            @Override
            public void onResponse(Call<MainMoviesModel> call, Response<MainMoviesModel> response) {
                if (response.isSuccessful())
                    newData.setValue(response.body());
                Log.e("S", response.message());
            }

            @Override
            public void onFailure(Call<MainMoviesModel> call, Throwable t) {
                newData.setValue(null);
                Log.e("S", t.getMessage());
            }
        });

        return newData;
    }
}
