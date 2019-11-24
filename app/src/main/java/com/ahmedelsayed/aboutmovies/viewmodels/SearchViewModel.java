package com.ahmedelsayed.aboutmovies.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmedelsayed.aboutmovies.models.MoviesModel;
import com.ahmedelsayed.aboutmovies.repositories.SearchRepository;
import com.ahmedelsayed.aboutmovies.utils.Constants;

public class SearchViewModel extends ViewModel {

    private MutableLiveData<MoviesModel> searchMoviesLiveData;

    private SearchRepository searchRepository;

    public void init() {
        if (searchMoviesLiveData != null)
            return;

        searchRepository = SearchRepository.getInstance();

    }

    public LiveData<MoviesModel> getSearchableMovies(String query) {
        searchMoviesLiveData = searchRepository.getSearchableMovies(Constants.API_KEY, query);
        return searchMoviesLiveData;
    }

}
