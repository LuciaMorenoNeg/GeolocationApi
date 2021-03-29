package com.model.response;

import net.sf.json.JSONArray;
import java.util.List;

public class CountryInformationResponse {
    private JSONArray languages;
    private JSONArray currencies;
    private List<String> timezones;
    private List<Double> latlng;

    public List<Double> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }


    public JSONArray getLanguages() {
        return languages;
    }

    public void setLanguages(JSONArray languages) {
        this.languages = languages;
    }

    public JSONArray getCurrencies() {
        return currencies;
    }

    public void setCurrencies(JSONArray currencies) {
        this.currencies = currencies;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }
}
