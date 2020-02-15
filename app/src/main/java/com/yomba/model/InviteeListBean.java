
package com.yomba.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


@SuppressWarnings("unused")
public class InviteeListBean {

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
    public static class Gameinfo {

        @SerializedName("about_game")
        private String aboutGame;
        @Expose
        private String ages;
        @SerializedName("c_code")
        private String cCode;
        @SerializedName("city_id")
        private String cityId;
        @SerializedName("country_id")
        private String countryId;
        @SerializedName("country_name")
        private String countryName;
        @Expose
        private String created;
        @Expose
        private String description;
        @Expose
        private String duration;
        @Expose
        private String endpoint;
        @SerializedName("g_name")
        private String gName;
        @SerializedName("game_city")
        private String gameCity;
        @SerializedName("game_id")
        private String gameId;
        @SerializedName("game_no")
        private String gameNo;
        @SerializedName("game_type")
        private String gameType;
        @SerializedName("lang_id")
        private String langId;
        @SerializedName("last_updated")
        private String lastUpdated;
        @Expose
        private String name;
        @Expose
        private String parking;
        @SerializedName("picture_image")
        private String pictureImage;
        @Expose
        private String price;
        @Expose
        private String saturday;
        @Expose
        private String status;
        @Expose
        private String story;
        @Expose
        private String stpoint;
        @Expose
        private String teamsize;
        @SerializedName("thumbnail_image")
        private String thumbnailImage;
        @Expose
        private String tmode;
        @SerializedName("type_id")
        private String typeId;
        @SerializedName("updated_by")
        private String updatedBy;

        public String getAboutGame() {
            return aboutGame;
        }

        public void setAboutGame(String aboutGame) {
            this.aboutGame = aboutGame;
        }

        public String getAges() {
            return ages;
        }

        public void setAges(String ages) {
            this.ages = ages;
        }

        public String getCCode() {
            return cCode;
        }

