package com.example.app.ui.goals_tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.R;

public class GoalsFragment extends Fragment {

    private GoalsViewModel goalsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        goalsViewModel =
                new ViewModelProvider(this).get(GoalsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_goals, container, false);
       // final TextView textView = root.findViewById(R.id.);
        //stressViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
           // @Override
           // public void onChanged(@Nullable String s) {
                //textView.setText(s);
            //}
       // });
        return root;
    }
}