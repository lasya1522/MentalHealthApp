package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MorningQuizActivity extends AppCompatActivity {
    private EditText et_sleepRating;
    private EditText et_sleepDuration;

    private Button btn_submit, btn_save;

    private Button btn_mood1;
    private Button btn_mood2;
    private Button btn_mood3;
    private Button btn_mood4;
    private Button btn_mood5;

    private Button btn_stress1;
    private Button btn_stress2;
    private Button btn_stress3;

    private Integer moodChoice; //is this really the best way to keep track of the mood and the stress level choice?
    private Integer stressLevelChoice;

    DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morning_quiz);
        //the back arrow will appear on the morning quiz in the action bar

        btn_submit = findViewById(R.id.btn_submit);

        btn_mood1 = findViewById(R.id.btn_mood1);
        btn_mood2 = findViewById(R.id.btn_mood2);
        btn_mood3 = findViewById(R.id.btn_mood3);
        btn_mood4 = findViewById(R.id.btn_mood4);
        btn_mood5 = findViewById(R.id.btn_mood5);

        btn_stress2 = findViewById(R.id.btn_stress2);

        et_sleepDuration = findViewById(R.id.et_sleepDuration);
        et_sleepRating = findViewById(R.id.et_sleepRating);

        databaseHelper = new DatabaseHelper(MorningQuizActivity.this);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MorningQuiz morningQuiz;

                try {
                    morningQuiz = new MorningQuiz(moodChoice, stressLevelChoice, Calendar.getInstance().getTime(),
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

        btn_stress1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stressLevelChoice = 1;

            }
        });
        btn_stress2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stressLevelChoice = 2;

            }
        });
        btn_stress3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stressLevelChoice = 3;

            }
        });


    }

}