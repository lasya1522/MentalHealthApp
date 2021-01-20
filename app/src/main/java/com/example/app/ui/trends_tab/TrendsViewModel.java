package com.example.app.ui.trends_tab;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrendsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TrendsViewModel() {
        mText = new MutableLiveData<>();
      // mText.setValue("This is stress fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}