package com.example.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.MorningQuizActivity;
import com.example.app.R;

public class DailyQuizzesActivity extends AppCompatActivity {
    private Button button; //Daily quiz button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_quizzes);

        //takes user from daily quizzes to the actual night quiz when the touch the morning quiz btn
        Button btnM = (Button) (findViewById(R.id.btn_morningQuiz));

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
                //will REMOVE BC IT IS FOR BTN
               // Intent intent2 = new Intent(DailyQuizzesActivity.this, NightQuizActivity.class);
               // startActivity(intent2);
            }
        });


    }
}