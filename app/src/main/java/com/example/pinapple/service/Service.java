package com.example.pinapple.service;

import com.example.pinapple.models.DataImage;
import com.example.pinapple.models.DataVideo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("images/")
    Call<DataImage> getDataImages();

    @GET("videos/")
    Call<DataVideo> getDataVideos();
}
