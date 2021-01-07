package com.example.galgeleg.GalgeLogik;

public class HighScore {
    private String ordet;
    private String score;

    public HighScore(String ordet, String score) {
        this.ordet = ordet;
        this.score = score;
    }

    public String getOrdet() {
        return ordet;
    }

    public String getScore() {
        return score;
    }
}
