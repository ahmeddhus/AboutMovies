package com.ahmedelsayed.aboutmovies.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.ahmedelsayed.aboutmovies.basics.retrofit.RetrofitClient;
import com.ahmedelsayed.aboutmovies.basics.retrofit.RetrofitService;
import com.ahmedelsayed.aboutmovies.models.MovieDetailsModel;
import com.ahmedelsayed.aboutmovies.models.PeopleModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeopleRepository {

    private RetrofitClient retrofitClient;
    private static PeopleRepository peopleRepository;

    public static PeopleRepository getInstance(){
        if(peopleRepository == null)
            peopleRepository = new PeopleRepository();
        return peopleRepository;
    }

    private PeopleRepository(){
        retrofitClient = RetrofitService.getRetro().create(RetrofitClient.class);
    }

    public MutableLiveData<PeopleModel> getPeople(int personId, String apiKey){
        MutableLiveData<PeopleModel> newData = new MutableLiveData<>();

        retrofitClient.getPeople(personId, apiKey).enqueue(new Callback<PeopleModel>() {
            @Override
            public void onResponse(Call<PeopleModel> call, Response<PeopleModel> response) {
                if (response.isSuccessful())
                    newData.setValue(response.body());
                Log.e("getPeople: ", response.message());
            }

            @Override
            public void onFailure(Call<PeopleModel> call, Throwable t) {
                newData.setValue(null);
                Log.e("getPeopleFailure: ", t.getMessage());
            }
        });
        return newData;
    }

}
