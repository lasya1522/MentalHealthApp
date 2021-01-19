package com.example.app;

import java.util.Date;

public class NightQuiz extends Quiz{
    private String productiveTime;
    private String relaxTime;
    private String exerciseTime;
    private Integer stressLevel;

    public NightQuiz(Integer currentMood, Integer stressLevel, Date date, String productiveTime, String relaxTime, String exerciseTime) {
        super(currentMood, date);
        this.productiveTime = productiveTime;
        this.relaxTime = relaxTime;
        this.exerciseTime = exerciseTime;
        this.stressLevel = stressLevel;
    }

    @Override
    public String toString() {
        return "NightQuiz{" +
                "productiveTime=" + productiveTime +
                ", relaxTime=" + relaxTime +
                ", exerciseTime=" + exerciseTime +
                 ", date=" + date.toString() +
                ", currentMood=" + currentMood +
                ", stressLevel=" + stressLevel +
                '}' ;
    }

    public String getProductiveTime() { return productiveTime; }
    public String getRelaxTime() { return relaxTime; }
    public String getExerciseTime(){return exerciseTime; }
    public Integer getCurrentMood(){
        return currentMood;
    }
    public Integer getStressLevel(){
        return stressLevel;
    }
    public Date getDate(){
        return date;
    }
}
