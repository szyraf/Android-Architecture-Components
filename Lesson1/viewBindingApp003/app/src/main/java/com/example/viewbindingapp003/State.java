package com.example.viewbindingapp003;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class State extends BaseObservable {
    @Bindable
    private boolean isChecked;

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
        notifyPropertyChanged(BR.isChecked);
    }

    public State(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
