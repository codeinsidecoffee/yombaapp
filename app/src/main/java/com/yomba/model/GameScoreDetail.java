
package com.yomba.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class GameScoreDetail {

    @SerializedName("consecutive_points")
    private String consecutivePoints;
    @Expose
    private String headline;
    @SerializedName("is_open_solve")
    private String isOpenSolve;
    @SerializedName("is_task_skip")
    private String isTaskSkip;
    @Expose
    private String points;
    @SerializedName("task_consecutive_points")
    private String taskConsecutivePoints;
    @SerializedName("task_id")
    private String taskId;
    @SerializedName("task_points")
    private String taskPoints;
    @SerializedName("total_task_points")
    private String totalTaskPoints;
    @SerializedName("total_used_clue")
    private String totalUsedClue;
    @SerializedName("total_wrong_answer")
    private String totalWrongAnswer;

    public String getConsecutivePoints() {
        return consecutivePoints;
    }

    public void setConsecutivePoints(String consecutivePoints) {
        this.consecutivePoints = consecutivePoints;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getIsOpenSolve() {
        return isOpenSolve;
    }

    public void setIsOpenSolve(String isOpenSolve) {
        this.isOpenSolve = isOpenSolve;
    }

    public String getIsTaskSkip() {
        return isTaskSkip;
    }

    public void setIsTaskSkip(String isTaskSkip) {
        this.isTaskSkip = isTaskSkip;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getTaskConsecutivePoints() {
        return taskConsecutivePoints;
    }

    public void setTaskConsecutivePoints(String taskConsecutivePoints) {
        this.taskConsecutivePoints = taskConsecutivePoints;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskPoints() {
        return taskPoints;
    }

    public void setTaskPoints(String taskPoints) {
        this.taskPoints = taskPoints;
    }

    public String getTotalTaskPoints() {
        return totalTaskPoints;
    }

    public void setTotalTaskPoints(String totalTaskPoints) {
        this.totalTaskPoints = totalTaskPoints;
    }

    public String getTotalUsedClue() {
        return totalUsedClue;
    }

    public void setTotalUsedClue(String totalUsedClue) {
        this.totalUsedClue = totalUsedClue;
    }

    public String getTotalWrongAnswer() {
        return totalWrongAnswer;
    }

    public void setTotalWrongAnswer(String totalWrongAnswer) {
        this.totalWrongAnswer = totalWrongAnswer;
    }
}
