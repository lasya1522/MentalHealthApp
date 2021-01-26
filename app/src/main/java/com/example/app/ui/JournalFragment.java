package com.example.app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
//import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.R;
import java.util.Arrays;
import java.util.ArrayList;

public class JournalFragment extends Fragment {

    private JournalViewModel journalViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        journalViewModel =
                new ViewModelProvider(this).get(JournalViewModel.class);
        View root = inflater.inflate(R.layout.fragment_journal, container, false);
      //  final TextView textView = root.findViewById(R.id.test_text);
        //dailyViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            //@Override
            //public void onChanged(@Nullable String s) {
               // textView.setText(s);
           // }
        //});

        return root;
    }

    // range menthod
    public static int returnRange(int[] array)
    {
        // Our arr contains 8 elements

        Arrays.sort(array);
        int max = array[array.length-1];
        int min = array[0];

        return max-min;
    }
}