package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewPastQuizActivity extends AppCompatActivity {
    TextView tv_date;
    TextView tv_mood;

    List<DailyQuiz> list;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_past_quiz);

        tv_date = findViewById(R.id.tv_date);

        Intent intent = getIntent();
        String passedDate = intent.getStringExtra("date");

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        list = databaseHelper.getDailyQuizData();

        tv_date.setText(passedDate);

    }

}
