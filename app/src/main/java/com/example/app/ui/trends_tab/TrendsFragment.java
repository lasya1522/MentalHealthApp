package com.example.app.ui.trends_tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
        final TextView textView = root.findViewById(R.id.trendTitle);
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

        //we should do a "past 30 days" or "past 7 days" or whatever thing so that we have a finite thing
        BarChart barChart;
        barChart = (BarChart) root.findViewById(R.id.chart);
        List<BarEntry> entries = new ArrayList<>();

        //IMPORTANT: it is not showing the correct number on the graph here.?????????????????????
        //entries.add(new BarEntry(0f, dailyQuizData.get(0).getProductiveTime()));
        //Toast.makeText(this.getContext(), String.valueOf(dailyQuizData.get(0)), Toast.LENGTH_SHORT).show();
        //entries.add(new BarEntry(1f, dailyQuizData.get(1).getProductiveTime()));
        //entries.add(new BarEntry(2f, dailyQuizData.get(2).getProductiveTime()));
        //entries.add(new BarEntry(3f, dailyQuizData.get(3).getProductiveTime()));
        // gap of 2f
        entries.add(new BarEntry(5f, 70f));
        entries.add(new BarEntry(6f, 60f));
        BarDataSet set = new BarDataSet(entries, "BarDataSet");
        BarData data = new BarData(set);
        data.setBarWidth(0.9f); // set custom bar width
        barChart.setData(data);
        barChart.setFitBars(true); // make the x-axis fit exactly all bars
        barChart.invalidate(); // refresh

        return root;
    }

    }