
package com.yomba.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


@SuppressWarnings("unused")
public class SummaryBean {

    @Expose
    private Data data;
    @Expose
    private String message;
    @Expose
    private Long status;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    @SuppressWarnings("unused")
    public static class Data {

        @SerializedName("all_time_rank")
        private String allTimeRank;
        @SerializedName("clue_used")
        private String clueUsed;
        @SerializedName("current_month_rank")
        private String currentMonthRank;
        @SerializedName("game_score")
        private String gameScore;
        @SerializedName("game_score_detail")
        private List<GameScoreDetail> gameScoreDetail;
        @SerializedName("overall_time")
        private String overallTime;
        @SerializedName("saveme_used")
        private String savemeUsed;
        @SerializedName("total_played_games")
        private String totalPlayedGames;

        public String getAllTimeRank() {
            return allTimeRank;
        }

        public void setAllTimeRank(String allTimeRank) {
            this.allTimeRank = allTimeRank;
        }

        public String getClueUsed() {
            return clueUsed;
        }

        public void setClueUsed(String clueUsed) {
            this.clueUsed = clueUsed;
        }

        public String getCurrentMonthRank() {
            return currentMonthRank;
        }

        public void setCurrentMonthRank(String currentMonthRank) {
            this.currentMonthRank = currentMonthRank;
        }

        public String getGameScore() {
            return gameScore;
        }

        public void setGameScore(String gameScore) {
            this.gameScore = gameScore;
        }

        public List<GameScoreDetail> getGameScoreDetail() {
            return gameScoreDetail;
        }

        public void setGameScoreDetail(List<GameScoreDetail> gameScoreDetail) {
            this.gameScoreDetail = gameScoreDetail;
        }

        public String getOverallTime() {
            return overallTime;
        }

        public void setOverallTime(String overallTime) {
            this.overallTime = overallTime;
        }

        public String getSavemeUsed() {
            return savemeUsed;
        }

        public void setSavemeUsed(String savemeUsed) {
            this.savemeUsed = savemeUsed;
        }

        public String getTotalPlayedGames() {
            return totalPlayedGames;
        }

        public void setTotalPlayedGames(String totalPlayedGames) {
            this.totalPlayedGames = totalPlayedGames;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "allTimeRank='" + allTimeRank + '\'' +
                    ", clueUsed='" + clueUsed + '\'' +
                    ", currentMonthRank='" + currentMonthRank + '\'' +
                    ", gameScore='" + gameScore + '\'' +
                    ", gameScoreDetail=" + gameScoreDetail +
                    ", overallTime='" + overallTime + '\'' +
                    ", savemeUsed='" + savemeUsed + '\'' +
                    ", totalPlayedGames='" + totalPlayedGames + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SummaryBean{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
