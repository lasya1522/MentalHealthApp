package com.example.app.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.Calendar;
import java.util.Date;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> dText;

    public HomeViewModel() {
        //mText = new MutableLiveData<>();
        //mText.setValue("This is home fragment");
        dText = new MutableLiveData<>();
        Date date = Calendar.getInstance().getTime();
        dText.setValue(date.toString());
    }

    public LiveData<String> getText() {
        return dText;
    }
}