        public void setCCode(String cCode) {
            this.cCode = cCode;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getCountryId() {
            return countryId;
        }

        public void setCountryId(String countryId) {
            this.countryId = countryId;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public String getGName() {
            return gName;
        }

        public void setGName(String gName) {
            this.gName = gName;
        }

        public String getGameCity() {
            return gameCity;
        }

        public void setGameCity(String gameCity) {
            this.gameCity = gameCity;
        }

        public String getGameId() {
            return gameId;
        }

        public void setGameId(String gameId) {
            this.gameId = gameId;
        }

        public String getGameNo() {
            return gameNo;
        }

        public void setGameNo(String gameNo) {
            this.gameNo = gameNo;
        }

        public String getGameType() {
            return gameType;
        }

        public void setGameType(String gameType) {
            this.gameType = gameType;
        }

        public String getLangId() {
            return langId;
        }

        public void setLangId(String langId) {
            this.langId = langId;
        }

        public String getLastUpdated() {
            return lastUpdated;
        }

        public void setLastUpdated(String lastUpdated) {
            this.lastUpdated = lastUpdated;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParking() {
            return parking;
        }

        public void setParking(String parking) {
            this.parking = parking;
        }

        public String getPictureImage() {
            return pictureImage;
        }

        public void setPictureImage(String pictureImage) {
            this.pictureImage = pictureImage;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSaturday() {
            return saturday;
        }

        public void setSaturday(String saturday) {
            this.saturday = saturday;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStory() {
            return story;
        }

        public void setStory(String story) {
            this.story = story;
        }

        public String getStpoint() {
            return stpoint;
        }

        public void setStpoint(String stpoint) {
            this.stpoint = stpoint;
        }

        public String getTeamsize() {
            return teamsize;
        }

        public void setTeamsize(String teamsize) {
            this.teamsize = teamsize;
        }

        public String getThumbnailImage() {
            return thumbnailImage;
        }

        public void setThumbnailImage(String thumbnailImage) {
            this.thumbnailImage = thumbnailImage;
        }

        public String getTmode() {
            return tmode;
        }

        public void setTmode(String tmode) {
            this.tmode = tmode;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public String getUpdatedBy() {
            return updatedBy;
        }

        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

        @Override
        public String toString() {
            return "Gameinfo{" +
                    "aboutGame='" + aboutGame + '\'' +
                    ", ages='" + ages + '\'' +
                    ", cCode='" + cCode + '\'' +
                    ", cityId='" + cityId + '\'' +
                    ", countryId='" + countryId + '\'' +
                    ", countryName='" + countryName + '\'' +
                    ", created='" + created + '\'' +
                    ", description='" + description + '\'' +
                    ", duration='" + duration + '\'' +
                    ", endpoint='" + endpoint + '\'' +
                    ", gName='" + gName + '\'' +
                    ", gameCity='" + gameCity + '\'' +
                    ", gameId='" + gameId + '\'' +
                    ", gameNo='" + gameNo + '\'' +
                    ", gameType='" + gameType + '\'' +
                    ", langId='" + langId + '\'' +
                    ", lastUpdated='" + lastUpdated + '\'' +
                    ", name='" + name + '\'' +
                    ", parking='" + parking + '\'' +
                    ", pictureImage='" + pictureImage + '\'' +
                    ", price='" + price + '\'' +
                    ", saturday='" + saturday + '\'' +
                    ", status='" + status + '\'' +
                    ", story='" + story + '\'' +
                    ", stpoint='" + stpoint + '\'' +
                    ", teamsize='" + teamsize + '\'' +
                    ", thumbnailImage='" + thumbnailImage + '\'' +
                    ", tmode='" + tmode + '\'' +
                    ", typeId='" + typeId + '\'' +
                    ", updatedBy='" + updatedBy + '\'' +
                    '}';
        }
    }

    @SuppressWarnings("unused")
    public static class Data {

        @Expose
        private List<Friendlist> friendlist;
        @Expose
        private Gameinfo gameinfo;
        @Expose
        private Logininfo logininfo;

        public List<Friendlist> getFriendlist() {
            return friendlist;
        }

        public void setFriendlist(List<Friendlist> friendlist) {
            this.friendlist = friendlist;
        }

        public Gameinfo getGameinfo() {
            return gameinfo;
        }

        public void setGameinfo(Gameinfo gameinfo) {
            this.gameinfo = gameinfo;
        }

        public Logininfo getLogininfo() {
            return logininfo;
        }

        public void setLogininfo(Logininfo logininfo) {
            this.logininfo = logininfo;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "friendlist=" + friendlist +
                    ", gameinfo=" + gameinfo +
                    ", logininfo=" + logininfo +
                    '}';
        }
    }

    @SuppressWarnings("unused")
    public static class Friendlist {

        @SerializedName("first_name")
        private String firstName;
        @SerializedName("g_name")
        private String gName;
        @SerializedName("game_id")
        private String gameId;
        @SerializedName("invitee_email")
        private String inviteeEmail;
        @SerializedName("invitee_id")
        private String inviteeId;
        @SerializedName("invitee_phone")
        private String inviteePhone;
        @SerializedName("is_approved")
        private String isApproved;
        @SerializedName("is_email_verified")
        private String isEmailVerified;
        @SerializedName("is_phone_verified")
        private String isPhoneVerified;
        @SerializedName("last_name")
        private String lastName;
        @Expose
        private String nickname;
        @SerializedName("order_id")
        private String orderId;

        public Friendlist(String inviteeEmail, String nickname) {
            this.inviteeEmail = inviteeEmail;
            this.nickname = nickname;
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

        public String getInviteeEmail() {
            return inviteeEmail;
        }

        public void setInviteeEmail(String inviteeEmail) {
            this.inviteeEmail = inviteeEmail;
        }

        public String getInviteeId() {
            return inviteeId;
        }

        public void setInviteeId(String inviteeId) {
            this.inviteeId = inviteeId;
        }

        public String getInviteePhone() {
            return inviteePhone;
        }

        public void setInviteePhone(String inviteePhone) {
            this.inviteePhone = inviteePhone;
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

        @Override
        public String toString() {
            return "Friendlist{" +
                    "firstName='" + firstName + '\'' +
                    ", gName='" + gName + '\'' +
                    ", gameId='" + gameId + '\'' +
                    ", inviteeEmail='" + inviteeEmail + '\'' +
                    ", inviteeId='" + inviteeId + '\'' +
                    ", inviteePhone='" + inviteePhone + '\'' +
                    ", isApproved='" + isApproved + '\'' +
                    ", isEmailVerified='" + isEmailVerified + '\'' +
                    ", isPhoneVerified='" + isPhoneVerified + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", orderId='" + orderId + '\'' +
                    '}';
        }
    }

    @SuppressWarnings("unused")
    public static class Logininfo {

        @SerializedName("game_id")
        private String gameId;
        @SerializedName("login_id")
        private String loginId;
        @Expose
        private String scenario;
        @SerializedName("user_id")
        private String userId;
        @SerializedName("play_id")
        private String playId;


        public String getPlayId() {
            return playId;
        }

        public void setPlayId(String playId) {
            this.playId = playId;
        }

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
                    ", scenario='" + scenario + '\'' +
                    ", userId='" + userId + '\'' +
                    ", playId='" + playId + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "InviteeListBean{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
