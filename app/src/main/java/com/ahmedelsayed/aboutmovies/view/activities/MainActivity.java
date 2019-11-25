package com.ahmedelsayed.aboutmovies.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ahmedelsayed.aboutmovies.R;
import com.ahmedelsayed.aboutmovies.models.MoviesModel;
import com.ahmedelsayed.aboutmovies.utils.Constants;
import com.ahmedelsayed.aboutmovies.view.customLayouts.CustomLayoutManager;
import com.ahmedelsayed.aboutmovies.view.adapters.MainMoviesAdapter;
import com.ahmedelsayed.aboutmovies.view.adapters.MoviesAdapter;
import com.ahmedelsayed.aboutmovies.viewmodels.MoviesViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ahmedelsayed.aboutmovies.utils.HelperMethods.IsConnected;

public class MainActivity extends AppCompatActivity implements MainMoviesAdapter.OnItemClickListener {

    @BindView(R.id.rv_popular)
    RecyclerView rv_popular;
    @BindView(R.id.rv_top)
    RecyclerView rv_top;
    @BindView(R.id.rv_now)
    RecyclerView rv_now;
    @BindView(R.id.rv_coming)
    RecyclerView rv_coming;
    @BindView(R.id.s)
    LinearLayout linearLayout;
    @BindView(R.id.main_refresh)
    SwipeRefreshLayout refreshLayout;

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
        refreshLayout.setOnRefreshListener(this::getData);
        if (!IsConnected(MainActivity.this))
            Toast.makeText(MainActivity.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
    }

    private void getData() {
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesViewModel.init();

        moviesViewModel.getPopularMovies(1).observe(this, moviesModel -> {
            if (moviesModel != null) {
                setRVPopular(moviesModel.getResults());
                popularMoviesModels1 = moviesModel.getResults();
            } else if (IsConnected(MainActivity.this))
                Toast.makeText(MainActivity.this, "an error has occurred", Toast.LENGTH_LONG).show();
            refreshLayout.setRefreshing(false);
        });

        moviesViewModel.getTopMovies(1).observe(this, moviesModel -> {
            if (moviesModel != null)
                setRV(moviesModel.getResults(), rv_top);
            else if (IsConnected(MainActivity.this))
                Toast.makeText(MainActivity.this, "an error has occurred", Toast.LENGTH_LONG).show();
            refreshLayout.setRefreshing(false);

        });
        moviesViewModel.getNowMovies(1).observe(this, moviesModel -> {
            if (moviesModel != null)
                setRV(moviesModel.getResults(), rv_now);
            else if (IsConnected(MainActivity.this))
                Toast.makeText(MainActivity.this, "an error has occurred", Toast.LENGTH_LONG).show();
            refreshLayout.setRefreshing(false);
        });

        moviesViewModel.getComingMovies(1).observe(this, moviesModel -> {
            if (moviesModel != null)
                setRV(moviesModel.getResults(), rv_coming);
            else if (IsConnected(MainActivity.this))
                Toast.makeText(MainActivity.this, "Can error has occurred", Toast.LENGTH_LONG).show();
            refreshLayout.setRefreshing(false);
        });
    }

    private void setRVPopular(List<MoviesModel.Results> mainMoviesModels) {
        mainMoviesAdapter = new MainMoviesAdapter(mainMoviesModels, this);
        rv_popular.setAdapter(mainMoviesAdapter);
        rv_popular.setLayoutManager(new CustomLayoutManager(MainActivity.this,
                LinearLayoutManager.HORIZONTAL, false, linearLayout.getWidth()));
        rv_popular.smoothScrollToPosition(2);
    }

    private void setRV(List<MoviesModel.Results> mainMoviesModels, RecyclerView rv) {
        moviesAdapter = new MoviesAdapter(mainMoviesModels, MainActivity.this);
        rv.setAdapter(moviesAdapter);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
    }


    @Override
    public void onItemClikced(int position, ImageView imageView) {
        int movie_id = popularMoviesModels1.get(position).getId();
        startActivity(new Intent(this, MovieDetailsActivity.class).
                putExtra(Constants.MOVIE_ID, movie_id));
    }

    public void goSearch(View view) {
        startActivity(new Intent(MainActivity.this, SearchableActivity.class));
    }
}

