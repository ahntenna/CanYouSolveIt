package com.ahn.cysi.canyousolveit;

/**
 * Created by ahn on 2018. 12. 12..
 */

public class QuizList {

    private int mNumber;
    private String mTitle, mPreview, mContent;
    private double mLevel;
    private int mChallenger, mPasser;

    public QuizList(int number, String title, String preview, String content, double level, int passer, int challenger) {
        this.mNumber = number;
        this.mTitle = title;
        this.mPreview = preview;
        this.mContent = content;
        this.mLevel = level;
        this.mPasser = passer;
        this.mChallenger = challenger;
    }

    public int getNumber() {
        return mNumber;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getPreview() {
        return mPreview;
    }

    public String getContent() {
        return mContent;
    }

    public double getLevel() {
        return mLevel;
    }

    public int getChallenger() {
        return mChallenger;
    }

    public int getPasser() {
        return mPasser;
    }
}
