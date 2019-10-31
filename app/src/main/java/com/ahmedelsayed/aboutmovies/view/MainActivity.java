package com.ahmedelsayed.aboutmovies.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmedelsayed.aboutmovies.R;
import com.ahmedelsayed.aboutmovies.models.MainMoviesModel;
import com.ahmedelsayed.aboutmovies.viewmodels.MoviesViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView rv;

    MainMoviesAdapter mainMoviesAdapter;
    MoviesViewModel moviesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesViewModel.init();
        moviesViewModel.getMovies().observe(this, mainMoviesModels -> setRV(mainMoviesModels.getResults()));
    }

    private void setRV(List<MainMoviesModel.Results> mainMoviesModels){
        mainMoviesAdapter = new MainMoviesAdapter(MainActivity.this, mainMoviesModels);
        rv.setAdapter(mainMoviesAdapter);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
}
