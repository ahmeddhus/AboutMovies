package com.ahmedelsayed.aboutmovies.view.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmedelsayed.aboutmovies.R;
import com.ahmedelsayed.aboutmovies.basics.ConfigYT;
import com.ahmedelsayed.aboutmovies.models.VideosModel;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ItemViewHolder>{

    private List<VideosModel.Results> items;
    private Context context;
    private static final int RECOVERY_REQUEST = 1;

    public VideosAdapter(List<VideosModel.Results> items, Context context) {
        this.items = items;
        this.context = context;
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
        holder.bind(position, context);
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
        Context context;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            playerView = itemView.findViewById(R.id.youtube_player_view);
        }

        void bind(int position, Context context) {
            VideosModel.Results results = items.get(position);
            this.context = context;
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
