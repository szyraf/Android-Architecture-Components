package com.example.viewbindingapp2_003;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.viewbindingapp2_003.databinding.FragmentImageBinding;

public class image extends Fragment {

    private FragmentImageBinding fragmentImageBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentImageBinding = FragmentImageBinding.inflate(getLayoutInflater());

        Data data = new Data("image fragment", 0xff003d42, 50, "https://img.freepik.com/premium-vector/red-apple-vector-healthy-sweet-fruit_68708-3076.jpg");
        fragmentImageBinding.setData(data);

        View view = fragmentImageBinding.getRoot();
        return view;
    }
}