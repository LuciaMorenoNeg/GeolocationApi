package com.service;

import com.client.CountryInformationClient;
import com.client.CurrencyInformationClient;
import com.client.GeolocationClient;
import com.model.response.CountryInformationResponse;
import com.model.response.CurrencyInformationResponse;
import com.model.response.GeolocationResponse;
import com.model.response.IpInformation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IpServiceTest {

    @InjectMocks
    private IpService ipService;

    @Mock
    private CountryInformationClient countryInformationClient;

    @Mock
    private CurrencyInformationClient currencyInformationClient;

    @Mock
    private GeolocationClient geolocationClient;

    @Test
    public void testGetIpInformation() throws Exception {
        String ip = "5.6.7.8";
        List<Double> latLon = new ArrayList();
        latLon.add(51.0);
        latLon.add(9.0);
        List<String> timeZone = new ArrayList<>();
        timeZone.add("UTC+01:00");
        JSONArray languages = new JSONArray();
        JSONObject jsonObjectLanguages = new JSONObject();
        jsonObjectLanguages.put("iso639_1", "de" );
        jsonObjectLanguages.put("iso639_2", "deu" );
        jsonObjectLanguages.put("name", "German" );
        jsonObjectLanguages.put("nativeName", "Deutsch" );
        languages.add(jsonObjectLanguages);
        JSONObject jsonObjectCurrencies = new JSONObject();
        JSONArray currencies = new JSONArray();
        jsonObjectCurrencies.put("code","EUR");
        jsonObjectCurrencies.put("name", "Euro");
        jsonObjectCurrencies.put("symbol", "€");
        currencies.add(jsonObjectCurrencies);
        Map<String, Float> rates = new HashMap<>();
        rates.put("EUR", 1f);
        GeolocationResponse geolocationResponse = new GeolocationResponse();
        geolocationResponse.setCountryName("Germany");
        geolocationResponse.setCountryCode("DE");
        String currenciesData = "EUR ( 1 EUR = 1.0 € )";
        String languagesData = "German(de)";


        CurrencyInformationResponse currencyInformation = new CurrencyInformationResponse();
        currencyInformation.setBase("EUR");
        currencyInformation.setDate("2021-03-26");
        currencyInformation.setRates(rates);

        CountryInformationResponse countryInformation = new CountryInformationResponse();
        countryInformation.setCurrencies(currencies);
        countryInformation.setLanguages(languages);
        countryInformation.setLatlng(latLon);
        countryInformation.setTimezones(timeZone);

        when(geolocationClient.getGeolocationClient(eq(ip))).thenReturn(geolocationResponse);
        when(currencyInformationClient.getCurrencyInformation()).thenReturn(currencyInformation);
        when(countryInformationClient.getCountryInformation("DE")).thenReturn(countryInformation);
        IpInformation info = ipService.getInformation(ip);

        assertEquals(ip, info.getIp());
        assertEquals("Germany", info.getCountry());
        assertEquals("DE", info.getIso_code());
        assertEquals(currenciesData, info.getCurrency());
        assertEquals(languagesData, info.getLanguages());
        assertEquals(timeZone.get(0), info.getTimes());




    }
}
