package com.ahmedelsayed.aboutmovies.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmedelsayed.aboutmovies.models.MovieDetailsModel;
import com.ahmedelsayed.aboutmovies.repositories.MDetailsRepository;

import static android.provider.MediaStore.Video.VideoColumns.LANGUAGE;
import static com.ahmedelsayed.aboutmovies.basics.RetrofitService.API_KEY;

public class MDetailsViewModel extends ViewModel {

    private MutableLiveData<MovieDetailsModel> mutableLiveData;
    private MDetailsRepository mDetailsRepository;

    public void inti(String movieId){
        if(mutableLiveData != null)
            return;

        mDetailsRepository = MDetailsRepository.getInstance();
        mutableLiveData = mDetailsRepository.getMDetails(movieId, API_KEY, LANGUAGE);
    }

    public LiveData<MovieDetailsModel> getMDetails(){
        return mutableLiveData;
    }
}
