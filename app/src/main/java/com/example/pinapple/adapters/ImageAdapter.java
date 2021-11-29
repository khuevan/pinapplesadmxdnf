package com.example.pinapple.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pinapple.ImageActivity;
import com.example.pinapple.R;
//import com.example.pinapple.models.ImageModel;
import com.example.pinapple.models.DataImage;
import com.example.pinapple.ui.home.HomeFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    List<DataImage.ImageModel> imageModelList;
    Context context;


    public ImageAdapter(List<DataImage.ImageModel> imageModelList) {
        this.imageModelList = imageModelList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        final Context  context = holder.imageView.getContext();
        Picasso.get().load("http://10.0.2.2:8000"+imageModelList.get(position).getSrc()).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return imageModelList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewrowitem);

        }
    }
}
