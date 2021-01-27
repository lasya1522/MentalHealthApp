package com.example.app.ui.trends_tab;

import android.graphics.Color;
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
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TrendsFragment extends Fragment {

    private TrendsViewModel trendsViewModel;
    //create references to buttons
    Button submitBtn;
    Button saveBtn;
    Button btn_viewPastQuizzes; //Previous quiz button

    TextView tv_sleepTimeData;
    TextView tv_productiveTimeData;
    TextView tv_relaxTimeData;
    TextView tv_exerciseTimeData;

    DatabaseHelper databaseHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        trendsViewModel = new ViewModelProvider(this).get(TrendsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_trends, container, false);
        databaseHelper = new DatabaseHelper(this.getContext()); //is this.getContext() safe to use? I guessed.

        tv_sleepTimeData = root.findViewById(R.id.tv_sleepTimeData);
        tv_productiveTimeData = root.findViewById(R.id.tv_productiveTimeData);
        tv_relaxTimeData = root.findViewById(R.id.tv_relaxTimeData);
        tv_exerciseTimeData = root.findViewById(R.id.tv_exerciseTimeData);

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
        sleepTimeSet.setColors(ColorTemplate.MATERIAL_COLORS);
        sleepTimeSet.setValueTextColor(Color.BLACK);
        sleepTimeSet.setValueTextSize(22f);
        sleepTimeData.setBarWidth(1f); // set custom bar width
        chart_sleepTime.setData(sleepTimeData);
        chart_sleepTime.getViewPortHandler().setMinMaxScaleX(0, 10);
        chart_sleepTime.getViewPortHandler().setMinMaxScaleY(0, 20);
        chart_sleepTime.invalidate(); // refresh

        ArrayList<Integer> sleepTimeList = new ArrayList<>();
        for (int i = 0; i < dailyQuizData.size(); i++){
            sleepTimeList.add(dailyQuizData.get(i).getSleepTime());
        }
        double sleepTimeMean = calculateMean(sleepTimeList);
        double sleepTimeMedian = calculateMedian(sleepTimeList);
        double sleepTimeMode = calculateMode(sleepTimeList);
        double sleepTimeRange = calculateRange(sleepTimeList);
        tv_sleepTimeData.setText("Mean = " + sleepTimeMean + " Median = " +sleepTimeMedian + " Mode = " + sleepTimeMode + " Range = " + sleepTimeRange);


        ArrayList<Integer> productiveTimeList = new ArrayList<>();
        for (int i = 0; i < dailyQuizData.size(); i++){
            productiveTimeList.add(dailyQuizData.get(i).getProductiveTime());
        }
        double productiveTimeMean = calculateMean(productiveTimeList);
        double productiveTimeMedian = calculateMedian(productiveTimeList);
        double productiveTimeMode = calculateMode(productiveTimeList);
        double productiveTimeRange = calculateRange(productiveTimeList);

        tv_productiveTimeData.setText("Mean = " + productiveTimeMean + " Median = " +productiveTimeMedian + " Mode = " + productiveTimeMode + " Range = " + productiveTimeRange);

        ArrayList<Integer> relaxTimeList = new ArrayList<>();
        for (int i = 0; i < dailyQuizData.size(); i++ ){
            relaxTimeList.add(dailyQuizData.get(i).getRelaxTime());
        }
        double relaxTimeMean = calculateMean(relaxTimeList);
        double relaxTimeMedian = calculateMedian(relaxTimeList);
        double relaxTimeMode = calculateMode(relaxTimeList);
        double relaxTimeRange = calculateRange(relaxTimeList);
        tv_relaxTimeData.setText("Mean = " + relaxTimeMean + " Median = " + relaxTimeMedian + " Mode = " +relaxTimeMode + " Range = " + relaxTimeRange);

        ArrayList<Integer> exerciseTimeList = new ArrayList<>();
        for (int i = 0; i < dailyQuizData.size(); i++ ){
            exerciseTimeList.add(dailyQuizData.get(i).getExerciseTime());
        }
        double exerciseTimeMean = calculateMean(exerciseTimeList);
        double exerciseTimeMedian = calculateMedian(exerciseTimeList);
        double exerciseTimeMode = calculateMode(exerciseTimeList);
        double exerciseTimeRange = calculateRange(exerciseTimeList);
        tv_exerciseTimeData.setText("Mean = " + exerciseTimeMean + " Median = " + exerciseTimeMedian + " Mode = " + exerciseTimeMode + " Range = " + exerciseTimeRange);


        BarChart chart_productiveTime;
        chart_productiveTime = (BarChart) root.findViewById(R.id.chart_productiveTime);
        List<BarEntry> productiveTimeEntries = new ArrayList<>();
        for (int i = 0; i < dailyQuizData.size(); i++){
            productiveTimeEntries.add( new BarEntry(i, dailyQuizData.get(i).getProductiveTime()));
        }
        BarDataSet productiveTimeSet = new BarDataSet(productiveTimeEntries, "Productive Time");
        BarData productiveTimeData = new BarData(productiveTimeSet);
        productiveTimeSet.setColors(ColorTemplate.MATERIAL_COLORS);
        productiveTimeSet.setValueTextColor(Color.BLACK);
        productiveTimeSet.setValueTextSize(22f);
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
        relaxTimeSet.setColors(ColorTemplate.MATERIAL_COLORS);
        relaxTimeSet.setValueTextColor(Color.BLACK);
        relaxTimeSet.setValueTextSize(22f);
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
        exerciseTimeSet.setColors(ColorTemplate.MATERIAL_COLORS);
        exerciseTimeSet.setValueTextColor(Color.BLACK);
        exerciseTimeSet.setValueTextSize(22f);
        relaxTimeData.setBarWidth(1f); // set custom bar width

        //set axis limits
        chart_exerciseTime.getViewPortHandler().setMinMaxScaleX(0, 10);
        chart_exerciseTime.getViewPortHandler().setMinMaxScaleY(0, 20);



        chart_exerciseTime.setData(exerciseTimeData);
        chart_exerciseTime.invalidate(); // refresh

        return root;
    }

    private double calculateMean(ArrayList<Integer> list ){
        double mean = 0;
        double sleepTimeMean = 0;
        for (int i = 0; i < list.size(); i++){
            mean += list.get(i);
        }
        mean /= list.size();
        return mean;
    }

    private double calculateMedian(ArrayList<Integer> list){
        //TEST AND MAKE SURE THIS ACTUALLY WORKS
        double median = 0;
        Collections.sort(list);
        if (list.size() % 2 == 0) {
            median = (list.get(list.size()/2) + list.get(list.size()/2)+1)/2; //check to make sure this line actually works. integer divisi
        } else {
            median = list.get(list.size() / 2);
        }
        return median;
    }

   private int calculateMode(ArrayList<Integer> list){
        // ********************
       // do we need to account for sets with multiple modes???????????????
       int mode = 0;
       int max = Integer.MIN_VALUE;
        int[] freq = new int[list.size()];
        for (int i = 0; i < list.size(); i++){
            int counter = 0;
            for (int j = 0; j < list.size(); j ++){
                if (list.get(i) == list.get(j)){
                    counter++;
                }
            }
            if (counter > max){
                max = counter;
                mode = list.get(i);
            }

        }
        return mode;
   }

   private int calculateRange(ArrayList<Integer> list){
        int range = 0;
        Collections.sort(list);
        range = list.get(list.size()-1) - list.get(0);
        return range;
   }



    }