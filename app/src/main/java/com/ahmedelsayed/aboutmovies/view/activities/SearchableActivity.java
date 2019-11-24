package com.ahmedelsayed.aboutmovies.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.ahmedelsayed.aboutmovies.R;
import com.ahmedelsayed.aboutmovies.models.MoviesModel;
import com.ahmedelsayed.aboutmovies.view.adapters.MoviesAdapter;
import com.ahmedelsayed.aboutmovies.view.adapters.SeeAllAdapter;
import com.ahmedelsayed.aboutmovies.view.customLayouts.CustomLayoutSeeAll;
import com.ahmedelsayed.aboutmovies.viewmodels.MoviesViewModel;
import com.ahmedelsayed.aboutmovies.viewmodels.SearchViewModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ahmedelsayed.aboutmovies.utils.HelperMethods.Count;

public class SearchableActivity extends AppCompatActivity  implements SeeAllAdapter.OnItemClickListener {

    @BindView(R.id.toolbar_search)
    Toolbar toolbar;
    @BindView(R.id.rv_search)
    RecyclerView rv_search;
    @BindView(R.id.search_loading)
    ImageView loading;
    @BindView(R.id.search_view)
    SearchView search_view;

    SeeAllAdapter seeAllAdapter;
    SearchViewModel searchViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        init();
        searchListener();
    }

    private void init() {
        ButterKnife.bind(this);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        searchViewModel.init();
        setData("A");
        Glide.with(SearchableActivity.this)
                .load(R.drawable.loading)
                .into(loading);
    }

    private void searchListener(){
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                setData(newText);
                return true;
            }
        });
    }

    private void setData(String query) {
        searchViewModel.getSearchableMovies(query).observe(this, moviesModel -> {
            setRV(moviesModel.getResults());
            loading.setVisibility(View.GONE);
        });
    }

    private void setRV(List<MoviesModel.Results> mainMoviesModels) {
        seeAllAdapter = new SeeAllAdapter(SearchableActivity.this, mainMoviesModels, SearchableActivity.this);
        rv_search.setAdapter(seeAllAdapter);
        rv_search.setLayoutManager(new CustomLayoutSeeAll(SearchableActivity.this,
                Count(SearchableActivity.this), GridLayoutManager.VERTICAL, false, loading));
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        finish();
//        return true;
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        finish();
//    }

    @Override
    public void onItemClikced(int position) {

    }
}
