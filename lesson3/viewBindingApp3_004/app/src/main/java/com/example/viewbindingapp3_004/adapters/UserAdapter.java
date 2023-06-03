package com.example.viewbindingapp3_004.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.viewbindingapp3_004.databinding.ListViewItemBinding;
import com.example.viewbindingapp3_004.model.User;
import com.example.viewbindingapp3_004.viewmodel.UserViewModel;

public class UserAdapter extends BaseAdapter {

    private UserViewModel userViewModel;
    private LayoutInflater layoutInflater;

    public UserAdapter(UserViewModel userViewModel) {
        this.userViewModel = userViewModel;
    }

    @Override
    public int getCount() {
        // zwraca długość obserwowanej listy w ViewModel ;
        return userViewModel.getObservedUserList().getValue().size();
    }

    @Override
    public Object getItem(int i) {
        // zwraca element obserwowanej listy z ViewModel;
        return userViewModel.getObservedUserList().getValue().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View root = convertView;
        ListViewItemBinding binding; // nazwa bnding powstaje na podstawie nazwy itema xml
        if (root == null) {
            if (layoutInflater == null) {
                layoutInflater = (LayoutInflater) viewGroup.getContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }
            binding = ListViewItemBinding.inflate(layoutInflater, viewGroup, false);
            root = binding.getRoot();
            root.setTag(binding);

        } else {
            binding = (ListViewItemBinding) root.getTag();

        }
        // binding.setUser("user pobrany z listy w ViewModel");

        binding.setUser(userViewModel.getObservedUserList().getValue().get(position));

        // binding.setUser((User) this.getItem(position));
        binding.itemBt.setOnClickListener(v -> {
            // userViewModel.delUser(...); // usuwamy element z listy w ViewModel
            userViewModel.deleteUser(userViewModel.getObservedUserList().getValue().get(position));
        });

        return root;
    }

}
