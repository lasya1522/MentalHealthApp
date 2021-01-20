package com.example.app.ui.trends_tab;

public class SleepData {
    //private instance variables
    private String affectSleep;
    private String goalsSleep;

    //constructor
    public SleepData(String affectSleep, String goalsSleep){
        this.affectSleep = affectSleep;
        this.goalsSleep = goalsSleep;
    }

    //getters and setters
    public String getAffectSleep(){
        return affectSleep;
    }

    public void setAffectSleep(String affectSleep) {
        this.affectSleep = affectSleep;
    }

    public String getGoalsSleep(){
        return goalsSleep;
    }
    public void setGoalsSleep(){
        this.goalsSleep = goalsSleep;
    }

    //tostring methods
    @Override
    public String toString() {
        return "SleepData{" +
                "affectSleep='" + affectSleep + '\'' +
                ", goalsSleep='" + goalsSleep + '\'' +
                '}';
    }
}
