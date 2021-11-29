package com.example.pinapple.adapters;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pinapple.R;
import com.example.pinapple.models.DataVideo;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.PlayerView;
import com.squareup.picasso.Picasso;

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
        Picasso.get().load("http://10.0.2.2:8000" + videoModels.get(position).getThumbnail_image()).into(holder.thumbnail);

        try {
//            String link = "http://10.0.2.2:8000" + videoModel.getVideo();
            String link = videoModel.getVideo();
//            String link = "https://ba2e-2402-800-620e-c0f2-f11c-9c7e-c705-c4f9.ngrok.io/media/album/videos/detect/test_detect.mp4";
//            MediaController mediaController = new MediaController(context);
//            mediaController.setAnchorView(holder.videoView);
            Uri uri = Uri.parse(link);
//            holder.videoView.setMediaController(mediaController);
//            holder.videoView.setVideoURI(uri);
//            holder.thumbnail.setOnClickListener(view -> {
//                holder.thumbnail.setVisibility(View.GONE);
//                holder.videoView.start();
//           });
//            holder.videoView.setOnCompletionListener(mediaPlayer -> holder.thumbnail.setVisibility(View.VISIBLE));

            ExoPlayer player = new ExoPlayer.Builder(context).build();
            holder.videoView.setPlayer(player);
            MediaItem mediaItem = MediaItem.fromUri(uri);
            player.setMediaItem(mediaItem);
            player.prepare();
            player.play();

        } catch (Exception e) {
            Log.d("Video Adapter:", "error");

        }
    }

    @Override
    public int getItemCount() {
        return videoModels.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        PlayerView videoView;
        ImageView thumbnail;

        public VideoViewHolder(@NonNull View view) {
            super(view);
            videoView = view.findViewById(R.id.videoView);
            thumbnail = view.findViewById(R.id.video_thumnail);

        }
    }
}
