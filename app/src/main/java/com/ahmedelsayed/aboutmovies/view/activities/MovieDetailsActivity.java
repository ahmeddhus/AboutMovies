package com.ahmedelsayed.aboutmovies.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.ahmedelsayed.aboutmovies.R;
import com.ahmedelsayed.aboutmovies.databinding.ActivityMovieDetailsBinding;
import com.ahmedelsayed.aboutmovies.models.CreditsModel;
import com.ahmedelsayed.aboutmovies.models.VideosModel;
import com.ahmedelsayed.aboutmovies.utils.Constants;
import com.ahmedelsayed.aboutmovies.view.adapters.CastAdapter;
import com.ahmedelsayed.aboutmovies.view.adapters.CrewAdapter;
import com.ahmedelsayed.aboutmovies.view.adapters.VideosAdapter;
import com.ahmedelsayed.aboutmovies.viewmodels.MDetailsViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity {

    private int movie_id = 0;

    @BindView(R.id.toolbar_details)
    Toolbar toolbar;
    @BindView(R.id.movie_details)
    ImageView movie_details;
    @BindView(R.id.rv_videos)
    RecyclerView rv_videos;
    @BindView(R.id.rv_cast)
    RecyclerView rv_cast;
    @BindView(R.id.rv_crew)
    RecyclerView rv_crew;

    MDetailsViewModel mDetailsViewModel;
    VideosAdapter videosAdapter;
    CastAdapter castAdapter;
    CrewAdapter crewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMovieDetailsBinding binding =
                DataBindingUtil.setContentView(MovieDetailsActivity.this, R.layout.activity_movie_details);
        init();
        getData(binding);
    }

    private void init() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getData(ActivityMovieDetailsBinding binding) {
        mDetailsViewModel = ViewModelProviders.of(this).get(MDetailsViewModel.class);

        Intent intent = getIntent();
        movie_id = intent.getIntExtra(Constants.MOVIE_ID, 0);
        Log.e("ID", movie_id + "");

        mDetailsViewModel.inti(movie_id);
        mDetailsViewModel.getMDetails().observe(this, binding::setMdetails);

        mDetailsViewModel.getVideos().observe(this, videosModel -> setYoutubeRV(videosModel.getResults()));
        mDetailsViewModel.getCredits().observe(this, creditsModel -> {
            setCastRV(creditsModel.getCast());
            setCrewRV(creditsModel.getCrew());
        });
    }

    private void setYoutubeRV(List<VideosModel.Results> videosModel) {
        videosAdapter = new VideosAdapter(videosModel);
        rv_videos.setAdapter(videosAdapter);
        rv_videos.setLayoutManager(new LinearLayoutManager
                (MovieDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void setCastRV(List<CreditsModel.Cast> casts){
        castAdapter = new CastAdapter(casts, MovieDetailsActivity.this);
        rv_cast.setAdapter(castAdapter);
        rv_cast.setLayoutManager(new LinearLayoutManager
                (MovieDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void setCrewRV(List<CreditsModel.Crew> crews){
        crewAdapter = new CrewAdapter(crews, MovieDetailsActivity.this);
        rv_crew.setAdapter(crewAdapter);
        rv_crew.setLayoutManager(new LinearLayoutManager
                (MovieDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
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
}







