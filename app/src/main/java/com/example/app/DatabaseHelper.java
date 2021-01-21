package com.example.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    //??: are for the sql commands ??

    //Emily: yes but why declare redundant strings?
    public static final String DAILY_QUIZ_TABLE = "DAILY_QUIZ_TABLE";
    public static final String COLUMN_MOOD = "MOOD";
    public static final String COLUMN_DATE = "DATE";
    public static final String COLUMN_SLEEP_TIME = "SLEEP_TIME";
    public static final String COLUMN_SLEEP_RATING = "SLEEP_RATING";
    public static final String COLUMN_PRODUCTIVE_TIME = "PRODUCTIVE_TIME";
    public static final String COLUMN_RELAX_TIME= "RELAX_TIME";
    public static final String COLUMN_EXERCISE_TIME = "EXERCISE_TIME";
    public static final String COLUMN_STRESS_LEVEL= "STRESS_LEVEL";
    public static final String COLUMN_STRESSORS = "STRESSORS";
    public static final String COLUMN_OTHER = "OTHER";//don't totally understand these vars


    //IS THERE A WAY TO SET THIS UP SO I DONT HAVE TO RECREATE THE THING EVERY TIME

    public DatabaseHelper(@Nullable Context context) {

        super(context, "Save trial", null, 6); //I had to increment the version number in order to get it to work after adding the date column
        //the onUpgrade code won't be called unless I do that
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //how do I delete our old test data?

        String createDailyQuizTableStatement = "CREATE TABLE DAILY_QUIZ_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, MOOD TEXT, SLEEP_TIME TEXT, SLEEP_RATING TEXT, PRODUCTIVE_TIME TEXT, RELAX_TIME TEXT, " + "EXERCISE_TIME TEXT, STRESS_LEVEL TEXT, STRESSORS TEXT, OTHER TEXT )";

        db.execSQL(createDailyQuizTableStatement);

    }

    //called if the database version number changes. It prevents previous user's apps from breaking when you change the database design
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String createDailyQuizTableStatement = "CREATE TABLE DAILY_QUIZ_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT, MOOD TEXT, SLEEP_TIME TEXT, SLEEP_RATING TEXT, PRODUCTIVE_TIME TEXT, RELAX_TIME TEXT, " + "EXERCISE_TIME TEXT, STRESS_LEVEL TEXT, STRESSORS TEXT, OTHER TEXT )";

        db.execSQL(createDailyQuizTableStatement);

    }

    public boolean addDailyQuiz(DailyQuiz dailyQuiz){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DATE, dailyQuiz.getDate());
        cv.put(COLUMN_MOOD, dailyQuiz.getMood());
        cv.put(COLUMN_SLEEP_TIME, dailyQuiz.getSleepTime());
        cv.put(COLUMN_SLEEP_RATING, dailyQuiz.getSleepRating());
        cv.put(COLUMN_PRODUCTIVE_TIME, dailyQuiz.getProductiveTime());
        cv.put(COLUMN_RELAX_TIME, dailyQuiz.getRelaxTime());
        cv.put(COLUMN_EXERCISE_TIME, dailyQuiz.getExerciseTime());
        cv.put(COLUMN_STRESS_LEVEL, dailyQuiz.getStressLevel());
        cv.put(COLUMN_STRESSORS, dailyQuiz.getStressors());
        cv.put(COLUMN_OTHER, dailyQuiz.getOther());

        //tells me whether the item was put into the database successfully
        long insert = db.insert(DAILY_QUIZ_TABLE, null, cv);
        if (insert == -1){
            return false;
        } else {
            return true;
        }
    }

    public List<DailyQuiz> getDailyQuizData () {
        List<DailyQuiz> returnList = new ArrayList<DailyQuiz>();
        String queryString = "SELECT * FROM " + DAILY_QUIZ_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            // loop through the cursor (result set) and create new DailyQuiz objects. Put them into the return list
            do {
                //should i change the order of the stuff because the date is second and that's kind of annoying? should I make it second
                String dailyQuizDate = cursor.getString(1);
                String dailyQuizMood = cursor.getString(2);
                int dailyQuizSleepTime = cursor.getInt(3);
                String dailyQuizSleepRating = cursor.getString(4);
                int dailyQuizProductiveTime = cursor.getInt(5);
                int dailyQuizRelaxTime = cursor.getInt(6);
                int dailyQuizExerciseTime = cursor.getInt(7);
                String dailyQuizStressLevel = cursor.getString(8);
                String dailyQuizStressors = cursor.getString(9);
                String dailyQuizOther = cursor.getString(10);

                DailyQuiz quiz = new DailyQuiz(dailyQuizDate, dailyQuizMood, dailyQuizSleepTime, dailyQuizSleepRating, dailyQuizProductiveTime, dailyQuizRelaxTime, dailyQuizExerciseTime, dailyQuizStressLevel, dailyQuizStressors, dailyQuizOther  );
                returnList.add(quiz);
            } while(cursor.moveToNext());

        } else {
            //failure. do not add anything to the list

        }
        return returnList;
    }
}
