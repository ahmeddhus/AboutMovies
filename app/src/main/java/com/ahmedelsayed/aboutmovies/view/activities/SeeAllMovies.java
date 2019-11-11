package com.ahmedelsayed.aboutmovies.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.ahmedelsayed.aboutmovies.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SeeAllMovies extends AppCompatActivity {

    public static final String POPULAR = "popular";
    public static final String TOP_MOVIES = "top movies";
    public static final String NOW_PLAYING = "now playing";
    public static final String COMING_SOON = "coming soon";

    @BindView(R.id.toolbar_seeall)
    Toolbar toolbar;

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
        setType();
    }

    private void setType(){

        Intent intent = SeeAllMovies.this.getIntent();
        if (intent.getAction() != null){

            switch (intent.getAction()){
                case POPULAR:
                    toolbar.setTitle("Popular Movies");
                    break;
                case TOP_MOVIES:
                    toolbar.setTitle("Top Movies");
                    break;
                case NOW_PLAYING:
                    toolbar.setTitle("Now Playing");
                    break;
                case COMING_SOON:
                    toolbar.setTitle("Coming Soon");
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
