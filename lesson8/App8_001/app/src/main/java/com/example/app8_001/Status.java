package com.example.app8_001;

import com.google.gson.annotations.SerializedName;

public class Status {
    @SerializedName("status")
    String status;

    public Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
