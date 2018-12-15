package com.ahn.cysi.canyousolveit;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizActivity extends AppCompatActivity {

    @BindView(R.id.quiz_title) EditText mEditQuizTitle;
    @BindView(R.id.quiz_content) EditText mEditQuizContent;
    @BindView(R.id.quiz_hint) EditText mEditQuizHint;
    @BindView(R.id.quiz_ok) Button mBtQuizOk;

    private int getNumber;
    private String getTitle, getContent;
    private int getPasser, getChallenger;
    private double getLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);
        View parentView = findViewById(android.R.id.content);

        Intent intent = getIntent();
        getNumber = intent.getIntExtra("NUM", -1);
        getTitle = intent.getStringExtra("TITLE");
        getContent = intent.getStringExtra("CONTENT");
        getPasser = intent.getIntExtra("PASS", -1);
        getChallenger = intent.getIntExtra("CHLNG", -1);
        getLevel = intent.getDoubleExtra("LEVEL", -1);

        mEditQuizTitle.setText("[" + getNumber + "] " + getTitle);
        mEditQuizContent.setText(getContent);
        mEditQuizHint.setText("Not Yet.");

        Snackbar.make(parentView, String.format("Passer:%d Challenger:%d Level:%.1f", getPasser, getChallenger, getLevel), Snackbar.LENGTH_LONG).show();
    }
}
