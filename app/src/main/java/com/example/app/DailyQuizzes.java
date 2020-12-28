package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//BOTH buttons are not working...
public class DailyQuizzes extends AppCompatActivity {
    private Button btnM;
    private Button btnN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//already here... dont really know what it means
        setContentView(R.layout.activity_daily_quizzes);
        //Button that leads to Morning Quiz
        btnM = (Button) findViewById(R.id.btn_morningQuiz);
        btnM.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
               Intent intent = new Intent(DailyQuizzes.this, MorningQuiz.class);
                startActivity(intent);
            }
        });

        //Button that leads to Night Quiz
        btnN = (Button) findViewById(R.id.btn_nightQuiz);
        btnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(DailyQuizzes.this, NightQuiz.class);
                startActivity(intent);
            }
        });
    }
}