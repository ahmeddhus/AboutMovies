package com.ahmedelsayed.aboutmovies.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmedelsayed.aboutmovies.models.CreditsModel;
import com.ahmedelsayed.aboutmovies.models.MovieDetailsModel;
import com.ahmedelsayed.aboutmovies.models.VideosModel;
import com.ahmedelsayed.aboutmovies.repositories.MDetailsRepository;
import com.ahmedelsayed.aboutmovies.utils.Constants;

import static android.provider.MediaStore.Video.VideoColumns.LANGUAGE;

public class MDetailsViewModel extends ViewModel {

    private MutableLiveData<MovieDetailsModel> detailsModelMutableLiveData;
    private MutableLiveData<VideosModel> viewModelMutableLiveData;
    private MutableLiveData<CreditsModel> creditsModelMutableLiveData;


    public void inti(int movieId){
        if(detailsModelMutableLiveData != null || viewModelMutableLiveData != null)
            return;

        MDetailsRepository mDetailsRepository = MDetailsRepository.getInstance();
        detailsModelMutableLiveData = mDetailsRepository.getMDetails(movieId, Constants.API_KEY, LANGUAGE);
        viewModelMutableLiveData = mDetailsRepository.getVideos(movieId, Constants.API_KEY);
        creditsModelMutableLiveData = mDetailsRepository.getCredits(movieId, Constants.API_KEY);
    }

    public LiveData<MovieDetailsModel> getMDetails(){
        return detailsModelMutableLiveData;
    }

    public LiveData<VideosModel> getVideos(){
        return viewModelMutableLiveData;
    }

    public LiveData<CreditsModel> getCredits(){
        return creditsModelMutableLiveData;
    }

}
