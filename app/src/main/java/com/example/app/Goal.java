package com.example.app;

import java.util.Date;

public class Goal {

    private String goal;
    private String dateCreated;
    private boolean isCompleted;
    private String dateCompleted;

    public Goal(String goal, String dateCreated, boolean isCompleted, String dateCompleted) {
        this.goal = goal;
        this.dateCreated = dateCreated;
        this.dateCompleted = dateCompleted;
    }

}
