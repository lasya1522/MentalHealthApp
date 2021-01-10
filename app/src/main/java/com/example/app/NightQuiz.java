package com.example.app;

import java.util.Date;

public class NightQuiz extends Quiz{
    private Integer productiveTime;
    private Integer relaxTime;
    private Integer studyTime;

    public NightQuiz(Integer currentMood, Integer stressLevel, Date date, Integer productiveTime, Integer relaxTime, Integer studyTime) {
        super(currentMood, stressLevel, date);
        this.productiveTime = productiveTime;
        this.relaxTime = relaxTime;
        this.studyTime = studyTime;
    }

    /*public NightQuiz(Integer productiveTime, Integer relaxTime, Integer studyTime) {
        this.productiveTime = productiveTime;
        this.relaxTime = relaxTime;
        this.studyTime = studyTime;
    }
     */

}
