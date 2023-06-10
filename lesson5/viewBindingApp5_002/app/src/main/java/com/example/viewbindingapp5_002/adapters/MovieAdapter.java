package com.example.viewbindingapp5_002.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.viewbindingapp5_002.databinding.ListItemBinding;
import com.example.viewbindingapp5_002.viewmodel.MovieViewModel;

public class MovieAdapter extends BaseAdapter {

    private MovieViewModel movieViewModel;
    private LayoutInflater layoutInflater;

    public MovieAdapter(MovieViewModel movieViewModel) {
        this.movieViewModel = movieViewModel;
    }

    @Override
    public int getCount() {
        return movieViewModel.getObservedMovies().getValue().getResults().size();
    }

    @Override
    public Object getItem(int i) {
        return movieViewModel.getObservedMovies().getValue().getResults().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View root = convertView;

        ListItemBinding binding; // nazwa binding powstaje na podstawie nazwy itema xml
        if (root == null) {
            if (layoutInflater == null) {
                layoutInflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            binding = ListItemBinding.inflate(layoutInflater, viewGroup, false);
            root = binding.getRoot();
            root.setTag(binding);

        } else {
            binding = (ListItemBinding) root.getTag();
        }
        // binding.setMovie("element obserwowanej listy filmów");
        binding.setMovie(movieViewModel.getObservedMovies().getValue().getResults().get(position));

        return root;

    }

}
