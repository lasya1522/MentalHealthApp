package com.example.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//Class where all data will be stored(database) don't know what I'm doing anymore-
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String MORNING_QUIZ_TABLE = "MORNING_QUIZ_TABLE"; //don't totally understand these vars
    public static final String COLUMN_MORNING_CURRENT_MOOD = "MORNING_CURRENT_MOOD";
    public static final String COLUMN_MORNING_STRESS_LEVEL = "MORNING_STRESS_LEVEL";
    public static final String COLUMN_MORNING_DATE = "MORNING_DATE";
    public static final String COLUMN_MORNING_SLEEP_RATING = "MORNING_SLEEP_RATING";
    public static final String COLUMN_MORNING_SLEEP_DURATION = "MORNING_SLEEP_DURATION";
    public static final String COLUMN_ID = "ID";

    public DatabaseHelper(@Nullable Context context) {

        super(context, "Save trial", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE MORNING_QUIZ_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "MORNING_CURRENT_MOOD TEXT, MORNING_STRESS_LEVEL TEXT, "
                + "MORNING_DATE TEXT, MORNING_SLEEP_RATING TEXT, MORNING_SLEEP_DURATION TEXT)";
        //not sure what's going on with the commmas and the + here... look more into SQL statements

        db.execSQL(createTableStatement);
    }

    //called if the database version number changes. It prevents previous user's apps from breaking when you change the database design
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addMorningQuiz(MorningQuiz morningQuiz){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put( COLUMN_MORNING_CURRENT_MOOD, morningQuiz.getCurrentMood());
        cv.put( COLUMN_MORNING_STRESS_LEVEL, morningQuiz.getStressLevel());
        cv.put( COLUMN_MORNING_DATE, morningQuiz.getDate().toString()); //should I keep the toString()?
        cv.put( COLUMN_MORNING_SLEEP_RATING, morningQuiz.getSleepRating());
        cv.put( COLUMN_MORNING_SLEEP_DURATION, morningQuiz.getSleepDuration());

        long insert = db.insert(MORNING_QUIZ_TABLE, null, cv);
        if (insert == -1){
            return false;
        } else {
            return true;
        }
    }


}
