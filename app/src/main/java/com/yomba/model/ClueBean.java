
package com.yomba.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class ClueBean {

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

        @Expose
        private String clue;
        @SerializedName("clue_data")
        private String clueData;
        @SerializedName("clue_media_type")
        private String clueMediaType;
        @SerializedName("clue_number")
        private String clueNumber;
        @SerializedName("remaining_clue")
        private Long remainingClue;

        public Data(String clue,String clueMediaType,String clueData, String  clueNumber,long remainingClue) {
            this.clue = clue;
            this.clueData = clueData;
            this.clueMediaType = clueMediaType;
            this.clueNumber = clueNumber;
            this.remainingClue=remainingClue;
        }

        public String getClue() {
            return clue;
        }

        public void setClue(String clue) {
            this.clue = clue;
        }

        public String getClueData() {
            return clueData;
        }

        public void setClueData(String clueData) {
            this.clueData = clueData;
        }

        public String getClueMediaType() {
            return clueMediaType;
        }

        public void setClueMediaType(String clueMediaType) {
            this.clueMediaType = clueMediaType;
        }

        public String getClueNumber() {
            return clueNumber;
        }

        public void setClueNumber(String clueNumber) {
            this.clueNumber = clueNumber;
        }

        public Long getRemainingClue() {
            return remainingClue;
        }

        public void setRemainingClue(Long remainingClue) {
            this.remainingClue = remainingClue;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "clue='" + clue + '\'' +
                    ", clueData='" + clueData + '\'' +
                    ", clueMediaType='" + clueMediaType + '\'' +
                    ", clueNumber='" + clueNumber + '\'' +
                    ", remainingClue=" + remainingClue +
                    '}';
        }
    }
}
