package com.example.app.ui.home_tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.DailyQuizActivity;
import com.example.app.PastQuizzesActivity;
import com.example.app.R;

//import com.example.app.DailyQuizActivity;
//import com.example.app.ui.DailyQuizzesActivity;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button button; //Daily quiz button
    Button btn_viewPastQuizzes; //Previous quiz button
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
            homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        Button button = (Button) root.findViewById(R.id.viewQuiz);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(this.getContext(), )

                Intent intent = new Intent(getActivity(), DailyQuizActivity.class);
                startActivity(intent);
            }
        });

        //past quizzes btn
        btn_viewPastQuizzes = root.findViewById(R.id.btn_viewPastQuizzes);
        btn_viewPastQuizzes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PastQuizzesActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}