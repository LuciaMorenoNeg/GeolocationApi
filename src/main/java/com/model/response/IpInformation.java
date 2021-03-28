package com.model.response;

import net.sf.json.JSONArray;

public class IpInformation {
    private String ip;
    private String date;
    private String country;
    private String iso_code;
    private JSONArray languages;
    private String currency;
    private String times;
    private double estimated_distance;

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIso_code() {
        return iso_code;
    }

    public void setIso_code(String iso_code) {
        this.iso_code = iso_code;
    }

    public JSONArray getLanguages() {
        return languages;
    }

        public void setLanguages(JSONArray languages) {
        this.languages = languages;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public double getEstimated_distance() {
        return estimated_distance;
    }

    public void setEstimated_distance(double estimated_distance) {
        this.estimated_distance = estimated_distance;
    }
}
