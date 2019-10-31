package com.ahmedelsayed.aboutmovies.view;

import android.content.Context;
import android.view.LayoutInflater;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmedelsayed.aboutmovies.R;
import com.ahmedelsayed.aboutmovies.databinding.MainMoviesAdapterBinding;
import com.ahmedelsayed.aboutmovies.models.MoviesModel;

import java.util.List;

public class MainMoviesAdapter extends RecyclerView.Adapter<MainMoviesAdapter.ItemViewHolder>{

    private Context context;
    private List<MoviesModel.Results> items;

    MainMoviesAdapter(Context context, List<MoviesModel.Results> items) {
        this.context = context;
        this.items = items;
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


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        MainMoviesAdapterBinding mainMoviesAdapterBinding;

        public ItemViewHolder(@NonNull MainMoviesAdapterBinding itemView) {
            super(itemView.getRoot());
            mainMoviesAdapterBinding = itemView;
        }

        public void bind(int position) {
            MoviesModel.Results results = items.get(position);
            mainMoviesAdapterBinding.setMainMovies(results);
        }
    }
}










