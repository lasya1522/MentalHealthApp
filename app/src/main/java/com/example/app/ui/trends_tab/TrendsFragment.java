package com.example.app.ui.trends_tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.DailyQuiz;
import com.example.app.DatabaseHelper;
import com.example.app.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class TrendsFragment extends Fragment {

    private TrendsViewModel trendsViewModel;
    //create references to buttons
    Button submitBtn;
    Button saveBtn;
    Button btn_viewPastQuizzes; //Previous quiz button
    DatabaseHelper databaseHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        trendsViewModel = new ViewModelProvider(this).get(TrendsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_trends, container, false);
        databaseHelper = new DatabaseHelper(this.getContext()); //is this.getContext() safe to use? I guessed.


        //code for buttons
       // submitBtn = (Button) root.findViewById(R.id.submitBtn);
      /*  submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                SleepData sleepData2 = new SleepData("Homework", "Sleep for 8 hours tonight");
                //Toast.makeText(SleepFragment.this, "Submit", Toast.LENGTH_SHORT).show();
                //find out where and why use Toast(to confirm that you have added some respond
                // which we have to further discuss and implement into our design
            }
        });

       */
     //   saveBtn = (Button) root.findViewById(R.id.saveBtn);
       /* saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        */

       /* trendsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        */



        List<DailyQuiz> dailyQuizData = databaseHelper.getDailyQuizData();

        //TESTED OUT GRAPHS BY CREATING A TEST GRAPH HERE

        //NEED TO PROTECT AGAINST NULL VALUES
        //we should do a "past 30 days" or "past 7 days" or whatever thing so that we have a finite thing
        BarChart chart_sleepTime;
        chart_sleepTime = (BarChart) root.findViewById(R.id.chart_sleepTime);
        List<BarEntry> sleepTimeEntries = new ArrayList<>();
        for (int i = 0; i < dailyQuizData.size(); i++){
            sleepTimeEntries.add( new BarEntry(i, dailyQuizData.get(i).getSleepTime()));
        }
        BarDataSet sleepTimeSet = new BarDataSet(sleepTimeEntries, "Sleep Time");
        BarData sleepTimeData = new BarData(sleepTimeSet);
        sleepTimeData.setBarWidth(1f); // set custom bar width
        chart_sleepTime.setData(sleepTimeData);
        chart_sleepTime.invalidate(); // refresh

        BarChart chart_productiveTime;
        chart_productiveTime = (BarChart) root.findViewById(R.id.chart_productiveTime);
        List<BarEntry> productiveTimeEntries = new ArrayList<>();
        for (int i = 0; i < dailyQuizData.size(); i++){
            productiveTimeEntries.add( new BarEntry(i, dailyQuizData.get(i).getProductiveTime()));
        }
        BarDataSet productiveTimeSet = new BarDataSet(productiveTimeEntries, "Productive Time");
        BarData productiveTimeData = new BarData(productiveTimeSet);
        productiveTimeData.setBarWidth(1f); // set custom bar width
        chart_productiveTime.setData(productiveTimeData);
        chart_productiveTime.invalidate(); // refresh

        BarChart chart_relaxTime;
        chart_relaxTime = (BarChart) root.findViewById(R.id.chart_relaxTime);
        List<BarEntry> relaxTimeEntries = new ArrayList<>();
        for (int i = 0; i < dailyQuizData.size(); i++){
            relaxTimeEntries.add( new BarEntry(i, dailyQuizData.get(i).getRelaxTime()));
        }
        BarDataSet relaxTimeSet = new BarDataSet(relaxTimeEntries, "Relax Time");
        BarData relaxTimeData = new BarData(relaxTimeSet);
        relaxTimeData.setBarWidth(1f); // set custom bar width
        chart_relaxTime.setData(relaxTimeData);
        chart_relaxTime.invalidate(); // refresh

        BarChart chart_exerciseTime;
        chart_exerciseTime = (BarChart) root.findViewById(R.id.chart_exerciseTime);
        List<BarEntry> exerciseTimeEntries = new ArrayList<>();
        for (int i = 0; i < dailyQuizData.size(); i++){
            exerciseTimeEntries.add( new BarEntry(i, dailyQuizData.get(i).getExerciseTime()));
        }
        BarDataSet exerciseTimeSet = new BarDataSet(exerciseTimeEntries, "Exercise Time");
        BarData exerciseTimeData = new BarData(exerciseTimeSet);
        relaxTimeData.setBarWidth(1f); // set custom bar width
        chart_exerciseTime.setData(exerciseTimeData);
        chart_exerciseTime.invalidate(); // refresh



        return root;
    }

    }