package com.example.pinapple.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataVideo {
    @SerializedName("data")
    @Expose
    public List<VideoModel> data;

    public DataVideo(List<VideoModel> data) {
        this.data = data;
    }

    public List<VideoModel> getData() {
        return data;
    }

    public void setData(List<VideoModel> data) {
        this.data = data;
    }

    public static class VideoModel {
        @SerializedName("id")
        @Expose
        public int id;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("date")
        @Expose
        public String date;
        @SerializedName("caption")
        @Expose
        public String caption;
        @SerializedName("video")
        @Expose
        public String video;
        @SerializedName("thumbnail_image")
        @Expose
        public String thumbnail_image;

        public VideoModel(int id, String title, String date, String caption, String video, String thumbnail_image) {
            this.id = id;
            this.title = title;
            this.date = date;
            this.caption = caption;
            this.video = video;
            this.thumbnail_image = thumbnail_image;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getThumbnail_image() {
            return thumbnail_image;
        }

        public void setThumbnail_image(String thumbnail_image) {
            this.thumbnail_image = thumbnail_image;
        }
    }
}
