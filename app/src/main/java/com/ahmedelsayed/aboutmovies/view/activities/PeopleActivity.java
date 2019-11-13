package com.ahmedelsayed.aboutmovies.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ahmedelsayed.aboutmovies.R;
import com.ahmedelsayed.aboutmovies.databinding.ActivityMovieDetailsBinding;
import com.ahmedelsayed.aboutmovies.databinding.ActivityPeopleBinding;
import com.ahmedelsayed.aboutmovies.models.PeopleModel;
import com.ahmedelsayed.aboutmovies.viewmodels.MDetailsViewModel;
import com.ahmedelsayed.aboutmovies.viewmodels.PeopleViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PeopleActivity extends AppCompatActivity {

    public static final String PEOPLE_ID = "people_id";
    private int people_id = 0;

    @BindView(R.id.toolbar_people)
    Toolbar toolbar;

    PeopleViewModel peopleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityPeopleBinding binding =
                DataBindingUtil.setContentView(PeopleActivity.this, R.layout.activity_people);
        init();
        getData(binding);
    }

    private void init() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getData(ActivityPeopleBinding binding){
        peopleViewModel = ViewModelProviders.of(this).get(PeopleViewModel.class);

        Intent intent = getIntent();
        people_id = intent.getIntExtra(PEOPLE_ID, 0);
        Log.e("ID", people_id + "");

        peopleViewModel.init();
        peopleViewModel.getPeople(people_id).observe(this, binding::setPeople);
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
