package com.example.app;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    private Button button; //Daily quiz button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_trends, R.id.navigation_goals, R.id.navigation_strategies, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        //-------------------------------------------
        //Beginning of notification section
        //-------------------------------------------
        //new class goes with this - ReminderBroadcast.java
        //pretty sure that class is fine, its this place that has issues
        //-------------------------------------------
        // reminder button
        createNotificationChannel();

        //find button to use. btn_remind is on the settings page
        Button button_remind = findViewById(R.id.btn_remind);

        //button can be found on settings page - fragment_settings.xml

        //this is where the error is. he uses this weird v-> thing which i guess is like a method
        button_remind.setOnClickListener(v -> {
            //toast says that user clicked the button.
            Toast.makeText(this, "Reminder in one hour set!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, ReminderBroadcast.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

            //get alarm manager
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

            long timeAtButtonClick = System.currentTimeMillis();
            // This is where user defines time. Right now its at 10 seconds for debugging reasons.
            //We could later have a user input field and take that information and use it for custom time here.
            long tenSecondsInMillis = 1000 * 10;

            alarmManager.set(AlarmManager.RTC_WAKEUP,
                    timeAtButtonClick + tenSecondsInMillis, pendingIntent);
        });


    }
    //compatibility method for lower java versions
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Willow";
            String description = "An hour ago you wanted a reminder to take your daily quiz!";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyUser", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}

