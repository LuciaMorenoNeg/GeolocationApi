package com.client;

import com.model.CountryInformationResponse;
import org.springframework.web.reactive.function.client.WebClient;

public class CountryInformationClient {
    private static final String BASE_URL = "https://restcountries.eu/rest/v2/alpha/";
    private WebClient client;

    public CountryInformationClient() {
        client = WebClient.create(BASE_URL);
    }

    public CountryInformationResponse getCountryInformation() {
        return client
                .get()
                .uri("")
                .retrieve()
                .bodyToMono(CountryInformationResponse.class)
                .block();
    }
}
