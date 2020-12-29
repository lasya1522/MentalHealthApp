package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DailyQuizzesActivity extends AppCompatActivity {
    private Button button; //Daily quiz button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_quizzes);

        //takes user from daily quizzes to the actual night quiz when the touch the morning quiz btn
        Button btnM = (findViewById(R.id.btn_morningQuiz));
        btnM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DailyQuizzesActivity.this, MorningQuizActivity.class);
                startActivity(intent1);
            }
        });
        //takes user from daily quizzes to the actual night quiz when the touch the night quiz btn
        Button btnN = (findViewById(R.id.btn_nightQuiz));
        btnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(DailyQuizzesActivity.this, NightQuizActivity.class);
                startActivity(intent2);
            }
        });


    }
}