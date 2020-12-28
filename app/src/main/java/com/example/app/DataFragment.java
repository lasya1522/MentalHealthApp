package com.example.app;

import android.graphics.DashPathEffect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
//import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.model.GradientColor;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;
import java.util.List;

public class DataFragment extends Fragment {

    private DataViewModel dataViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dataViewModel =
                new ViewModelProvider(this).get(DataViewModel.class);
        View root = inflater.inflate(R.layout.fragment_data, container, false);
        final TextView textView = root.findViewById(R.id.test_text);
        /*dataViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

         */
        PieChart pieChart;
        pieChart = (PieChart) root.findViewById(R.id.chart);

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(2));
        entries.add(new PieEntry(3));
        entries.add(new PieEntry(2));

        PieDataSet pieDataSet = new PieDataSet(entries, "pie chart");
        PieData data = new PieData(pieDataSet);
        pieChart.setData(data);


        return root;
    }
}
