package com.ahn.cysi.canyousolveit;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static ArrayList<QuizList> mQuizLists;

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_drawer) NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Can You Solve It ?");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.rgb(0, 66, 0));
        ButterKnife.bind(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mQuizLists = new ArrayList<>();
        mQuizLists.add(new QuizList(1, "Test Title 1", "this is test quiz.\nvery difficult.", 4.8, 0, 3));
        mQuizLists.add(new QuizList(2, "Test Title 2", "this is test quiz.\nvery difficult.", 3.2, 4, 7));
        mQuizLists.add(new QuizList(4, "Test Title 3", "this is test quiz.\nvery difficult.", 2.5, 10, 12));

        QuizAdapter quizAdapter = new QuizAdapter(this, MainActivity.mQuizLists, mRecyclerView);
        mRecyclerView.setAdapter(quizAdapter);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int getId = item.getItemId();

        switch (getId) {
            case R.id.list_item1:
                Snackbar.make(mDrawerLayout, "1", Snackbar.LENGTH_SHORT)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        }).show();
                break;
            case R.id.list_item2:
                Snackbar.make(mDrawerLayout, "2", Snackbar.LENGTH_SHORT)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        }).show();
                break;
            case R.id.list_item3:
                Snackbar.make(mDrawerLayout, "3", Snackbar.LENGTH_SHORT)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        }).show();
                break;

            case R.id.logout:
                if(LoginActivity.prefEditor != null) {
                    LoginActivity.prefEditor.clear();
                    LoginActivity.prefEditor.commit();
                }

                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                break;
        }

        return false;
    }
}
