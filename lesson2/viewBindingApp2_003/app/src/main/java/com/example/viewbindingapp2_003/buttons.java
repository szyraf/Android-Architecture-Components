package com.example.viewbindingapp2_003;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.viewbindingapp2_003.databinding.FragmentButtonsBinding;

public class buttons extends Fragment {

    private FragmentButtonsBinding fragmentButtonsBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentButtonsBinding = FragmentButtonsBinding.inflate(getLayoutInflater());

        Data data = new Data("buttons fragment", 0xff003d42, 50);
        fragmentButtonsBinding.setData(data);

        View view = fragmentButtonsBinding.getRoot();
        return view;
    }
}