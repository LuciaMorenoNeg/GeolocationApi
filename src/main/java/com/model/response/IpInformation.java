package com.model.response;

import net.sf.json.JSONObject;

public class IpInformation {
    private String ip;
    private String date;
    private JSONObject country;
    private String iso_code;
    private JSONObject languages;
    private JSONObject currency;
    private JSONObject times;
    private String estimated_distance;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public JSONObject getCountry() {
        return country;
    }

    public void setCountry(JSONObject country) {
        this.country = country;
    }

    public String getIso_code() {
        return iso_code;
    }

    public void setIso_code(String iso_code) {
        this.iso_code = iso_code;
    }

    public JSONObject getLanguages() {
        return languages;
    }

    public void setLanguages(JSONObject languages) {
        this.languages = languages;
    }

    public JSONObject getCurrency() {
        return currency;
    }

    public void setCurrency(JSONObject currency) {
        this.currency = currency;
    }

    public JSONObject getTimes() {
        return times;
    }

    public void setTimes(JSONObject times) {
        this.times = times;
    }

    public String getEstimated_distance() {
        return estimated_distance;
    }

    public void setEstimated_distance(String estimated_distance) {
        this.estimated_distance = estimated_distance;
    }
}
