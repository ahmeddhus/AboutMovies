package com.ahmedelsayed.aboutmovies.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ahmedelsayed.aboutmovies.R;
import com.ahmedelsayed.aboutmovies.models.MoviesModel;
import com.ahmedelsayed.aboutmovies.view.adapters.MainMoviesAdapter;
import com.ahmedelsayed.aboutmovies.view.adapters.MoviesAdapter;
import com.ahmedelsayed.aboutmovies.view.adapters.SeeAllAdapter;
import com.ahmedelsayed.aboutmovies.view.customLayouts.CustomLayoutSeeAll;
import com.ahmedelsayed.aboutmovies.viewmodels.MoviesViewModel;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ahmedelsayed.aboutmovies.view.activities.MovieDetailsActivity.MOVIE_ID;

public class SeeAllMovies extends AppCompatActivity implements SeeAllAdapter.OnItemClickListener{

    public static final String POPULAR = "popular";
    public static final String TOP_MOVIES = "top movies";
    public static final String NOW_PLAYING = "now playing";
    public static final String COMING_SOON = "coming soon";

    @BindView(R.id.toolbar_seeall)
    Toolbar toolbar;
    @BindView(R.id.rv_seeall)
    RecyclerView rv;
    @BindView(R.id.loading)
    ImageView loading;

    SeeAllAdapter seeAllAdapter;
    MoviesViewModel moviesViewModel;
    List<MoviesModel.Results> moviesModelsAction = new ArrayList<>();
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all_movies);
        init();
    }

    private void init(){
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesViewModel.init();
        setData(1, false);

        Glide.with(SeeAllMovies.this)
                .load(R.drawable.loading)
                .into(loading);

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    loading.setVisibility(View.VISIBLE);
                    i++;
                    setData(i, true);
                }
            }
        });
    }

    private void setData(int pageNum, boolean paging){
        Intent intent = SeeAllMovies.this.getIntent();
        if (intent.getAction() != null){
            switch (intent.getAction()){
                case POPULAR:
                    toolbar.setTitle("Popular Movies");
                    moviesViewModel.getPopularMovies(pageNum).observe(this, moviesModel -> setRV(moviesModel.getResults(), paging));
                    break;
                case TOP_MOVIES:
                    toolbar.setTitle("Top Movies");
                    moviesViewModel.getTopMovies(pageNum).observe(this, moviesModel -> setRV(moviesModel.getResults(), paging));
                    break;
                case NOW_PLAYING:
                    toolbar.setTitle("Now Playing");
                    moviesViewModel.getNowMovies(pageNum).observe(this, moviesModel -> setRV(moviesModel.getResults(), paging));
                    break;
                case COMING_SOON:
                    toolbar.setTitle("Coming Soon");
                    moviesViewModel.getComingMovies(pageNum).observe(this, moviesModel -> setRV(moviesModel.getResults(), paging));
                    break;
                default:
                    Toast.makeText(SeeAllMovies.this, "ERROR!", Toast.LENGTH_LONG).show();
                    finish();
                    break;
            }
        }
        else {
            Toast.makeText(SeeAllMovies.this, "ERROR!", Toast.LENGTH_LONG).show();
        }
    }

    private void setRV(List<MoviesModel.Results> mainMoviesModels, boolean paging){
        moviesModelsAction.addAll(mainMoviesModels);

        if(!paging) {
            seeAllAdapter = new SeeAllAdapter(SeeAllMovies.this, moviesModelsAction, SeeAllMovies.this);
            rv.setAdapter(seeAllAdapter);
            rv.setLayoutManager(new CustomLayoutSeeAll(SeeAllMovies.this, count(), GridLayoutManager.VERTICAL, false, loading));
        }
        else{
            loading.setVisibility(View.GONE);
            seeAllAdapter.notifyDataSetChanged ();
        }
    }


    private int count(){
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 150);
        return noOfColumns;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onItemClikced(int position) {
        int movie_id = moviesModelsAction.get(position).getId();
        startActivity(new Intent(this, MovieDetailsActivity.class).putExtra(MOVIE_ID, movie_id));
    }
}