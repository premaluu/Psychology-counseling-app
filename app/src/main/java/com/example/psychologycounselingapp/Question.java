package com.example.psychologycounselingapp;

public class Question {
    private String qustion;
    private boolean answer;
    private int value;
    private int days;
    public Question(String qustion, boolean answer, int value, int days) {
        this.qustion = qustion;
        this.answer = answer;
        this.value = value;
        this.days = days;
    }

    public String getQustion() {
        return qustion;
    }

    public void setQustion(String qustion) {
        this.qustion = qustion;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public void setValue(int value) { this.value = value; }

    public int getValue() { return this.value; }

    public void setDays(int days) { this.days = days; }

    public int getDays() { return this.days; }
}
