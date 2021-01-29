package com.example.app.ui.trends_tab;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.DailyQuiz;
import com.example.app.DatabaseHelper;
import com.example.app.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
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

        //NEED TO PROTECT AGAINST NULL VALUES
        //we should do a "past 30 days" or "past 7 days" or whatever thing so that we have a finite thing
        ArrayList<String> sleepRatingList = new ArrayList<>();
        for (int i = 0; i < dailyQuizData.size(); i++){
            sleepRatingList.add(dailyQuizData.get(i).getSleepRating());
        }
        PieChart chart_sleepRating;
        chart_sleepRating = (PieChart) root.findViewById(R.id.chart_sleepRating);
        Toast.makeText(this.getContext(), sleepRatingList.toString(), Toast.LENGTH_LONG).show();
        List<PieEntry> sleepRatingEntries = new ArrayList<>();
        List<Float> sleepRatingFreqData = getMoodFreq(sleepRatingList);
        sleepRatingEntries.add(new PieEntry(sleepRatingFreqData.get(0), "Good"));
        sleepRatingEntries.add(new PieEntry(sleepRatingFreqData.get(1), "Decent"));
        sleepRatingEntries.add(new PieEntry(sleepRatingFreqData.get(2), "Bad"));
        sleepRatingEntries.add(new PieEntry(sleepRatingFreqData.get(3), "Tired"));
        PieDataSet sleepRatingSet = new PieDataSet(sleepRatingEntries, "Mood");
        PieData sleepRatingData = new PieData(sleepRatingSet);
        sleepRatingSet.setColors(new int[] {R.color.purple_graph_1, R.color.purple_graph_2, R.color.purple_graph_3, R.color.purple_graph_4}, this.getContext());
        chart_sleepRating.setData(sleepRatingData);
        //makes the pie chart completely filled in
        chart_sleepRating.setHoleRadius(0);
        chart_sleepRating.setTransparentCircleAlpha(0);

        chart_sleepRating.invalidate(); // refresh
        ArrayList<String> moodList = new ArrayList<>();
        for (int i = 0; i < dailyQuizData.size(); i++){
            moodList.add(dailyQuizData.get(i).getMood());
        }
        PieChart chart_mood;
        chart_mood = (PieChart) root.findViewById(R.id.chart_mood);
        Toast.makeText(this.getContext(), moodList.toString(), Toast.LENGTH_LONG).show();
        List<PieEntry> moodEntries = new ArrayList<>();
        List<Float> moodFreqData = getMoodFreq(moodList);
        moodEntries.add(new PieEntry(moodFreqData.get(0), "Good"));
        moodEntries.add(new PieEntry(moodFreqData.get(1), "Decent"));
        moodEntries.add(new PieEntry(moodFreqData.get(2), "Bad"));
        moodEntries.add(new PieEntry(moodFreqData.get(3), "Tired"));
        PieDataSet moodSet = new PieDataSet(moodEntries, "Mood");
        PieData moodData = new PieData(moodSet);
        moodSet.setColors(new int[] {R.color.purple_graph_1, R.color.purple_graph_2, R.color.purple_graph_3, R.color.purple_graph_4}, this.getContext());
        chart_mood.setData(moodData);
        //makes the pie chart completely filled in
        chart_mood.setHoleRadius(0);
        chart_mood.setTransparentCircleAlpha(0);

        chart_mood.invalidate(); // refresh

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

        //sleep time bar chart
        BarChart chart_sleepTime;
        chart_sleepTime = (BarChart) root.findViewById(R.id.chart_sleepTime);
        List<BarEntry> sleepTimeEntries = new ArrayList<>();
        if (dailyQuizData.size() >= 7){
            for (int i = dailyQuizData.size()-7; i < dailyQuizData.size(); i++) {
                sleepTimeEntries.add(new BarEntry(i, dailyQuizData.get(i).getSleepTime()));
            }
        } else {
            for (int i = 0; i < dailyQuizData.size(); i++) {
                sleepTimeEntries.add(new BarEntry(i, dailyQuizData.get(i).getSleepTime()));
            }
        }
        BarDataSet sleepTimeSet = new BarDataSet(sleepTimeEntries, "Sleep Time");
        BarData sleepTimeData = new BarData(sleepTimeSet);
        sleepTimeSet.setColors(new int[] {R.color.purple_graph_1, R.color.purple_graph_2, R.color.purple_graph_3}, this.getContext());
        chart_sleepTime.setEnabled(false);

        XAxis xaxis_sleepTime = chart_sleepTime.getXAxis();
        xaxis_sleepTime.setDrawGridLines(false);
        xaxis_sleepTime.setDrawAxisLine(false);

        
        //do I have to call other things? AxisDependenct.LEFT or RIGHT in order for this to work properly
        YAxis left_axis_sleepTime = chart_sleepTime.getAxisLeft();
        left_axis_sleepTime.setDrawGridLines(false);
        left_axis_sleepTime.setDrawAxisLine(false);
        left_axis_sleepTime.setDrawAxisLine(false);
        left_axis_sleepTime.setDrawLabels(false);

        YAxis right_axis_sleepTime = chart_sleepTime.getAxisRight();
        right_axis_sleepTime.setDrawGridLines(false);
        right_axis_sleepTime.setDrawAxisLine(false);
        right_axis_sleepTime.setDrawAxisLine(false);
        right_axis_sleepTime.setDrawLabels(false);


        sleepTimeData.setBarWidth(0.9f);
        chart_sleepTime.setDrawGridBackground(false);
        chart_sleepTime.setData(sleepTimeData);
        chart_sleepTime.setFitBars(true);
        chart_sleepTime.getViewPortHandler().setMinMaxScaleX(0, 7);
        chart_sleepTime.getViewPortHandler().setMinMaxScaleY(0, 24);
        chart_sleepTime.invalidate(); // refresh


        //productive time bar chart
        BarChart chart_productiveTime;
        chart_productiveTime = (BarChart) root.findViewById(R.id.chart_productiveTime);
        List<BarEntry> productiveTimeEntries = new ArrayList<>();
        for (int i = 0; i < dailyQuizData.size(); i++){
            productiveTimeEntries.add( new BarEntry(i, dailyQuizData.get(i).getProductiveTime()));
        }
        BarDataSet productiveTimeSet = new BarDataSet(productiveTimeEntries, "Productive Time");
        BarData productiveTimeData = new BarData(productiveTimeSet);
        productiveTimeSet.setColors(new int[] { R.color.purple_graph_1, R.color.purple_graph_2, R.color.purple_graph_3}, this.getContext());
        productiveTimeSet.setValueTextColor(Color.BLACK);
        productiveTimeSet.setValueTextSize(22f);
        productiveTimeData.setBarWidth(1f); // set custom bar width
        chart_productiveTime.setData(productiveTimeData);
        chart_productiveTime.invalidate(); // refresh

        //relax time bar chart
        BarChart chart_relaxTime;
        chart_relaxTime = (BarChart) root.findViewById(R.id.chart_relaxTime);
        List<BarEntry> relaxTimeEntries = new ArrayList<>();
        for (int i = 0; i < dailyQuizData.size(); i++){
            relaxTimeEntries.add( new BarEntry(i, dailyQuizData.get(i).getRelaxTime()));
        }
        BarDataSet relaxTimeSet = new BarDataSet(relaxTimeEntries, "Relax Time");
        BarData relaxTimeData = new BarData(relaxTimeSet);
        relaxTimeSet.setColors(new int[] {R.color.purple_graph_1, R.color.purple_graph_2, R.color.purple_graph_3}, this.getContext() );
        relaxTimeSet.setValueTextColor(Color.BLACK);
        relaxTimeSet.setValueTextSize(22f);
        relaxTimeData.setBarWidth(1f); // set custom bar width
        chart_relaxTime.setData(relaxTimeData);
        chart_relaxTime.invalidate(); // refresh

        //exercise time bar chart
        BarChart chart_exerciseTime;
        chart_exerciseTime = (BarChart) root.findViewById(R.id.chart_exerciseTime);
        List<BarEntry> exerciseTimeEntries = new ArrayList<>();
        for (int i = 0; i < dailyQuizData.size(); i++){
            exerciseTimeEntries.add( new BarEntry(i, dailyQuizData.get(i).getExerciseTime()));
        }
        BarDataSet exerciseTimeSet = new BarDataSet(exerciseTimeEntries, "Exercise Time");
        BarData exerciseTimeData = new BarData(exerciseTimeSet);
        exerciseTimeSet.setColors(new int[] {R.color.purple_graph_1, R.color.purple_graph_2, R.color.purple_graph_3}, this.getContext());
        exerciseTimeSet.setValueTextColor(Color.BLACK);
        exerciseTimeSet.setValueTextSize(22f);
        chart_exerciseTime.setData(exerciseTimeData);
        chart_exerciseTime.invalidate(); // refresh
        relaxTimeData.setBarWidth(1f); // set custom bar width

        //set axis limits
        chart_sleepTime.getViewPortHandler().setMinMaxScaleX(0, 10);
        chart_sleepTime.getViewPortHandler().setMinMaxScaleY(0, 20);

        chart_productiveTime.getViewPortHandler().setMinMaxScaleX(0, 10);
        chart_productiveTime.getViewPortHandler().setMinMaxScaleY(0, 20);

        chart_relaxTime.getViewPortHandler().setMinMaxScaleX(0, 10);
        chart_relaxTime.getViewPortHandler().setMinMaxScaleY(0, 20);

        chart_exerciseTime.getViewPortHandler().setMinMaxScaleX(0, 10);
        chart_exerciseTime.getViewPortHandler().setMinMaxScaleY(0, 20);

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

   private ArrayList<Float> getMoodFreq(ArrayList<String> list){
        ArrayList<Float> counter = new ArrayList<>();
        counter.add(0f);
        counter.add(0f);
        counter.add(0f);
        counter.add(0f);

        for (int i = 0; i < list.size(); i++){
            if (list.get(i).equals("Good")){
                counter.set(0, counter.get(0)+1);
                Toast.makeText(this.getContext(), "counter.get(0) == " + counter.get(0), Toast.LENGTH_SHORT).show();
            } else if (list.get(i).equals("Decent")){
                counter.set(1, counter.get(1)+1);
            } else if (list.get(i).equals("Bad")){
                counter.set(2, counter.get(2)+1);
            } else {
                counter.set(3, counter.get(3)+1);
            }
        }
        counter.set(0, counter.get(0));
        Toast.makeText(this.getContext(), counter.get(0).toString(), Toast.LENGTH_SHORT).show();
        counter.set(1, counter.get(1));
        counter.set(2, counter.get(2));
        counter.set(3, counter.get(3));

       return counter;
    }


    }