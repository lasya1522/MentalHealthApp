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
    TextView tv_sleepTime;
    TextView tv_sleepRating;
    TextView tv_productiveTime;
    TextView tv_relaxTime;
    TextView tv_exerciseTime;
    TextView tv_stressLevel;
    TextView tv_stressors;
    TextView tv_other;

    List<DailyQuiz> list;
    DailyQuiz dailyQuiz;

    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_past_quiz);

        tv_date = findViewById(R.id.tv_date);
        tv_mood = findViewById(R.id.tv_mood);
        tv_sleepTime = findViewById(R.id.tv_sleepTime);
        tv_sleepRating = findViewById(R.id.tv_sleepRating);
        tv_productiveTime = findViewById(R.id.tv_productiveTime);
        tv_relaxTime = findViewById(R.id.tv_relaxTime);
        tv_exerciseTime = findViewById(R.id.tv_exerciseTime);
        tv_stressLevel = findViewById(R.id.tv_stressLevel);
        tv_stressors = findViewById(R.id.tv_stressors);
        tv_other = findViewById(R.id.tv_other);

        Intent intent = getIntent();
        String passedDate = intent.getStringExtra("date");

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        dailyQuiz = databaseHelper.getDailyQuiz(passedDate);
        tv_date.setText(passedDate);
        tv_mood.setText("Mood: " + dailyQuiz.getMood());
        tv_sleepTime.setText("sleepTime: " + dailyQuiz.getSleepTime().toString());
        tv_sleepRating.setText("sleepRating: " + dailyQuiz.getSleepRating());
        tv_productiveTime.setText("productiveTime: " + dailyQuiz.getProductiveTime().toString());
        tv_relaxTime.setText("relaxTime: " + dailyQuiz.getRelaxTime().toString());
        tv_exerciseTime.setText("exerciseTime: " + dailyQuiz.getExerciseTime().toString());
        tv_stressLevel.setText("stressLevel: " + dailyQuiz.getStressLevel());
        tv_stressors.setText("stressors: " + dailyQuiz.getStressors());
        tv_other.setText("other: " + dailyQuiz.getOther());


    }

}
