package com.example.app.ui.goals_tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.DatabaseHelper;
import com.example.app.Goal;
import com.example.app.GoalsAdapter;
import com.example.app.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GoalsFragment extends Fragment {

    //should all these things be private???

    private GoalsViewModel goalsViewModel;
    EditText et_goal;
    Button btn_addGoal;
    RecyclerView rv_currentGoals;
    RecyclerView.Adapter adapter;

    private List<Goal> goalsList;


    DatabaseHelper databaseHelper;
    String goalText;
    String date;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        goalsViewModel =
                new ViewModelProvider(this).get(GoalsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_goals, container, false);

        et_goal = root.findViewById(R.id.et_goal);
        btn_addGoal = root.findViewById(R.id.btn_addGoal);

        databaseHelper = new DatabaseHelper(this.getContext());

        rv_currentGoals = (RecyclerView)root.findViewById(R.id.rv_currentGoals);
        rv_currentGoals.setHasFixedSize(true);
        rv_currentGoals.setLayoutManager(new LinearLayoutManager((this.getContext())));

        goalsList = databaseHelper.getGoals();

        adapter = new GoalsAdapter(goalsList, this.getContext()); //takes 2 arguments?

        rv_currentGoals.setAdapter(adapter);


        goalText = "";

        btn_addGoal.setOnClickListener(v ->{
                try {

                    if (et_goal.getText().toString().trim().equals("")){
                        Toast.makeText(this.getContext(), "must specify input", Toast.LENGTH_SHORT).show();
                    } else {
                        date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
                        goalText = et_goal.getText().toString();
                    }
                        //technically, will we need to change this code because I basically copied it off a tutorial?
                        Goal goal = new Goal(goalText, date, null);
                        Toast.makeText(this.getContext(), goal.getGoalText(), Toast.LENGTH_SHORT).show();
                        databaseHelper = new DatabaseHelper(this.getContext());
                        boolean success = databaseHelper.addGoal(goal);
                        Toast.makeText(this.getContext(), "success " + success, Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(this.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
        });




        return root;
    }
}