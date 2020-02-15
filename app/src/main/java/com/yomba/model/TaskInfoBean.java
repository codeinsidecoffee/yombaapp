
package com.yomba.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class TaskInfoBean {

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

    @Override
    public String toString() {
        return "TaskInfoBean{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }

    @SuppressWarnings("unused")
    public static class Taskinfo {

        @Expose
        private String answer;
        @SerializedName("answer_data")
        private AnswerData answerData;
        @SerializedName("answer_explanation")
        private String answerExplanation;
        @SerializedName("final_answer")
        private String finalAnswer;
        @SerializedName("answer_media_type")
        private String answerMediaType;
        @SerializedName("answer_type")
        private String answerType;

        @SerializedName("negative_feedback")
        private String negativeFeedback;

        @SerializedName("positive_feedback")
        private String positiveFeedback;

        @SerializedName("can_be_skip")
        private String canBeSkip;
        @Expose
        private String clue1;
        @SerializedName("clue1_data")
        private String clue1Data;
        @SerializedName("clue1_media_type")
        private String clue1MediaType;
        @Expose
        private String clue2;
        @SerializedName("clue2_data")
        private String clue2Data;
        @SerializedName("clue2_media_type")
        private String clue2MediaType;
        @Expose
        private String clue3;
        @SerializedName("clue3_data")
        private String clue3Data;
        @SerializedName("clue3_media_type")
        private String clue3MediaType;
        @Expose
        private String created;
        @SerializedName("game_id")
        private String gameId;
        @Expose
        private String headline;
        @Expose
        private String instruction1;
        @SerializedName("instruction1_data")
        private String instruction1Data;
        @SerializedName("instruction1_media_type")
        private String instruction1MediaType;
        @Expose
        private String instruction2;
        @SerializedName("instruction2_data")
        private String instruction2Data;
        @SerializedName("instruction2_media_type")
        private String instruction2MediaType;
        @SerializedName("last_updated")
        private String lastUpdated;
        @Expose
        private String latitude;
        @Expose
        private String longitude;
        @SerializedName("map_type")
        private String mapType;
        @Expose
        private String mapdata;
        @SerializedName("remaining_clue")
        private Long remainingClue;

        @SerializedName("total_clue")
        private Long totalClue;
        @SerializedName("task_id")
        private String taskId;
        @SerializedName("task_no")
        private String taskNo;
        @SerializedName("task_status")
        private String taskStatus;
        @SerializedName("task_type1")
        private String taskType1;
        @SerializedName("updated_by")
        private String updatedBy;


        public String getCanBeSkip() {
            return canBeSkip;
        }

        public void setCanBeSkip(String canBeSkip) {
            this.canBeSkip = canBeSkip;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public AnswerData getAnswerData() {
            return answerData;
        }

        public void setAnswerData(AnswerData answerData) {
            this.answerData = answerData;
        }

        public String getAnswerExplanation() {
            return answerExplanation;
        }

        public void setAnswerExplanation(String answerExplanation) {
            this.answerExplanation = answerExplanation;
        }

        public String getAnswerMediaType() {
            return answerMediaType;
        }

        public void setAnswerMediaType(String answerMediaType) {
            this.answerMediaType = answerMediaType;
        }

        public String getAnswerType() {
            return answerType;
        }

        public void setAnswerType(String answerType) {
            this.answerType = answerType;
        }

        public String getClue1() {
            return clue1;
        }

        public void setClue1(String clue1) {
            this.clue1 = clue1;
        }

        public String getClue1Data() {
            return clue1Data;
        }

        public void setClue1Data(String clue1Data) {
            this.clue1Data = clue1Data;
        }

        public String getClue1MediaType() {
            return clue1MediaType;
        }

        public void setClue1MediaType(String clue1MediaType) {
            this.clue1MediaType = clue1MediaType;
        }

        public String getNegativeFeedback() {
            return negativeFeedback;
        }

        public void setNegativeFeedback(String negativeFeedback) {
            this.negativeFeedback = negativeFeedback;
        }

        public String getPositiveFeedback() {
            return positiveFeedback;
        }

        public void setPositiveFeedback(String positiveFeedback) {
            this.positiveFeedback = positiveFeedback;
        }

        public String getClue2() {
            return clue2;
        }

        public void setClue2(String clue2) {
            this.clue2 = clue2;
        }

        public String getClue2Data() {
            return clue2Data;
        }

        public void setClue2Data(String clue2Data) {
            this.clue2Data = clue2Data;
        }

        public String getClue2MediaType() {
            return clue2MediaType;
        }

        public void setClue2MediaType(String clue2MediaType) {
            this.clue2MediaType = clue2MediaType;
        }

        public String getClue3() {
            return clue3;
        }

        public void setClue3(String clue3) {
            this.clue3 = clue3;
        }

        public String getClue3Data() {
            return clue3Data;
        }

        public void setClue3Data(String clue3Data) {
            this.clue3Data = clue3Data;
        }

        public String getClue3MediaType() {
            return clue3MediaType;
        }

        public void setClue3MediaType(String clue3MediaType) {
            this.clue3MediaType = clue3MediaType;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getGameId() {
            return gameId;
        }

        public void setGameId(String gameId) {
            this.gameId = gameId;
        }

        public String getHeadline() {
            return headline;
        }

        public void setHeadline(String headline) {
            this.headline = headline;
        }

        public String getInstruction1() {
            return instruction1;
        }

        public void setInstruction1(String instruction1) {
            this.instruction1 = instruction1;
        }

        public String getInstruction1Data() {
            return instruction1Data;
        }

        public void setInstruction1Data(String instruction1Data) {
            this.instruction1Data = instruction1Data;
        }

        public String getInstruction1MediaType() {
            return instruction1MediaType;
        }

        public void setInstruction1MediaType(String instruction1MediaType) {
            this.instruction1MediaType = instruction1MediaType;
        }

        public String getInstruction2() {
            return instruction2;
        }

        public void setInstruction2(String instruction2) {
            this.instruction2 = instruction2;
        }

        public String getInstruction2Data() {
            return instruction2Data;
        }

        public void setInstruction2Data(String instruction2Data) {
            this.instruction2Data = instruction2Data;
        }

        public String getInstruction2MediaType() {
            return instruction2MediaType;
        }

        public void setInstruction2MediaType(String instruction2MediaType) {
            this.instruction2MediaType = instruction2MediaType;
        }

        public String getLastUpdated() {
            return lastUpdated;
        }

        public void setLastUpdated(String lastUpdated) {
            this.lastUpdated = lastUpdated;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getMapType() {
            return mapType;
        }

        public void setMapType(String mapType) {
            this.mapType = mapType;
        }

        public String getMapdata() {
            return mapdata;
        }

        public void setMapdata(String mapdata) {
            this.mapdata = mapdata;
        }

        public Long getRemainingClue() {
            return remainingClue;
        }

        public void setRemainingClue(Long remainingClue) {
            this.remainingClue = remainingClue;
        }

        public String getTaskId() {
            return taskId;
        }

        public void setTaskId(String taskId) {
            this.taskId = taskId;
        }

        public String getTaskNo() {
            return taskNo;
        }

        public void setTaskNo(String taskNo) {
            this.taskNo = taskNo;
        }

        public String getTaskStatus() {
            return taskStatus;
        }

        public void setTaskStatus(String taskStatus) {
            this.taskStatus = taskStatus;
        }

        public String getTaskType1() {
            return taskType1;
        }

        public void setTaskType1(String taskType1) {
            this.taskType1 = taskType1;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

        public Long getTotalClue() {
            return totalClue;
        }

        public void setTotalClue(Long totalClue) {
            this.totalClue = totalClue;
        }

        public String getFinalAnswer() {
            return finalAnswer;
        }

        public void setFinalAnswer(String finalAnswer) {
            this.finalAnswer = finalAnswer;
        }

        @Override
        public String toString() {
            return "Taskinfo{" +
                    "answer='" + answer + '\'' +
                    ", answerData=" + answerData +
                    ", answerExplanation='" + answerExplanation + '\'' +
                    ", finalAnswer='" + finalAnswer + '\'' +
                    ", answerMediaType='" + answerMediaType + '\'' +
                    ", answerType='" + answerType + '\'' +
                    ", negativeFeedback='" + negativeFeedback + '\'' +
                    ", positiveFeedback='" + positiveFeedback + '\'' +
                    ", canBeSkip='" + canBeSkip + '\'' +
                    ", clue1='" + clue1 + '\'' +
                    ", clue1Data='" + clue1Data + '\'' +
                    ", clue1MediaType='" + clue1MediaType + '\'' +
                    ", clue2='" + clue2 + '\'' +
                    ", clue2Data='" + clue2Data + '\'' +
                    ", clue2MediaType='" + clue2MediaType + '\'' +
                    ", clue3='" + clue3 + '\'' +
                    ", clue3Data='" + clue3Data + '\'' +
                    ", clue3MediaType='" + clue3MediaType + '\'' +
                    ", created='" + created + '\'' +
                    ", gameId='" + gameId + '\'' +
                    ", headline='" + headline + '\'' +
                    ", instruction1='" + instruction1 + '\'' +
                    ", instruction1Data='" + instruction1Data + '\'' +
                    ", instruction1MediaType='" + instruction1MediaType + '\'' +
                    ", instruction2='" + instruction2 + '\'' +
                    ", instruction2Data='" + instruction2Data + '\'' +
                    ", instruction2MediaType='" + instruction2MediaType + '\'' +
                    ", lastUpdated='" + lastUpdated + '\'' +
                    ", latitude='" + latitude + '\'' +
                    ", longitude='" + longitude + '\'' +
                    ", mapType='" + mapType + '\'' +
                    ", mapdata='" + mapdata + '\'' +
                    ", remainingClue=" + remainingClue +
                    ", totalClue=" + totalClue +
                    ", taskId='" + taskId + '\'' +
                    ", taskNo='" + taskNo + '\'' +
                    ", taskStatus='" + taskStatus + '\'' +
                    ", taskType1='" + taskType1 + '\'' +
                    ", updatedBy='" + updatedBy + '\'' +
                    '}';
        }
    }

    @SuppressWarnings("unused")
    public static class Logininfo {

        @SerializedName("game_id")
        private String gameId;
        @SerializedName("login_id")
        private String loginId;
        @SerializedName("play_id")
        private String playId;
        @Expose
        private String scenario;
        @SerializedName("user_id")
        private String userId;

        public String getGameId() {
            return gameId;
        }

        public void setGameId(String gameId) {
            this.gameId = gameId;
        }

        public String getLoginId() {
            return loginId;
        }

        public void setLoginId(String loginId) {
            this.loginId = loginId;
        }

        public String getPlayId() {
            return playId;
        }

        public void setPlayId(String playId) {
            this.playId = playId;
        }

        public String getScenario() {
            return scenario;
        }

        public void setScenario(String scenario) {
            this.scenario = scenario;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        @Override
        public String toString() {
            return "Logininfo{" +
                    "gameId='" + gameId + '\'' +
                    ", loginId='" + loginId + '\'' +
                    ", playId='" + playId + '\'' +
                    ", scenario='" + scenario + '\'' +
                    ", userId='" + userId + '\'' +
                    '}';
        }
    }

    @SuppressWarnings("unused")
    public static class AnswerData {

        @SerializedName("A")
        private String a;
        @SerializedName("B")
        private String b;
        @SerializedName("C")
        private String c;
        @SerializedName("D")
        private String d;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        public String getD() {
            return d;
        }

        public void setD(String d) {
            this.d = d;
        }

        @Override
        public String toString() {
            return "AnswerData{" +
                    "a='" + a + '\'' +
                    ", b='" + b + '\'' +
                    ", c='" + c + '\'' +
                    ", d='" + d + '\'' +
                    '}';
        }
    }

    @SuppressWarnings("unused")
    public static class Data {

        @SerializedName("current_task_no")
        private Long currentTaskNo;
        @SerializedName("is_game_completed")
        private String isGameCompleted;

        @SerializedName("is_task_verified")
        private String isTaskVerified;
        @Expose
        private Logininfo logininfo;
        @Expose
        private Taskinfo taskinfo;
        @SerializedName("total_skipped_task")
        private Long totalSkippedTask;
        @SerializedName("total_solved_task")
        private Long totalSolvedTask;
        @SerializedName("total_task")
        private Long totalTask;
        @SerializedName("game_score")
        private String gameScore;

        public String getGameScore() {
            return gameScore;
        }

        public void setGameScore(String gameScore) {
            this.gameScore = gameScore;
        }

        public String getIsTaskVerified() {
            return isTaskVerified;
        }

        public void setIsTaskVerified(String isTaskVerified) {
            this.isTaskVerified = isTaskVerified;
        }

        public Long getCurrentTaskNo() {
            return currentTaskNo;
        }

        public void setCurrentTaskNo(Long currentTaskNo) {
            this.currentTaskNo = currentTaskNo;
        }

        public String getIsGameCompleted() {
            return isGameCompleted;
        }



        public void setIsGameCompleted(String isGameCompleted) {
            this.isGameCompleted = isGameCompleted;
        }

        public Logininfo getLogininfo() {
            return logininfo;
        }

        public void setLogininfo(Logininfo logininfo) {
            this.logininfo = logininfo;
        }

        public Taskinfo getTaskinfo() {
            return taskinfo;
        }

        public void setTaskinfo(Taskinfo taskinfo) {
            this.taskinfo = taskinfo;
        }

        public Long getTotalSkippedTask() {
            return totalSkippedTask;
        }

        public void setTotalSkippedTask(Long totalSkippedTask) {
            this.totalSkippedTask = totalSkippedTask;
        }

        public Long getTotalSolvedTask() {
            return totalSolvedTask;
        }

        public void setTotalSolvedTask(Long totalSolvedTask) {
            this.totalSolvedTask = totalSolvedTask;
        }

        public Long getTotalTask() {
            return totalTask;
        }

        public void setTotalTask(Long totalTask) {
            this.totalTask = totalTask;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "currentTaskNo=" + currentTaskNo +
                    ", isGameCompleted='" + isGameCompleted + '\'' +
                    ", isTaskVerified='" + isTaskVerified + '\'' +
                    ", logininfo=" + logininfo +
                    ", taskinfo=" + taskinfo +
                    ", totalSkippedTask=" + totalSkippedTask +
                    ", totalSolvedTask=" + totalSolvedTask +
                    ", totalTask=" + totalTask +
                    ", gameScore='" + gameScore + '\'' +
                    '}';
        }
    }
}
