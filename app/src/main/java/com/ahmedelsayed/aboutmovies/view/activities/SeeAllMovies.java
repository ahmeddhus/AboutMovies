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
import android.widget.ImageView;
import android.widget.Toast;

import com.ahmedelsayed.aboutmovies.R;
import com.ahmedelsayed.aboutmovies.models.MoviesModel;
import com.ahmedelsayed.aboutmovies.view.adapters.MainMoviesAdapter;
import com.ahmedelsayed.aboutmovies.view.adapters.MoviesAdapter;
import com.ahmedelsayed.aboutmovies.view.adapters.SeeAllAdapter;
import com.ahmedelsayed.aboutmovies.view.customLayouts.CustomLayoutSeeAll;
import com.ahmedelsayed.aboutmovies.viewmodels.MoviesViewModel;

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
    List<MoviesModel.Results> moviesModelsAction;

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
        setData();
    }

    private void setData(){
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);
        moviesViewModel.init();

        Intent intent = SeeAllMovies.this.getIntent();
        if (intent.getAction() != null){

            switch (intent.getAction()){
                case POPULAR:
                    toolbar.setTitle("Popular Movies");
                    moviesViewModel.getPopularMovies(1).observe(this, moviesModel -> { setRV(moviesModel.getResults());
                    moviesModelsAction = moviesModel.getResults();});
                    break;
                case TOP_MOVIES:
                    toolbar.setTitle("Top Movies");
                    moviesViewModel.getTopMovies(1).observe(this, moviesModel -> setRV(moviesModel.getResults()));
                    break;
                case NOW_PLAYING:
                    toolbar.setTitle("Now Playing");
                    moviesViewModel.getNowMovies(1).observe(this, moviesModel -> setRV(moviesModel.getResults()));
                    break;
                case COMING_SOON:
                    toolbar.setTitle("Coming Soon");
                    moviesViewModel.getComingMovies(1).observe(this, moviesModel -> setRV(moviesModel.getResults()));
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

    private void setRV(List<MoviesModel.Results> mainMoviesModels){
        seeAllAdapter = new SeeAllAdapter(mainMoviesModels, SeeAllMovies.this);
        rv.setAdapter(seeAllAdapter);
        rv.setLayoutManager(new CustomLayoutSeeAll(SeeAllMovies.this, count(), GridLayoutManager.VERTICAL, false, loading));
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
