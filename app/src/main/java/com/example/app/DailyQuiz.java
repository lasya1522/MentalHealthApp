package com.example.app;

import java.util.Date;

public class DailyQuiz {

    private Date date;
    private String mood;
    private Integer sleepTime;
    private String sleepRating;
    private Integer productiveTime;
    private Integer relaxTime;
    private Integer exerciseTime;
    private String stressLevel;
    private String stressors;
    private String other;


    public DailyQuiz(Date date, String mood, Integer sleepTime, String sleepRating, Integer productiveTime, Integer relaxTime, Integer exerciseTime, String stressLevel, String stressors, String other) {
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

    public Integer getSleepTime() {
        return sleepTime;
    }

    public String getSleepRating() {
        return sleepRating;
    }

    public Integer getProductiveTime() {
        return productiveTime;
    }

    public Integer getRelaxTime() {
        return relaxTime;
    }

    public Integer getExerciseTime() {
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

    public void setSleepTime(Integer sleepTime) {
        this.sleepTime = sleepTime;
    }

    public void setSleepRating(String sleepRating) {
        this.sleepRating = sleepRating;
    }

    public void setProductiveTime(Integer productiveTime) {
        this.productiveTime = productiveTime;
    }

    public void setRelaxTime(Integer relaxTime) {
        this.relaxTime = relaxTime;
    }

    public void setExerciseTime(Integer exerciseTime) {
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

    @Override
    public String toString() {
        return "DailyQuiz{" +
                "date=" + date +
                ", mood='" + mood + '\'' +
                ", sleepTime=" + sleepTime +
                ", sleepRating='" + sleepRating + '\'' +
                ", productiveTime=" + productiveTime +
                ", relaxTime=" + relaxTime +
                ", exerciseTime=" + exerciseTime +
                ", stressLevel='" + stressLevel + '\'' +
                ", stressors='" + stressors + '\'' +
                ", other='" + other + '\'' +
                '}';
    }
}
