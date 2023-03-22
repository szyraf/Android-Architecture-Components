package com.example.viewbindingapp004;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.Bindable;

import com.example.viewbindingapp004.databinding.ItemRowLayoutBinding;

import java.io.Console;
import java.util.ArrayList;

public class TestAdapter extends BaseAdapter {

    private ArrayList<Item> list;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemRowLayoutBinding itemRowLayoutBinding;

        LayoutInflater layoutInflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        itemRowLayoutBinding = ItemRowLayoutBinding.inflate(layoutInflater, viewGroup, false);
        view = itemRowLayoutBinding.getRoot();
        itemRowLayoutBinding.setItem(list.get(i));

        if (!list.get(i).getColor().equals("")) {
            int colorInt = Integer.parseInt(list.get(i).getColor()) % (256 * 256 * 256);
            Color color = new Color();
            int[] rgb = colorToRGB(colorInt);
            colorInt = color.rgb(rgb[0], rgb[1], rgb[2]);
            itemRowLayoutBinding.imageColor.setBackgroundColor(colorInt);
        }

        return view;
    }

    public static int[] colorToRGB(int color) {
        int[] rgb = new int[3];
        rgb[0] = (color >> 16) & 0xFF; // extract red component
        rgb[1] = (color >> 8) & 0xFF; // extract green component
        rgb[2] = color & 0xFF; // extract blue component
        return rgb;
    }

    public TestAdapter(ArrayList<Item> list) {
        this.list = list;
    }
}
