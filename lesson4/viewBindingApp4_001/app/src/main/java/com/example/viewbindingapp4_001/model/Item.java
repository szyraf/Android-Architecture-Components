package com.example.viewbindingapp4_001.model;

public class Item {
    private String category;
    private int color;

    public Item(String category, int color) {
        this.category = category;
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
