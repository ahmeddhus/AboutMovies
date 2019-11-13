package com.ahmedelsayed.aboutmovies.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmedelsayed.aboutmovies.R;
import com.ahmedelsayed.aboutmovies.databinding.CastItemBinding;
import com.ahmedelsayed.aboutmovies.models.CreditsModel;
import com.ahmedelsayed.aboutmovies.models.MoviesModel;
import com.ahmedelsayed.aboutmovies.view.activities.PeopleActivity;

import java.util.List;

import static com.ahmedelsayed.aboutmovies.view.activities.PeopleActivity.PEOPLE_ID;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.ItemViewHolder> {

    private List<CreditsModel.Cast> items;
    private Context context;

    public CastAdapter(List<CreditsModel.Cast> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public CastAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CastItemBinding castItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.cast_item, parent, false);
        return new CastAdapter.ItemViewHolder(castItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CastAdapter.ItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if(items == null)
            return 0;
        return items.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CastItemBinding castItemBinding;

        public ItemViewHolder(@NonNull CastItemBinding itemView) {
            super(itemView.getRoot());
            castItemBinding = itemView;
            itemView.getRoot().setOnClickListener(this);
        }

        void bind(int position) {
            CreditsModel.Cast results = items.get(position);
            castItemBinding.setCast(results);
        }

        @Override
        public void onClick(View v) {
            int people_id = items.get(getAdapterPosition()).getId();
            context.startActivity(new Intent(context, PeopleActivity.class).putExtra(PEOPLE_ID, people_id));
        }
    }
}
