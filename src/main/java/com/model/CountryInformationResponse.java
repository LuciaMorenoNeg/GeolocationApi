package com.model;

import net.sf.json.JSONObject;

public class CountryInformationResponse {
    private JSONObject languages;
    private JSONObject currencies;
    private String timezones;

    public JSONObject getLanguages() {
        return languages;
    }

    public void setLanguages(JSONObject languages) {
        this.languages = languages;
    }

    public JSONObject getCurrencies() {
        return currencies;
    }

    public void setCurrencies(JSONObject currencies) {
        this.currencies = currencies;
    }

    public String getTimezones() {
        return timezones;
    }

    public void setTimezones(String timezones) {
        this.timezones = timezones;
    }
}
