
package com.yomba.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


@SuppressWarnings("unused")
public class CountryBean {

    @Expose
    private List<Datum> data;
    @Expose
    private String message;
    @Expose
    private Long status;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
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
    public static class Datum {

        public Datum(String countryId, String image, String iso, String name, String nicename, String phonecode) {
            this.countryId = countryId;
            this.image = image;
            this.iso = iso;
            this.name = name;
            this.nicename = nicename;
            this.phonecode = phonecode;
        }

        @SerializedName("country_id")
        private String countryId;
        @Expose
        private String image;
        @Expose
        private String iso;
        @Expose
        private String name;
        @Expose
        private String nicename;
        @Expose
        private String phonecode;

        public String getCountryId() {
            return countryId;
        }

        public void setCountryId(String countryId) {
            this.countryId = countryId;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getIso() {
            return iso;
        }

        public void setIso(String iso) {
            this.iso = iso;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNicename() {
            return nicename;
        }

        public void setNicename(String nicename) {
            this.nicename = nicename;
        }

        public String getPhonecode() {
            return phonecode;
        }

        public void setPhonecode(String phonecode) {
            this.phonecode = phonecode;
        }

    }
}
