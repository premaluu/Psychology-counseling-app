package com.example.psychologycounselingapp;

public class Result {
    private String test_age;
    private String test_score;
    private String user_mobile;
    private int progress_result;

    public int getProgress_result() {
        return progress_result;
    }

    public void setProgress_result(int progress_result) {
        this.progress_result = progress_result;
    }

    public Result(String test_age, String test_score, String user_mobile, int progress_result) {
        this.test_age = test_age;
        this.test_score = test_score;
        this.user_mobile = user_mobile;
        this.progress_result = progress_result;
    }

    public String getTest_age() {
        return test_age;
    }

    public void setTest_age(String test_age) {
        this.test_age = test_age;
    }

    public String getTest_score() {
        return test_score;
    }

    public void setTest_score(String test_score) {
        this.test_score = test_score;
    }

    public String getUser_mobile() {
        return user_mobile;
    }

    public void setUser_mobile(String user_mobile) {
        this.user_mobile = user_mobile;
    }
}
