package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class PastQuizzesActivity extends AppCompatActivity {

    ListView lv_quizData; //change to recyclerView???????
    Button btn_viewAll;
    ArrayAdapter dailyQuizArrayAdapter; //ARE THESE SUPPOSED TO BE PRIVATE?

    //revise code and var names so it isn't copying off the tutorial????????????? ********* very important************


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_quizzes);

        lv_quizData = findViewById(R.id.lv_quizData);
        btn_viewAll = findViewById(R.id.btn_viewAll);

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DatabaseHelper databaseHelper = new DatabaseHelper(PastQuizzesActivity.this);
            List<DailyQuiz> everyone = databaseHelper.getDailyQuizData();
            dailyQuizArrayAdapter = new ArrayAdapter<DailyQuiz>(PastQuizzesActivity.this, android.R.layout.simple_selectable_list_item, everyone);
            lv_quizData.setAdapter(dailyQuizArrayAdapter);
        }
    });


    }

}
