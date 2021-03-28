package com.client;

import com.model.response.CountryInformationResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CountryInformationClient {
    private static final String BASE_URL = "https://restcountries.eu/rest/v2/alpha/";
    private WebClient client;

    public CountryInformationClient() {
        client = WebClient.create(BASE_URL);
    }

    public CountryInformationResponse getCountryInformation(String countryName) {
        return client
                .get()
                .uri(countryName)
                .retrieve()
                .bodyToMono(CountryInformationResponse.class)
                .block();
    }
}
