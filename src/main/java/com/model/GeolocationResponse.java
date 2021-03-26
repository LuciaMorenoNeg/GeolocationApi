package com.model;

public class GeolocationResponse {
    private String countryName;
    private String countryCode;


    public GeolocationResponse(String countryName, String countryCode){
        this.countryName = countryName;
        this.countryCode = countryCode;

    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
