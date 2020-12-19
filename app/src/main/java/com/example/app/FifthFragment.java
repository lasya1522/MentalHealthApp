package com.example.app;

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

public class FifthFragment extends Fragment {

    private FifthViewModel fifthViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        fifthViewModel =
                new ViewModelProvider(this).get(FifthViewModel.class);
        View root = inflater.inflate(R.layout.fragment_fifth, container, false);
        final TextView textView = root.findViewById(R.id.test_text);
        fifthViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
