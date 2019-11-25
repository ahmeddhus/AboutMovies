package com.ahmedelsayed.aboutmovies.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ahmedelsayed.aboutmovies.R;
import com.ahmedelsayed.aboutmovies.databinding.ActivityPeopleBinding;
import com.ahmedelsayed.aboutmovies.models.PeopleModel;
import com.ahmedelsayed.aboutmovies.utils.Constants;
import com.ahmedelsayed.aboutmovies.viewmodels.PeopleViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ahmedelsayed.aboutmovies.utils.HelperMethods.IsConnected;

public class PeopleActivity extends AppCompatActivity {

    private int people_id = 0;

    @BindView(R.id.toolbar_people)
    Toolbar toolbar;
    @BindView(R.id.people_refresh)
    SwipeRefreshLayout refreshLayout;

    PeopleViewModel peopleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityPeopleBinding binding =
                DataBindingUtil.setContentView(PeopleActivity.this, R.layout.activity_people);
        init();
        getData(binding);

        refreshLayout.setOnRefreshListener(() -> getData(binding));

        if (!IsConnected(PeopleActivity.this))
            Toast.makeText(PeopleActivity.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
    }

    private void init() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getData(ActivityPeopleBinding binding) {
        peopleViewModel = ViewModelProviders.of(this).get(PeopleViewModel.class);

        Intent intent = getIntent();
        people_id = intent.getIntExtra(Constants.PEOPLE_ID, 0);
        Log.e("ID", people_id + "");

        peopleViewModel.init();
        peopleViewModel.getPeople(people_id).observe(this, peopleModel -> {
            if (peopleModel != null)
                binding.setPeople(peopleModel);
            else if (IsConnected(PeopleActivity.this))
                Toast.makeText(PeopleActivity.this, "an error has occurred", Toast.LENGTH_LONG).show();
            refreshLayout.setRefreshing(false);
        });
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
