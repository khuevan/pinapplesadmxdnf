package com.example.pinapple.adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pinapple.R;
import com.example.pinapple.models.DataVideo;
import com.example.pinapple.ui.home.HomeFragment;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    List<DataVideo.VideoModel> videoModels;
    Context context;


    public VideoAdapter(List<DataVideo.VideoModel> videoModels, Context context) {
        this.videoModels = videoModels;
        this.context = context;

    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.row_video, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        DataVideo.VideoModel videoModel = videoModels.get(position);
        Picasso.get().load("http://10.0.2.2:8000"+ videoModels.get(position).getThumbnail_image()).into(holder.thumbnail);

        try {
//            String link = "http://10.0.2.2:8000" + videoModel.getVideo();
            String link = videoModel.getVideo();
            MediaController mediaController = new MediaController(context);
            mediaController.setAnchorView(holder.videoView);
            Uri uri = Uri.parse(link);
            holder.videoView.setMediaController(mediaController);
            holder.videoView.setVideoURI(uri);
            holder.thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.thumbnail.setVisibility(View.GONE);
                    holder.videoView.start();
               }
            });
            holder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                holder.thumbnail.setVisibility(View.VISIBLE);
            }
        });

        }catch (Exception e){
            Log.d("Video Adapter:", "error");

        }
    }

    @Override
    public int getItemCount() {
        return videoModels.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;
        ImageView thumbnail;
        public VideoViewHolder(@NonNull View view) {
            super(view);
                videoView = view.findViewById(R.id.videoView);
                thumbnail = view.findViewById(R.id.video_thumnail);

        }
    }
}
