package com.example.viewbindingapp2_001;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.viewbindingapp2_001.databinding.Fragment1Binding;

public class Fragment1 extends Fragment {

    private Fragment1Binding fragment1Binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment1Binding = Fragment1Binding.inflate(getLayoutInflater());

        getParentFragmentManager().setFragmentResultListener("datafromactivity", this, (s, b) -> {
            fragment1Binding.text.setText(b.getString("data"));
        });

        getParentFragmentManager().setFragmentResultListener("datafromfragment2", this, (s, b) -> {
            fragment1Binding.text.setText(b.getString("data"));
        });

        fragment1Binding.btnFragmentA.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("data", String.valueOf(fragment1Binding.editText1.getText()));
            getParentFragmentManager().setFragmentResult("datafromfragment1", bundle);
        });

        View view = fragment1Binding.getRoot();
        return view;
    }
}