package com.ahmedelsayed.aboutmovies.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ahmedelsayed.aboutmovies.basics.RetrofitClient;
import com.ahmedelsayed.aboutmovies.basics.RetrofitService;
import com.ahmedelsayed.aboutmovies.models.MovieDetailsModel;
import com.ahmedelsayed.aboutmovies.view.activities.MovieDetailsActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MDetailsRepository {

    private RetrofitClient retrofitClient;
    private static MDetailsRepository mDetailsRepository;

    public static MDetailsRepository getInstance(){
        if(mDetailsRepository == null)
            mDetailsRepository = new MDetailsRepository();
        return mDetailsRepository;
    }

    private MDetailsRepository(){
        retrofitClient = RetrofitService.getRetro().create(RetrofitClient.class);
    }

    public MutableLiveData<MovieDetailsModel> getMDetails(int movieId, String apiKey, String langyage){
        MutableLiveData<MovieDetailsModel> newData = new MutableLiveData<>();

        retrofitClient.getMDetails(movieId, apiKey, langyage).enqueue(new Callback<MovieDetailsModel>() {
            @Override
            public void onResponse(Call<MovieDetailsModel> call, Response<MovieDetailsModel> response) {
                if (response.isSuccessful())
                    newData.setValue(response.body());
                Log.e("getMoviesonFailure: ", response.message());
            }

            @Override
            public void onFailure(Call<MovieDetailsModel> call, Throwable t) {
                newData.setValue(null);
                Log.e("getMoviesonFailure: ", t.getMessage());
            }
        });
        return newData;
    }
}
