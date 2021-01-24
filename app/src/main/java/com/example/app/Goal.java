package com.example.app;

import java.util.Date;

public class Goal {

    private String goalText;
    private String dateCreated;
    //private boolean isCompleted; //don't need this bc dateCompleted will be null if it isn't completed
    private String dateCompleted;

    public Goal(String goalText, String dateCreated,String dateCompleted) {
        this.goalText = goalText;
        this.dateCreated = dateCreated;
      //  this.isCompleted = isCompleted;
        this.dateCompleted = dateCompleted;
    }

    public String getGoalText() {
        return goalText;
    }

    public void setGoalText(String goalText) {
        this.goalText = goalText;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(String dateCompleted) {
        this.dateCompleted = dateCompleted;
    }
}
