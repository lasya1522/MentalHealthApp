package com.example.app.ui.trends_tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.R;

public class TrendsFragment extends Fragment {
    private TrendsViewModel trendsViewModel;
    //create references to buttons
    Button submitBtn;
    Button saveBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        trendsViewModel = new ViewModelProvider(this).get(TrendsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_trends, container, false);
        final TextView textView = root.findViewById(R.id.trendTitle);
        //code for buttons
        submitBtn = (Button) root.findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                SleepData sleepData2 = new SleepData("Homework", "Sleep for 8 hours tonight");
                //Toast.makeText(SleepFragment.this, "Submit", Toast.LENGTH_SHORT).show();
                //find out where and why use Toast(to confirm that you have added some respond
                // which we have to further discuss and implement into our design
            }
        });
        saveBtn = (Button) root.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        trendsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    }