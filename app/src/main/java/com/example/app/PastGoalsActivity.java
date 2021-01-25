package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PastGoalsActivity extends AppCompatActivity {
    RecyclerView rv_pastGoals;
    RecyclerView.Adapter adapter;

    DatabaseHelper databaseHelper;

    private List<Goal> pastGoalsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_goals);

        databaseHelper = new DatabaseHelper(PastGoalsActivity.this);

        pastGoalsList = databaseHelper.getCompletedGoals();

        rv_pastGoals = findViewById(R.id.rv_pastGoals);
        rv_pastGoals.setHasFixedSize(true);
        rv_pastGoals.setLayoutManager(new LinearLayoutManager(PastGoalsActivity.this));

        adapter = new PastGoalsAdapter(pastGoalsList, PastGoalsActivity.this); // this or PastGoalsActivity.this?

        rv_pastGoals.setAdapter(adapter);

    }

}
