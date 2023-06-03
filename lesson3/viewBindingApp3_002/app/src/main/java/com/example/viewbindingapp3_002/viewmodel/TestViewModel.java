package com.example.viewbindingapp3_002.viewmodel;

import android.os.Build;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.time.Instant;

public class TestViewModel extends ViewModel {

    public MutableLiveData<Integer> ageLiveData;

    public MutableLiveData<Long> timestampLiveData;

    public TestViewModel() {
        ageLiveData = new MutableLiveData<>();
        timestampLiveData = new MutableLiveData<>();
    }

    public void setupData(int age, long timestamp){

        this.ageLiveData.setValue(age);
        this.timestampLiveData.setValue(timestamp);
    }

    public MutableLiveData<Integer> getObservedAge() {
        return ageLiveData;
    }

    public void changeAge(int age) {
        ageLiveData.setValue(age);
    }

    public MutableLiveData<Long> getObservedTimestamp() {
        return timestampLiveData;
    }

    public void changeTimestamp() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            timestampLiveData.setValue(Instant.now().getEpochSecond());
        }
    }
}