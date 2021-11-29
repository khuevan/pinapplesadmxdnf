package com.example.pinapple.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataImage {
//    @SerializedName("data")
//    @Expose
//    private ImageModel data;
//
//    public ImageModel getImageModel() {
//        return data;
//    }
//
//    public void setImageModel(ImageModel imageModel) {
//        this.data = imageModel;
//    }
//
//    public DataImage(ImageModel imageModel) {
//        this.data = imageModel;
//    }

    @SerializedName("data")
    @Expose
    public List<ImageModel> imageModel = null;

    public class ImageModel {
        @SerializedName("title")
        public String title;

        @SerializedName("date")
        public String date;

        @SerializedName("caption")
        public String caption;

        @SerializedName("type")
        public String type;

        @SerializedName("id")
        public Integer id;

        @SerializedName("cover")
        public String src;


        public ImageModel(String title, String date, String caption, String type, Integer id, String src) {
            this.title = title;
            this.date = date;
            this.caption = caption;
            this.type = type;
            this.id = id;
            this.src = src;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }
    }

    public List<ImageModel> getImageModel() {
        return imageModel;
    }

    public DataImage(List<ImageModel> imageModel) {
        this.imageModel = imageModel;
    }
}
