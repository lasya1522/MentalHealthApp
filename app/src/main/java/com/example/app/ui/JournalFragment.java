package com.example.app.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
//import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.DatabaseHelper;
import com.example.app.Goal;
import com.example.app.JournalEntry;
import com.example.app.R;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class JournalFragment extends Fragment {

    private JournalViewModel journalViewModel;
    Button btn_save;
    EditText et_journal;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        journalViewModel =
                new ViewModelProvider(this).get(JournalViewModel.class);
        View root = inflater.inflate(R.layout.fragment_journal, container, false);
        btn_save = root.findViewById(R.id.btn_save);
        et_journal = root.findViewById(R.id.et_journal);

        btn_save.setOnClickListener(v ->{
            Toast.makeText(this.getContext(), "hi", Toast.LENGTH_SHORT).show();
            
            try {

                if (et_journal.getText().toString().trim().equals("")){
                    Toast.makeText(this.getContext(), "must specify input", Toast.LENGTH_SHORT).show();
                } else {
                    String date = getTodayDate();
                    String entry = et_journal.getText().toString();
                    //technically, will we need to change this code because I basically copied it off a tutorial?
                    JournalEntry journalEntry = new JournalEntry(entry, date);
                    Toast.makeText(this.getContext(), journalEntry.toString(), Toast.LENGTH_SHORT).show();
                    DatabaseHelper databaseHelper = new DatabaseHelper(this.getContext());
                    boolean success = databaseHelper.addJournalEntry(journalEntry);
                    Toast.makeText(this.getContext(), "success " + success, Toast.LENGTH_SHORT).show();
                    et_journal.setText("");
                }

            } catch (Exception e) {
                Toast.makeText(this.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }

    private String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int year = cal.get(Calendar.YEAR);
        return makeDateString(day, month, year);
    }

    private String makeDateString(int dayOfMonth, int month, int year) {
        return (month + "-" + dayOfMonth + "-" + year);
    }
}