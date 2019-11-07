package com.ahmedelsayed.aboutmovies.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmedelsayed.aboutmovies.R;
import com.ahmedelsayed.aboutmovies.basics.BaseActivity;
import com.ahmedelsayed.aboutmovies.models.MoviesModel;
import com.ahmedelsayed.aboutmovies.view.adapters.MainMoviesAdapter;
import com.ahmedelsayed.aboutmovies.view.adapters.MoviesAdapter;
import com.ahmedelsayed.aboutmovies.viewmodels.MoviesViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ahmedelsayed.aboutmovies.view.activities.MovieDetailsActivity.MOVIE_ID;


public class MainActivity extends BaseActivity implements MainMoviesAdapter.OnItemClickListener{

    @BindView(R.id.rv_popular)
    RecyclerView rv_popular;
    @BindView(R.id.rv_top)
    RecyclerView rv_top;
    @BindView(R.id.rv_now)
    RecyclerView rv_now;
    @BindView(R.id.rv_coming)
    RecyclerView rv_coming;

    MainMoviesAdapter mainMoviesAdapter;
    MoviesAdapter moviesAdapter;
    MoviesViewModel moviesViewModel;
    List<MoviesModel.Results> popularMoviesModels1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesViewModel.init();

        moviesViewModel.getPopularMovies().observe(this, moviesModel -> {
            setRVPopular(moviesModel.getResults());
            popularMoviesModels1 = moviesModel.getResults();
        });

        moviesViewModel.getTopMovies().observe(this, moviesModel -> setRV(moviesModel.getResults(), rv_top));

        moviesViewModel.getNowMovies().observe(this, moviesModel -> setRV(moviesModel.getResults(), rv_now));

        moviesViewModel.getComingMovies().observe(this, moviesModel -> setRV(moviesModel.getResults(), rv_coming));
    }

    private void setRVPopular(List<MoviesModel.Results> mainMoviesModels){
        mainMoviesAdapter = new MainMoviesAdapter(mainMoviesModels, this);
        rv_popular.setAdapter(mainMoviesAdapter);
        rv_popular.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void setRV(List<MoviesModel.Results> mainMoviesModels, RecyclerView rv){
        moviesAdapter = new MoviesAdapter(mainMoviesModels, MainActivity.this);
        rv.setAdapter(moviesAdapter);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void onItemClikced(int position) {
        int movie_id = popularMoviesModels1.get(position).getId();

        startActivity(new Intent(this, MovieDetailsActivity.class)
                .putExtra(MOVIE_ID, movie_id));
    }

}

