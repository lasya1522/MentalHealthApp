package com.example.app;

import java.util.Date;

public class Goal {

    private String goal;
    private Date dateCreated;
    private Date dateCompleted;

    public Goal(String goal, Date dateCreated, Date dateCompleted) {
        this.goal = goal;
        this.dateCreated = dateCreated;
        this.dateCompleted = dateCompleted;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }
}
