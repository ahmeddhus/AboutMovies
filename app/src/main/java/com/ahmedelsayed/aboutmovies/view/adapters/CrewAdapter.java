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
import com.ahmedelsayed.aboutmovies.databinding.CrewItemBinding;
import com.ahmedelsayed.aboutmovies.models.CreditsModel;
import com.ahmedelsayed.aboutmovies.models.MoviesModel;
import com.ahmedelsayed.aboutmovies.utils.Constants;
import com.ahmedelsayed.aboutmovies.view.activities.PeopleActivity;

import java.util.List;


public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.ItemViewHolder> {

    private List<CreditsModel.Crew> items;
    private Context context;

    public CrewAdapter(List<CreditsModel.Crew> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public CrewAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CrewItemBinding crewItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.crew_item, parent, false);
        return new CrewAdapter.ItemViewHolder(crewItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CrewAdapter.ItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if(items == null)
            return 0;
        return items.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        CrewItemBinding crewItemBinding;

        public ItemViewHolder(@NonNull CrewItemBinding itemView) {
            super(itemView.getRoot());
            crewItemBinding = itemView;
            itemView.getRoot().setOnClickListener(this);
        }

        void bind(int position) {
            CreditsModel.Crew results = items.get(position);
            crewItemBinding.setCrew(results);
        }

        @Override
        public void onClick(View v) {
            int people_id = items.get(getAdapterPosition()).getId();
            context.startActivity(new Intent(context, PeopleActivity.class).putExtra(Constants.PEOPLE_ID, people_id));
        }
    }
}
