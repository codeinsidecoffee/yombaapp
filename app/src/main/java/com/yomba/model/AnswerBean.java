package com.yomba.model;

public class AnswerBean {
    String key;
    String value;
    boolean checkStatus=false;

    public AnswerBean(String key, String value, boolean checkStatus) {
        this.key = key;
        this.value = value;
        this.checkStatus = checkStatus;
    }

    public boolean isCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(boolean checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AnswerBean{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", checkStatus=" + checkStatus +
                '}';
    }
}
