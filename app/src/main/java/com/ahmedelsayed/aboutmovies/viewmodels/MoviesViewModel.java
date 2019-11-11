package com.ahmedelsayed.aboutmovies.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmedelsayed.aboutmovies.models.MoviesModel;
import com.ahmedelsayed.aboutmovies.repositories.MoviesRepository;

import static android.provider.MediaStore.Video.VideoColumns.LANGUAGE;
import static com.ahmedelsayed.aboutmovies.basics.retrofit.RetrofitService.API_KEY;

public class MoviesViewModel extends ViewModel {

    private MutableLiveData<MoviesModel> popularLiveData;
    private MutableLiveData<MoviesModel> topLiveData;
    private MutableLiveData<MoviesModel> nowLiveData;
    private MutableLiveData<MoviesModel> comingLiveData;

    private MoviesRepository moviesRepository;

    public void init(){
        if (popularLiveData != null || topLiveData != null)
            return;

        moviesRepository = MoviesRepository.getInstance();
    }

    public LiveData<MoviesModel> getPopularMovies(int pageNum){
        popularLiveData = moviesRepository.getPopularMovies(API_KEY, LANGUAGE, pageNum);
        return popularLiveData;
    }

    public LiveData<MoviesModel> getTopMovies(int pageNum){
        topLiveData = moviesRepository.getTopMovies(API_KEY, LANGUAGE, pageNum);
        return topLiveData;
    }

    public LiveData<MoviesModel> getNowMovies(int pageNum){
        nowLiveData = moviesRepository.getNowMovies(API_KEY, LANGUAGE, pageNum);
        return nowLiveData;
    }

    public LiveData<MoviesModel> getComingMovies(int pageNum){
        comingLiveData = moviesRepository.getComingMovies(API_KEY, LANGUAGE, pageNum);
        return comingLiveData;
    }


}
