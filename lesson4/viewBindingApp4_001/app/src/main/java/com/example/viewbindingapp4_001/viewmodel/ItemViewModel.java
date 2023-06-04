package com.example.viewbindingapp4_001.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ItemViewModel extends ViewModel {

    private MutableLiveData<ArrayList<String>> mutableItemList;

    private ArrayList<String> itemList;

    private MutableLiveData<Integer> index;

    public ItemViewModel() {
        this.mutableItemList = new MutableLiveData<>();
        this.index = new MutableLiveData<>();

        this.itemList = new ArrayList<>();
        itemList.add("category 1");
        itemList.add("category 2");
        itemList.add("category 3");

        this.index.setValue(0);
        this.mutableItemList.setValue(this.itemList);
    }

    public MutableLiveData<ArrayList<String>> getObservedItemList() {
        return mutableItemList;
    }

    public MutableLiveData<Integer> getObservedIndex() {
        return index;
    }

    public void changeIndex(int index) {
        this.index.setValue(index);
    }

    public void addItem(String name) {
        this.itemList.add(name);
        this.mutableItemList.setValue(this.itemList);
    }

}
