package com.service;

import com.client.CountryInformationClient;
import com.client.CurrencyInformationClient;
import com.client.GeolocationClient;
import com.model.response.CountryInformationResponse;
import com.model.response.CurrencyInformationResponse;
import com.model.response.GeolocationResponse;
import com.model.response.IpInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class IpService {
    private final CurrencyInformationClient currencyInformationClient;
    private final CountryInformationClient countryInformationClient;
    private static final String IP_PATTERN = "^((1?\\d?\\d|2[0-4]\\d|25[0-5])\\.){3}((1?\\d?\\d|2[0-4]\\d|25[0-5]))$";
    private final GeolocationClient geolocationClient;
    private CurrencyInformationResponse currencyInformation;
    double earthRadius = 6371;
    private static final double LAT_BSAS = -34.6037;
    private static final double LONG_BSAS = -58.3816;


    @Autowired
    public IpService(CurrencyInformationClient currencyInformationClient, CountryInformationClient countryInformationClient, GeolocationClient geolocationClient, IpInformation ipInformation) {
        this.currencyInformationClient = currencyInformationClient;
        this.countryInformationClient = countryInformationClient;
        this.geolocationClient = geolocationClient;
    }

    public int lastDateFromCurrency() throws Exception{
        Date lastDate =new SimpleDateFormat("dd/MM/yyyy").parse(currencyInformation.getDate());
        Date fechaActual = new Date(System.currentTimeMillis());
        int dias = (int) ((lastDate.getTime() - fechaActual.getTime()));
        return dias;
    }

    public IpInformation getInformation(String ip) throws Exception {

        Pattern pattern = Pattern.compile(IP_PATTERN);
        Matcher matcher = pattern.matcher(ip);
        if (matcher.matches()){
            IpInformation ipInformation = new IpInformation();
            GeolocationResponse geolocationResponse = geolocationClient.getGeolocationClient(ip);
            if(geolocationResponse.getCountryName() != null){
                CountryInformationResponse countryInformation = countryInformationClient.getCountryInformation(geolocationResponse.getCountryName());
                if(lastDateFromCurrency() > 1){
                    CurrencyInformationResponse currencyInformation = currencyInformationClient.getCurrencyInformation();
                }

            }
        return ipInformation;
        }
        else {

        }

    return null;
    }

    public double getDistance(double latOrigin, double longOrigin, double latDest, double longDest) {
        double distance = Math.acos(Math.sin(latDest * Math.PI / 180.0) * Math.sin(latOrigin * Math.PI / 180.0) +
                Math.cos(latDest * Math.PI / 180.0) * Math.cos(latOrigin * Math.PI / 180.0) *
                        Math.cos((longOrigin - longDest) * Math.PI / 180.0)) * earthRadius;
        return distance;
    }

    public static void main(String[] arrays){
        IpService ipservice = new IpService(new CurrencyInformationClient(), new CountryInformationClient(), new GeolocationClient(), new IpInformation());
        double val = ipservice.getDistance(-34, -64, 4, -72);
        System.out.println(val);

    }

}
