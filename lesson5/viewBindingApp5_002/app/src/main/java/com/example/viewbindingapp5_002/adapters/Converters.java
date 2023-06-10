package com.example.viewbindingapp5_002.adapters;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class Converters {
    @BindingAdapter("android:posterUrl")
    public static void changeUrl(ImageView imageView, String imageUrl){
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .into(imageView);

    }
}
