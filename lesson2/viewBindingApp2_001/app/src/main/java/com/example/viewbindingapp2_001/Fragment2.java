package com.example.viewbindingapp2_001;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.viewbindingapp2_001.databinding.Fragment1Binding;
import com.example.viewbindingapp2_001.databinding.Fragment2Binding;

public class Fragment2 extends Fragment {

    private Fragment2Binding fragment2Binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment2Binding = Fragment2Binding.inflate(getLayoutInflater());

        getParentFragmentManager().setFragmentResultListener("datafromactivity", this, (s, b) -> {
            fragment2Binding.text.setText(b.getString("data"));
        });

        getParentFragmentManager().setFragmentResultListener("datafromfragment1", this, (s, b) -> {
            fragment2Binding.text.setText(b.getString("data"));
        });

        fragment2Binding.btnFragmentB.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("data", String.valueOf(fragment2Binding.editText1.getText()));
            getParentFragmentManager().setFragmentResult("datafromfragment2", bundle);
        });

        View view = fragment2Binding.getRoot();
        return view;
    }
}