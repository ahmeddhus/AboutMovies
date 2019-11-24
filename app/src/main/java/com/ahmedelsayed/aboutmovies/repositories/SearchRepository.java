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

public class SearchRepository {
    private RetrofitClient retrofitClient;
    private static SearchRepository searchRepository;

    public static SearchRepository getInstance(){
        if (searchRepository == null){
            searchRepository = new SearchRepository();
        }
        return searchRepository;
    }

    private SearchRepository() {
        retrofitClient = RetrofitService.getRetro().create(RetrofitClient.class);
    }

    public MutableLiveData<MoviesModel> getSearchableMovies(String apiKey, String query){
        MutableLiveData<MoviesModel> newData = new MutableLiveData<>();

        retrofitClient.getSearchableMovies(apiKey, query).enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(@NotNull Call<MoviesModel> call, @NotNull Response<MoviesModel> response) {
                if (response.isSuccessful())
                    newData.setValue(response.body());
                Log.e("getSearchableMovies", response.message());
            }

            @Override
            public void onFailure(@NotNull Call<MoviesModel> call, @NotNull Throwable t) {
                newData.setValue(null);
                Log.e("SearchableMFailure", t.getMessage());
            }
        });

        return newData;
    }
}
