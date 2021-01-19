package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Calendar;

public class NightQuizActivity extends AppCompatActivity {
    private Button btn_mood1_n;
    private Button btn_mood2_n;
    private Button btn_mood3_n;
    private Button btn_mood4_n;
    private Button btn_mood5_n;

    private Button btn_productive_1;
    private Button btn_productive_2;
    private Button btn_productive_3;
    private Button btn_productive_4;

    private Button btn_relax_1;
    private Button btn_relax_2;
    private Button btn_relax_3;
    private Button btn_relax_4;

    private Button btn_exercise_1;
    private Button btn_exercise_2;
    private Button btn_exercise_3;
    private Button btn_exercise_4;

    private Button btn_stress1_n;
    private Button btn_stress2_n;
    private Button btn_stress3_n;


    private Button btn_submit_n;

    private Integer moodChoice;
    private String productiveTime;
    private String relaxTime;
    private String exerciseTime;
    private Integer stressLevel;

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

        btn_productive_1 = findViewById(R.id.btn_productive_1);
        btn_productive_2 = findViewById(R.id.btn_productive_2);
        btn_productive_3 = findViewById(R.id.btn_productive_3);
        btn_productive_4 = findViewById(R.id.btn_productive_4);

        btn_relax_1 = findViewById(R.id.btn_relax_1);
        btn_relax_2 = findViewById(R.id.btn_relax_2);
        btn_relax_3 = findViewById(R.id.btn_relax_3);
        btn_relax_4 = findViewById(R.id.btn_relax_4);

        btn_exercise_1 = findViewById(R.id.btn_relax_1);
        btn_exercise_2 = findViewById(R.id.btn_relax_2);
        btn_exercise_3 = findViewById(R.id.btn_relax_3);
        btn_exercise_4 = findViewById(R.id.btn_relax_4);

        btn_stress1_n = findViewById(R.id.btn_stress1_n);
        btn_stress2_n = findViewById(R.id.btn_stress2_n);
        btn_stress3_n = findViewById(R.id.btn_stress3_n);


        btn_submit_n = findViewById(R.id.btn_submit_n);


        moodChoice = 0;
        productiveTime = "";
        relaxTime = "";
        exerciseTime = ""; // should we initialize these to null, though?? hmm



        databaseHelper = new DatabaseHelper(NightQuizActivity.this);

        btn_submit_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NightQuiz nightQuiz;

                try {
                    nightQuiz = new NightQuiz(moodChoice, stressLevel, Calendar.getInstance().getTime(), productiveTime, relaxTime, exerciseTime);
                    Toast.makeText(NightQuizActivity.this, nightQuiz.toString(), Toast.LENGTH_LONG).show();
                    databaseHelper = new DatabaseHelper(NightQuizActivity.this);
                    boolean success = databaseHelper.addNightQuiz(nightQuiz);
                    Toast.makeText(NightQuizActivity.this, "success " + success, Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(NightQuizActivity.this, e.toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });


        btn_mood1_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodChoice = 1;
                Toast.makeText(NightQuizActivity.this, "it works", Toast.LENGTH_SHORT).show();

            }
        });
        btn_mood2_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodChoice = 2;

            }
        });
        btn_mood3_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodChoice = 3;

            }
        });
        btn_mood4_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodChoice = 4;

            }
        });
        btn_mood5_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moodChoice = 5;

            }
        });

        btn_productive_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productiveTime = "0 hours";
                Toast.makeText(NightQuizActivity.this, productiveTime, Toast.LENGTH_SHORT).show();
            }
        });

        btn_productive_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productiveTime = "1-3 hours";
                Toast.makeText(NightQuizActivity.this, productiveTime, Toast.LENGTH_SHORT).show();
            }
        });

        btn_productive_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productiveTime = "4-6 hours";
                Toast.makeText(NightQuizActivity.this, productiveTime, Toast.LENGTH_SHORT).show();
            }
        });

        btn_productive_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productiveTime = "6+ hours";
                Toast.makeText(NightQuizActivity.this, productiveTime, Toast.LENGTH_SHORT).show();
            }
        });

        btn_relax_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relaxTime = "0 hours";
                Toast.makeText(NightQuizActivity.this, relaxTime, Toast.LENGTH_SHORT).show();
            }
        });

        btn_relax_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relaxTime = "1-3 hours";
                Toast.makeText(NightQuizActivity.this, productiveTime, Toast.LENGTH_SHORT).show();
            }
        });

        btn_relax_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relaxTime = "4-6 hours";
                Toast.makeText(NightQuizActivity.this, relaxTime, Toast.LENGTH_SHORT).show();
            }
        });

        btn_relax_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relaxTime = "6+ hours";
                Toast.makeText(NightQuizActivity.this, relaxTime, Toast.LENGTH_SHORT).show();
            }
        });

        btn_exercise_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exerciseTime = "0 hours";
                Toast.makeText(NightQuizActivity.this, exerciseTime, Toast.LENGTH_SHORT).show();
            }
        });

        btn_exercise_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exerciseTime = "1-3 hours";
                Toast.makeText(NightQuizActivity.this, exerciseTime, Toast.LENGTH_SHORT).show();
            }
        });

        btn_exercise_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exerciseTime = "4-6 hours";
                Toast.makeText(NightQuizActivity.this, exerciseTime, Toast.LENGTH_SHORT).show();
            }
        });

        btn_exercise_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exerciseTime = "6+ hours";
                Toast.makeText(NightQuizActivity.this, exerciseTime, Toast.LENGTH_SHORT).show();
            }
        });

        btn_stress1_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stressLevel = 1;
                Toast.makeText(NightQuizActivity.this, stressLevel.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        btn_stress2_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stressLevel = 2;
                Toast.makeText(NightQuizActivity.this, stressLevel.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        btn_stress3_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stressLevel = 3;
                Toast.makeText(NightQuizActivity.this, stressLevel.toString(), Toast.LENGTH_SHORT).show();
            }
        });





    }

}