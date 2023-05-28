package com.example.viewbindingapp2_003;

import android.content.Context;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class Converters {
    @BindingAdapter("android:textSize")
    public static void setTextSizeInDp(TextView textView, int size) {
        Context context = textView.getContext();
        float textSizeInDp = convertPxToDp(size, context);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSizeInDp * 2);
    }

    private static float convertPxToDp(float px, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return px / density;
    }

    @BindingAdapter("android:imageUrl")
    public static void changeUrl(ImageView imageView, String imageUrl){
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .into(imageView);
    }

    @BindingAdapter("android:imageSize")
    public static void setImageSize(ImageView imageView, int size) {
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = size * 10;
        layoutParams.height = size * 10;
        imageView.setLayoutParams(layoutParams);
    }
}