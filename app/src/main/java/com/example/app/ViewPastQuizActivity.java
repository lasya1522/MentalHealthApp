package com.example.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewPastQuizActivity extends AppCompatActivity {
    TextView tv_date;
    TextView tv_mood;

    List<DailyQuiz> list;
    DailyQuiz dailyQuiz;

    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_past_quiz);

        tv_date = findViewById(R.id.tv_date);
        tv_mood = findViewById(R.id.tv_mood);

        Intent intent = getIntent();
        String passedDate = intent.getStringExtra("date");

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        dailyQuiz = databaseHelper.getDailyQuiz(passedDate);
        tv_date.setText(passedDate);
        tv_mood.setText(dailyQuiz.getMood());


    }

}
