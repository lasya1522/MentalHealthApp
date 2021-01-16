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
    public static final String MORNING_QUIZ_TABLE = "MORNING_QUIZ_TABLE"; //don't totally understand these vars
    public static final String COLUMN_MORNING_CURRENT_MOOD = "MORNING_CURRENT_MOOD";
    public static final String COLUMN_MORNING_STRESS_LEVEL = "MORNING_STRESS_LEVEL";
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

    public DatabaseHelper(@Nullable Context context) {

        super(context, "Save trial", null, 2); //I had to increment the version number in order to get it to work after adding the date column
        //the onUpgrade code won't be called unless I do that
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //database is made up of different tables ??
        String createMorningTableStatement = "CREATE TABLE MORNING_QUIZ_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, MORNING_CURRENT_MOOD TEXT, MORNING_STRESS_LEVEL TEXT, MORNING_DATE TEXT, MORNING_SLEEP_RATING TEXT, MORNING_SLEEP_DURATION TEXT)";
        //not sure what's going on with the commas and the + here... look more into SQL statements

        String createNightTableStatement = "CREATE TABLE MORNING_QUIZ_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, NIGHT_CURRENT_MOOD TEXT, NIGHT_STRESS_LEVEL TEXT, NIGHT_DATE TEXT, NIGHT_PRODUCTIVE_TIME TEXT, NIGHT_RELAX_TIME TEXT)";

        db.execSQL(createMorningTableStatement);
        db.execSQL(createNightTableStatement);
    }

    //called if the database version number changes. It prevents previous user's apps from breaking when you change the database design
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion){
            db.execSQL("ALTER TABLE MORNING_QUIZ_TABLE ADD COLUMN MORNING_DATE INTEGER DEFAULT 0");
        }

    }

    public boolean addMorningQuiz(MorningQuiz morningQuiz){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put( COLUMN_MORNING_CURRENT_MOOD, morningQuiz.getCurrentMood());
        cv.put( COLUMN_MORNING_STRESS_LEVEL, morningQuiz.getStressLevel());
        cv.put( COLUMN_MORNING_DATE, morningQuiz.getDate().toString()); //should I keep the toString()?
        cv.put( COLUMN_MORNING_SLEEP_RATING, morningQuiz.getSleepRating());
        cv.put( COLUMN_MORNING_SLEEP_DURATION, morningQuiz.getSleepDuration());

        //tells me whether the item was put into the database successfully
        long insert = db.insert(MORNING_QUIZ_TABLE, null, cv);
        if (insert == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean addNightQuiz(NightQuiz nightQuiz){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put( COLUMN_NIGHT_CURRENT_MOOD, nightQuiz.getCurrentMood());
        cv.put( COLUMN_NIGHT_STRESS_LEVEL, nightQuiz.getStressLevel());
        cv.put( COLUMN_NIGHT_DATE, nightQuiz.getDate().toString()); //should I keep the toString()?
        cv.put( COLUMN_NIGHT_RELAX_TIME, nightQuiz.getRelaxTime());
        cv.put( COLUMN_NIGHT_PRODUCTIVE_TIME, nightQuiz.getProductiveTime());

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


}
