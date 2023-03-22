package com.example.viewbindingapp004;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Item extends BaseObservable {
    @Bindable
    private String color;
    @Bindable
    private String text;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        notifyPropertyChanged(BR.color);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);
    }

    public Item(String color, String text) {
        this.color = color;
        this.text = text;
    }
}
