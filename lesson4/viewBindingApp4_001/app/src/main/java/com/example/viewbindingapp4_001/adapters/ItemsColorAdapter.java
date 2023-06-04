package com.example.viewbindingapp4_001.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.viewbindingapp4_001.databinding.SpinnerItemBinding;
import com.example.viewbindingapp4_001.viewmodel.ItemColorViewModel;

public class ItemsColorAdapter extends BaseAdapter {

    private ItemColorViewModel itemImageViewModel;
    private LayoutInflater layoutInflater;

    public ItemsColorAdapter(ItemColorViewModel itemImageViewModel) {
        this.itemImageViewModel = itemImageViewModel;
    }

    @Override
    public int getCount() {
        //return "obserwowana lista z viewmodel".size();
        return itemImageViewModel.getObservedItemList().getValue().size();
    }

    @Override
    public Object getItem(int i) {
        //return "obserwowana lista z viewmodel".get(i);
        return itemImageViewModel.getObservedItemList().getValue().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View root = convertView;
        SpinnerItemBinding binding; // nazwa z itema xml
        if (root == null) {
            if (layoutInflater == null) {
                layoutInflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            binding = SpinnerItemBinding.inflate(layoutInflater, viewGroup, false);
            root = binding.getRoot();
            root.setTag(binding);
        } else {
            binding = (SpinnerItemBinding) root.getTag();
        }
        binding.setItem(itemImageViewModel.getObservedItemList().getValue().get(position));

        return root;
    }
}
