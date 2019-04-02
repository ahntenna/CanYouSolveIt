package com.ahn.cysi.canyousolveit;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Retrofit mRetrofit;
    private RetrofitBase mRetrofitBase;

    public static ArrayList<QuizList> mQuizLists;

    private RecyclerView.LayoutManager mLayoutManager;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
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
        loadQuizList();

        Snackbar.make(mRecyclerView, "Welcome! " + LoginActivity.preferences.getString("EMAIL", null).split("@")[0], Snackbar.LENGTH_SHORT).show();
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
                SharedPreferences auto = getSharedPreferences("AUTO", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = auto.edit();
                editor.clear();
                editor.commit();

                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();

                break;
        }

        return false;
    }

    private void loadQuizList() {
        mRetrofit = new Retrofit.Builder().baseUrl(RetrofitBase.url).addConverterFactory(GsonConverterFactory.create()).build();
        mRetrofitBase = mRetrofit.create(RetrofitBase.class);
        Call<RetrofitQuizList> call = mRetrofitBase.load();
        call.enqueue(new Callback<RetrofitQuizList>() {
            @Override
            public void onResponse(Call<RetrofitQuizList> call, Response<RetrofitQuizList> response) {
                if(response.isSuccessful()) {
                    RetrofitQuizList quizList = response.body();

                    for(int i=0; i<quizList.getResult().size(); i++) {     // db 검색 결과가 있는 경우. 즉, 계정 정보가 맞을 경우
                        int id = quizList.getResult().get(i).getId();
                        String title = quizList.getResult().get(i).getTitle();
                        String preview = quizList.getResult().get(i).getPreview();
                        String content = quizList.getResult().get(i).getContent();
                        float level = quizList.getResult().get(i).getLevel();
                        int passer = quizList.getResult().get(i).getPasser();
                        int challenger = quizList.getResult().get(i).getChallenger();
                        mQuizLists.add(new QuizList(id, title, preview, content, level, passer, challenger));
                    }
                    mRecyclerView.setAdapter(new QuizAdapter(MainActivity.this, mQuizLists, mRecyclerView));
                } else {
                    Snackbar.make(mRecyclerView, "failed to load quiz list", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RetrofitQuizList> call, Throwable t) {
                Snackbar.make(mRecyclerView, "failed to call", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

}
