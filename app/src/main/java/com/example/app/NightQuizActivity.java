package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class NightQuizActivity extends AppCompatActivity {
    private Button btn_mood1_n;
    private Button btn_mood2_n;
    private Button btn_mood3_n;
    private Button btn_mood4_n;
    private Button btn_mood5_n;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night_quiz);

        btn_mood1_n = findViewById(R.id.btn_mood1_n);
        btn_mood2_n = findViewById(R.id.btn_mood2_n);
        btn_mood3_n = findViewById(R.id.btn_mood3_n);
        btn_mood4_n = findViewById(R.id.btn_mood4_n);
        btn_mood5_n = findViewById(R.id.btn_mood5_n);

        databaseHelper = new DatabaseHelper(NightQuizActivity.this);

    }

}