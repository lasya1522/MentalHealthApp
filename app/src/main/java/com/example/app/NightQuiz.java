package com.example.app;

import java.util.Date;

public class NightQuiz extends Quiz{
    private Integer productiveTime;
    private Integer relaxTime;

    public NightQuiz(Integer currentMood, Integer stressLevel, Date date, Integer productiveTime, Integer relaxTime, Integer studyTime) {
        super(currentMood, stressLevel, date);
        this.productiveTime = productiveTime;
        this.relaxTime = relaxTime;
    }

    @Override
    public String toString() {
        return "NightQuiz{" +
                "productiveTime=" + productiveTime +
                ", relaxTime=" + relaxTime +
                 ", date=" + date.toString() +
                ", currentMood=" + currentMood +
                ", stressLevel=" + stressLevel +
                '}';
    }

    public Integer getProductiveTime() { return productiveTime; }
    public Integer getRelaxTime() { return relaxTime; }
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
