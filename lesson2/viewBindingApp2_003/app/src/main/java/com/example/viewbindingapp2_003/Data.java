package com.example.viewbindingapp2_003;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Data extends BaseObservable {
    @Bindable
    private String title;
    @Bindable
    private int color;
    @Bindable
    private int size;
    @Bindable
    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        notifyPropertyChanged(BR.color);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        notifyPropertyChanged(BR.size);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(BR.imageUrl);
    }

    public Data(String title, int color, int size) {
        this.title = title;
        this.color = color;
        this.size = size;
    }
    public Data(String title, int color, int size, String imageUrl) {
        this.title = title;
        this.color = color;
        this.size = size;
        this.imageUrl = imageUrl;
    }
}