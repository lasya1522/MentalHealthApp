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
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
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

        //******** PIE CHART SLEEP RATING *******
         ArrayList<String> sleepRatingList = new ArrayList<>();
        for (int i = 0; i < dailyQuizData.size(); i++){
            sleepRatingList.add(dailyQuizData.get(i).getSleepRating());
        }
        PieChart chart_sleepRating;

        chart_sleepRating = (PieChart) root.findViewById(R.id.chart_sleepRating);
        Toast.makeText(this.getContext(), sleepRatingList.toString(), Toast.LENGTH_LONG).show();
        List<PieEntry> sleepRatingEntries = new ArrayList<>();
        List<Integer> sleepRatingFreqData = getFreq(sleepRatingList, "Good", "Decent", "Bad");
        sleepRatingEntries.add(new PieEntry(sleepRatingFreqData.get(0), "Good"));
        sleepRatingEntries.add(new PieEntry(sleepRatingFreqData.get(1), "Decent"));
        sleepRatingEntries.add(new PieEntry(sleepRatingFreqData.get(2), "Bad"));

        PieDataSet sleepRatingSet = new PieDataSet(sleepRatingEntries, "Sleep Rating");
        PieData sleepRatingData = new PieData(sleepRatingSet);
        sleepRatingSet.setColors(new int[] {R.color.scheme_green_1, R.color.scheme_blue_1, R.color.scheme_blue_2}, this.getContext());
        chart_sleepRating.setData(sleepRatingData);

        //makes the pie chart completely filled in
        chart_sleepRating.setHoleRadius(0);
        chart_sleepRating.setTransparentCircleAlpha(0);

        //********* PIE CHART MOOD ************
        ArrayList<String> moodList = new ArrayList<>();
        for (int i = 0; i < dailyQuizData.size(); i++){
            moodList.add(dailyQuizData.get(i).getMood());
        }
        PieChart chart_mood;
        chart_mood = (PieChart) root.findViewById(R.id.chart_mood);
        Toast.makeText(this.getContext(), moodList.toString(), Toast.LENGTH_LONG).show();
        List<PieEntry> moodEntries = new ArrayList<>();
        List<Integer> moodFreqData = getFreq(moodList, "Good", "Decent", "Bad");
        moodEntries.add(new PieEntry(moodFreqData.get(0), "Good"));
        moodEntries.add(new PieEntry(moodFreqData.get(1), "Decent"));
        moodEntries.add(new PieEntry(moodFreqData.get(2), "Bad"));
        PieDataSet moodSet = new PieDataSet(moodEntries, "Mood");
        PieData moodData = new PieData(moodSet);
        moodSet.setColors(new int[] {R.color.scheme_blue_1, R.color.scheme_blue_2, R.color.scheme_purple_1}, this.getContext());
        chart_mood.setData(moodData);
        //makes the pie chart completely filled in
        chart_mood.setHoleRadius(0);
        chart_mood.setTransparentCircleAlpha(0);

        chart_mood.invalidate(); // refresh

        //******** PIE CHART SLEEP RATING *******
         ArrayList<String> stressLevelList = new ArrayList<>();
        for (int i = 0; i < dailyQuizData.size(); i++){
            stressLevelList.add(dailyQuizData.get(i).getStressLevel());
        }
        PieChart chart_stressLevel;

        chart_stressLevel = (PieChart) root.findViewById(R.id.chart_stressLevel);
        Toast.makeText(this.getContext(), stressLevelList.toString(), Toast.LENGTH_LONG).show();
        List<PieEntry> stressLevelEntries = new ArrayList<>();
        List<Integer> stressLevelFreqData = getFreq(sleepRatingList, "High", "Moderate", "Low");
        stressLevelEntries.add(new PieEntry(stressLevelFreqData.get(0), "High"));
        stressLevelEntries.add(new PieEntry(stressLevelFreqData.get(1), "Moderate"));
        stressLevelEntries.add(new PieEntry(stressLevelFreqData.get(2), "Low"));

        PieDataSet stressLevelSet = new PieDataSet(stressLevelEntries, "Stress Level");
        PieData stressLevelData = new PieData(stressLevelSet);
        stressLevelSet.setColors(new int[] {R.color.scheme_blue_2, R.color.scheme_purple_1, R.color.scheme_purple_2}, this.getContext());
        chart_stressLevel.setData(stressLevelData);

        //makes the pie chart completely filled in
        chart_stressLevel.setHoleRadius(0);
        chart_stressLevel.setTransparentCircleAlpha(0);

        // ******* SLEEP TIME DATA ************
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
        sleepTimeSet.setColors(new int[] {R.color.scheme_blue_1, R.color.scheme_blue_2, R.color.scheme_purple_1}, this.getContext());
        sleepTimeData.setBarWidth(0.9f);
        //do we need to explicitly set all of these to false, or is it default
        chart_sleepTime.setEnabled(false);
        chart_sleepTime.setTouchEnabled(false);
        chart_sleepTime.setDragEnabled(false);
        chart_sleepTime.setScaleXEnabled(false);
        chart_sleepTime.setScaleYEnabled(false);
        chart_sleepTime.setScaleEnabled(false);
        chart_sleepTime.setPinchZoom(false);
        chart_sleepTime.setDoubleTapToZoomEnabled(false);
        chart_sleepTime.setHighlightPerDragEnabled(false);
        chart_sleepTime.setHighlightPerTapEnabled(false);
        chart_sleepTime.setDrawGridBackground(false);
        chart_sleepTime.setData(sleepTimeData);
        chart_sleepTime.setFitBars(true);
        chart_sleepTime.getViewPortHandler().setMinMaxScaleX(0, 7);
        chart_sleepTime.getViewPortHandler().setMinMaxScaleY(0, 24);
        chart_sleepTime.invalidate(); // refresh


        //xaxis settings
        XAxis xaxis_sleepTime = chart_sleepTime.getXAxis();
        xaxis_sleepTime.setDrawGridLines(false);
        xaxis_sleepTime.setDrawAxisLine(true);
        xaxis_sleepTime.setTextSize(10f);
        xaxis_sleepTime.setDrawAxisLine(true);
        xaxis_sleepTime.setDrawGridLines(false);
        xaxis_sleepTime.setAxisMaximum(7f);
        // the labels that should be drawn on the XAxis
        final String[] dates = new String[7];
        for (int i = 0; i < 7; i++){
    // i< dailyQuizData.size() is what it really should b
            dates[i] = dailyQuizData.get(i).getDate();
        }
        // this is current NOT WORKING------ see comment inside of getAxisLabel
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return dates[(int) value]; //for some reason, this is using the y value instead of the x value, and that's causing an index out of bounds exception
            }
        };
       // xaxis_sleepTime.setValueFormatter(formatter);
        //do I have to call other things? AxisDependenct.LEFT or RIGHT in order for this to work properly
        YAxis left_axis_sleepTime = chart_sleepTime.getAxisLeft();
        left_axis_sleepTime.setDrawGridLines(false);
        left_axis_sleepTime.setDrawAxisLine(true);
        //left_axis_sleepTime.setDrawAxisLine(false);
        left_axis_sleepTime.setDrawLabels(false);
        left_axis_sleepTime.setAxisMaximum(24f);

        YAxis right_axis_sleepTime = chart_sleepTime.getAxisRight();
        right_axis_sleepTime.setDrawGridLines(false);
        right_axis_sleepTime.setDrawAxisLine(false);
        right_axis_sleepTime.setDrawAxisLine(false);
        right_axis_sleepTime.setDrawLabels(false);
        right_axis_sleepTime.setAxisMaximum(24f);


        //productive time bar chart
        BarChart chart_productiveTime;
        chart_productiveTime = (BarChart) root.findViewById(R.id.chart_productiveTime);
        List<BarEntry> productiveTimeEntries = new ArrayList<>();
        for (int i = 0; i < dailyQuizData.size(); i++){
            productiveTimeEntries.add( new BarEntry(i, dailyQuizData.get(i).getProductiveTime()));
        }
        BarDataSet productiveTimeSet = new BarDataSet(productiveTimeEntries, "Productive Time");
        BarData productiveTimeData = new BarData(productiveTimeSet);
        productiveTimeSet.setColors(new int[] { R.color.scheme_blue_2, R.color.scheme_purple_1, R.color.scheme_purple_2}, this.getContext());
        productiveTimeData.setBarWidth(0.9f);
        //do we need to explicitly set all of these to false, or is it default
        chart_productiveTime.setEnabled(false);
        chart_productiveTime.setTouchEnabled(false);
        chart_productiveTime.setDragEnabled(false);
        chart_productiveTime.setScaleXEnabled(false);
        chart_productiveTime.setScaleYEnabled(false);
        chart_productiveTime.setScaleEnabled(false);
        chart_productiveTime.setPinchZoom(false);
        chart_productiveTime.setDoubleTapToZoomEnabled(false);
        chart_productiveTime.setHighlightPerDragEnabled(false);
        chart_productiveTime.setHighlightPerTapEnabled(false);
        chart_productiveTime.setDrawGridBackground(false);
        chart_productiveTime.setData(productiveTimeData);
        chart_productiveTime.setFitBars(true);
        chart_productiveTime.getViewPortHandler().setMinMaxScaleX(0, 7);
        chart_productiveTime.getViewPortHandler().setMinMaxScaleY(0, 24);
        chart_productiveTime.invalidate(); // refresh
        //xaxis settings
        XAxis xaxis_productiveTime = chart_productiveTime.getXAxis();
        xaxis_productiveTime.setDrawGridLines(false);
        xaxis_productiveTime.setDrawAxisLine(true);
        xaxis_productiveTime.setTextSize(10f);
        xaxis_productiveTime.setDrawAxisLine(true);
        xaxis_productiveTime.setDrawGridLines(false);
        //do I have to call other things? AxisDependenct.LEFT or RIGHT in order for this to work properly
        YAxis left_axis_productiveTime = chart_productiveTime.getAxisLeft();
        left_axis_productiveTime.setDrawGridLines(false);
        left_axis_productiveTime.setDrawAxisLine(true);
        //left_axis_sleepTime.setDrawAxisLine(false);
        left_axis_productiveTime.setDrawLabels(false);
        left_axis_productiveTime.setAxisMaximum(24f);

        YAxis right_axis_productiveTime = chart_productiveTime.getAxisRight();
        right_axis_productiveTime.setDrawGridLines(false);
        right_axis_productiveTime.setDrawAxisLine(false);
        right_axis_productiveTime.setDrawAxisLine(false);
        right_axis_productiveTime.setDrawLabels(false);
        right_axis_productiveTime.setAxisMaximum(24f);
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
        relaxTimeSet.setColors(new int[] {R.color.scheme_purple_1, R.color.scheme_purple_2, R.color.scheme_blue_1}, this.getContext() );
        relaxTimeData.setBarWidth(0.9f);
        //do we need to explicitly set all of these to false, or is it default
        chart_relaxTime.setEnabled(false);
        chart_relaxTime.setTouchEnabled(false);
        chart_relaxTime.setDragEnabled(false);
        chart_relaxTime.setScaleXEnabled(false);
        chart_relaxTime.setScaleYEnabled(false);
        chart_relaxTime.setScaleEnabled(false);
        chart_relaxTime.setPinchZoom(false);
        chart_relaxTime.setDoubleTapToZoomEnabled(false);
        chart_relaxTime.setHighlightPerDragEnabled(false);
        chart_relaxTime.setHighlightPerTapEnabled(false);
        chart_relaxTime.setDrawGridBackground(false);
        chart_relaxTime.setData(relaxTimeData);
        chart_relaxTime.setFitBars(true);
        chart_relaxTime.getViewPortHandler().setMinMaxScaleX(0, 7);
        chart_relaxTime.getViewPortHandler().setMinMaxScaleY(0, 24);
        chart_relaxTime.invalidate(); // refresh
        //xaxis settings
        XAxis xaxis_relaxTime = chart_relaxTime.getXAxis();
        xaxis_relaxTime.setDrawGridLines(false);
        xaxis_relaxTime.setDrawAxisLine(true);
        xaxis_relaxTime.setTextSize(10f);
        xaxis_relaxTime.setDrawAxisLine(true);
        xaxis_relaxTime.setDrawGridLines(false);
        //do I have to call other things? AxisDependenct.LEFT or RIGHT in order for this to work properly
        YAxis left_axis_relaxTime = chart_relaxTime.getAxisLeft();
        left_axis_relaxTime.setDrawGridLines(false);
        left_axis_relaxTime.setDrawAxisLine(true);
        //left_axis_sleepTime.setDrawAxisLine(false);
        left_axis_relaxTime.setDrawLabels(false);
        left_axis_relaxTime.setAxisMaximum(24f);

        YAxis right_axis_relaxTime = chart_relaxTime.getAxisRight();
        right_axis_relaxTime.setDrawGridLines(false);
        right_axis_relaxTime.setDrawAxisLine(false);
        right_axis_relaxTime.setDrawAxisLine(false);
        right_axis_relaxTime.setDrawLabels(false);
        right_axis_relaxTime.setAxisMaximum(24f);
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
        exerciseTimeSet.setColors(new int[] {R.color.scheme_purple_2, R.color.scheme_blue_1, R.color.scheme_blue_2}, this.getContext());
        exerciseTimeData.setBarWidth(0.9f);
        //do we need to explicitly set all of these to false, or is it default
        chart_exerciseTime.setEnabled(false);
        chart_exerciseTime.setTouchEnabled(false);
        chart_exerciseTime.setDragEnabled(false);
        chart_exerciseTime.setScaleXEnabled(false);
        chart_exerciseTime.setScaleYEnabled(false);
        chart_exerciseTime.setScaleEnabled(false);
        chart_exerciseTime.setPinchZoom(false);
        chart_exerciseTime.setDoubleTapToZoomEnabled(false);
        chart_exerciseTime.setHighlightPerDragEnabled(false);
        chart_exerciseTime.setHighlightPerTapEnabled(false);
        chart_exerciseTime.setDrawGridBackground(false);
        chart_exerciseTime.setData(exerciseTimeData);
        chart_exerciseTime.setFitBars(true);
        chart_exerciseTime.getViewPortHandler().setMinMaxScaleX(0, 7);
        chart_exerciseTime.getViewPortHandler().setMinMaxScaleY(0, 24);
        chart_exerciseTime.invalidate(); // refresh
        //xaxis settings
        XAxis xaxis_exerciseTime = chart_exerciseTime.getXAxis();
        xaxis_exerciseTime.setDrawGridLines(false);
        xaxis_exerciseTime.setDrawAxisLine(true);
        xaxis_exerciseTime.setTextSize(10f);
        xaxis_exerciseTime.setDrawAxisLine(true);
        xaxis_exerciseTime.setDrawGridLines(false);
        //do I have to call other things? AxisDependenct.LEFT or RIGHT in order for this to work properly
        YAxis left_axis_exerciseTime = chart_exerciseTime.getAxisLeft();
        left_axis_exerciseTime.setDrawGridLines(false);
        left_axis_exerciseTime.setDrawAxisLine(true);
        //left_axis_sleepTime.setDrawAxisLine(false);
        left_axis_exerciseTime.setDrawLabels(false);
        left_axis_exerciseTime.setAxisMaximum(24f);

        YAxis right_axis_exerciseTime = chart_exerciseTime.getAxisRight();
        right_axis_exerciseTime.setDrawGridLines(false);
        right_axis_exerciseTime.setDrawAxisLine(false);
        right_axis_exerciseTime.setDrawAxisLine(false);
        right_axis_exerciseTime.setDrawLabels(false);
        right_axis_exerciseTime.setAxisMaximum(24f);
        chart_exerciseTime.setData(exerciseTimeData);
        chart_exerciseTime.invalidate(); // refresh

        chart_exerciseTime.setData(exerciseTimeData);
        chart_exerciseTime.invalidate(); // refresh

        //set axis limits
       /* chart_sleepTime.getViewPortHandler().setMinMaxScaleX(0, 10);
        chart_sleepTime.getViewPortHandler().setMinMaxScaleY(0, 20);

        chart_productiveTime.getViewPortHandler().setMinMaxScaleX(0, 10);
        chart_productiveTime.getViewPortHandler().setMinMaxScaleY(0, 20);

        chart_relaxTime.getViewPortHandler().setMinMaxScaleX(0, 10);
        chart_relaxTime.getViewPortHandler().setMinMaxScaleY(0, 20);

        chart_exerciseTime.getViewPortHandler().setMinMaxScaleX(0, 10);
        chart_exerciseTime.getViewPortHandler().setMinMaxScaleY(0, 20);

        */

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

   /*
    Gets frequencies for the Pie Chart for mood and for sleep rating
    */
   private ArrayList<Integer> getFreq(ArrayList<String> list, String option1, String option2, String option3){
        ArrayList<Integer> counter = new ArrayList<>();
        counter.add(0);
        counter.add(0);
        counter.add(0);
        counter.add(0);

        for (int i = 0; i < list.size(); i++){
            if (list.get(i).equals(option1)){
                counter.set(0, counter.get(0)+1);
                Toast.makeText(this.getContext(), "counter.get(0) == " + counter.get(0), Toast.LENGTH_SHORT).show();
            } else if (list.get(i).equals(option2)){
                counter.set(1, counter.get(1)+1);
            } else if (list.get(i).equals(option3)){
                counter.set(2, counter.get(2)+1);
            } else {
               Toast.makeText(this.getContext(), "not valid String", Toast.LENGTH_SHORT).show();
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