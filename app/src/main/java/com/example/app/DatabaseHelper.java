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

    //are for the sql commands ??
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

   /* public static final String COLUMN_MORNING_CURRENT_MOOD = "MORNING_CURRENT_MOOD";
    public static final String COLUMN_MORNING_DATE = "MORNING_DATE";
    public static final String COLUMN_MORNING_SLEEP_RATING = "MORNING_SLEEP_RATING";
    public static final String COLUMN_MORNING_SLEEP_DURATION = "MORNING_SLEEP_DURATION";
    public static final String COLUMN_ID = "ID";

    public static final String NIGHT_QUIZ_TABLE = "NIGHT_QUIZ_TABLE";
    public static final String COLUMN_NIGHT_CURRENT_MOOD = "NIGHT_CURRENT_MOOD";
    public static final String COLUMN_NIGHT_STRESS_LEVEL = "NIGHT_STRESS_LEVEL";
    public static final String COLUMN_NIGHT_DATE = "NIGHT_DATE";
    public static final String COLUMN_NIGHT_PRODUCTIVE_TIME = "NIGHT_PRODUCTIVE_TIME";
    public static final String COLUMN_NIGHT_RELAX_TIME = "NIGHT_RELAX_TIME";
    public static final String COLUMN_NIGHT_EXERCISE_TIME = "NIGHT_EXERCISE_TIME";

    */

    public DatabaseHelper(@Nullable Context context) {

        super(context, "Save trial", null, 5); //I had to increment the version number in order to get it to work after adding the date column
        //the onUpgrade code won't be called unless I do that
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //get rid of the +, combine into one long string
        String createDailyQuizTableStatement = "CREATE TABLE DAILY_QUIZ_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, MOOD TEXT, " + "DATE TEXT, SLEEP_TIME TEXT, SLEEP_RATING TEXT, PRODUCTIVE_TIME TEXT, RELAX_TIME TEXT, " + "EXERCISE_TIME TEXT, STRESS_LEVEL TEXT, STRESSORS TEXT, OTHER TEXT )";

        db.execSQL(createDailyQuizTableStatement);
        //database is made up of different tables ?? //edit: yes, i think so
       // String createMorningTableStatement = "CREATE TABLE MORNING_QUIZ_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, MORNING_CURRENT_MOOD TEXT, MORNING_DATE TEXT, MORNING_SLEEP_RATING TEXT, MORNING_SLEEP_DURATION TEXT)";

        //String createNightTableStatement = "CREATE TABLE NIGHT_QUIZ_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, NIGHT_CURRENT_MOOD TEXT, NIGHT_STRESS_LEVEL TEXT, NIGHT_DATE TEXT, NIGHT_PRODUCTIVE_TIME TEXT, NIGHT_RELAX_TIME TEXT, NIGHT_EXERCISE_TIME TEXT)";

        //db.execSQL(createMorningTableStatement);
        //db.execSQL(createNightTableStatement);
    }

    //called if the database version number changes. It prevents previous user's apps from breaking when you change the database design
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*if (newVersion > oldVersion){
           //db.execSQL("ALTER TABLE MORNING_QUIZ_TABLE ADD COLUMN MORNING_DATE STRING DEFAULT 0");

            String createNightTableStatement = "CREATE TABLE NIGHT_QUIZ_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, NIGHT_CURRENT_MOOD TEXT, NIGHT_STRESS_LEVEL TEXT, NIGHT_DATE TEXT, NIGHT_PRODUCTIVE_TIME TEXT, NIGHT_RELAX_TIME TEXT, NIGHT_EXERCISE_TIME TEXT)";
            db.execSQL(createNightTableStatement);
        }

         */

    }

    public boolean addDailyQuiz(DailyQuiz dailyQuiz){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_MOOD, dailyQuiz.getMood());
        cv.put(COLUMN_DATE, dailyQuiz.getDate().toString()); //should I keep the toString()? No, I don't want to keep the toString(). How to store as type Date?????
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

   /* public boolean addNightQuiz(NightQuiz nightQuiz){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put( COLUMN_NIGHT_CURRENT_MOOD, nightQuiz.getCurrentMood());
        cv.put( COLUMN_NIGHT_DATE, nightQuiz.getDate().toString()); //should I keep the toString()?
        cv.put( COLUMN_NIGHT_RELAX_TIME, nightQuiz.getRelaxTime());
        cv.put( COLUMN_NIGHT_PRODUCTIVE_TIME, nightQuiz.getProductiveTime());
        cv.put( COLUMN_NIGHT_EXERCISE_TIME, nightQuiz.getExerciseTime());

        //tells me whether the item was put into the database successfully
        long insert = db.insert(NIGHT_QUIZ_TABLE, null, cv);
        if (insert == -1){
            return false;
        } else {
            return true;
        }

    }

    public List<MorningQuiz> getMorningQuizData () {
        List<MorningQuiz> returnList = new ArrayList<MorningQuiz>();
        String queryString = "SELECT * FROM " + MORNING_QUIZ_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            // loop through the cursor (result set) and create new morningQuiz objects. Put them into the return list
            do {
                int morningQuizID = cursor.getInt(0);
                String morning = cursor.getString(1);
                int customerAge = cursor.getInt(2);
                boolean customerActive = cursor.getInt(3) == 1 ? true: false;

                //CustomerModel newCustomer = new CustomerModel(customerID, customerName, customerAge, customerActive );
                //returnList.add(newCustomer);
            } while(cursor.moveToNext());

        } else {
            //failure. do not add anything to the list

        }
        return returnList;
    }

    */
}
