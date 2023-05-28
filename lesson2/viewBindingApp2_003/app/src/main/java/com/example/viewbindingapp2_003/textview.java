package com.example.viewbindingapp2_003;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.viewbindingapp2_003.databinding.FragmentTextviewBinding;

public class textview extends Fragment {

    private FragmentTextviewBinding fragmentTextviewBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentTextviewBinding = FragmentTextviewBinding.inflate(getLayoutInflater());

        Data data = new Data("textview fragment", 0xff003d42, 50);
        fragmentTextviewBinding.setData(data);

        View view = fragmentTextviewBinding.getRoot();
        return view;
    }
}