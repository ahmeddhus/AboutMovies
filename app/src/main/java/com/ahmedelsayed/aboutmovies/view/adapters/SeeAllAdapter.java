package com.ahmedelsayed.aboutmovies.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmedelsayed.aboutmovies.R;
import com.ahmedelsayed.aboutmovies.databinding.SeeallItemBinding;
import com.ahmedelsayed.aboutmovies.models.MoviesModel;

import java.util.List;

public class SeeAllAdapter extends RecyclerView.Adapter<SeeAllAdapter.ItemViewHolder> {

    private List<MoviesModel.Results> items;
    SeeAllAdapter.OnItemClickListener mOnItemClickListener;

    public SeeAllAdapter(List<MoviesModel.Results> items, SeeAllAdapter.OnItemClickListener mOnItemClickListener) {
        this.items = items;
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public SeeAllAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SeeallItemBinding seeallItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.seeall_item, parent, false);

        return new SeeAllAdapter.ItemViewHolder(seeallItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SeeAllAdapter.ItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if(items == null)
            return 0;
        return items.size();
    }

    public interface OnItemClickListener {
        void onItemClikced(int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {
        SeeallItemBinding seeallItemBinding;

        public ItemViewHolder(@NonNull SeeallItemBinding itemView) {
            super(itemView.getRoot());
            seeallItemBinding = itemView;
            itemView.getRoot().setOnClickListener(this);
        }

        void bind(int position) {
            MoviesModel.Results results = items.get(position);
            seeallItemBinding.setSeeall(results);
        }

        @Override
        public void onClick(View v) {
            mOnItemClickListener.onItemClikced(getAdapterPosition());
        }
    }
}
