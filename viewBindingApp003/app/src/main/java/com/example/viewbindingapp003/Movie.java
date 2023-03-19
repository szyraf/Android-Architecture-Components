package com.example.viewbindingapp003;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Movie extends BaseObservable {
    @Bindable
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    public Movie(String title) {
        this.title = title;
    }
}
