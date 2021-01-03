package com.example.app.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SleepViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SleepViewModel() {
        mText = new MutableLiveData<>();
      // mText.setValue("This is stress fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}