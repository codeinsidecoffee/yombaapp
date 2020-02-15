
package com.yomba.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class UserInfo {

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

        @SerializedName("buyer_email")
        private String buyerEmail;
        @SerializedName("buyer_id")
        private String buyerId;
        @SerializedName("buyer_nickname")
        private String buyerNickname;

        @SerializedName("terms_condition")
        private String termsCondition;

        @SerializedName("first_name")
        private String firstName;
        @SerializedName("g_name")
        private String gName;
        @SerializedName("game_id")
        private String gameId;
        @SerializedName("is_approved")
        private String isApproved;
        @SerializedName("is_email_verified")
        private String isEmailVerified;
        @SerializedName("is_phone_verified")
        private String isPhoneVerified;
        @SerializedName("last_name")
        private String lastName;
        @SerializedName("login_id")
        private String loginId;
        @Expose
        private String nickname;
        @SerializedName("order_id")
        private String orderId;

        @SerializedName("purchase_date")
        private String purchaseDate;

        @Expose
        private String phone;
        @Expose
        private String scenario;
        @SerializedName("user_email")
        private String userEmail;
        @SerializedName("user_id")
        private String userId;
        @SerializedName("user_registration_code")
        private String userRegistrationCode;

        @SerializedName("is_game_already_played")
        private String isGameAlreadyPlayed;

        @SerializedName("is_game_already_started")
        private String isGameAlreadyStarted;

        @SerializedName("play_id")
        private String playID;

        public String getPlayID() {
            return playID;
        }

        public void setPlayID(String playID) {
            this.playID = playID;
        }

        public String getBuyerEmail() {
            return buyerEmail;
        }

        public void setBuyerEmail(String buyerEmail) {
            this.buyerEmail = buyerEmail;
        }

        public String getBuyerId() {
            return buyerId;
        }

        public void setBuyerId(String buyerId) {
            this.buyerId = buyerId;
        }

        public String getBuyerNickname() {
            return buyerNickname;
        }

        public void setBuyerNickname(String buyerNickname) {
            this.buyerNickname = buyerNickname;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getGName() {
            return gName;
        }

        public void setGName(String gName) {
            this.gName = gName;
        }

        public String getGameId() {
            return gameId;
        }

        public void setGameId(String gameId) {
            this.gameId = gameId;
        }

        public String getIsApproved() {
            return isApproved;
        }

        public void setIsApproved(String isApproved) {
            this.isApproved = isApproved;
        }

        public String getIsEmailVerified() {
            return isEmailVerified;
        }

        public void setIsEmailVerified(String isEmailVerified) {
            this.isEmailVerified = isEmailVerified;
        }

        public String getIsPhoneVerified() {
            return isPhoneVerified;
        }

        public void setIsPhoneVerified(String isPhoneVerified) {
            this.isPhoneVerified = isPhoneVerified;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getLoginId() {
            return loginId;
        }

        public void setLoginId(String loginId) {
            this.loginId = loginId;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getScenario() {
            return scenario;
        }

        public void setScenario(String scenario) {
            this.scenario = scenario;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserRegistrationCode() {
            return userRegistrationCode;
        }

        public void setUserRegistrationCode(String userRegistrationCode) {
            this.userRegistrationCode = userRegistrationCode;
        }

        public String getTermsCondition() {
            return termsCondition;
        }

        public void setTermsCondition(String termsCondition) {
            this.termsCondition = termsCondition;
        }

        public String getgName() {
            return gName;
        }

        public void setgName(String gName) {
            this.gName = gName;
        }

        public String getIsGameAlreadyPlayed() {
            return isGameAlreadyPlayed;
        }

        public void setIsGameAlreadyPlayed(String isGameAlreadyPlayed) {
            this.isGameAlreadyPlayed = isGameAlreadyPlayed;
        }

        public String getIsGameAlreadyStarted() {
            return isGameAlreadyStarted;
        }

        public void setIsGameAlreadyStarted(String isGameAlreadyStarted) {
            this.isGameAlreadyStarted = isGameAlreadyStarted;
        }

        public String getPurchaseDate() {
            return purchaseDate;
        }

        public void setPurchaseDate(String purchaseDate) {
            this.purchaseDate = purchaseDate;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "buyerEmail='" + buyerEmail + '\'' +
                    ", buyerId='" + buyerId + '\'' +
                    ", buyerNickname='" + buyerNickname + '\'' +
                    ", termsCondition='" + termsCondition + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", gName='" + gName + '\'' +
                    ", gameId='" + gameId + '\'' +
                    ", isApproved='" + isApproved + '\'' +
                    ", isEmailVerified='" + isEmailVerified + '\'' +
                    ", isPhoneVerified='" + isPhoneVerified + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", loginId='" + loginId + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", orderId='" + orderId + '\'' +
                    ", purchaseDate='" + purchaseDate + '\'' +
                    ", phone='" + phone + '\'' +
                    ", scenario='" + scenario + '\'' +
                    ", userEmail='" + userEmail + '\'' +
                    ", userId='" + userId + '\'' +
                    ", userRegistrationCode='" + userRegistrationCode + '\'' +
                    ", isGameAlreadyPlayed='" + isGameAlreadyPlayed + '\'' +
                    ", isGameAlreadyStarted='" + isGameAlreadyStarted + '\'' +
                    ", playID='" + playID + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
