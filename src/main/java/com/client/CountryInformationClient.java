package com.client;

import org.springframework.web.reactive.function.client.WebClient;

public class CountryInformationClient {
    private static final String BASE_URL = "https://restcountries.eu/rest/v2/alpha/";
    private WebClient client;

    public CountryInformationClient() {
        client = WebClient.create(BASE_URL);
    }

    public String getCountryInformation() {
        return client
                .get()
                .uri("")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
