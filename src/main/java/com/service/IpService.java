package com.service;

import com.client.CountryInformationClient;
import com.client.CurrencyInformationClient;
import com.client.GeolocationClient;
import com.exception.ApiException;
import com.model.response.CountryInformationResponse;
import com.model.response.CurrencyInformationResponse;
import com.model.response.GeolocationResponse;
import com.model.response.IpInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class IpService {
    private static final String IP_PATTERN = "^((1?\\d?\\d|2[0-4]\\d|25[0-5])\\.){3}((1?\\d?\\d|2[0-4]\\d|25[0-5]))$";
    private static final double LAT_BSAS = -34.6037F;
    private static final double LONG_BSAS = -58.3816F;
    private final double EARTH_RADIUS = 6371;

    private final StatsService statsService;
    private final CurrencyInformationClient currencyInformationClient;
    private final CountryInformationClient countryInformationClient;
    private final GeolocationClient geolocationClient;
    private CurrencyInformationResponse currencyInformation;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");


    @Autowired
    public IpService(StatsService statsService, CurrencyInformationClient currencyInformationClient, CountryInformationClient countryInformationClient, GeolocationClient geolocationClient) {
        this.statsService = statsService;
        this.currencyInformationClient = currencyInformationClient;
        this.countryInformationClient = countryInformationClient;
        this.geolocationClient = geolocationClient;
    }

    public long lastDateFromCurrency() {
        try {
            LocalDateTime lastDate = new SimpleDateFormat("yyyy-MM-dd").parse(currencyInformation.getDate())
                    .toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDateTime today = LocalDateTime.now();
            return Duration.between(lastDate, today).toDays();
        } catch (ParseException e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error parsing currency information date", e);
        }
    }

    public IpInformation getInformation(String ip) {
        Pattern pattern = Pattern.compile(IP_PATTERN);
        Matcher matcher = pattern.matcher(ip);
        if (matcher.matches()) {
            IpInformation ipInformation = new IpInformation();
            try {
                GeolocationResponse geolocationResponse = geolocationClient.getGeolocationClient(ip);
                if (geolocationResponse.getCountryName() != null) {
                    setGeolocation(ipInformation, ip, geolocationResponse);
                    CountryInformationResponse countryInformation = countryInformationClient.getCountryInformation(geolocationResponse.getCountryCode());
                    if (countryInformation.getLatlng() != null) {
                        setCountryInformation(ipInformation, countryInformation);
                    }
                    if (currencyInformation == null || lastDateFromCurrency() >= 1) {
                        currencyInformation = currencyInformationClient.getCurrencyInformation();
                    }
                    setCurrency(ipInformation, countryInformation);
                }

            } catch (ApiException e) {
                throw e;
            }

            return ipInformation;

        } else {
            throw new ApiException(HttpStatus.BAD_REQUEST.value(), "Required IP has an invalid format. IP: '" + ip + "'", null);
        }
    }

    public double getDistance(double latOrigin, double longOrigin, double latDest, double longDest) {
        double distance = Math.acos(Math.sin(latDest * Math.PI / 180.0) * Math.sin(latOrigin * Math.PI / 180.0) +
                Math.cos(latDest * Math.PI / 180.0) * Math.cos(latOrigin * Math.PI / 180.0) *
                        Math.cos((longOrigin - longDest) * Math.PI / 180.0)) * EARTH_RADIUS;
        return distance;
    }

    public void setGeolocation(IpInformation ipInformation, String ip, GeolocationResponse geolocation) {
        ipInformation.setIp(ip);
        ipInformation.setDate(dateFormat.format(Calendar.getInstance().getTime()));
        ipInformation.setCountry(geolocation.getCountryName());
        ipInformation.setIso_code(geolocation.getCountryCode());
    }

    public void setCountryInformation(IpInformation ipInformation, CountryInformationResponse countryInformation) {
        String languages = "";
        for (int i = 0; i < countryInformation.getLanguages().size(); i++) {
            languages += countryInformation.getLanguages().getJSONObject(i).getString("name") + "" + "(" + countryInformation.getLanguages().getJSONObject(i).getString("iso639_1") + ")";

        }
        ipInformation.setLanguages(languages);
        String timezones = "";
        for (int i = 0; i < countryInformation.getTimezones().size(); i++) {
            timezones += countryInformation.getTimezones().get(i);
        }
        ipInformation.setTimes(timezones);
        double latDest = countryInformation.getLatlng().get(0);
        double longDest = countryInformation.getLatlng().get(1);
        double distance = getDistance(LAT_BSAS, LONG_BSAS, latDest, longDest);
        statsService.updateStatistics(distance);
        DecimalFormat df = new DecimalFormat("###.##");
        ipInformation.setEstimated_distance(df.format(distance) + " kms");

    }

    public void setCurrency(IpInformation ipInformation, CountryInformationResponse countryInformation) {
        String base = "";
        String symbol = "";
        String currency = "";
        for (int i = 0; i < countryInformation.getCurrencies().size(); i++) {
            base += countryInformation.getCurrencies().getJSONObject(i).getString("code");
            symbol += countryInformation.getCurrencies().getJSONObject(i).getString("symbol");
            Float amount = currencyInformation.getRates().get(base);
            currency = base + " ( 1 EUR = " + amount + " " + symbol + " )";
        }
        ipInformation.setCurrency(currency);
    }


}
