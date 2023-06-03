package com.example.viewbindingapp3_001.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.viewbindingapp3_001.model.Settings;

public class SettingsViewModel extends ViewModel {
    private MutableLiveData<Settings> settingsMutableLiveData;
    private Settings settings;

    public SettingsViewModel() {
        this.settingsMutableLiveData = new MutableLiveData<>();
        this.settings = new Settings("http://aaa.pl", "3000");
    }

    public MutableLiveData<Settings> getObservedSettings() {
        return settingsMutableLiveData;
    }

    public void setupSettings() {
        this.settingsMutableLiveData.setValue(this.settings);
    }

    public void changeSettings(String url, String port) {
        this.settings.setUrl(url);
        this.settings.setPort(port);
        settingsMutableLiveData.setValue(this.settings);
    }
}