package com.example.app.ui.home;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app.DailyQuizzes;
import com.example.app.R;

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
       // Button button = (Button) findViewByID()
    }

    public LiveData<String> getText() {
        return dText;
    }
}