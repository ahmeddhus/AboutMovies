package com.ahmedelsayed.aboutmovies.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmedelsayed.aboutmovies.R;
import com.ahmedelsayed.aboutmovies.basics.BaseActivity;
import com.ahmedelsayed.aboutmovies.models.MoviesModel;
import com.ahmedelsayed.aboutmovies.view.adapters.MainMoviesAdapter;
import com.ahmedelsayed.aboutmovies.viewmodels.MoviesViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ahmedelsayed.aboutmovies.view.activities.MovieDetailsActivity.MOVIE_ID;


public class MainActivity extends BaseActivity implements MainMoviesAdapter.OnItemClickListener{

    @BindView(R.id.rv_popular)
    RecyclerView rv;

    MainMoviesAdapter mainMoviesAdapter;
    MoviesViewModel moviesViewModel;
    List<MoviesModel.Results> mainMoviesModels1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesViewModel.init();
        moviesViewModel.getMovies().observe(this, moviesModel -> {
            setRV(moviesModel.getResults());
            mainMoviesModels1 = moviesModel.getResults();
        });
    }

    private void setRV(List<MoviesModel.Results> mainMoviesModels){
        mainMoviesAdapter = new MainMoviesAdapter(mainMoviesModels, this);
        rv.setAdapter(mainMoviesAdapter);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void onItemClikced(int position) {
        int movie_id = mainMoviesModels1.get(position).getId();

        startActivity(new Intent(this, MovieDetailsActivity.class)
                .putExtra(MOVIE_ID, movie_id));
    }
}
