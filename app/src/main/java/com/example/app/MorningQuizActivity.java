package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class MorningQuizActivity extends AppCompatActivity {

   private EditText et_sleepRating;
   private EditText et_sleepDuration;

    private Button btn_submit;

    private Button btn_mood1;
    private Button btn_mood2;
    private Button btn_mood3;
    private Button btn_mood4;
    private Button btn_mood5;

    private Date date;
    private String mood;
    private String sleepTime;
    private String sleepRating;
    private String productiveTime;
    private String relaxTime;
    private String exerciseTime;
    private String stressLevel;
    private String stressors;
    private String other;//is this really the best way to keep track of the mood ? // may change to MC buttons

    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_quiz);
        //the back arrow will appear on the morning quiz in the action bar

        //btn_submit = findViewById(R.id.btn_submit);

        //btn_mood1 = findViewById(R.id.btn_mood1);
        //btn_mood2 = findViewById(R.id.btn_mood2);
        //btn_mood3 = findViewById(R.id.btn_mood3);
        //btn_mood4 = findViewById(R.id.btn_mood4);
        //btn_mood5 = findViewById(R.id.btn_mood5);

        //btn_stress2 = findViewById(R.id.btn_stress2);

        //et_sleepDuration = findViewById(R.id.et_sleepDuration);
        //et_sleepRating = findViewById(R.id.et_sleepRating);

        databaseHelper = new DatabaseHelper(MorningQuizActivity.this);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MorningQuiz morningQuiz;

                try {
                    morningQuiz = new MorningQuiz(mood, Calendar.getInstance().getTime(),
                            Integer.parseInt(et_sleepRating.getText().toString()),
                            Integer.parseInt(et_sleepDuration.getText().toString()));

                    Toast.makeText(MorningQuizActivity.this, morningQuiz.toString(), Toast.LENGTH_LONG).show();
                    databaseHelper = new DatabaseHelper(MorningQuizActivity.this);
                    boolean success = databaseHelper.addMorningQuiz(morningQuiz);
                    Toast.makeText(MorningQuizActivity.this, "success " + success, Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(MorningQuizActivity.this, e.toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });

        // whenever the button is selected, the instance variable is updated with that choice.
        // Lasya is right; are buttons really the best choice for this? idk, but here we are for now
        btn_mood1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodChoice = 1;
                Toast.makeText(MorningQuizActivity.this, "it works", Toast.LENGTH_SHORT).show();

            }
        });
        btn_mood2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodChoice = 2;

            }
        });
        btn_mood3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodChoice = 3;

            }
        });
        btn_mood4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodChoice = 4;

            }
        });
        btn_mood5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodChoice = 5;

            }
        });

    }

}