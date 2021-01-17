package com.example.app;

import java.util.Date;

public class NightQuiz extends Quiz{
    private Integer productiveTime;
    private Integer relaxTime;
    private Integer exerciseTime;

    public NightQuiz(Integer currentMood,  Date date, Integer productiveTime, Integer relaxTime, Integer studyTime) {
        super(currentMood, date);
        this.productiveTime = productiveTime;
        this.relaxTime = relaxTime;
        this.exerciseTime = exerciseTime;
    }

    @Override
    public String toString() {
        return "NightQuiz{" +
                "productiveTime=" + productiveTime +
                ", relaxTime=" + relaxTime +
                ", exerciseTime=" + exerciseTime +
                 ", date=" + date.toString() +
                ", currentMood=" + currentMood +
                '}';
    }

    public Integer getProductiveTime() { return productiveTime; }
    public Integer getRelaxTime() { return relaxTime; }
    public Integer getExerciseTime(){return exerciseTime; }
    public Integer getCurrentMood(){
        return currentMood;
    }
    public Date getDate(){
        return date;
    }
}
