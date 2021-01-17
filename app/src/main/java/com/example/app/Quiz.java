package com.example.app;

import java.util.Date;

public class Quiz {
    public Integer currentMood; // should these be public? i'm a little fuzzy on inheritance
    public Date date;

    public Quiz(Integer currentMood, Date date) {
        this.currentMood = currentMood;
        this.date = date;
    }
}
