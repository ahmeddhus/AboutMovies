package com.ahmedelsayed.aboutmovies.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.ahmedelsayed.aboutmovies.R;
import com.ahmedelsayed.aboutmovies.databinding.ActivityMovieDetailsBinding;
import com.ahmedelsayed.aboutmovies.models.VideosModel;
import com.ahmedelsayed.aboutmovies.view.adapters.VideosAdapter;
import com.ahmedelsayed.aboutmovies.viewmodels.MDetailsViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity {

    public static final String MOVIE_ID = "movie_id";
    private int movie_id = 0;

    @BindView(R.id.toolbar_details)
    Toolbar toolbar;
    @BindView(R.id.rv_videos)
    RecyclerView rv_videos;

    MDetailsViewModel mDetailsViewModel;
    VideosAdapter videosAdapter;

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

    private void getData( ActivityMovieDetailsBinding binding){
        mDetailsViewModel = ViewModelProviders.of(this).get(MDetailsViewModel.class);

        Intent intent = getIntent();
        movie_id = intent.getIntExtra(MOVIE_ID,0);
        Log.e("ID", movie_id+"");

        mDetailsViewModel.inti(movie_id);
        mDetailsViewModel.getMDetails().observe(this, binding::setMdetails);

        mDetailsViewModel.getVideos().observe(this, videosModel -> setRV(videosModel.getResults()));
    }

    private void setRV(List<VideosModel.Results> videosModel){
        videosAdapter = new VideosAdapter(videosModel);
        rv_videos.setAdapter(videosAdapter);
        rv_videos.setLayoutManager(new LinearLayoutManager
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







