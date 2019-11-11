package com.ahmedelsayed.aboutmovies.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmedelsayed.aboutmovies.R;
import com.ahmedelsayed.aboutmovies.databinding.MoviesAdapterBinding;
import com.ahmedelsayed.aboutmovies.models.MoviesModel;
import com.ahmedelsayed.aboutmovies.view.activities.MovieDetailsActivity;

import java.util.List;

import static com.ahmedelsayed.aboutmovies.view.activities.MovieDetailsActivity.MOVIE_ID;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ItemViewHolder>{

    private List<MoviesModel.Results> items;
    private Context context;

    public MoviesAdapter(List<MoviesModel.Results> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public MoviesAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MoviesAdapterBinding moviesAdapterBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.movies_adapter, parent, false);
        return new MoviesAdapter.ItemViewHolder(moviesAdapterBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.ItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if(items == null)
            return 0;
        return items.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        MoviesAdapterBinding moviesAdapterBinding;
        ImageView movies;

        public ItemViewHolder(@NonNull MoviesAdapterBinding itemView) {
            super(itemView.getRoot());
            moviesAdapterBinding = itemView;
            itemView.getRoot().setOnClickListener(this);
            movies = itemView.getRoot().findViewById(R.id.movies);
        }

        void bind(int position) {
            MoviesModel.Results results = items.get(position);
            moviesAdapterBinding.setMovies(results);
        }

        @Override
        public void onClick(View v) {
            int movie_id = items.get(getAdapterPosition()).getId();
            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, movies, "imageMain");
            Intent in = new Intent(context, MovieDetailsActivity.class).putExtra(MOVIE_ID, movie_id);
            context.startActivity(in, activityOptionsCompat.toBundle());
        }
    }
}
