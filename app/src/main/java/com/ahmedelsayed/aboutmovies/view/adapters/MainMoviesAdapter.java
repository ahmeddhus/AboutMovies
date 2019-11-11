package com.ahmedelsayed.aboutmovies.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmedelsayed.aboutmovies.R;
import com.ahmedelsayed.aboutmovies.databinding.MainMoviesAdapterBinding;
import com.ahmedelsayed.aboutmovies.models.MoviesModel;

import java.util.List;

public class MainMoviesAdapter extends RecyclerView.Adapter<MainMoviesAdapter.ItemViewHolder>{

    private List<MoviesModel.Results> items;
    MainMoviesAdapter.OnItemClickListener mOnItemClickListener;

    public MainMoviesAdapter(List<MoviesModel.Results> items, OnItemClickListener mOnItemClickListener) {
        this.items = items;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public MainMoviesAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MainMoviesAdapterBinding mainMoviesAdapterBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.main_movies_adapter, parent, false);
        return new MainMoviesAdapter.ItemViewHolder(mainMoviesAdapterBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainMoviesAdapter.ItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if(items == null)
            return 0;
        return items.size();
    }

    public interface OnItemClickListener {
        void onItemClikced(int position, ImageView imageView);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        MainMoviesAdapterBinding mainMoviesAdapterBinding;
        ImageView main_movies;

        ItemViewHolder(@NonNull MainMoviesAdapterBinding itemView) {
            super(itemView.getRoot());
            mainMoviesAdapterBinding = itemView;
            itemView.getRoot().setOnClickListener(this);
            main_movies = itemView.getRoot().findViewById(R.id.main_movies);
        }

        void bind(int position) {
            MoviesModel.Results results = items.get(position);
            mainMoviesAdapterBinding.setMainMovies(results);
        }

        @Override
        public void onClick(View v) {
            mOnItemClickListener.onItemClikced(getAdapterPosition(), main_movies);
        }
    }
}