package com.example.viewbindingapp4_002;

import com.google.gson.annotations.SerializedName;

public class Post {
    private int id;
    private int userId;
    private String title;

    @SerializedName("body") // nazwa pola w API przekładana jest na
    private String text; // naszą nazwę w aplikacji - nie jest to konieczna operacja ale warto wiedzieć że się da

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

