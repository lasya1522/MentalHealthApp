package com.example.app;

public class JournalEntry {
    String date;
    String text;

    public JournalEntry(String date, String text) {
        this.date = date;
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "JournalEntry{" +
                "date='" + date + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
