package com.ahn.cysi.canyousolveit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadingActivity extends Activity {

    private int mPercentage;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loading);
        ButterKnife.bind(this);

        mPercentage = 0;

        new Thread() {
            public void run() {
                while (true) {
                    try {
                        while (!Thread.currentThread().isInterrupted()) {
                            mPercentage += (Math.random() * 20) + 5;
                            mProgressBar.setProgress(mPercentage);

                            if (mPercentage >= 100) {
                                startActivity(new Intent(LoadingActivity.this, LoginActivity.class));
                                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                currentThread().interrupt();
                                finish();
                            } else {
                                Thread.sleep(200);
                            }
                        }
                    } catch (Throwable t) {
                    }
                }
            }
        }.start();
    }
}
