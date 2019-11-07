package com.ahmedelsayed.aboutmovies.view.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmedelsayed.aboutmovies.R;
import com.ahmedelsayed.aboutmovies.models.VideosModel;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ItemViewHolder>{

    private List<VideosModel.Results> items;
    private static final int RECOVERY_REQUEST = 1;

    public VideosAdapter(List<VideosModel.Results> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public VideosAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_item , parent , false);

        return new VideosAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideosAdapter.ItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        if(items == null)
            return 0;
        return items.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        YouTubePlayerView playerView;

        private String key = "";

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            playerView = itemView.findViewById(R.id.youtube_player_view);
        }

        void bind(int position) {
            VideosModel.Results results = items.get(position);
            key = results.getKey();

            playerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NotNull YouTubePlayer youTubePlayer) {
                    youTubePlayer.loadVideo(key, 0);
                    youTubePlayer.pause();
                }
            });

        }
    }
}
