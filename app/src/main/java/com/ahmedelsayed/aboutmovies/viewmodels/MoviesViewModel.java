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

        popularLiveData = moviesRepository.getPopularMovies(API_KEY, LANGUAGE, 1);
        topLiveData = moviesRepository.getTopMovies(API_KEY, LANGUAGE, 1);
        nowLiveData = moviesRepository.getNowMovies(API_KEY, LANGUAGE, 1);
        comingLiveData = moviesRepository.getComingMovies(API_KEY, LANGUAGE, 1);
    }

    public LiveData<MoviesModel> getPopularMovies(){
        return popularLiveData;
    }

    public LiveData<MoviesModel> getTopMovies(){
        return topLiveData;
    }

    public LiveData<MoviesModel> getNowMovies(){
        return nowLiveData;
    }

    public LiveData<MoviesModel> getComingMovies(){
        return comingLiveData;
    }


}
