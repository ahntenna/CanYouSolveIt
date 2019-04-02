package com.ahn.cysi.canyousolveit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RetrofitQuizList {

    private List<Result> result = new ArrayList<>();

    public class Result {
        @SerializedName("id")
        private int id;
        @SerializedName("title")
        private String title;
        @SerializedName("preview")
        private String preview;
        @SerializedName("content")
        private String content;
        @SerializedName("level")
        private float level;
        @SerializedName("passer")
        private int passer;
        @SerializedName("challenger")
        private int challenger;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPreview() {
            return preview;
        }

        public void setPreview(String preview) {
            this.preview = preview;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public float getLevel() {
            return level;
        }

        public void setLevel(float level) {
            this.level = level;
        }

        public int getPasser() {
            return passer;
        }

        public void setPasser(int passer) {
            this.passer = passer;
        }

        public int getChallenger() {
            return challenger;
        }

        public void setChallenger(int challenger) {
            this.challenger = challenger;
        }
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
}
