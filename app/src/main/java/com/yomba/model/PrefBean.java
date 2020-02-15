package com.yomba.model;

public class PrefBean {
    String currentLang;
    String loginID;
    String gameID;
    String orderID;
    String userID;
    String playID;
    String scenario;
    Boolean isLogin=false;
    Boolean isGameStarted =false;
    Boolean isGameFinish =false;
    Boolean isWaitForGame=false;
    Boolean isInviteScreen=false;
    String buyerNickName;
    String buyerEmail;


    public PrefBean() {
    }

    public String getCurrentLang() {
        return currentLang;
    }

    public void setCurrentLang(String currentLang) {
        this.currentLang = currentLang;
    }

    public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPlayID() {
        return playID;
    }

    public void setPlayID(String playID) {
        this.playID = playID;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public Boolean getLogin() {
        return isLogin;
    }

    public void setLogin(Boolean login) {
        isLogin = login;
    }

    public Boolean getGameStarted() {
        return isGameStarted;
    }

    public void setGameStarted(Boolean gameStarted) {
        isGameStarted = gameStarted;
    }

    public Boolean getGameFinish() {
        return isGameFinish;
    }

    public void setGameFinish(Boolean gameFinish) {
        isGameFinish = gameFinish;
    }

    public Boolean getWaitForGame() {
        return isWaitForGame;
    }

    public void setWaitForGame(Boolean waitForGame) {
        isWaitForGame = waitForGame;
    }

    public String getBuyerNickName() {
        return buyerNickName;
    }

    public void setBuyerNickName(String buyerNickName) {
        this.buyerNickName = buyerNickName;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public Boolean getInviteScreen() {
        return isInviteScreen;
    }

    public void setInviteScreen(Boolean inviteScreen) {
        isInviteScreen = inviteScreen;
    }

    @Override
    public String toString() {
        return "PrefBean{" +
                "\n currentLang='" + currentLang + '\'' +
                ",\n loginID='" + loginID + '\'' +
                ",\n gameID='" + gameID + '\'' +
                ",\n orderID='" + orderID + '\'' +
                ",\n userID='" + userID + '\'' +
                ",\n playID='" + playID + '\'' +
                ",\n scenario='" + scenario + '\'' +
                ",\n isLogin=" + isLogin +
                ",\n isGameStarted=" + isGameStarted +
                ",\n isGameFinish=" + isGameFinish +
                ",\n isWaitForGame=" + isWaitForGame +
                ",\n isInviteScreen=" + isInviteScreen +
                ",\n buyerNickName='" + buyerNickName + '\'' +
                ",\n buyerEmail='" + buyerEmail + '\'' +
                '}';
    }
}
