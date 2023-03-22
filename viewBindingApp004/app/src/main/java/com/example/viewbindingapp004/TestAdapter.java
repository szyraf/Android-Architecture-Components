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
//        list.get(i).getColor()

        //list.get(i).getColor()
        //Color color = new Color( % (255 * 255), )
        //itemRowLayoutBinding.imageColor.setBackgroundColor();
        return view;
    }

    public TestAdapter(ArrayList<Item> list) {
        this.list = list;
    }
}
