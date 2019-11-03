package com.ahmedelsayed.aboutmovies.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.ahmedelsayed.aboutmovies.R;
import com.ahmedelsayed.aboutmovies.databinding.ActivityMovieDetailsBinding;
import com.ahmedelsayed.aboutmovies.viewmodels.MDetailsViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_details)
    Toolbar toolbar;

    MDetailsViewModel mDetailsViewModel;

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
        mDetailsViewModel.inti(290859);
        mDetailsViewModel.getMDetails().observe(this, binding::setMdetails);
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
