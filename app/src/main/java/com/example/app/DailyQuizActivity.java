package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class DailyQuizActivity extends AppCompatActivity {

   private EditText et_sleepRating;
   private EditText et_sleepDuration;

    private Button btn_submit;

    private Button btn_mood1;
    private Button btn_mood2;
    private Button btn_mood3;
    private Button btn_mood4;

    private EditText et_sleepTime;

    private Button btn_sleepRating1;
    private Button btn_sleepRating2;
    private Button btn_sleepRating3;

    private EditText et_productiveTime;
    private EditText et_relaxTime;
    private EditText et_exerciseTime;


    private Date date;
    private String mood;
    private Integer sleepTime;
    private String sleepRating;
    private Integer productiveTime;
    private Integer relaxTime;
    private Integer exerciseTime;
    private String stressLevel;
    private String stressors;
    private String other;//is this really the best way to keep track of the mood ? // may change to MC buttons

    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_quiz);

        btn_mood1 = findViewById(R.id.btn_mood1);
        btn_mood2 = findViewById(R.id.btn_mood2);
        btn_mood3 = findViewById(R.id.btn_mood3);
        btn_mood4 = findViewById(R.id.btn_mood4);

        et_sleepTime = findViewById(R.id.et_sleepTime);

        btn_sleepRating1 = findViewById(R.id.btn_sleepRating1);
        btn_sleepRating2 = findViewById(R.id.btn_sleepRating2);
        btn_sleepRating3 = findViewById(R.id.btn_sleepRating3);

        et_productiveTime = findViewById(R.id.et_productiveTime);
        et_relaxTime = findViewById(R.id.et_relaxTime);
        et_exerciseTime = findViewById(R.id.et_exerciseTime);

        btn_submit = findViewById(R.id.btn_submit);

        databaseHelper = new DatabaseHelper(DailyQuizActivity.this);

      btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DailyQuiz dailyQuiz;

                //need some way to test whether all necessary fields were filled in with a valid number. initialize int vars to -1? check before try?

                try {
                    Date date = Calendar.getInstance().getTime();

                    if ((Integer.parseInt(et_sleepTime.getText().toString()) < 24) && (Integer.parseInt(et_sleepTime.getText().toString()) >= 0)){
                        sleepTime = Integer.parseInt(et_sleepTime.getText().toString());
                        Toast.makeText(DailyQuizActivity.this, sleepTime, Toast.LENGTH_SHORT).show();
                    }
                    if ((Integer.parseInt(et_productiveTime.getText().toString()) < 24) && (Integer.parseInt(et_productiveTime.getText().toString()) >= 0)){
                        productiveTime = Integer.parseInt(et_productiveTime.getText().toString());
                        Toast.makeText(DailyQuizActivity.this, productiveTime, Toast.LENGTH_SHORT).show();
                    }
                    if ((Integer.parseInt(et_relaxTime.getText().toString()) < 24) && (Integer.parseInt(et_relaxTime.getText().toString()) >= 0)){
                        relaxTime = Integer.parseInt(et_relaxTime.getText().toString());
                        Toast.makeText(DailyQuizActivity.this, relaxTime, Toast.LENGTH_SHORT).show();
                    }
                    if ((Integer.parseInt(et_exerciseTime.getText().toString()) < 24) && (Integer.parseInt(et_exerciseTime.getText().toString()) >= 0)){
                        exerciseTime = Integer.parseInt(et_exerciseTime.getText().toString());
                        Toast.makeText(DailyQuizActivity.this, exerciseTime, Toast.LENGTH_SHORT).show();
                    }

                    //technically, will we need to change this code because I basically copied it off a tutorial?
                    dailyQuiz = new DailyQuiz(date, mood, sleepTime, sleepRating, productiveTime, relaxTime, exerciseTime, stressLevel, stressors, other);

                    Toast.makeText(DailyQuizActivity.this, dailyQuiz.toString(), Toast.LENGTH_LONG).show();

                    databaseHelper = new DatabaseHelper(DailyQuizActivity.this);
                    boolean success = databaseHelper.addDailyQuiz(dailyQuiz);

                    Toast.makeText(DailyQuizActivity.this, "success " + success, Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(DailyQuizActivity.this, e.toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });

        btn_mood1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mood = "Good";
               // Toast.makeText(MorningQuizActivity.this, "it works", Toast.LENGTH_SHORT).show();

            }
        });
        btn_mood2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mood = "Decent";

            }
        });
        btn_mood3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = "Bad";

            }
        });
        btn_mood4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mood = "Tired";

            }
        });

        btn_sleepRating1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = "Good";
                // Toast.makeText(MorningQuizActivity.this, "it works", Toast.LENGTH_SHORT).show();

            }
        });
        btn_sleepRating2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = "Decent";

            }
        });
        btn_sleepRating3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mood = "Bad";

            }
        });
    }


}