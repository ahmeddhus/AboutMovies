package com.ahmedelsayed.aboutmovies.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmedelsayed.aboutmovies.models.MainMoviesModel;
import com.ahmedelsayed.aboutmovies.repositories.MoviesRepository;

import java.util.List;

import static android.provider.MediaStore.Video.VideoColumns.LANGUAGE;
import static com.ahmedelsayed.aboutmovies.RetrofitService.API_KEY;

public class MoviesViewModel extends ViewModel {

    private MutableLiveData<MainMoviesModel> listMutableLiveData;
    private MoviesRepository moviesRepository;

    public void init(){
        if (listMutableLiveData != null)
            return;

        moviesRepository = MoviesRepository.getInstance();
        listMutableLiveData = moviesRepository.getMovies(API_KEY, LANGUAGE, 1);
    }

    public LiveData<MainMoviesModel> getMovies(){
        return listMutableLiveData;
    }
}
