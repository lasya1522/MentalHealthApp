package com.example.app;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.List;


public class PastQuizzesActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private Button dateBtn;

    ListView lv_quizData; //change to recyclerView???????
    Button btn_viewAll;
    ArrayAdapter dailyQuizArrayAdapter; //ARE THESE SUPPOSED TO BE PRIVATE?

    //revise code and var names so it isn't copying off the tutorial????????????? ********* very important************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_quizzes);
        initDatePicker();
        dateBtn = findViewById(R.id.datePickerBtn);
        dateBtn.setText(getTodayDate());

        //lv_quizData = findViewById(R.id.lv_quizData);
        btn_viewAll = findViewById(R.id.btn_viewAll);

        btn_viewAll.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DatabaseHelper databaseHelper = new DatabaseHelper(PastQuizzesActivity.this);
            List<DailyQuiz> everyone = databaseHelper.getDailyQuizData();
            dailyQuizArrayAdapter = new ArrayAdapter<DailyQuiz>(PastQuizzesActivity.this, android.R.layout.simple_selectable_list_item, everyone);
            lv_quizData.setAdapter(dailyQuizArrayAdapter);
        }
    });
    }

    private String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int year = cal.get(Calendar.YEAR);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = makeDateString(dayOfMonth, month, year);
                dateBtn.setText(date);

            }
        };
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int dayOfMonth, int month, int year) {
        return getMonthFormat(month)+ "/" + dayOfMonth + "/" + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "Jan";
        else if(month ==2)
            return "Feb";
        else if(month == 3)
            return "Mar";
        else if(month == 4)
            return "Apr";
        else if(month == 5)
            return "May";
        else if(month == 6)
            return "June";
        else if(month == 7)
            return "July";
        else if(month == 8)
            return "Aug";
        else if(month == 9)
            return "Sept";
        else if(month == 10)
            return "Oct";
        else if(month == 11)
            return "Nov";
        else if(month == 12)
            return "Dec";
        return "Jan";
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }
}
