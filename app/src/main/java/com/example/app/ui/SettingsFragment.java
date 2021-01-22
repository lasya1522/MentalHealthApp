package com.example.app.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
//import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.DatabaseHelper;
import com.example.app.MainActivity;
import com.example.app.R;
import com.example.app.ReminderBroadcast;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import static androidx.core.content.ContextCompat.getSystemService;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;
    private Button btn_clearData; // should the variables be private or not????????????
    DatabaseHelper databaseHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        btn_clearData = root.findViewById(R.id.btn_clearData);
        databaseHelper = new DatabaseHelper(this.getContext()); // does this.getContext() work?

        btn_clearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteAll();
            }
        });

        //-------------------------------------------
        //Beginning of notification section
        //-------------------------------------------
        //new class goes with this - ReminderBroadcast.java
        //pretty sure that class is fine, its this place that has issues
        //-------------------------------------------
        // reminder button
        createNotificationChannel();

        //find button to use. btn_remind is on the settings page
        Button button_remind = root.findViewById(R.id.btn_remind);

        //button can be found on settings page - fragment_settings.xml

        //this is where the error is. he uses this weird v-> thing which i guess is like a method
        button_remind.setOnClickListener(v -> { //what is this doing???? why is ther v-> iinsteadof new View.OnClickListener? code consistency
            //toast says that user clicked the button.
            Toast.makeText(this.getContext(), "Reminder in one hour set!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this.getContext(), ReminderBroadcast.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getContext(), 0, intent, 0);

            //get alarm manager
            Context context = this.getContext(); // idk if this is best practice; i created this variable myself
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            long timeAtButtonClick = System.currentTimeMillis();
            // This is where user defines time. Right now its at 10 seconds for debugging reasons.
            //We could later have a user input field and take that information and use it for custom time here.
            long tenSecondsInMillis = 1000+5;

            alarmManager.set(AlarmManager.RTC_WAKEUP,
                    timeAtButtonClick + tenSecondsInMillis, pendingIntent);
        });

        return root;

    }

    //compatibility method for lower java versions
    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Willow";
            String description = "An hour ago you wanted a reminder to take your daily quiz!";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyUser", name, importance);
            channel.setDescription(description);
            Context context = this.getContext(); // idk if this is best practice; i created this variable myself
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }


}
