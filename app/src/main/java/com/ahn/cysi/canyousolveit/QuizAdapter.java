package com.ahn.cysi.canyousolveit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahn on 2018. 12. 12..
 */

public class QuizAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<QuizList> mLists;
    private RecyclerView mRecyclerView;
    private MainActivity mMainActivity;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.row_number) TextView mTextNumber;
        @BindView(R.id.row_title) TextView mTextTitle;
        @BindView(R.id.row_content) TextView mTextContent;
        @BindView(R.id.row_passer) TextView mTextPasser;
        @BindView(R.id.row_challenger) TextView mTextChallenger;
        @BindView(R.id.row_level) TextView mTextLevel;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public QuizAdapter(MainActivity mainActivity, ArrayList<QuizList> lists, RecyclerView recyclerView) {
        this.mMainActivity = mainActivity;
        this.mLists = lists;
        this.mRecyclerView = recyclerView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mRecyclerView.getChildAdapterPosition(v);

                Snackbar.make(v, mLists.get(position).getTitle(), Snackbar.LENGTH_SHORT)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        }).show();

                Intent intent = new Intent(mMainActivity, QuizActivity.class);
                intent.putExtra("NUM", mLists.get(position).getNumber());
                intent.putExtra("TITLE", mLists.get(position).getTitle());
                intent.putExtra("CONTENT", mLists.get(position).getContent());
                intent.putExtra("PASS", mLists.get(position).getPasser());
                intent.putExtra("CHLNG", mLists.get(position).getChallenger());
                intent.putExtra("LEVEL", mLists.get(position).getLevel());
                mMainActivity.startActivity(intent);
            }
        });

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder)holder;
        myViewHolder.mTextNumber.setText(String.valueOf(mLists.get(position).getNumber()));
        myViewHolder.mTextTitle.setText(mLists.get(position).getTitle());
        myViewHolder.mTextContent.setText(mLists.get(position).getContent());
        myViewHolder.mTextPasser.setText("Passer:" + String.valueOf(mLists.get(position).getPasser()));
        myViewHolder.mTextChallenger.setText("Challenger:" + String.valueOf(mLists.get(position).getChallenger()));
        myViewHolder.mTextLevel.setText(String.valueOf(mLists.get(position).getLevel()));
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }

}
