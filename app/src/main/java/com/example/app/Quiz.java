package com.example.app;

import java.util.Date;

public class Quiz {

    private Date date;
    private String mood;
    private String sleepTime;
    private String sleepRating;
    private String productiveTime;
    private String relaxTime;
    private String exerciseTime;
    private String stressLevel;
    private String stressors;
    private String other;


    public Quiz(Date date, String mood, String sleepTime, String sleepRating, String productiveTime, String relaxTime, String exerciseTime, String stressLevel, String stressors, String other) {
        this.date = date;
        this.mood = mood;
        this.sleepTime = sleepTime;
        this.sleepRating = sleepRating;
        this.productiveTime = productiveTime;
        this.relaxTime = relaxTime;
        this.exerciseTime = exerciseTime;
        this.stressLevel = stressLevel;
        this.stressors = stressors;
        this.other = other;
    }

    public Date getDate() {
        return date;
    }

    public String getMood() {
        return mood;
    }

    public String getSleepTime() {
        return sleepTime;
    }

    public String getSleepRating() {
        return sleepRating;
    }

    public String getProductiveTime() {
        return productiveTime;
    }

    public String getRelaxTime() {
        return relaxTime;
    }

    public String getExerciseTime() {
        return exerciseTime;
    }

    public String getStressLevel() {
        return stressLevel;
    }

    public String getStressors() {
        return stressors;
    }

    public String getOther() {
        return other;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public void setSleepTime(String sleepTime) {
        this.sleepTime = sleepTime;
    }

    public void setSleepRating(String sleepRating) {
        this.sleepRating = sleepRating;
    }

    public void setProductiveTime(String productiveTime) {
        this.productiveTime = productiveTime;
    }

    public void setRelaxTime(String relaxTime) {
        this.relaxTime = relaxTime;
    }

    public void setExerciseTime(String exerciseTime) {
        this.exerciseTime = exerciseTime;
    }

    public void setStressLevel(String stressLevel) {
        this.stressLevel = stressLevel;
    }

    public void setStressors(String stressors) {
        this.stressors = stressors;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
