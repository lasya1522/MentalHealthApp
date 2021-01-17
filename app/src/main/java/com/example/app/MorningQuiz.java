package com.example.app;

import java.util.Date;

public class MorningQuiz extends Quiz {

    private Integer sleepRating;
    private Integer sleepDuration;

    public MorningQuiz(Integer currentMood, Date date, Integer sleepRating, Integer sleepDuration) {
        super(currentMood, date);
        this.sleepRating = sleepRating;
        this.sleepDuration = sleepDuration;
    }


    @Override
    public String toString() {
        return "MorningQuiz{" +
                "sleepRating=" + sleepRating +
                ", sleepDuration=" + sleepDuration +
                ", date=" + date.toString() +
                ", currentMood=" + currentMood +
                '}';
    }

    //getters & setters
    public void setSleepRating(Integer sleepRating) {
        this.sleepRating = sleepRating;
    }

    public void setSleepDuration(Integer sleepDuration) {
        this.sleepDuration = sleepDuration;
    }

    public void setCurrentMood(Integer currentMood) {
        this.currentMood = currentMood;
    }

    public Integer getSleepRating() {
        return sleepRating;
    }

    public Integer getSleepDuration() {
        return sleepDuration;
    }

    public Integer getCurrentMood(){
        return currentMood;
    }
    public Date getDate(){
        return date;
    }

}
