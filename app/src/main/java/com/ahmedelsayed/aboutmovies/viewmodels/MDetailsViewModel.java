package com.ahmedelsayed.aboutmovies.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmedelsayed.aboutmovies.models.MovieDetailsModel;
import com.ahmedelsayed.aboutmovies.models.VideosModel;
import com.ahmedelsayed.aboutmovies.repositories.MDetailsRepository;

import static android.provider.MediaStore.Video.VideoColumns.LANGUAGE;
import static com.ahmedelsayed.aboutmovies.basics.RetrofitService.API_KEY;

public class MDetailsViewModel extends ViewModel {

    private MutableLiveData<MovieDetailsModel> detailsModelMutableLiveData;
    private MutableLiveData<VideosModel> viewModelMutableLiveData;

    public void inti(int movieId){
        if(detailsModelMutableLiveData != null || viewModelMutableLiveData != null)
            return;

        MDetailsRepository mDetailsRepository = MDetailsRepository.getInstance();
        detailsModelMutableLiveData = mDetailsRepository.getMDetails(movieId, API_KEY, LANGUAGE);
        viewModelMutableLiveData = mDetailsRepository.getVideos(movieId, API_KEY);
    }

    public LiveData<MovieDetailsModel> getMDetails(){
        return detailsModelMutableLiveData;
    }

    public LiveData<VideosModel> getVideos(){
        return viewModelMutableLiveData;
    }
}
