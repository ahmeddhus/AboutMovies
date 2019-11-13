package com.ahmedelsayed.aboutmovies.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmedelsayed.aboutmovies.models.PeopleModel;
import com.ahmedelsayed.aboutmovies.repositories.PeopleRepository;

import static com.ahmedelsayed.aboutmovies.basics.retrofit.RetrofitService.API_KEY;

public class PeopleViewModel extends ViewModel {

    private MutableLiveData<PeopleModel> peopleModelMutableLiveData;
    private PeopleRepository peopleRepository;

    public void init(){
        if (peopleModelMutableLiveData != null)
            return;

        peopleRepository = PeopleRepository.getInstance();
    }

    public LiveData<PeopleModel> getPeople(int personId){
        peopleModelMutableLiveData = peopleRepository.getPeople(personId, API_KEY);
        return peopleModelMutableLiveData;
    }
}
