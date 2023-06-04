package com.example.viewbindingapp4_001.viewmodel;

import android.graphics.Color;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.viewbindingapp4_001.model.Item;

import java.util.ArrayList;

public class ItemColorViewModel extends ViewModel {
    private ArrayList<Item> itemList;
    private MutableLiveData<ArrayList<Item>> mutableItemList;
    private MutableLiveData<Color> color;

    public ItemColorViewModel() {
        this.mutableItemList = new MutableLiveData<>();
        this.color = new MutableLiveData<>();

        this.itemList = new ArrayList<>();
        itemList.add(new Item("cat 1",0xff00ff00));
        itemList.add(new Item("cat 2",0xffff0000));
        itemList.add(new Item("cat 3",0xff0000ff));
        //

        //Color c = new Color(0xff00ff00);

        //this.color.setValue(new Color(0xff00ff00));
        this.mutableItemList.setValue(this.itemList);
    }

    public MutableLiveData<ArrayList<Item>> getObservedItemList() {
        return mutableItemList;
    }

    public MutableLiveData<Color> getObservedColor() {
        return color;
    }

    public void addItem(String name) {
        this.itemList.add(new Item(name, 0xffbb00ff));
        this.mutableItemList.setValue(this.itemList);
    }
}
