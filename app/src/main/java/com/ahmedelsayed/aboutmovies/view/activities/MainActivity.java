package com.ahmedelsayed.aboutmovies.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmedelsayed.aboutmovies.R;
import com.ahmedelsayed.aboutmovies.basics.BaseActivity;
import com.ahmedelsayed.aboutmovies.models.MoviesModel;
import com.ahmedelsayed.aboutmovies.view.customLayouts.CustomLayoutManager;
import com.ahmedelsayed.aboutmovies.view.adapters.MainMoviesAdapter;
import com.ahmedelsayed.aboutmovies.view.adapters.MoviesAdapter;
import com.ahmedelsayed.aboutmovies.viewmodels.MoviesViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ahmedelsayed.aboutmovies.view.activities.MovieDetailsActivity.MOVIE_ID;
import static com.ahmedelsayed.aboutmovies.view.activities.SeeAllMovies.COMING_SOON;
import static com.ahmedelsayed.aboutmovies.view.activities.SeeAllMovies.NOW_PLAYING;
import static com.ahmedelsayed.aboutmovies.view.activities.SeeAllMovies.POPULAR;
import static com.ahmedelsayed.aboutmovies.view.activities.SeeAllMovies.TOP_MOVIES;


public class MainActivity extends BaseActivity implements MainMoviesAdapter.OnItemClickListener{

    @BindView(R.id.rv_popular)
    RecyclerView rv_popular;
    @BindView(R.id.rv_top)
    RecyclerView rv_top;
    @BindView(R.id.rv_now)
    RecyclerView rv_now;
    @BindView(R.id.rv_coming)
    RecyclerView rv_coming;
    @BindView(R.id.s)
    ScrollView s;

    MainMoviesAdapter mainMoviesAdapter;
    MoviesAdapter moviesAdapter;
    MoviesViewModel moviesViewModel;
    List<MoviesModel.Results> popularMoviesModels1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getData();
    }

    private void getData(){
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesViewModel.init();
        moviesViewModel.getPopularMovies(1).observe(this, moviesModel -> {
            setRVPopular(moviesModel.getResults());
            popularMoviesModels1 = moviesModel.getResults();
        });
        moviesViewModel.getTopMovies(1).observe(this, moviesModel -> setRV(moviesModel.getResults(), rv_top));
        moviesViewModel.getNowMovies(1).observe(this, moviesModel -> setRV(moviesModel.getResults(), rv_now));
        moviesViewModel.getComingMovies(1).observe(this, moviesModel -> setRV(moviesModel.getResults(), rv_coming));
    }

    private void setRVPopular(List<MoviesModel.Results> mainMoviesModels){
        mainMoviesAdapter = new MainMoviesAdapter(mainMoviesModels, this);
        rv_popular.setAdapter(mainMoviesAdapter);
        rv_popular.setLayoutManager(new CustomLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false,  s.getWidth(), 250));
        rv_popular.smoothScrollToPosition(2);
    }

    private void setRV(List<MoviesModel.Results> mainMoviesModels, RecyclerView rv){
        moviesAdapter = new MoviesAdapter(mainMoviesModels, MainActivity.this);
        rv.setAdapter(moviesAdapter);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void onItemClikced(int position, ImageView imageView) {
        int movie_id = popularMoviesModels1.get(position).getId();
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, "imageMain");
        Intent in = new Intent(this, MovieDetailsActivity.class).putExtra(MOVIE_ID, movie_id);
        startActivity(in, activityOptionsCompat.toBundle());
    }

    public void seeAll(View view) {
        switch (view.getId()){
            case R.id.allPopular:
                startActivity(new Intent(MainActivity.this, SeeAllMovies.class)
                        .setAction(POPULAR));
                break;
            case R.id.allTop:
                startActivity(new Intent(MainActivity.this, SeeAllMovies.class)
                        .setAction(TOP_MOVIES));
                break;
            case R.id.allNow:
                startActivity(new Intent(MainActivity.this, SeeAllMovies.class)
                        .setAction(NOW_PLAYING));
                break;
            case R.id.allComing:
                startActivity(new Intent(MainActivity.this, SeeAllMovies.class)
                        .setAction(COMING_SOON));
                break;
            default:
                break;
        }
    }
}

