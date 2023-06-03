package com.example.viewbindingapp3_004.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.viewbindingapp3_004.model.User;

import java.util.ArrayList;

public class UserViewModel extends ViewModel {

    private MutableLiveData<ArrayList<User>> mutableUserList;

    private ArrayList<User> userList;

    public UserViewModel() {

        this.mutableUserList = new MutableLiveData<>();

        userList = new ArrayList<>();

        // baza danych userów na start

        userList.add(new User(1, "Jan"));
        userList.add(new User(2, "Anna"));
        userList.add(new User(3, "Piotr"));

        // wpisanie do mutable dla obserwacji

        this.mutableUserList.setValue(this.userList);
    }

    public MutableLiveData<ArrayList<User>> getObservedUserList() {
        return mutableUserList;
    }

    public void addUser(String name) {
        int id = 1;
        if (userList.size() > 0) {
            id = userList.get(userList.size() - 1).getId() + 1;
        }
        userList.add(new User(id, name));
        this.mutableUserList.setValue(this.userList);
    }

    public void deleteUser(User user) {
        userList.remove(user);
        this.mutableUserList.setValue(this.userList);
    }

    public void deleteAll() {
        userList.clear();
        this.mutableUserList.setValue(this.userList);
    }

}
